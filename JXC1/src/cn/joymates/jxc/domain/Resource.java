package cn.joymates.jxc.domain;

import java.util.List;

public class Resource {
	private String resourceId;
	private String resourceUrl;
	private String resourceOrder;
	
	private String parentId;
	private String resourceRemark;
	private String resourceTitle;
	
	private String parentTitle;
	
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	private List<Resource> childrenList;
	
	public List<Resource> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<Resource> childrenList) {
		this.childrenList = childrenList;
	}
	public String getResourceTitle() {
		return resourceTitle;
	}
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceOrder() {
		return resourceOrder;
	}
	public void setResourceOrder(String resourceOrder) {
		this.resourceOrder = resourceOrder;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResourceRemark() {
		return resourceRemark;
	}
	public void setResourceRemark(String resourceRemark) {
		this.resourceRemark = resourceRemark;
	}
	
	

}
