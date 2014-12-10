package cn.joymates.jxc.action.getin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Getin;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.CategoryService;
import cn.joymates.jxc.service.GetinService;
import cn.joymates.jxc.service.GetinbillService;
import cn.joymates.jxc.service.GoodsService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

public class GetinAction extends BaseAction {

	/**
	 * 采购单登记action
	 * 
	 * @return
	 */
	public String save() {
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);

		User u = (User) session.getAttribute("loggedUser");
		getinbill.setRegPerson(u.getUserId());
		getinbill.setRegDate(new Date());
		try {
			GetinService getinService = new GetinService();
			GoodsService goodsService = new GoodsService();
			SqlSession sess = SessionFactoryUtil.getSession();
			getinService.savebill(getinbill);
			
			String[] cates = goods.getCateId().split(",");
			Getin getin = new Getin();
			for (int i = 0; i < goodsIds.length - 1; i++) {
				getin.setBillId(getinbill.getBillId());
				getin.setGoodsId(goodsIds[i + 1]);
				getin.setPrice(new BigDecimal(prices[i + 1]));
				getin.setConnt(Integer.parseInt(connt[i + 1]));
				getin.setRemark(remark[i + 1]);

				// 计算进货总价
				BigDecimal total = getin.getPrice().multiply(
						new BigDecimal(getin.getConnt()));
				getin.setTotalPrice(total);
				getinService.save(getin);
				
				if (Category.COUNT_IN.equals(CommonDataCache.CATEGORY.get(cates[i + 1].trim()).getIsCountIn())) {
					Integer newCount = getin.getConnt();// 新增加数量
					Integer oldCount = goodsService.findCount(getin.getGoodsId());// 原有库存量
					Integer nowCount = (oldCount == null ? newCount
							: (newCount + oldCount));// 现有库存量

					goods.setHaveCount(nowCount);
					goods.setGoodsId(getin.getGoodsId());
					goodsService.updateCount(goods);
				}
				
			}
			SessionFactoryUtil.getSession().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SessionFactoryUtil.getSession().rollback();
			e.printStackTrace();
		} finally{
			SessionFactoryUtil.closeSession();
		}
		return "insert";
	}

	public String goinput() {
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		return "insert";
	}

	// 修改购入单据
	public String update() {
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);

		getinbillservice.update(getinbill);
		// getinservice.updategetin(getin);
		return "maintain";
	}

	/**
	 * 采购单维护action
	 * @return
	 */
	public String maintain() {
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		try {
			GetinService getinSerivce = new GetinService();
			SqlSession sess = SessionFactoryUtil.getSession();
			getinSerivce.deletegetinUpdateBill(getinbill);
			
			String[] cates = goods.getCateId().split(",");
			Getin getin = new Getin();
			for (int i = 0; i < goodsIds.length - 1; i++) {
				getin.setBillId(getinbill.getBillId().replaceAll(",", ""));
				getin.setGoodsId(goodsIds[i + 1]);
				getin.setPrice(new BigDecimal(prices[i + 1]));
				getin.setConnt(Integer.parseInt(connt[i + 1]));
				getin.setRemark(remark[i + 1]);
				
				BigDecimal total = getin.getPrice().multiply(
						new BigDecimal(getin.getConnt()));
				getin.setTotalPrice(total);
				getinSerivce.save(getin);
				
				int oldCount1 = Integer.parseInt(oldCount[i + 1]);
				if (oldCount1 != getin.getConnt().intValue()) {
					if (Category.COUNT_IN.equals(CommonDataCache.CATEGORY.get(cates[i + 1].trim()).getIsCountIn())) {
						Integer newCount = getin.getConnt();// 新增加数量
						Integer dbCount = goodsservice.findCount(getin.getGoodsId());// 原有库存量
						
						//维护时：库存-上次加入数+本次加入数
						Integer nowCount = (dbCount == null ? newCount
								: (dbCount - oldCount1 + newCount));// 现有库存量
						goods.setHaveCount(nowCount);
						
						goods.setGoodsId(getin.getGoodsId());
						getinSerivce.updateCount(goods);
					}
				}
			}
			SessionFactoryUtil.getSession().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SessionFactoryUtil.getSession().rollback();
			e.printStackTrace();
		} finally{
			SessionFactoryUtil.closeSession();
		}
		return "maintain";
	}

	private List billlist;
	private Goods goods;
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	private GetinbillService getinbillservice = ServiceProxyFactory
			.getInstance(new GetinbillService());

	private GetinService getinservice = ServiceProxyFactory
			.getInstance(new GetinService());

	private CategoryService categoryservice = ServiceProxyFactory
			.getInstance(new CategoryService());

	private GoodsService goodsservice = ServiceProxyFactory
			.getInstance(new GoodsService());
	private String[] goodsIds;
	private String[] prices;
	private String[] connt;
	private String[] remark;
	private String[] oldCount;//库中保存的数量

	public String[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String[] getPrices() {
		return prices;
	}

	public void setPrices(String[] prices) {
		this.prices = prices;
	}

	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	private Getin getin;
	private Getinbill getinbill;

	public Getin getGetin() {
		return getin;
	}

	public void setGetin(Getin getin) {
		this.getin = getin;
	}

	public Getinbill getGetinbill() {
		return getinbill;
	}

	public void setGetinbill(Getinbill getinbill) {
		this.getinbill = getinbill;
	}

	public String[] getConnt() {
		return connt;
	}

	public void setConnt(String[] connt) {
		this.connt = connt;
	}

	public String[] getOldCount() {
		return oldCount;
	}

	public void setOldCount(String[] oldCount) {
		this.oldCount = oldCount;
	}
	
}
