package cn.joymates.jxc.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 存单
 * @author Jackie Hou
 *
 */
public class MemberPack {        
	private String packUuid; 		
    private String memberUuid; 		
    private String goodsId; 		
    private Date leftDate; 		
    private Integer leftCount;  //存放数量
    private Date lastAwayTime;
    private Integer leftCount1;	//剩余量
    private Integer lastAwayCount;
    private String regPerson; 		
    private String lastRemark; 		
    private String remark; 			
    private String remark2;
    private Date regTime;
    
    
    private String goodsCategory; //存放商品类型
    private String purOrGet;
  
	/**
     * 剩余
     */
    public static final String HAS = "1";
    public static final String NO_MORE = "0";
    public static Map<String, String> SURPLUS_MAP;
    
    public static final String PUT = "1";
    public static final String GET = "2";
    public static Map<String, String> PUTGET_MAP;
    static {
    	SURPLUS_MAP = new HashMap<String, String>();
    	SURPLUS_MAP.put(HAS, "有");
    	SURPLUS_MAP.put(NO_MORE, "无");
    	
    	PUTGET_MAP = new HashMap<String, String>();
    	PUTGET_MAP.put(PUT, "存单");
    	PUTGET_MAP.put(GET, "取单");
    }
    
    
	public String getGoodsCategory() {
		return goodsCategory;
	}
	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}
	public String getPackUuid() {
		return packUuid;
	}
	public void setPackUuid(String packUuid) {
		this.packUuid = packUuid;
	}
	public String getMemberUuid() {
		return memberUuid;
	}
	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Date getLeftDate() {
		return leftDate;
	}
	public void setLeftDate(Date leftDate) {
		this.leftDate = leftDate;
	}
	public Date getLastAwayTime() {
		return lastAwayTime;
	}
	public void setLastAwayTime(Date lastAwayTime) {
		this.lastAwayTime = lastAwayTime;
	}
	public String getRegPerson() {
		return regPerson;
	}
	public void setRegPerson(String regPerson) {
		this.regPerson = regPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	public Integer getLeftCount1() {
		return leftCount1;
	}
	public void setLeftCount1(Integer leftCount1) {
		this.leftCount1 = leftCount1;
	}
	public Integer getLastAwayCount() {
		return lastAwayCount;
	}
	public void setLastAwayCount(Integer lastAwayCount) {
		this.lastAwayCount = lastAwayCount;
	}
    
	public String getLastRemark() {
		return lastRemark;
	}
	public void setLastRemark(String lastRemark) {
		this.lastRemark = lastRemark;
	}
	public String getPurOrGet() {
		return purOrGet;
	}
	public void setPurOrGet(String purOrGet) {
		this.purOrGet = purOrGet;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
}
