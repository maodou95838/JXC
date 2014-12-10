package cn.joymates.jxc.action.member;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberCharge;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.MemberService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;

/**
 * 会员管理action
 * 
 * @author Jackie Hou
 *
 */
public class MemberAction extends BaseAction {
	
	public String findUI() {
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		return "find";
	}
	
	public String find() {
		memberList = memberService.find(ec_rd, req, member);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		req.setAttribute("logoutMap", Member.LOGOUT_MAP);
		
		if (findType != null) {
			req.setAttribute("findTypeMap", Member.findTypeMap);
			return "chgCsumHome";
		}
		return "find";
	}
	
	public String regUI() {
		req.setAttribute("sex", Member.sexMap);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		return "regUI";
	}
	
	public String reg() {
		memberService.memberReg(member);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		return "find";
	}
	
	public String modifyUI() {
		String type = member.getRemark1();
		
		member = memberService.findById(member);
		if (StringUtils.isEmpty(member.getLastChargeUuid())) {
			member.setRemark1("0");//从未充值
		}
		
		req.setAttribute("sex", Member.sexMap);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		
		if (type != null && "charge".equals(type)) {
			return "chargeUI";
		}
		return "modifyUI";
	}
	
	public String modify() {
		User u = (User)session.getAttribute("loggedUser");
		member.setModifyPerson(u.getUserId());
		
		memberService.modify(member, charge);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		return "find";
	}
	
	public String charge() {
		memberService.charge(member, charge);
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		return "find";
	}
	
	
	private MemberService memberService = ServiceProxyFactory.getInstance(new MemberService()); 
	
	private Member member;
	
	private String findType;
	
	private MemberCharge charge;
	
	private List memberList;
	
	public List getMemberList() {
		return memberList;
	}

	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberCharge getCharge() {
		return charge;
	}

	public void setCharge(MemberCharge charge) {
		this.charge = charge;
	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}
	
}
