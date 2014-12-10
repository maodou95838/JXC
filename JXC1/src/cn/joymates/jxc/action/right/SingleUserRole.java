package cn.joymates.jxc.action.right;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;

/**
 * 单一用户、角色
 * 
 * @author Jackie Hou
 *
 */
public class SingleUserRole {
	/**
	 * 查询已经存在的用户Id
	 * @param userId
	 * @param userCode
	 * @return
	 */
	public int getExistUserCount(String userId, String userCode) {
		if (StringUtils.isEmpty(userId) || StringUtils.isBlank(userId)) {
			return 99;
		}
		
		SqlSession sess = SessionFactoryUtil.getSession();
		User u = new User();
		u.setUserCode(userCode);
		u.setUserId(userId);
		
		int count =  sess.selectOne("basic.findUserByUserId", u);
		SessionFactoryUtil.closeSession();
		return count;
	}
	
	/**
	 * 查询已经存在的角色
	 * @param roleName
	 * @param roleUuid
	 * @return
	 */
	public int getExistRoleCount(String roleName, String roleUuid) {
		if (StringUtils.isEmpty(roleName) || StringUtils.isBlank(roleName)) {
			return 99;
		}
		
		SqlSession sess = SessionFactoryUtil.getSession();
		Role r = new Role();
		r.setRoleName(roleName);
		r.setRoleUuid(roleUuid);
		
		int count =  sess.selectOne("basic.findRoleByRoleName", r);
		SessionFactoryUtil.closeSession();
		return count;
	}
}
