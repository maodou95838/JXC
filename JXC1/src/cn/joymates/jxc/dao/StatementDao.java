package cn.joymates.jxc.dao;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.domain.Sellbill;

public class StatementDao {
	public List findAll(String ecRd, HttpServletRequest req, Sellbill sellbill){
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder cond = new StringBuilder();
		
		if (!"0".equals(sellbill.getNum())&&sellbill.getNum()!=null){
			cond.append(" and  MONTH(sell_date)='" + sellbill.getNum() +"' ");
		}
		if (sellbill.getYears()!=null&&!"".equals(sellbill.getYears().trim())){
			cond.append("and  YEAR(sell_date)='" + sellbill.getYears() +"' ");
		}else{
			cond.append("and  YEAR(sell_date)='" + Calendar.getInstance().get(Calendar.YEAR) +"' ");
		}
		
		if ("6".equals(sellbill.getQuarter())){
							
			cond.append(" and  MONTH(sell_date)<='" + sellbill.getQuarter() +"' ");
			
		}
		if("7".equals(sellbill.getQuarter())){
			cond.append(" and  MONTH(sell_date)>='" + sellbill.getQuarter() +"' ");
		}
		if(!"0".equals(sellbill.getJidu())&&sellbill.getJidu()!=null){
			if("1".equals(sellbill.getJidu())){
				cond.append(" and  MONTH(sell_date)<='" + 3 +"' ");
			}
			if("2".equals(sellbill.getJidu())){
				cond.append(" and  MONTH(sell_date)>'" + 3 +"' and  MONTH(sell_date)<='" + 6 +"'");
			}
			if("3".equals(sellbill.getJidu())){
				cond.append(" and  MONTH(sell_date)>'" + 6 +"' and  MONTH(sell_date)<='" + 9 +"'");
			}
			if("4".equals(sellbill.getJidu())){
				cond.append(" and  MONTH(sell_date)>'" + 9 +"'");
			}
		}
			
		
		String searchsql = "select count(1) from (SELECT gt.goods_name,sg.price,gt.have_count,SUM(sg.sell_count)  sell_count,SUM(sg.total_price)  total_price   FROM sell_goods sg,goods_tb gt,sell_bill_tb sb WHERE 1=1 and sg.goods_id=gt.goods_id AND sb.sell_bill_id=sg.sell_bill_id   " + 
							cond.toString() +" GROUP BY sg.goods_id) a";
		String resultsql = "SELECT gt.goods_name,sg.price,cast(gt.have_count as decimal) have_count,SUM(sg.sell_count)  sell_count,SUM(sg.total_price)  total_price  FROM sell_goods sg,goods_tb gt,sell_bill_tb sb WHERE 1=1 and sg.goods_id=gt.goods_id AND sb.sell_bill_id=sg.sell_bill_id   " + 
							cond.toString() +" GROUP BY sg.goods_id"+" limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
}
