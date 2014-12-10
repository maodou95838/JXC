package cn.joymates.jxc.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.Sellbill;
import cn.joymates.jxc.domain.Sellgoods;

public class SellbillDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public List<Sellbill> findall(){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("selectSellbill");
	}
	
	/**
	 * 查询会员消费记录
	 * @param ecRd
	 * @param req
	 * @param sellbill
	 * @return
	 */
	public List findMemberConsume(String ecRd, HttpServletRequest req, Sellbill sellbill){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
//		if (sellbill.getSellBillId()!= null  && !"".equals(sellbill.getSellBillId().trim())) {
//			cond.append(" and sell_bill_id='" + sellbill.getSellBillId()+"' ");
//		}
		
		if (sellbill.getMemberUuid()!= null  && !"".equals(sellbill.getMemberUuid().trim())) {
			cond.append(" and member_uuid='" + sellbill.getMemberUuid()+"' ");
		}
		
		String searchsql = "select count(1) from sell_bill_tb where 1=1" + cond.toString();
		String resultsql = "select * from sell_bill_tb where 1=1 " + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	public Sellbill findById() {
		return null;
	}
	
	/**
	 * 查询销售单dao
	 * @param ecRd
	 * @param req
	 * @param sellbill
	 * @return
	 * 此方法被多处调用，若要修改请告知！
	 */
	public List findSellBill(String ecRd, HttpServletRequest req, Sellbill bill, 
			Member m, String startDate, String endDate){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		
		if (bill != null) {
			if (StringUtils.isNotEmpty(bill.getSellBillCode())) {
				cond.append(" and s.sell_bill_code='" + bill.getSellBillCode()+"' ");
			}
			if (!"0".equals(bill.getIsPay())) {
				cond.append(" and s.is_pay='" + bill.getIsPay()+"'  ");
			}
			
		}
		
		if (m != null) {
			if (StringUtils.isNotEmpty(m.getCellPhone())) {
				cond.append(" and m.cell_phone='" + m.getCellPhone() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getName())) {
				cond.append(" and m.name='" + m.getName() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getMemberUuid())) {
				cond.append(" and m.member_uuid='" + m.getMemberUuid() + "' ");
			}
		}
		
		if (startDate != null ) {
			cond.append(" and s.sell_date>='" + startDate + " 00:00:00' ");
		}
		
		if (endDate != null) {
			cond.append(" and s.sell_date<='" + startDate + " 23:59:59' ");
		}
		
		String searchsql = "select count(1) from member_tb m right join sell_bill_tb s " +
				" on m.member_uuid = s.member_uuid where 1=1" + cond.toString();
		String resultsql = "select s.*, m.* from member_tb m right join sell_bill_tb s on m.member_uuid = s.member_uuid" +
						   " where 1=1  " + cond.toString() + " limit ?, ? ";
//		//查消费记录，会员信息已经存在
//		//查销售单，会员不一定存在
//		if (m != null && m.getMemberUuid() != null) {
//			searchsql = searchsql.replace("right join", " left join ");
//			resultsql = resultsql.replace("right join", " left join ");
//		}
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	/**
	 * 消费详单
	 * @param bill
	 * @return
	 */
	public Map<String, Object> findBillDetailById(Sellbill bill) {
		Map<String, String> memberBillMap = sess.selectOne("sellbill.findSellbillById", bill);
		List<Map<String, String>> billGoods = sess.selectList("sellbill.findSellGoodsByBillId", bill);
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("member", memberBillMap);
		rtnMap.put("goods", billGoods);
		return rtnMap;
	}
	/**
	 * 查询会员信息列表
	 */
	public List findmember(String ecRd, HttpServletRequest req, Member member ){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		if (member.getCellPhone()!= null && !"".equals(member.getCellPhone())) {
			cond.append(" and mt.cell_phone='" + member.getCellPhone()+ "' ");
		}
		if (member.getName2()!= null && !"".equals(member.getName2())) {
			cond.append(" and mt.name2='" + member.getName2()+ "' ");
		}
		String searchsql = "SELECT count(*) FROM member_tb mt where 1=1" + cond.toString();
		String resultsql = "SELECT  * FROM member_tb mt" +
						   " where 1=1 " + cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	public void addsellbill(Sellbill sellbill){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("addsellbill", sellbill);
	}
	/**
	 * 查询销售单据
	 */
	public List findAllsellbill(String ecRd, HttpServletRequest req, Sellbill bill,Member m){
			DBOperationDAO bdao = DBOperationDAO.getInstance();
			StringBuilder cond = new StringBuilder();
			
			if (bill != null) {
				if (StringUtils.isNotEmpty(bill.getSellBillCode())) {
					cond.append(" and s.sell_bill_code='" + bill.getSellBillCode()+"' ");
				}
				if (bill.getSellDate()!=null) {
					cond.append(" and s.sell_date='" + new SimpleDateFormat("yyyy-MM-dd").format(bill.getSellDate())+"' ");
				}
				
			}
			
			if (m != null) {
				if (StringUtils.isNotEmpty(m.getCellPhone())) {
					cond.append(" and m.cell_phone='" + m.getCellPhone() + "' ");
				}
				
				if (StringUtils.isNotEmpty(m.getName())) {
					cond.append(" and m.name='" + m.getName() + "' ");
				}
				
				if (StringUtils.isNotEmpty(m.getMemberUuid())) {
					cond.append(" and m.member_uuid='" + m.getMemberUuid() + "' ");
				}
			}
			
			String searchsql = "select count(1) from member_tb m right join sell_bill_tb s " +
					" on m.member_uuid = s.member_uuid where 1=1" + cond.toString();
			String resultsql = "select s.*, m.* from member_tb m right join sell_bill_tb s on m.member_uuid = s.member_uuid" +
							   " where 1=1 " + cond.toString() +
							   " order by s.sell_date desc , s.reg_time desc "  +  " limit ?, ? ";
			return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
		
	}
	/**
	 * 添加销售商品
	 */
	public void addsellgoods(Sellgoods sellgoods){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("addsellgoods", sellgoods);
	}
	/**
	 * 销售单维护
	 */
	public List<Sellbill> findAllmaintain(String ecRd, HttpServletRequest req, Sellbill sellbill){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		if (sellbill.getSellBillId()!= null  && !"".equals(sellbill.getSellBillId().trim())) {
			cond.append(" and sbt.sell_bill_id='" + sellbill.getSellBillId() +"' ");
		}
		String searchsql = "SELECT count(1) FROM category_tb ct, sell_goods sg,sell_bill_tb sbt,goods_tb gt WHERE sg.sell_bill_id=sbt.sell_bill_id AND gt.goods_id=sg.goods_id AND gt.cate_id= ct.cate_id and 1=1" + cond.toString();
		String resultsql = "SELECT sg.*,gt.goods_name,ct.name,ct.cate_id,gt.bar_code FROM category_tb ct, sell_goods sg,sell_bill_tb sbt,goods_tb gt WHERE sg.sell_bill_id=sbt.sell_bill_id AND gt.goods_id=sg.goods_id AND gt.cate_id= ct.cate_id" + 
							cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	/**
	 * 删除商品表
	 * @param sell_goods_id
	 */
	public void deleteSellgoods(String sell_bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.delete("deletesellgoods", sell_bill_id);
	}
	/**
	 * 修改销售单据
	 * @param sellbill
	 */
	public void updateSellbill(Sellbill sellbill){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("updateSellbill", sellbill);
	}
	public void updateSbill(Sellbill sellbill){
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("updatesbill", sellbill);
	}
	
	public Sellbill findByID(String sell_bill_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("findBySellbillid", sell_bill_id);
	}
	public Member findByMemberId(String member_uuid) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("findMemberById",member_uuid);
	}
	/**
	 * 异常单查询
	 */
	public List findDisposeSellBill(String ecRd, HttpServletRequest req, Sellbill bill, 
			Member m, String startDate, String endDate){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		
		if (bill != null) {
			if (StringUtils.isNotEmpty(bill.getSellBillCode())) {
				cond.append(" and s.sell_bill_code='" + bill.getSellBillCode()+"' ");
			}
			
		}
		
		if (m != null) {
			if (StringUtils.isNotEmpty(m.getCellPhone())) {
				cond.append(" and m.cell_phone='" + m.getCellPhone() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getName())) {
				cond.append(" and m.name='" + m.getName() + "' ");
			}
			
			if (StringUtils.isNotEmpty(m.getMemberUuid())) {
				cond.append(" and m.member_uuid='" + m.getMemberUuid() + "' ");
			}
		}
		
		if (startDate != null ) {
			cond.append(" and s.sell_date>='" + startDate + " 00:00:00' ");
		}
		
		if (endDate != null) {
			cond.append(" and s.sell_date<='" + startDate + " 23:59:59' ");
		}
		
		String searchsql = "select count(1) from member_tb m right join sell_bill_tb s " +
				" on m.member_uuid = s.member_uuid where 1=1 and s.is_pay!='0' and s.is_exec_bill='1'" + cond.toString();
		String resultsql = "select s.*, m.* from member_tb m right join sell_bill_tb s on m.member_uuid = s.member_uuid" +
						   " where 1=1 and s.is_pay!='0' and s.is_exec_bill='1'" + cond.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
}
