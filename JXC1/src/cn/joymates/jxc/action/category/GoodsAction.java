package cn.joymates.jxc.action.category;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.service.CategoryService;
import cn.joymates.jxc.service.GoodsService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

public class GoodsAction extends BaseAction{

	//增加商品  进action后跳到显示页面
	public String save(){
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		goodsservice.save(goods);
		return "insert";
	}
	public String goinput(){
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		return "insert";
	}
	//显示页的增加 进action后跳到add页面
	public String zengjia(){
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		return "zengj";
	}
	//修改
	public String update(){
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		goodsservice.update(goods);
		return "findall";
	}
	public String delete(){
		goodsservice.delete(goods.getGoodsId());
		return "findall";
	}
	public String findall(){
		listgoods = goodsservice.findAll(ec_rd, req, goods);
		
		List<Category> list = categoryservice.find();
		req.setAttribute("category", list);
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		return "findall";
	}
	public String findByOne(){
		Map<String, Category> map = CommonDataCache.CATEGORY;
		req.setAttribute("category", map);
		goods = goodsservice.findById(goods.getGoodsId());
		return "findbyone";
	}

	private GoodsService goodsservice= ServiceProxyFactory.getInstance(new GoodsService());
	
	private CategoryService categoryservice = ServiceProxyFactory.getInstance(new CategoryService());
	private Goods goods;
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	private List listgoods;
	
	public List getListgoods() {
		return listgoods;
	}
	public void setListgoods(List listgoods) {
		this.listgoods = listgoods;
	}
	
}
