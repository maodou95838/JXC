package cn.joymates.jxc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.jxc.dao.MemberDao;
import cn.joymates.jxc.dao.SellbillDao;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberCharge;
import cn.joymates.jxc.domain.Sellbill;

import com.future.existabc.util.UUIDGener;

public class MemberService {
	
	public List find(String ecRd, HttpServletRequest req, Member member) {
		return dao.findAll(ecRd, req, member);
	}
	
	public void memberReg(Member member) {
		member.setMemberUuid(UUIDGener.getUUID());
		member.setIsLogout(Member.NOT_LOGOUT);
		
		//积分暂时设为零，目前还没有积分算法
		member.setMark(BigDecimal.ZERO);
		member.setRegDate(new Date());
		member.setLeftMoney(new BigDecimal("0"));
		
		if (member.getLeftMoney() == null) {
			member.setLeftMoney(BigDecimal.ZERO);
		}
		dao.insert(member);
	}
	
	public Member findById(Member m) {
		return dao.findById(m);
	}
	
	public void modify(Member m, MemberCharge charge) {
		m.setIsLogout(Member.LOGOUT_MAP.get(m.getIsLogout()));
		m.setLogoutDate(new Date());
		m.setModifyTime(new Date());
		
		//未找到充值记录
		if (charge == null || charge.getChargeUuid() == null || StringUtils.isEmpty(charge.getChargeUuid())) {
			dao.update(m);
			return;
		}
		
		BigDecimal oldMoney = new BigDecimal(charge.getRemark());
		BigDecimal newMoney = charge.getMoney();
		if (!oldMoney.equals(charge.getMoney())) {
			//新值 - 旧值 + 新值
//			BigDecimal surplus = newMoney.subtract(oldMoney);
//			charge.setMoney(newMoney.add(surplus));
			dao.updateMemberChange(charge);
			
			BigDecimal leftMoney = m.getLeftMoney().subtract(oldMoney).add(newMoney);
			m.setLeftMoney(leftMoney);
			
			BigDecimal oldMark = BigDecimal.valueOf(oldMoney.intValue());
			BigDecimal newMark = BigDecimal.valueOf(newMoney.intValue());
			m.setMark(m.getMark().subtract(oldMark).add(newMark));
		}
		
		dao.update(m);
	}
	
	public void charge(Member m, MemberCharge charge) {
		charge.setChargeUuid(UUIDGener.getUUID());
		charge.setMemberUuid(m.getMemberUuid());
		charge.setChargeTime(new Date());
		dao.insert(charge);

		BigDecimal lm = (m.getLeftMoney() == null ? new BigDecimal(0) : m.getLeftMoney());
		m.setLeftMoney(lm.add(charge.getMoney()));
		m.setMark(m.getLeftMoney());
		dao.updateMoney(m);
			
	}
	
	/**
	 * 查询会员的消费记录、充值记录
	 * @param ecRd
	 * @param req
	 * @param m
	 * @return
	 */
	public Map<String, Object> findAllConsumeCharge(String ecRd, HttpServletRequest req, Member m) {
		Member member = dao.findById(m);
		
		SellbillDao sbdao = new SellbillDao();
		Sellbill sb = new Sellbill();
		sb.setMemberUuid(m.getMemberUuid());
		List bList = sbdao.findMemberConsume(ecRd, req, sb);
		
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("member", member);
		rmap.put("consumeList", bList);
		rmap.put("chargeList", dao.findChargeByMemberId(ecRd, req, m));
		return rmap;
	}
	
	public Member findByMemberId(String member_uuid) {
		return dao.findByMemberId(member_uuid);
	}
	public void updateMember(Member m){
		dao.updateMoney(m);
	}
	public void updateMemberIsConsume(Member member){
		dao.updateMemberIsConsume(member);
	}
	public Member selectMember(Member m){
		return dao.selectMember(m);
	}
	
	private MemberDao dao = new MemberDao();
}
