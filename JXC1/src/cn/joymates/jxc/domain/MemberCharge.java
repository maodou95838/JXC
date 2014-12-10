package cn.joymates.jxc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员充值记录
 * @author Jackie Hou
 *
 */
public class MemberCharge {
	private String chargeUuid;
    private String memberUuid;
    private BigDecimal money;
    private Date chargeTime;
    private String remark;
    
	public String getChargeUuid() {
		return chargeUuid;
	}
	public void setChargeUuid(String chargeUuid) {
		this.chargeUuid = chargeUuid;
	}
	public String getMemberUuid() {
		return memberUuid;
	}
	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
