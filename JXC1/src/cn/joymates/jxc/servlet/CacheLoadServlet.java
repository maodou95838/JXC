package cn.joymates.jxc.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.domain.MemberGrade;
import cn.joymates.jxc.utils.CommonDataCache;
/**
 * Jxc cache
 * 
 * @author Jackie Hou
 *
 */
public class CacheLoadServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		SqlSession sess = SessionFactoryUtil.getSession();
		
		List<Category> clist = sess.selectList("category.findCategory4Cached");
		CommonDataCache.CATEGORY = new HashMap<String, Category>();
		List<Goods> goodsList = new ArrayList<Goods>();
		CommonDataCache.GOODS = new HashMap<String, List<Goods>>();
		
		for (Category c : clist) {
			String cid = c.getCateId();
			CommonDataCache.CATEGORY.put(cid, c);
			
			goodsList = sess.selectList("category.findGoods4Cached", cid);
			CommonDataCache.GOODS.put(cid, goodsList);
		}
		
		//会员等级
		List<MemberGrade> gradeList = sess.selectList("member.findAllMemberGrade4Cache");
		CommonDataCache.MEMBER_GRADE = new HashMap<String, String>();
		for (MemberGrade grade : gradeList) {
			CommonDataCache.MEMBER_GRADE.put(grade.getGradeId(), grade.getGradeName());
		}
		
		SessionFactoryUtil.closeSession();
	}
	
	

}
