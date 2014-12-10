package cn.joymates.jxc.domain;

import java.math.BigDecimal;
import java.util.Date;


//销售单据清单
public class Sellbill {
	private String sellBillId;
	private String memberUuid;
	private Date sellDate;
	private Date regTime;
	private String regPerson;
	private BigDecimal totalPrice;
	private BigDecimal percentPrice;
	private BigDecimal actualMoney;
	private Byte[] sellBillImage;
	private String customerDesc;
	private String takeMoneyPerson;
	private String billPerson;
	private String isExecBill;
	private String execReason;
	private String execRemark;
	private Date recoveryTime;
	private String recoveryPerson;
	private String recoveryRemark;
	private Integer mark;
	private String remark;
	private String remark1;
	private String sellBillCode;
	private String isPay;
	private String num;
	private String years;
	private String quarter;
	private String jidu;
	
	
	
	public String getJidu() {
		return jidu;
	}
	public void setJidu(String jidu) {
		this.jidu = jidu;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * 是否异常单
	 */
	public static final String EXEC_BILL = "1";
	public static final String NOT_EXEC_BILL = "0";
	
	/**
	 * 是否付款
	 */
	public static final String PAYED = "1";
	public static final String NOT_PAY = "0";
	
	public String getSellBillId() {
		return sellBillId;
	}
	public void setSellBillId(String sellBillId) {
		this.sellBillId = sellBillId;
	}
	public String getMemberUuid() {
		return memberUuid;
	}
	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getRegPerson() {
		return regPerson;
	}
	public void setRegPerson(String regPerson) {
		this.regPerson = regPerson;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getPercentPrice() {
		return percentPrice;
	}
	public void setPercentPrice(BigDecimal percentPrice) {
		this.percentPrice = percentPrice;
	}
	public BigDecimal getActualMoney() {
		return actualMoney;
	}
	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
	}
	public Byte[] getSellBillImage() {
		return sellBillImage;
	}
	public void setSellBillImage(Byte[] sellBillImage) {
		this.sellBillImage = sellBillImage;
	}
	public String getCustomerDesc() {
		return customerDesc;
	}
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}
	public String getTakeMoneyPerson() {
		return takeMoneyPerson;
	}
	public void setTakeMoneyPerson(String takeMoneyPerson) {
		this.takeMoneyPerson = takeMoneyPerson;
	}
	public String getBillPerson() {
		return billPerson;
	}
	public void setBillPerson(String billPerson) {
		this.billPerson = billPerson;
	}
	public String getIsExecBill() {
		return isExecBill;
	}
	public void setIsExecBill(String isExecBill) {
		this.isExecBill = isExecBill;
	}
	public String getExecReason() {
		return execReason;
	}
	public void setExecReason(String execReason) {
		this.execReason = execReason;
	}
	public String getExecRemark() {
		return execRemark;
	}
	public void setExecRemark(String execRemark) {
		this.execRemark = execRemark;
	}
	public Date getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public String getRecoveryPerson() {
		return recoveryPerson;
	}
	public void setRecoveryPerson(String recoveryPerson) {
		this.recoveryPerson = recoveryPerson;
	}
	public String getRecoveryRemark() {
		return recoveryRemark;
	}
	public void setRecoveryRemark(String recoveryRemark) {
		this.recoveryRemark = recoveryRemark;
	}

	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
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
	public String getSellBillCode() {
		return sellBillCode;
	}
	public void setSellBillCode(String sellBillCode) {
		this.sellBillCode = sellBillCode;
	}
	public String getIsPay() {
		return isPay;
	}
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	
}
