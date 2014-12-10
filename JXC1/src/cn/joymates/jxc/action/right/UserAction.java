package cn.joymates.jxc.action.right;

import java.util.ArrayList;
import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.UserManageService;
import cn.joymates.jxc.utils.MD5Utils;
import cn.joymates.jxc.utils.ServiceProxyFactory;

/**
 * 用户管理action
 * 
 * @author Jackie Hou
 *
 */
public class UserAction extends BaseAction {
	public String find() {
		userList = userManageService.findAll(ec_rd, req, user);
		req.setAttribute("logout", User.LOGOUT_MAP);
		return "find";
	}
	
	public String add() {
		User u = (User)session.getAttribute("loggedUser");
		user.setCreatePerson(u.getUserId());
		
		//construct user data struct.
		Role r = new Role();
		for (String s :roleUuids) {
			r.setRoleUuid(s);
		}
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(r);
		user.setRoleList(roleList);
		userManageService.addUser(user);
		return "find";
	}
	
	public String showModifyPage() {
		user = userManageService.findById(user.getUserCode());
		List<Role> rlist = user.getRoleList();
		List<String> rslist = new ArrayList<String>(rlist.size());
		
		for (Role r : rlist) {
			rslist.add(r.getRoleUuid());
		}
		
		roleUuids = rslist.toArray(new String[rslist.size()]);
		
		//回显注销
		String logout = user.getIsLogout();
		user.setIsLogout("true");
		if (logout.equals(User.NO_LOGOUT)) {
			user.setIsLogout("false");
		}
		return "modifyUI";
	}
	
	public String modify() {
		//自己不能给自己分别角色
		if (roleUuids != null) {
			Role r = new Role();

			for (String s :roleUuids) {
				r.setRoleUuid(s);
			}
			
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(r);
			user.setRoleList(roleList);
		}
		
		userManageService.modify(user);
		return "find";
	}
	
	public String pwdModify() {
		User sessU = (User)session.getAttribute("loggedUser");
		String[] pwds = user.getPassword1().split(",");
		
		if (!pwds[0].trim().equals(pwds[1].trim())) {
			user = new User();
			user.setUserCode(sessU.getUserCode());
			user.setPassword1(MD5Utils.GetMD5Code(pwds[1].trim()));
			userManageService.modifyPassword(user);
		}
		
		req.setAttribute("flag", "1");
		return "pwdOver";
	}
	
	UserManageService userManageService = ServiceProxyFactory.getInstance(new UserManageService());
	
	private User user;
	
	private String[] roleUuids;
	
	private List userList;
	
	public String[] getRoleUuids() {
		return roleUuids;
	}

	public void setRoleUuids(String[] roleUuids) {
		this.roleUuids = roleUuids;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
