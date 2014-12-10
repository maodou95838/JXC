package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.CategoryDao;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.utils.CommonDataCache;

import com.future.existabc.util.UUIDGener;

public class CategoryService {
	
	private CategoryDao gategorydao = new CategoryDao();
	
	public void save(Category category){
		category.setCateId(UUIDGener.getUUID());
		gategorydao.insert(category);
	}
	public void delete(String cate_id){
		gategorydao.delete(cate_id);
	}
	
	//该方法名更改，会影响缓存，请注意！
	public void update(Category category){
		gategorydao.update(category);
	}
	public List findAll(String ecRd, HttpServletRequest req,Category category ){
		return gategorydao.findAll(ecRd, req, category);
	}
	public Category findById(String cate_id){
		return gategorydao.findById(cate_id);
	}
	public Category findByName(String name){
		return gategorydao.findByName(name);
	}
	public List<Category> find(){
		return gategorydao.find();
	}
	//根据cate_id 查询name
	public Category findByCateId(String cate_id){
		return gategorydao.findByCateId(cate_id);
	}
}
