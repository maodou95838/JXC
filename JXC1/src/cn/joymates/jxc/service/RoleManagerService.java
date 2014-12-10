package cn.joymates.jxc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.jxc.dao.ResourceDao;
import cn.joymates.jxc.dao.RoleDao;
import cn.joymates.jxc.domain.Resource;
import cn.joymates.jxc.domain.Role;

import com.future.existabc.util.UUIDGener;

/**
 * 角色管理服务类
 * 
 * @author Jackie Hou
 *
 */
public class RoleManagerService {
	
	/**
	 * add role
	 * @param role
	 */
	public void addRole(Role role) {
		role.setRoleUuid(UUIDGener.getUUID());
		role.setCreateTime(new Date());
		role.setIsLogout(Role.NO_LOGOUT);
		
		//默认有最小权
		role.setRoleQuota(Role.MIN_ROLE_QUOTA);
		dao.save(role);
	}
	
	/**
	 * find by condition
	 * @param ecRd
	 * @param req
	 * @return
	 */
	public List<Role> findAll(String ecRd, HttpServletRequest req, Role role) {
		return dao.findAll(ecRd, req, role);
	}
	
	public Role findById(String id) {
		return dao.findById(id);
	}
	
	public void modifyRole(Role role) {
		String logout = role.getIsLogout(); 
		if (logout == "false") {
			role.setIsLogout(Role.NO_LOGOUT);
			role.setLogoutReason("");
		} else {
			role.setIsLogout(Role.LOGOUT);
		}
		dao.update(role);
	}
	
	public Map<String, List<Resource>> getAuthData(String roleid) {
		Map<String, List<Resource>> resultMap = new HashMap<String, List<Resource>>();
		resourceDao =  new ResourceDao();
		resultMap.put("all", resourceDao.selectAll());
		resultMap.put("mine", resourceDao.selectResourceByRoleId(roleid));
		
		return resultMap;
	}
	
	/**
	 * 授权保存
	 * @param roleId
	 * @param resList
	 */
	public void saveAuth(String roleId, List<String> resList) {
		resourceDao =  new ResourceDao();
		resourceDao.deleteRolePrivilege(roleId);
		
		//get parentId
		Set<String> parentIdSet = new HashSet<String>();
		for (String res : resList) {
			String parentId = res.substring(0, (res.length() - 3));
			parentIdSet.add(parentId);
		}
		
		resList.addAll(parentIdSet);
		
		for (String res : resList) {
			resourceDao.insertRolePrivilege(roleId, res);
		}
	}
	
	private RoleDao dao ;
	
	private ResourceDao resourceDao;
	
	public RoleManagerService() {
		setDao(new RoleDao());
	}

	public void setDao(RoleDao dao) {
		this.dao = dao;
	}
	
	
}
