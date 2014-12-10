package cn.joymates.jxc.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;

public class GetinbillDao {
//	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public void insert(Getinbill getinbill){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("savelist", getinbill);
	}
	public void delete(String bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.delete("deletelist", bill_id);
	}
	public void update(Getinbill getinbill){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("updatelist", getinbill);
	}
	public List<Getinbill> findAll(String ecRd, HttpServletRequest req, Getinbill getinbill){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		if (getinbill.getBillId() != null  && !"".equals(getinbill.getBillId().trim())) {
			cond.append(" and aibt.bill_id='" + getinbill.getBillId() +"' ");
		}
		if (getinbill.getBuyerPerson() != null  && !"".equals(getinbill.getBuyerPerson().trim())) {
			cond.append(" and buyer_person='" + getinbill.getBuyerPerson() +"' ");
		}
		
		if (getinbill.getTotalMoney() != null && !"".equals(getinbill.getTotalMoney())) {
			
			if (!getinbill.getTotalMoney().equals(BigDecimal.ZERO)) {
				cond.append(" and total_money='" + getinbill.getTotalMoney() + "' ");
			}
		}
		if (getinbill.getGetInDate() != null && !"".equals(getinbill.getGetInDate())) {
			cond.append(" and get_in_date>='" + new SimpleDateFormat("yyyy-MM-dd").format(getinbill.getGetInDate())+" 00:00:00"+"'  and get_in_date<='" + new SimpleDateFormat("yyyy-MM-dd").format(getinbill.getGetInDate())+" 23:59:59"+"'");
		}
		
		String searchsql = "SELECT count(1) FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE  1=1 and aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id" + cond.toString();
		String resultsql = "SELECT DISTINCT aibt.* FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE  1=1 and aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id" + 
							cond.toString() + 
							" order by get_in_date desc, reg_date desc "
							+ " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	public List findAllByName(String ecRd, HttpServletRequest req, Getinbill getinbill,Getin getin){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		if (getin.getGoodsId()!= null && !"".equals(getin.getGoodsId())&& !"-1".equals(getin.getGoodsId())) {
			cond.append(" and gt.goods_Id='" + getin.getGoodsId()+ "' ");
		}
//		if (getin.getGoodsId() != null && !"".equals(getin.getGoodsId())) {
//			cond.append(" and gst.bill_id='" + getin.getGoodsId()+ "' ");
//		}
		String searchsql = "SELECT count(1) FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE  1=1 and aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id" + cond.toString();
		String resultsql = "SELECT DISTINCT aibt.* FROM get_in_tb gt,category_tb ct,goods_tb gst,get_in_bill_tb aibt WHERE  1=1 and aibt.bill_id = gt.bill_id AND gt.goods_id= gst.goods_id AND gst.cate_id= ct.cate_id" + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	public Getinbill findById(String bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectlistById", bill_id);
	}
	public Getinbill findByCode(String bill_code){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectlistByCode", bill_code);
	}
	public List<Getinbill> find(){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("selectlist");
	}
}
