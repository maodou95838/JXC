package cn.joymates.jxc.action.category;

import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.service.CategoryService;
import cn.joymates.jxc.utils.ServiceProxyFactory;

import com.opensymphony.xwork2.ActionContext;

public class CategoryAction extends BaseAction {
	
	
//增加商品种类
	public String save() {
		categoryservice.save(categorys);
		return "findall";
	}
	public String goinput(){
		ActionContext.getContext().put("catelists", catelists);
		return "insert";
	}
//修改商品种类
	public String update() {
		categoryservice.update(categorys);
		return "findall";
	}

	public String delete() {
		categoryservice.delete(categorys.getCateId());
		return "findall";
	}
//查询全部
	public String findall() {
		catelists = categoryservice.findAll(ec_rd, req, categorys);
		return "findall";
	}
//查询单个
	public String findById() {
		categorys = categoryservice.findById(categorys.getCateId());		
		return "findbyone";
	}
	private CategoryService categoryservice = ServiceProxyFactory
		.getInstance(new CategoryService());
	
	private List catelists;
	

	public List getCatelists() {
		return catelists;
	}

	public void setCatelists(List catelists) {
		this.catelists = catelists;
	}
	private Category categorys;
	

	public Category getCategory() {
		return categorys;
	}

	public void setCategory(Category categorys) {
		this.categorys = categorys;
	}


}
