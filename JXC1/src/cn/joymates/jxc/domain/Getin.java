package cn.joymates.jxc.domain;

import java.math.BigDecimal;

//采购商品的清单表
public class Getin {
	private	String getInGoodsId;
	private String billId;
	private String goodsId;
	private BigDecimal price;
	private Integer connt;
	private BigDecimal totalPrice;
	private String remark;
	public String getGetInGoodsId() {
		return getInGoodsId;
	}
	public void setGetInGoodsId(String getInGoodsId) {
		this.getInGoodsId = getInGoodsId;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getConnt() {
		return connt;
	}
	public void setConnt(Integer connt) {
		this.connt = connt;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
