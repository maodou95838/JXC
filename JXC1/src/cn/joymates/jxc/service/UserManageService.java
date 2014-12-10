package cn.joymates.jxc.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.RoleDao;
import cn.joymates.jxc.dao.UserDao;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.utils.MD5Utils;

import com.future.existabc.util.UUIDGener;

public class UserManageService {
	private UserDao dao = new UserDao();
	
	public void addUser(User user) {
		//默认密码123456
		user.setPassword1(MD5Utils.GetMD5Code("123456"));
		user.setIsLogout(User.NO_LOGOUT);
		user.setCreateTime(new Date());
		
		user.setUserCode(UUIDGener.getUUID());
		dao.insert(user);
		
		List<Role> roles = user.getRoleList();
		for (Role r : roles) {
			dao.insertUserRole(user.getUserCode(), r.getRoleUuid());
		}
	}
	
	public List findAll(String ecRd, HttpServletRequest req, User user) {
		return dao.findAll(ecRd, req, user);
	}
	
	public List<Role> getQuotaRoles(int quota) {
		RoleDao roledao = new RoleDao();
		return roledao.findByQuota(quota);
	}
	
	public User findById(String id) {
		return dao.findById(id);
		 
	}
	
	public void modify(User user) {
		String logout = user.getIsLogout();
		
		if (logout.equals("true")) {
			user.setIsLogout(User.LOGOUT);
		} else {
			user.setIsLogout(User.NO_LOGOUT);
			user.setLogoutReason("");
		}
		
		dao.update(user);
		List<Role> roles = user.getRoleList();
		
		if (roles == null) {
			return;
		}
		dao.deleteUserRole(user);
		
		for (Role r : roles) {
			dao.insertUserRole(user.getUserCode(), r.getRoleUuid());
		}
	}
	
	public void modifyPassword(User user) {
		String oldPwd = dao.findPwdByUserId(user.getUserCode());
		
		if(!oldPwd.equals(MD5Utils.GetMD5Code(user.getPassword1()))) {
			dao.updatePassword(user);
		}
	}
	
}
