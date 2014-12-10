package cn.joymates.jxc.action.sellbill;


import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Sellbill;
import cn.joymates.jxc.service.SellbillService;
import cn.joymates.jxc.utils.ServiceProxyFactory;

public class SellbillAction extends BaseAction {
	private SellbillService sellbillservice = ServiceProxyFactory.getInstance(new SellbillService());
	private Sellbill sellbill;
	private List<Sellbill> sellbills;

	public Sellbill getSellbill() {
		return sellbill;
	}

	public void setSellbill(Sellbill sellbill) {
		this.sellbill = sellbill;
	}

	public List<Sellbill> getSellbills() {
		return sellbills;
	}

	public void setSellbills(List<Sellbill> sellbills) {
		this.sellbills = sellbills;
	}

	public String findall(){
//		sellbills = sellbillservice.findAll(ec_rd, req, sellbill);
//		sellbills = sellbillservice.findall();
		System.out.println(sellbills);
		return "findall";
	}
	//去登记页面
	public String gosave(){
		return "gosave";
	}
	//去维护页面
	public String gomaintain(){
		return "gomaintain";
	}
	//去一场页面
	public String godispose(){
		return "godispose";
	}
	
}
