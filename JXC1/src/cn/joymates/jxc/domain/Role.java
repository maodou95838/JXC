package cn.joymates.jxc.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Role {
	private String roleUuid;	
	private String roleId;		
	private String roleName;
	
	/**
	 * 最大优惠金额
	 */
	private BigDecimal maxMinsMoney;	
	private String isLogout;
	private String logoutReason;
	
	private String createPerson;	
	private Date createTime;		
	private String remark;
	
	private int roleQuota;
	
	public static final int MIN_ROLE_QUOTA = Integer.MAX_VALUE;
	/**
	 * 注销
	 */
	public static final String LOGOUT = "1";
	public static final String NO_LOGOUT = "0";
	
	public static final Map<String, String> LOGOUT_MAP = new HashMap<String, String>() {
		{
			put(LOGOUT, "Y");
			put(NO_LOGOUT, "N");
		}
	};
	
//	public static final String 
//	public static final Map<String, String> ROLE_GRADE = new HashMap<String, String>() {
//		{
//			
//		}
//	};
	
	public int getRoleQuota() {
		return roleQuota;
	}
	public void setRoleQuota(int roleQuota) {
		this.roleQuota = roleQuota;
	}
	public String getRoleUuid() {
		return roleUuid;
	}
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public BigDecimal getMaxMinsMoney() {
		return maxMinsMoney;
	}
	public void setMaxMinsMoney(BigDecimal maxMinsMoney) {
		this.maxMinsMoney = maxMinsMoney;
	}
	public String getIsLogout() {
		return isLogout;
	}
	public void setIsLogout(String isLogout) {
		this.isLogout = isLogout;
	}
	public String getLogoutReason() {
		return logoutReason;
	}
	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	

}
