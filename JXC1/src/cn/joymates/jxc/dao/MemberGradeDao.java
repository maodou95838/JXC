package cn.joymates.jxc.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.MemberGrade;

public class MemberGradeDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public List findAll(String ecRd, HttpServletRequest req, MemberGrade grade) {
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		if (grade != null) {
			if (StringUtils.isNotEmpty(grade.getGradeName())) {
				sb.append(" and grade_name='" + grade.getGradeName() + "' ");
			}
			
			if (StringUtils.isNotEmpty(grade.getGradeId())) {
				sb.append(" and grade_id='" + grade.getGradeId() + "' ");
			}
		}
		
		String searchsql = "select count(1) from  member_grade_tb  t where 1=1 " + sb.toString();
		String resultsql = "select t.* from member_grade_tb t  where 1=1 " + sb.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	/**
	 * 会员等级添加
	 * @param grade
	 */
	public void insert(MemberGrade grade) {
		sess.insert("member.insertMemberGrade", grade);
	}
	
	public MemberGrade findById(MemberGrade grade) {
		return sess.selectOne("member.selectMemberGradeById", grade.getGradeId());
	}
	
	public void update(MemberGrade grade) {
		sess.update("member.updateMemberGrade", grade);
	}
	/**
	 * 折扣价查询
	 */
	public MemberGrade findByGradeId(String grade_id){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectGradepercent", grade_id);
	}
}
