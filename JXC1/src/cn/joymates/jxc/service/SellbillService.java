package cn.joymates.jxc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.dao.SellbillDao;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.Sellbill;
import cn.joymates.jxc.domain.Sellgoods;

import com.future.existabc.util.UUIDGener;

public class SellbillService {
	private SellbillDao sellbilldao = new SellbillDao();
	
	/**
	 * 销售单查询
	 */
	public List findSellBill(String ecRd, HttpServletRequest req, Sellbill bill, 
			Member m, String startDate, String endDate) {
		if (StringUtils.isEmpty(startDate)) {
			startDate = null;
		}
		
		if (StringUtils.isEmpty(endDate)) {
			endDate = null;
		}
		return sellbilldao.findSellBill(ecRd, req, bill, m, startDate, endDate);
	}
	/**
	 * 异常单查询
	 * @param ecRd
	 */
	public List findDisposeSellBill(String ecRd, HttpServletRequest req, Sellbill bill, 
			Member m, String startDate, String endDate) {
		if (StringUtils.isEmpty(startDate)) {
			startDate = null;
		}
		
		if (StringUtils.isEmpty(endDate)) {
			endDate = null;
		}
		return sellbilldao.findDisposeSellBill(ecRd, req, bill, m, startDate, endDate);
	}
	
	public Map<String, Object> findBillDetail(Sellbill bill) {
		if (StringUtils.isEmpty(bill.getMemberUuid())) {
			bill.setMemberUuid(null);
		}
		return sellbilldao.findBillDetailById(bill);
	}
	/**
	 * 查询会员信息列表
	 */
	public List findmember(String ecRd, HttpServletRequest req,Member member){
		return sellbilldao.findmember(ecRd, req, member);
	}
	/**
	 * 增加销售单据
	 */
	public void addsellbill(Sellbill sellbill){
		sellbill.setSellBillId(UUIDGener.getUUID());
		
		if (StringUtils.isEmpty(sellbill.getMemberUuid())) {
			sellbill.setMemberUuid(null);
		}
		if (StringUtils.isEmpty(sellbill.getIsExecBill())) {
			sellbill.setIsExecBill(null);
		}
		if (StringUtils.isEmpty(sellbill.getCustomerDesc())) {
			sellbill.setCustomerDesc(null);
		}
		sellbill.setRegTime(new Date());
		
		sellbilldao.addsellbill(sellbill);
	}
	/**
	 * 查询销售单据
	 * 
	 */
	public List findAllsellbill(String ecRd, HttpServletRequest req,Sellbill bill,Member m){
		return sellbilldao.findAllsellbill(ecRd, req, bill, m);
	}
	
	public void addsellgoods(Sellgoods sellgoods){
		sellgoods.setSellGoodsId(UUIDGener.getUUID());
		sellbilldao.addsellgoods(sellgoods);
	}
	/**
	 * 销售单维护
	 */
	public List findAllmaintain(String ecRd, HttpServletRequest req,Sellbill sellbill){
		return sellbilldao.findAllmaintain(ecRd, req, sellbill);
	}
	/**
	 * 删除商品表
	 */
	public void deletesellgoods(String sell_bill_id){
		sellbilldao.deleteSellgoods(sell_bill_id);
	}
	/**
	 * 修改销售单据
	 */
	public void updatesellbill(Sellbill sellbill){
		if (StringUtils.isEmpty(sellbill.getCustomerDesc())) {
			sellbill.setCustomerDesc(null);
		}
		sellbilldao.updateSellbill(sellbill);
	}
	public void updatesbill(Sellbill sellbill){
		sellbilldao.updateSbill(sellbill);
	}
	public Sellbill findById(String sell_bill_id){
		return sellbilldao.findByID(sell_bill_id);
	}
	
	public Member findByMemberId(String member_uuid) {
		return sellbilldao.findByMemberId(member_uuid);
	}
}
