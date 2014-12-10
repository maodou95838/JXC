package cn.joymates.jxc.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;

//购买的商品
public class GetinDao {
//	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public void insert(Getin getin){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("savegoods",getin);
		
	}
  //根据bill_id查询采购商品表 
	public Getin findByBillId(String bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectGetin", bill_id);
	}
	//?????????
	public List<Getinbill> findAll(String ecRd, HttpServletRequest req, Getinbill getinbill){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		if (getinbill.getBillId() != null  && !"".equals(getinbill.getBillId().trim())) {
			cond.append(" and aibt.bill_id='" + getinbill.getBillId() +"' ");
		}
		String searchsql = "SELECT count(1) FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id and 1=1" + cond.toString();
		String resultsql = "SELECT ct.name,ct.cate_id,gst.goods_name,gt.* FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id" + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	//修改商品清单
//	public void updategetin(Getin getin){
//		SqlSession sess = SessionFactoryUtil.getSession();
//		sess.update("updateGetin", getin);
//	}
	//删除商品清单
	public void deletegetin(String bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.delete("deleteGetin", bill_id);
	}
}
