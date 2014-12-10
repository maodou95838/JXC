package cn.joymates.jxc.action.member;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.service.MemberService;
import cn.joymates.jxc.utils.ServiceProxyFactory;


public class RennwalMember {
	
	/**
	 * 根据电话号码查询会员
	 * @param cellPhone
	 * @return
	 */
	public Member findByphone(String cellPhone, String memberUuid) {
		if (StringUtils.isEmpty(cellPhone)) {
			return null;
		}
		
		Member m = new Member();
		m.setCellPhone(cellPhone);
		m.setMemberUuid(memberUuid);
		
	    MemberService memberservice = ServiceProxyFactory.getInstance(new MemberService());
        return memberservice.selectMember(m);
	}
}
