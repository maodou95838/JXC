package cn.joymates.jxc.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.domain.MemberGrade;
import cn.joymates.jxc.exceptions.AppException;
import cn.joymates.jxc.service.CategoryService;
import cn.joymates.jxc.service.GoodsService;
import cn.joymates.jxc.service.MemberGradeService;

/**
 * 进销存服务代理工厂
 * 
 * @author Jackie Hou
 *
 */
public class ServiceProxyFactory {
    public static <T> T getInstance(T target) {  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(target.getClass());  
        
        enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy mProxy) throws Throwable {
				Object retObj = null;
				SqlSession session = SessionFactoryUtil.getSession();
				
				try {
					retObj = mProxy.invokeSuper(obj, args);
					session.commit();
					
					//add to cache
					Class<?> clazz = method.getDeclaringClass();
					String methodName = method.getName();
					
					Class<?>[] clazzArr = {GoodsService.class,
							CategoryService.class,
							MemberGradeService.class};
					
					if (ArrayUtils.contains(clazzArr, clazz)) {
						String[] mNameArr = {"save", "update"};
						
						if (ArrayUtils.contains(mNameArr, methodName)) {
							add2Cache(clazz, methodName, args);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					throw new AppException(e.getCause());
				} finally {
					SessionFactoryUtil.closeSession();
				}
				
				return retObj;
			}
        	
        });  
        return (T)enhancer.create();  
    }
    
    private static void add2Cache(Class<?> clazz, String methodName, Object[] objs) {
    	if (clazz == MemberGradeService.class) {
    		MemberGrade mg = (MemberGrade)objs[0];
    		CommonDataCache.MEMBER_GRADE.put(mg.getGradeId(), mg.getGradeName());
    		
    	} else if (clazz == CategoryService.class) {
    		Category  c = (Category)objs[0];
    		CommonDataCache.CATEGORY.put(c.getCateId(), c);
    		
    	} else {
    		Goods g = (Goods)objs[0];
    		List<Goods> goodsList = CommonDataCache.GOODS.get(g.getCateId());
    		if (goodsList == null) {
    			goodsList = new ArrayList<Goods>();
    		}
    		
    		goodsList.add(g);
    		CommonDataCache.GOODS.put(g.getCateId(), goodsList);
    	}
    	
    	
    }
	
}
