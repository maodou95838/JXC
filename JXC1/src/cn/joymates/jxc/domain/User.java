package cn.joymates.jxc.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	private String userCode;
    private String userId;
    private String userName;
    
    private String password1;
    private Date createTime;
    private String createPerson;
    
    private String contactPhone;
    private String isLogout;
    private String logoutReason;
    
    private String remark;
    
    private int maxRoleQuota; //最大权

	private List<Role> roleList;
	
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
    
	private Set<String> urlSet;
	
	//用户对应的权限资源
	private Map<Resource, List<Resource>> resourceMap;
	
    public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
     
	public Map<Resource, List<Resource>> getResourceMap() {
		return resourceMap;
	}
	public void setResourceMap(Map<Resource, List<Resource>> resourceMap) {
		this.resourceMap = resourceMap;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public int getMaxRoleQuota() {
			return maxRoleQuota;
	}
	public void setMaxRoleQuota(int maxRoleQuota) {
		this.maxRoleQuota = maxRoleQuota;
	} 
 
	public Set<String> getUrlSet() {
		return urlSet;
	}
	public void setUrlSet(Set<String> urlSet) {
		this.urlSet = urlSet;
	}
}
