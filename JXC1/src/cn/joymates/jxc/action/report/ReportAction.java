package cn.joymates.jxc.action.report;

import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Sellbill;
import cn.joymates.jxc.service.StatementService;
import cn.joymates.jxc.utils.ServiceProxyFactory;

public class ReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StatementService statementService =ServiceProxyFactory.getInstance(new StatementService());
	
	private Sellbill sellbill;

	private List list;
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Sellbill getSellbill() {
		return sellbill;
	}

	public void setSellbill(Sellbill sellbill) {
		this.sellbill = sellbill;
	}
	
	public String findAll(){
		list = statementService.findAll(ec_rd, req, sellbill);
		return "find";
		
	}
	
}
