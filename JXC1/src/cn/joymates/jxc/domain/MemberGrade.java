package cn.joymates.jxc.domain;

import java.math.BigDecimal;

public class MemberGrade extends BaseVO {
	private String gradeId;
	private String gradeName;
	private BigDecimal gradePercent;
	private String gradeDesc;
	private String isLogout;
	private String logoutReason;
	
//	public static final String LOGOUT = "1";
//	public static final String NOT_LOGOUT = "0";
//	
//	public static final Map<String, String> LOGOUT_MAP = new HashMap<String, String>() {
//		{
//			put(LOGOUT, "Y");
//			put(NOT_LOGOUT, "N");
//			put("true", LOGOUT);
//			put("false", NOT_LOGOUT);
//		}
//	};
	
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeDesc() {
		return gradeDesc;
	}
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
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
	public BigDecimal getGradePercent() {
		return gradePercent;
	}
	public void setGradePercent(BigDecimal gradePercent) {
		this.gradePercent = gradePercent;
	}
     
     
}
