package cn.joymates.jxc.action.member;

import java.util.List;
import java.util.Map;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberPack;
import cn.joymates.jxc.domain.PackAwayDetail;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.MemberPackService;
import cn.joymates.jxc.utils.CommonDataCache;
import cn.joymates.jxc.utils.ServiceProxyFactory;
/**
 * 存单管理action
 * 
 * @author Jackie Hou
 *
 */
public class PackMaintAction extends BaseAction {
	public String homeUI() {
		init();
		return "home";
	}
	
	public String find() {
		packList = memberPackService.findByCond(ec_rd, req, member, memberPack);
		init();
		return "home";
	}
	
	public String saveUI() {
		Map<String, Object> retMap = memberPackService.showSaveUISrv(member);
		member = (Member)retMap.get("member");
		
		String cateId = (String)retMap.get("packCateId");
		req.setAttribute("packGoodsList", CommonDataCache.GOODS.get(cateId));
		init();
		return "saveUI";
	}
	
	/**
	 * 存单action
	 * @return
	 */
	public String save() {
		User u = (User)session.getAttribute("loggedUser");
		memberPack.setRegPerson(u.getUserId());
		memberPackService.saveMemberPack(memberPack);
		init();
		return "home";
	}
	
	public String modifyUI() {
		Map<String, Object> retMap = memberPackService.showModifyUISrv(member, memberPack);
		member = (Member)retMap.get("member");
		
		String cateId = (String)retMap.get("packCateId");
		req.setAttribute("packGoodsList", CommonDataCache.GOODS.get(cateId));
		
		memberPack = (MemberPack)retMap.get("memberPack");
		init();
		return "modifyUI";
	}
	
	public String modify() {
		memberPackService.modify(memberPack);
		init();
		return "home";
	}
	
	public String getPackUI() {
		Map<String, Object> retMap = memberPackService.showModifyUISrv(member, memberPack);
		member = (Member)retMap.get("member");
		
		String cateId = (String)retMap.get("packCateId");
		req.setAttribute("packGoodsList", CommonDataCache.GOODS.get(cateId));
		
		memberPack = (MemberPack)retMap.get("memberPack");
		memberPack.setPurOrGet(MemberPack.GET);
		return "getPackUI";
	}
	
	public String getAway() {
		PackAwayDetail detail = new PackAwayDetail();
		User u = (User)session.getAttribute("loggedUser");
		detail.setRePerson(u.getUserId());
		
		memberPackService.getPackAway(memberPack, detail);
		init();
		return "home";
	}
	
	private void init() {
		req.setAttribute("memberGrade", CommonDataCache.MEMBER_GRADE);
		req.setAttribute("category", CommonDataCache.CATEGORY);
		req.setAttribute("goods", CommonDataCache.GOODS);
		req.setAttribute("surplus", MemberPack.SURPLUS_MAP);
		req.setAttribute("logout", Member.LOGOUT_MAP);
		req.setAttribute("putGetMap", MemberPack.PUTGET_MAP);
	}
	
	private MemberPackService memberPackService = ServiceProxyFactory.getInstance(new MemberPackService());
	
	private MemberPack memberPack;
	
	private Member member;
	
	private List packList;
	
	public List getPackList() {
		return packList;
	}

	public void setPackList(List packList) {
		this.packList = packList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberPack getMemberPack() {
		return memberPack;
	}

	public void setMemberPack(MemberPack memberPack) {
		this.memberPack = memberPack;
	}
}
