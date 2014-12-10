package cn.joymates.jxc.action.member;

import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.MemberGrade;
import cn.joymates.jxc.service.MemberGradeService;
import cn.joymates.jxc.utils.ServiceProxyFactory;

/**
 * 会员等级aciton
 * @author Jackie Hou
 *
 */
public class MemberLevelAction extends BaseAction {
	public String find() {
		gradeList = memberGradeService.find(ec_rd, req, grade);
		req.setAttribute("logoutMap", MemberGrade.LOGOUT_MAP);
		return "find";
	}
	
	public String add() {
		memberGradeService.save(grade);
		return "find";
	}
	
	public String showModifyUI() {
		grade = memberGradeService.findById(grade);
		return "modifyUi";
	}
	
	public String modify() {
		memberGradeService.update(grade);
		return "find";
	}
	
	private MemberGradeService memberGradeService = ServiceProxyFactory.getInstance(new MemberGradeService());
	
	private List gradeList;
	
	private MemberGrade grade;

	public MemberGrade getGrade() {
		return grade;
	}

	public void setGrade(MemberGrade grade) {
		this.grade = grade;
	}

	public List getGradeList() {
		return gradeList;
	}

	public void setGradeList(List gradeList) {
		this.gradeList = gradeList;
	}
	
}
