package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.future.existabc.util.UUIDGener;

import cn.joymates.jxc.dao.GetinDao;
import cn.joymates.jxc.dao.GetinbillDao;
import cn.joymates.jxc.dao.GoodsDao;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;


//	采购商品表的列清单
public class GetinService {
	private GetinDao getindao = new GetinDao();
	private GetinbillDao getinbilldao = new GetinbillDao();
	private Getinbill getinbill;
	private GoodsDao goodsdao = new GoodsDao();
	
	public Getinbill getGetinbill() {
		return getinbill;
	}
	public void setGetinbill(Getinbill getinbill) {
		this.getinbill = getinbill;
	}
	public void save(Getin getin){
		getin.setGetInGoodsId(UUIDGener.getUUID());
		getindao.insert(getin);
		
	}
	public void savebill(Getinbill getinbill){
		getinbill.setBillId(UUIDGener.getUUID());
		getinbilldao.insert(getinbill);
	}
	//根据bill_id查询采购商品表
	public Getin findByBillId(String bill_id){
		return getindao.findByBillId(bill_id);
	}
	//?????
	public List findAll(String ecRd, HttpServletRequest req,Getinbill getinbill){
		return getindao.findAll(ecRd, req, getinbill);
	}
//	//修改商品清单
//	public void updategetin(Getin getin){
//		getindao.updategetin(getin);
//	}
	public void updateCount(Goods goods){
		goodsdao.updateCount(goods);
	}
	/**
	 * 删除采购物品，修改采购单
	 */
	public void deletegetinUpdateBill(Getinbill bill){
		getindao.deletegetin(bill.getBillId());
		
		getinbilldao.update(bill);
	}
}
