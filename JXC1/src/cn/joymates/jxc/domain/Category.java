package cn.joymates.jxc.domain;


//商品的分类
public class Category {
	private String cateId;
	private String name;
	private String cateDesc;
	private String managePerson;
	private String countUnit;
	private String isCountIn;//是否计入库存
	private String canPack;//是否存单
	
	public static final String COUNT_IN = "1";//是否可入库
	public static final String CAN_PACK = "1";//是否可存单
	
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCateDesc() {
		return cateDesc;
	}
	public void setCateDesc(String cateDesc) {
		this.cateDesc = cateDesc;
	}
	public String getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
	}
	public String getCountUnit() {
		return countUnit;
	}
	public void setCountUnit(String countUnit) {
		this.countUnit = countUnit;
	}
	public String getIsCountIn() {
		return isCountIn;
	}
	public void setIsCountIn(String isCountIn) {
		this.isCountIn = isCountIn;
	}
	public String getCanPack() {
		return canPack;
	}
	public void setCanPack(String canPack) {
		this.canPack = canPack;
	}
	
}
