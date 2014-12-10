package cn.joymates.jxc.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Member extends BaseVO {
	private String memberUuid;
    private String gradeId;
    private String memberCode;
    private String name2;
    private String name;
    private String sex;
    private String cellPhone1;
    private String cellPhone;
    private Date regDate;
    private Date birthday;
    private String isLogout;
    private Date logoutDate;
    private String logoutReason;
    private String isConsume;
    private BigDecimal leftMoney;
    private BigDecimal mark;
    private String modifyPerson;
    private Date modifyTime;
    private String remark;
    private String remark1;
    
    private String lastChargeUuid;
    
    /**
     * 性别
     */
    public final static String MAN = "1";
    public final static String WOMAN = "2";
    public static Map<String, String> sexMap;
    
    /**
     * 查询类型
     */
    public final static String CHARGE = "1";
    public final static String CONSUME = "2";
    public static Map<String, String> findTypeMap;
	
    static {
    	sexMap = new HashMap<String, String>();
    	sexMap.put(MAN, "男");
    	sexMap.put(WOMAN, "女");
    	
    	findTypeMap = new HashMap<String, String>();
    	findTypeMap.put(CHARGE, "充值");
    	findTypeMap.put(CONSUME, "消费");
    }
    
	public String getMemberUuid() {
		return memberUuid;
	}
	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCellPhone1() {
		return cellPhone1;
	}
	public void setCellPhone1(String cellPhone1) {
		this.cellPhone1 = cellPhone1;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getIsLogout() {
		return isLogout;
	}
	public void setIsLogout(String isLogout) {
		this.isLogout = isLogout;
	}
	public Date getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getLogoutReason() {
		return logoutReason;
	}
	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}
	public String getIsConsume() {
		return isConsume;
	}
	public void setIsConsume(String isConsume) {
		this.isConsume = isConsume;
	}
	public BigDecimal getLeftMoney() {
		return leftMoney;
	}
	public void setLeftMoney(BigDecimal leftMoney) {
		this.leftMoney = leftMoney;
	}
	public BigDecimal getMark() {
		return mark;
	}
	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
	public String getLastChargeUuid() {
		return lastChargeUuid;
	}
	public void setLastChargeUuid(String lastChargeUuid) {
		this.lastChargeUuid = lastChargeUuid;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
     
}
