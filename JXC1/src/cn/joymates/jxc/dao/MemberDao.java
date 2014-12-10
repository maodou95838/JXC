package cn.joymates.jxc.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Member;
import cn.joymates.jxc.domain.MemberCharge;

public class MemberDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public List findAll(String ecRd, HttpServletRequest req, Member member) {
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		if (member != null) {
			if (StringUtils.isNotEmpty(member.getName())) {
				sb.append(" and name='" + member.getName() + "' ");
			}
			
			if (StringUtils.isNotEmpty(member.getGradeId()) && !"-1".equals(member.getGradeId())) {
				sb.append(" and grade_id='" + member.getGradeId() + "' ");
			}
			
			if (StringUtils.isNotEmpty(member.getCellPhone())) {
				sb.append(" and cell_phone='" + member.getCellPhone() + "' ");
			}
			
			if (StringUtils.isNotEmpty(member.getMemberCode())) {
				sb.append(" and member_code='" + member.getMemberCode() + "' ");
			}
		}
		
		String searchsql = "select count(1) from  member_tb  t where 1=1 " + sb.toString();
		String resultsql = "select t.* from member_tb t  where 1=1 " + sb.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	
	public void insert(Member member) {
		sess.insert("insertMember", member);
	}
	
	public Member findById(Member m) {
		 List<Member> list = sess.selectList("findMemberChargeById", m.getMemberUuid());
		 return ((list != null && !list.isEmpty()) ? list.get(0) : null);
	}
	
	public void updateMemberChange(MemberCharge charge) {
		sess.update("updateMemberChange", charge);
	}
	
	public void update(Member m) {
		sess.update("updateMember", m);
	}
	
	public void insert(MemberCharge charge) {
		sess.insert("insertMemberCharge", charge);
	}
	
	public void updateMoney(Member m) {
		sess.update("updateMemberMoney", m);
	}
	
	public List findChargeByMemberId(String ecRd, HttpServletRequest req, Member m) {
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		if (m != null) {
			if (StringUtils.isNotEmpty(m.getMemberCode())) {
				sb.append(" and member_code='" + m.getMemberCode() + "' ");
			}
		}
		
		String searchsql = "select count(1) from member_charge_tb c, member_tb m" +
						" where c.member_uuid = m.member_uuid " + sb.toString();
		String resultsql = "select * from member_charge_tb c, member_tb m" +
					" where c.member_uuid = m.member_uuid " +
					  sb.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	public Member findByMemberId(String member_uuid) {
		return sess.selectOne("findMemberById",member_uuid);
	}
	public void updateMemberIsConsume(Member member){
		sess.update("updateMemberIsConsume", member);
	}
	public Member selectMember(Member m){
		return sess.selectOne("selectMember", m);
	}
	
}
