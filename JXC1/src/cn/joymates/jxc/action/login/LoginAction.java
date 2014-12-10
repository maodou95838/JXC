package cn.joymates.jxc.action.login;

import java.util.List;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.LoginService;
import cn.joymates.jxc.service.UserManageService;
import cn.joymates.jxc.utils.ServiceProxyFactory;

/**
 * 登录action
 * 
 * @author Jackie Hou
 *
 */
public class LoginAction extends BaseAction {
	
	public String execute() {
		User user1 = loginService.login(user);
		if (user1 != null) {
			session.setAttribute("loggedUser", user1);

			int quota = user1.getMaxRoleQuota();
			List<Role> roleList = userManageService.getQuotaRoles(quota);
			session.setAttribute("roleList", roleList);
			return SUCCESS;
		}
		
		return "fail";
	}
	
	public String logout() {
		session.removeAttribute("loggedUser");
		session.removeAttribute("roleList");
		return "logout";
	}
	
	private LoginService loginService = ServiceProxyFactory.getInstance(new LoginService());
	
	private UserManageService userManageService = ServiceProxyFactory.getInstance(new UserManageService());
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
