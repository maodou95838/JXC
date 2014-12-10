package cn.joymates.jxc.action.member;

import java.util.List;
import java.util.Map;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.service.MemberService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

/**
 * 充值、消费action
 * 
 * @author Jackie Hou
 *
 */
public class ChargeConsumeAction extends BaseAction {
	
	public String homeUI() {
		init();
		return "home";
	}
	
	/**
	 * 消费详细信息
	 * @return
	 */
	public String detail() {
		Map<String, Object> rmap = memberService.findAllConsumeCharge(ec_rd, req, member);
//		for (Map.Entry<Member, List> entry : rmap.entrySet()) {
//			member = entry.getKey();
//			consumeList = entry.getValue();
//		}
		member = (Member)rmap.get("member");
		consumeList = (List)rmap.get("consumeList");
		chargeList = (List)rmap.get("chargeList");
		if ("1".equals(findType)) {
			return "detail1";
		}
		return "detail";
	}
	
	private void init() {
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		req.setAttribute("findTypeMap", Member.findTypeMap);
	}
	
	private Member member;
	
	private String findType;
	
	private List consumeList;
	
	private List chargeList;
	
	private MemberService memberService = ServiceProxyFactory.getInstance(new MemberService()); 

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getConsumeList() {
		return consumeList;
	}

	public void setConsumeList(List consumeList) {
		this.consumeList = consumeList;
	}

	public List getChargeList() {
		return chargeList;
	}

	public void setChargeList(List chargeList) {
		this.chargeList = chargeList;
	}
	
}
