package cn.joymates.jxc.domain;

import java.math.BigDecimal;



/**
 * 销售商品表
 * @author Administrator
 *
 */
public class Sellgoods {
	private String sellGoodsId;
	private String sellBillId;
	private String goodsId;
	private Integer sellCount;
	private BigDecimal price;
	private BigDecimal totalPrice;
	private String remark;
	public String getSellGoodsId() {
		return sellGoodsId;
	}
	public void setSellGoodsId(String sellGoodsId) {
		this.sellGoodsId = sellGoodsId;
	}
	public String getSellBillId() {
		return sellBillId;
	}
	public void setSellBillId(String sellBillId) {
		this.sellBillId = sellBillId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
