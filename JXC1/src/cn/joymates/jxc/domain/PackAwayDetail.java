package cn.joymates.jxc.domain;

import java.util.Date;

public class PackAwayDetail {
	private String awayUuid;
	private String packUuid;
	private Date putGetTime;
	private Integer putGetCount;
	private String rePerson;
	private String putGet;

	private String remark;
	private String remark2;

	public String getAwayUuid() {
		return awayUuid;
	}

	public void setAwayUuid(String awayUuid) {
		this.awayUuid = awayUuid;
	}

	public String getPackUuid() {
		return packUuid;
	}

	public void setPackUuid(String packUuid) {
		this.packUuid = packUuid;
	}


	public Date getPutGetTime() {
		return putGetTime;
	}

	public void setPutGetTime(Date putGetTime) {
		this.putGetTime = putGetTime;
	}

	public Integer getPutGetCount() {
		return putGetCount;
	}

	public void setPutGetCount(Integer putGetCount) {
		this.putGetCount = putGetCount;
	}

	public String getRePerson() {
		return rePerson;
	}

	public void setRePerson(String rePerson) {
		this.rePerson = rePerson;
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

	public String getPutGet() {
		return putGet;
	}

	public void setPutGet(String putGet) {
		this.putGet = putGet;
	}

}
