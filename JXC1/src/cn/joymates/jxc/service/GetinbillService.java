package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.future.existabc.util.UUIDGener;

import cn.joymates.jxc.dao.GetinbillDao;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;
//购入单据的列清单
public class GetinbillService {
	private GetinbillDao getinbilldao = new GetinbillDao();
	
	public void save(Getinbill getinbill){
		getinbill.setBillId(UUIDGener.getUUID());
		getinbilldao.insert(getinbill);
	}
	public void delete(String bill_id){
		getinbilldao.delete(bill_id);
	}
	public void update(Getinbill getinbill){
		getinbilldao.update(getinbill);
	}
	public List<Getinbill> findAll(String ecRd, HttpServletRequest req,Getinbill getinbill){
		return getinbilldao.findAll(ecRd, req, getinbill);
	}
	public Getinbill findById(String bill_id){
		return getinbilldao.findById(bill_id);
	}
	public Getinbill findByCode(String bill_code){
		return getinbilldao.findByCode(bill_code);
	}
	public List<Getinbill> find(){
		return getinbilldao.find();
	}
	public List findAllByName(String ecRd, HttpServletRequest req,Getinbill getinbill,Getin getin){
		return getinbilldao.findAllByName(ecRd, req, getinbill, getin);
	}
}
