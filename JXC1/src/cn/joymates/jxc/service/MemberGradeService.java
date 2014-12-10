package cn.joymates.jxc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.future.existabc.util.UUIDGener;

import cn.joymates.jxc.dao.MemberGradeDao;
import cn.joymates.jxc.domain.MemberGrade;

public class MemberGradeService {
	
	public List find(String ecRd, HttpServletRequest req, MemberGrade grade) {
		return dao.findAll(ecRd, req, grade);
	}
	
	public void save(MemberGrade grade) {
		grade.setGradeId(UUIDGener.getUUID());
		grade.setIsLogout(MemberGrade.NOT_LOGOUT);
		dao.insert(grade);
	}
	
	public MemberGrade findById(MemberGrade grade) {
		return dao.findById(grade);
	}
	
	//该方法名更改，会影响缓存，请注意！
	public void update(MemberGrade grade) {
		grade.setIsLogout(MemberGrade.LOGOUT_MAP.get(grade.getIsLogout()));
		dao.update(grade);
	}
	/**
	 * 折扣价查询
	 */
	public MemberGrade findByGradeId(String grade_id){
		return dao.findByGradeId(grade_id);
	}
	
	private MemberGradeDao dao = new MemberGradeDao();
}
