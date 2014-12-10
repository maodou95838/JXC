package cn.joymates.jxc.action.sellbill;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Getinbill;
import cn.joymates.jxc.domain.Goods;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberGrade;
import cn.joymates.jxc.domain.Sellbill;
import cn.joymates.jxc.domain.Sellgoods;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.GetinService;
import cn.joymates.jxc.service.GoodsService;
import cn.joymates.jxc.service.MemberGradeService;
import cn.joymates.jxc.service.MemberService;
import cn.joymates.jxc.service.SellbillService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

import com.opensymphony.xwork2.ActionContext;

/**
 * 销售单查询action
 * 
 * @author Jackie Hou
 *
 */
public class SessBillSearchAction extends BaseAction {
	public String search() {
		sellbillList = sellbillService.findSellBill(ec_rd, req, sellbill, member, sellDate1, sellDate2);
		for (int i = 0; i < sellbillList.size(); i++) {
			Map map = (Map) sellbillList.get(i);
			if(map.get("MEMBER_UUID") == null || map.get("MEMBER_UUID").equals("")){
				map.put("ISMEMBER",'否');
			}else{
				map.put("ISMEMBER", '是');
			}
		}
		return "search";
	}
	public String dispose() {
		sellbillList = sellbillService.findDisposeSellBill(ec_rd, req, sellbill, member, sellDate1, sellDate1);
		for (int i = 0; i < sellbillList.size(); i++) {
			Map map = (Map) sellbillList.get(i);
			if(map.get("MEMBER_UUID") == null || map.get("MEMBER_UUID").equals("")){
				map.put("ISMEMBER",'否');
			}else{
				map.put("ISMEMBER", '是');
			}
		}
		return "dispose";
	}
	
	public String billDetail() {
		Map<String, Object> detailMap = sellbillService.findBillDetail(sellbill);
		req.setAttribute("memberAndBill", detailMap.get("member"));
		req.setAttribute("goodsList", detailMap.get("goods"));
		return "billDetail";
	}
	
	/**
	 * 去登记页面
	 * @return
	 */
	public String gosave(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		return "gosave";
	}
	/**
	 * 查询会员折扣价
	 * @return
	 */
	public String findByMemberId(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		member = memberservice.findByMemberId(member.getMemberUuid());
		if(member!=null){
			membergrade = membergradeservice.findByGradeId(member.getGradeId());		
		}
		return "gosave";
	}
	
	/**
	 * 查询会员信息列表
	 */
	public String showmember(){
		sellbillList = sellbillService.findmember(ec_rd, req, member);
		return "memberlist";
	}
	
	/**
	 * 登记页面的添加
	 * @return
	 */
	public String addsellbill(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		User u = (User)session.getAttribute("loggedUser");
		
		try {
			SellbillService sellbillservice = new SellbillService();
			GoodsService goodsService = new GoodsService();
			SessionFactoryUtil.getSession();
			
			sellbill.setRegPerson(u.getUserId());
			if(StringUtils.isEmpty(sellbill.getIsExecBill())){
				sellbill.setIsExecBill(Sellbill.NOT_EXEC_BILL);
			}
			
			sellbillservice.addsellbill(sellbill);
			String[] cates = goods.getCateId().split(",");
			Goods goods = new Goods();
			Sellgoods sellgoods = new Sellgoods();
			for (int i = 0; i < goodsIds.length -1; i++) {
				sellgoods.setSellBillId(sellbill.getSellBillId());
				
				sellgoods.setGoodsId(goodsIds[i + 1]);
				goods.setGoodsId(sellgoods.getGoodsId());
				sellgoods.setRemark(remark[i+1]);
				
				int count = Integer.parseInt(connt[i + 1]);
				sellgoods.setSellCount(count);
				
				BigDecimal price = new BigDecimal(prices[i + 1]);
				sellgoods.setPrice(price);
				//计算单个商品总价
				sellgoods.setTotalPrice(price.multiply(BigDecimal.valueOf(count)));
				sellbillservice.addsellgoods(sellgoods);
				
				if (Category.COUNT_IN.equals(CommonDataCache.CATEGORY.get(cates[i + 1].trim()).getIsCountIn())) {
					Integer newCount = sellgoods.getSellCount();//卖出的数量
					Integer oldCount = goodsService.findCount(goods.getGoodsId());//原有库存量	
					oldCount = (oldCount == null ? 0 : oldCount);
					
					Integer nowCount = oldCount - newCount;//现有库存量;
					goods.setGoodsId(goodsIds[i + 1]);
					goods.setHaveCount(nowCount);
					goodsService.updateCount(goods);
				}
			}
			SessionFactoryUtil.getSession().commit();
		} catch (Exception e) {
			SessionFactoryUtil.getSession().rollback();
			e.printStackTrace();
		} finally{
			SessionFactoryUtil.closeSession();
		}	
		return "gosave";
	}
	/**
	 * 查询销售单据
	 */
	public String findAllsellbill(){
		sellbillList = sellbillService.findAllsellbill(ec_rd, req, sellbill, member);
		for (int i = 0; i < sellbillList.size(); i++) {
			Map map = (Map) sellbillList.get(i);
			if(map.get("MEMBER_UUID") == null || map.get("MEMBER_UUID").equals("")){
				map.put("ISMEMBER",'否');
			}else{
				map.put("ISMEMBER", '是');
			}
		}
		return "allsellbill";
	}
	
	/**
	 * 销售单维护
	 */
	public String findAllmaintain(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		
		try {
			SellbillService sellbillservice = new SellbillService();
			GoodsService goodsService = new GoodsService();
			MemberService memberService = new MemberService();
			SessionFactoryUtil.getSession();
			
//			member = memberService.selectMember(member);
			
			if (StringUtils.isNotEmpty(member.getMemberUuid())) {
				memberService.updateMemberIsConsume(member);
			}
			
			if(StringUtils.isEmpty(sellbill.getIsExecBill())){
				sellbill.setIsExecBill(Sellbill.NOT_EXEC_BILL);
			}
			sellbill.setMemberUuid(member.getMemberUuid());
			sellbillservice.updatesellbill(sellbill);//修改销售单据
											
			sellbillservice.deletesellgoods(sellbill.getSellBillId());//删除商品
			Sellgoods sellgoods = new Sellgoods();
			String[] cates = goods.getCateId().split(",");
			Goods goods = new Goods();
			sellgoods.setSellBillId(sellbill.getSellBillId());
			for (int i = 0; i < goodsIds.length -1; i++) {
				sellgoods.setGoodsId(goodsIds[i + 1]);
				int count = Integer.parseInt(connt[i + 1]);
				sellgoods.setSellCount(count);
				
				BigDecimal price = new BigDecimal(prices[i + 1]);
				sellgoods.setPrice(price);
				sellgoods.setRemark(remark[i+1]);
				//计算单个商品总价
				sellgoods.setTotalPrice(price.multiply(BigDecimal.valueOf(count)));
				sellbillservice.addsellgoods(sellgoods);
				
				int oldCount1 = Integer.parseInt(oldCount[i + 1]);
				if (oldCount1 != sellgoods.getSellCount().intValue() ) {
					if (Category.COUNT_IN.equals(CommonDataCache.CATEGORY.get(cates[i + 1].trim()).getIsCountIn())) {
						goods.setGoodsId(goodsIds[i + 1]);
						Integer newCount = sellgoods.getSellCount();//卖出的数量
						Integer dbCount = goodsService.findCount(goods.getGoodsId());//原有库存量
						
						dbCount = (dbCount == null ? 0 : dbCount);
						//库存维护：数据库 + 登记卖出量 - 维护卖出量
						Integer nowCount = dbCount + oldCount1 - newCount;//现有库存量;
						goods.setHaveCount(nowCount);
						goodsService.updateCount(goods);	
					}
				}
			}
			SessionFactoryUtil.getSession().commit();
		} catch (Exception e) {
			SessionFactoryUtil.getSession().rollback();
			e.printStackTrace();
		} finally{
			SessionFactoryUtil.closeSession();
		}
		return "allsellbill";
	}
	
	public String findBysellbillId(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		
		sellbillService = new SellbillService();
		try {
			sellbill = sellbillService.findById(sellbill.getSellBillId());
			
			if(sellbill.getMemberUuid() != null){
				member = sellbillService.findByMemberId(sellbill.getMemberUuid());
				if(member != null){
					membergrade = membergradeservice.findByGradeId(member.getGradeId());	
					if(member.getMark() == null) {
						member.setMark(new BigDecimal(0));
					}
				}
			}
			
			//What the fuck!
			ec_rd = "50";
			List alllist = sellbillService.findAllmaintain(ec_rd, req, sellbill);
			for (int i = 0; i < alllist.size(); i++) {
				Map map = (Map) alllist.get(i);
				List goodname = CommonDataCache.GOODS.get(map.get("CATE_ID"));
				req.setAttribute("goodname", goodname);
				map.put("gname", goodname);
				
			}
			
			ActionContext.getContext().put("alllist", alllist);
		} catch (Exception e) {
			SessionFactoryUtil.getSession().rollback();
			e.printStackTrace();
		} finally{
			SessionFactoryUtil.closeSession();
		}
		return "gomaintain";
	}
	
	/**
	 * 异常单
	 */
	public String findDisposeBysellbillId(){
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		sellbill = sellbillService.findById(sellbill.getSellBillId());
		if(sellbill.getMemberUuid()!=null){
			member = sellbillService.findByMemberId(sellbill.getMemberUuid());
			
		}
		List alllist = sellbillService.findAllmaintain(ec_rd, req, sellbill);
		for (int i = 0; i < alllist.size(); i++) {
			Map map = (Map) alllist.get(i);
			List goodname = CommonDataCache.GOODS.get(map.get("CATE_ID"));
			req.setAttribute("goodname", goodname);
			map.put("gname", goodname);
			
		}
		
		ActionContext.getContext().put("alllist", alllist);
		return "godispose";
	}
	/**
	 * 异常单处理
	 */
	public String disposepage(){
		sellbill.setSellBillId(sellbill.getSellBillId().replaceAll(", ", ""));
		User u = (User)session.getAttribute("loggedUser");
		sellbill.setRecoveryTime(new Date());
		sellbill.setRecoveryPerson(u.getUserId());
		sellbillService.updatesbill(sellbill);
		return "dispose";
	}
	/**
	 * 去维护页面
	 * @return
	 */
	public String gomaintain(){
		return "gomaintain";
	}
	/**
	 * 去一场页面
	 * @return
	 */
	public String godispose(){
		return "godispose";
	}
	
	private SellbillService sellbillService = ServiceProxyFactory.getInstance(new SellbillService());
	private MemberGradeService membergradeservice = ServiceProxyFactory.getInstance(new MemberGradeService());
	
	private List sellbillList;
	
	private Sellbill sellbill;
	
	private Member member;
	private MemberGrade membergrade;
	
	public MemberGrade getMembergrade() {
		return membergrade;
	}
	public void setMembergrade(MemberGrade membergrade) {
		this.membergrade = membergrade;
	}

	/**
	 * 销售起止日期
	 */
	private String sellDate1;
	private String sellDate2;
	
	public String getSellDate1() {
		return sellDate1;
	}

	public void setSellDate1(String sellDate1) {
		this.sellDate1 = sellDate1;
	}

	public String getSellDate2() {
		return sellDate2;
	}

	public void setSellDate2(String sellDate2) {
		this.sellDate2 = sellDate2;
	}

	public Sellbill getSellbill() {
		return sellbill;
	}

	public void setSellbill(Sellbill sellbill) {
		this.sellbill = sellbill;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getSellbillList() {
		return sellbillList;
	}

	public void setSellbillList(List sellbillList) {
		this.sellbillList = sellbillList;
	}
	private MemberService memberservice = ServiceProxyFactory.getInstance(new MemberService());
	private GoodsService goodsservice= ServiceProxyFactory.getInstance(new GoodsService());
	private GetinService getinservice =  ServiceProxyFactory.getInstance(new GetinService());
	private String [] goodsIds;
	private String[] prices;
	private String[] connt;
	private String[] oldCount;//原有数量
	private String[] remark;
	private Goods goods;
	private Getinbill getinbill;
	private Sellgoods sellgoods;
	
	public Sellgoods getSellgoods() {
		return sellgoods;
	}
	public void setSellgoods(Sellgoods sellgoods) {
		this.sellgoods = sellgoods;
	}
	public Getinbill getGetinbill() {
		return getinbill;
	}

	public void setGetinbill(Getinbill getinbill) {
		this.getinbill = getinbill;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

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

	public String[] getConnt() {
		return connt;
	}
	public void setConnt(String[] connt) {
		this.connt = connt;
	}
	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}
	public String[] getOldCount() {
		return oldCount;
	}
	public void setOldCount(String[] oldCount) {
		this.oldCount = oldCount;
	}
	
	
}
