package cn.joymates.jxc.action.getin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.service.CategoryService;
import cn.joymates.jxc.service.GetinService;
import cn.joymates.jxc.service.GetinbillService;
import cn.joymates.jxc.service.GoodsService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

//购入单据的action
public class GetinbillAction extends BaseAction{
	private GetinbillService getinbillservice = ServiceProxyFactory.getInstance(new GetinbillService());
	
	private CategoryService categoryservice = ServiceProxyFactory.getInstance(new CategoryService());
	
	private GoodsService goodsservice= ServiceProxyFactory.getInstance(new GoodsService());
	
	private GetinService getinservice =  ServiceProxyFactory.getInstance(new GetinService());
	//添加购入单据
	public String save(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		getinbillservice.save(getinbill);
		return "findall";
	}
	//初始页面
	public String goinput(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		return "findall";
	}
	//根据商品类型ID 获得到商品名字
	public String goodName(){

		List<Category> list = new ArrayList<Category>();
		list = categoryservice.find();
		req.setAttribute("category", list);
		List<Goods> list1 = new ArrayList<Goods>();
		list1 = goodsservice.find();
		req.setAttribute("goods", list1);
		List listname=goodsservice.findByGoodsName(goods.getCateId());
		req.setAttribute("listname", listname);
		return "findall";
	}
	public String maintain(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		return "findalllist";
	}
	public String findalllist(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		billlist = getinbillservice.findAll(ec_rd, req, getinbill);
		return "findalllist";
	}
	//维护页面
//	public String maintainpage(){
//		req.setAttribute("category", CommonDataCache.CATEGORY);
//		req.setAttribute("goods", CommonDataCache.GOODS);
//		return "maintainpage";
//	}
	//删除购入单据
	public String delete(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		getinbillservice.delete(getinbill.getBillId());
		return "";
	}
	
	//查询全部单据
	public String findall(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		billlist = getinbillservice.findAll(ec_rd, req, getinbill);
		return "findall";
	}
	
	//购物单维护
	public String findById(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		
		getinbill = getinbillservice.findById(getinbill.getBillId());
		List alllist = getinservice.findAll(ec_rd, req, getinbill);
		for (int i = 0; i < alllist.size(); i++) {
			Map map = (Map) alllist.get(i);
			List goodname = CommonDataCache.GOODS.get(map.get("CATE_ID"));
			req.setAttribute("goodname", goodname);
			map.put("gname", goodname);
		}
		
		ActionContext.getContext().put("alllist", alllist);
		return "findbyone";
	}
	//根据购物单编号查询
	public String findByCode(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		
		getinbill = getinbillservice.findByCode(getinbill.getBillCode());
		return "findbyone";
	}
	public String find(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		List<Getinbill> getinbills = new ArrayList<Getinbill>();
		getinbills = getinbillservice.find();
		return "findall";
	}
	//购物单明细
	public String minutelist(){
		getinbill = getinbillservice.findById(getinbill.getBillId());//查找购物单
		List alllist = getinservice.findAll(ec_rd, req, getinbill);
		
		
		ActionContext.getContext().put("alllist", alllist);
		return "minute";
	}
	
	public String findAllByName(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		billlist = getinbillservice.findAllByName(ec_rd, req, getinbill, getin);
		return "findall";
	}
	private Goods goods;
	private Getin getin;
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Getin getGetin() {
		return getin;
	}
	public void setGetin(Getin getin) {
		this.getin = getin;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	private List billlist;
	private Getinbill getinbill;
	public Getinbill getGetinbill() {
		return getinbill;
	}
	public void setGetinbill(Getinbill getinbill) {
		this.getinbill = getinbill;
	}
	public GetinbillService getGetinbillservice() {
		return getinbillservice;
	}
	public void setGetinbillservice(GetinbillService getinbillservice) {
		this.getinbillservice = getinbillservice;
	}
	public List getBilllist() {
		return billlist;
	}
	public void setBilllist(List billlist) {
		this.billlist = billlist;
	}
	
	
	
}
