package cn.joymates.jxc.domain;

import java.math.BigDecimal;
import java.util.Date;


//购物的清单表
public class Getinbill {
	private String billId;
	private String billCode;
	private Date getInDate;
	private Date regDate;
	private String regPerson;
	private BigDecimal totalMoney;
	private String remark;
	private String remark1;
	private String buyerPerson;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public Date getGetInDate() {
		return getInDate;
	}
	public void setGetInDate(Date getInDate) {
		this.getInDate = getInDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRegPerson() {
		return regPerson;
	}
	public void setRegPerson(String regPerson) {
		this.regPerson = regPerson;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getBuyerPerson() {
		return buyerPerson;
	}
	public void setBuyerPerson(String buyerPerson) {
		this.buyerPerson = buyerPerson;
	}
	
}
