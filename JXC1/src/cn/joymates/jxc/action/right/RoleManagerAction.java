package cn.joymates.jxc.action.right;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.joymates.jxc.action.BaseAction;
import cn.joymates.jxc.domain.Resource;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.service.RoleManagerService;
import cn.joymates.jxc.utils.ServiceProxyFactory;
/**
 * 角色管理action
 * 
 * @author Jackie Hou
 *
 */
public class RoleManagerAction extends BaseAction {
	/**
	 * 角色添加
	 * @return
	 */
	public String add() {
		User user = (User)session.getAttribute("loggedUser");
		role.setCreatePerson(user.getUserId());
		roleManagerService.addRole(role);
		return "find";
	}
	
	public String find() {
		roleList = roleManagerService.findAll(ec_rd, req, role);
		req.setAttribute("logout", Role.LOGOUT_MAP);
		return "find";
	}
	//根据编号查找
	public String showModifyPage() {
		role = roleManagerService.findById(role.getRoleUuid());
		String logout = role.getIsLogout();
		
		role.setIsLogout("true");
		if (logout.equals(Role.NO_LOGOUT)) {
			role.setIsLogout("false");
		}
		return "modifyUI";
	}
	
	public String modify() {
		roleManagerService.modifyRole(role);
		return "find";
	}
	
	/**
	 * 授权页
	 * @return
	 */
	public String authUI() {
		Map<String, List<Resource>> rmap = roleManagerService.getAuthData(role.getRoleUuid());
		req.setAttribute("resourceList", rmap.get("all"));
		
		List<Resource> mineResList = rmap.get("mine");
		resourceIds = new ArrayList<String>();
		for (Resource r : mineResList) {
			resourceIds.add(r.getResourceId());
		}
		return "resourceList";
	}
	
	/**
	 * 授权aciton
	 * @return
	 */
	public String auth() {
		roleManagerService.saveAuth(role.getRoleUuid(), resourceIds);
		return "find";
	}
	
	private RoleManagerService roleManagerService = ServiceProxyFactory.getInstance(new RoleManagerService());
	
	private Role role;
	
	private List roleList;
	
	private List<String> resourceIds; 

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
