package cn.joymates.jxc.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Goods;

public class GoodsDao {
private SqlSession sess = SessionFactoryUtil.getSession();
	
	public void insert(Goods goods) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("saveGoods", goods);
	}
	
	public void delete(String goods_id){
		sess.delete("deleteGoods",goods_id);
	} 
	public void update(Goods goods){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("updateGoods", goods);
	}
	public List<Goods> findAll(String ecRd, HttpServletRequest req, Goods goods){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		
		if (goods.getGoodsId() != null  && !"".equals(goods.getGoodsId().trim())) {
			cond.append(" and goods_id='" + goods.getGoodsId() +"' ");
		}
		
		if (goods.getGoodsName() != null && !"".equals(goods.getGoodsName().trim())) {
			cond.append(" and goods_name='" + goods.getGoodsName() + "' ");
		}
		
		if (goods.getBarCode() != null && !"".equals(goods.getBarCode().trim())) {
			cond.append(" and bar_code='" + goods.getBarCode() + "' ");
		}
		if (goods.getCateId() != null  && !"".equals(goods.getCateId().trim())&&!"-1".equals(goods.getCateId().trim())) {
			cond.append(" and gt.cate_id='" + goods.getCateId() + "' ");
		}
		String searchsql = "select  count(1) from category_tb ca,goods_tb gt where ca.cate_id=gt.cate_id and 1=1" + cond.toString();
		String resultsql = "SELECT ca.name ,gt.* FROM category_tb ca,goods_tb gt WHERE ca.cate_id=gt.cate_id" + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	public Goods findById(String goods_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("findByGoodId",goods_id);
	}
	public List<Goods> find(){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("findAllGoods");
	}
	public List findByGoodsName(String cate_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("findGoodsName", cate_id);
		
	}
	/*根据goods_id查询库存量*/
	public Integer findCount(String goods_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("findCount", goods_id);
	}
	/*修改库存量*/
	public void updateCount(Goods goods){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("updateCount",goods);
	}
	//根据goods_Id查询cate_Id和商品名称
	public Goods findBygoodId(String goods_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectGoodsName", goods_id);
	}
}

