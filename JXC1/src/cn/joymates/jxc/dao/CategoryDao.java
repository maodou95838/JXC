package cn.joymates.jxc.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Role;


public class CategoryDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public void insert(Category category) {
		sess.insert("saveCategory", category);
	}
	
	public void delete(String cate_id){
		sess.delete("deleteCategory",cate_id);
	} 
	public void update(Category category){
		sess.update("updateCategory", category);
	}
	public List<Category> findAll(String ecRd, HttpServletRequest req, Category category){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		
		if (category.getCateId() != null  && !"".equals(category.getCateId().trim())) {
			cond.append(" and cate_id='" + category.getCateId() +"' ");
		}
		
		if (category.getName() != null && !"".equals(category.getName().trim())) {
			cond.append(" and name='" + category.getName() + "' ");
		}
		
		String searchsql = "select count(1) from category_tb where 1=1" + cond.toString();
		String resultsql = "select * from category_tb where 1=1 " + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	public Category findById(String cate_id){
		return sess.selectOne("findByCateId",cate_id);
	}
	public Category findByName(String name){
		return sess.selectOne("findByCateId",name);
	}
	public List<Category> find(){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("findAllCategory");
	}
	//根据cate_id 查询name
	public Category findByCateId(String cate_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectname", cate_id);
	}
	
	/**
	 * 查询可存单的商品类型。注意：此系统中，目前只有酒类作为存单商品。并且，仅支持一种商品类型。
	 * @return
	 */
	public String findCanPackedOne() {
		return sess.selectOne("selectCanPackedCategory");
	}
}
