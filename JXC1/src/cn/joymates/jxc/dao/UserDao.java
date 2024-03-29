package cn.joymates.jxc.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.dao.ecside.DBOperationDAO;
import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;

public class UserDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public void insert(User user) {
		sess.insert("insertUser", user);
	}
	
	/**
	 * 查询当前登录用户及其管理的用户
	 * @param ecRd
	 * @param req
	 * @param user
	 * @return
	 */
	public List findAll(String ecRd, HttpServletRequest req, User user) {
		//Get max quota of current loggin user
		HttpSession session = req.getSession();       
		User loggedUser = (User)session.getAttribute("loggedUser");
		
		int quota = loggedUser.getMaxRoleQuota();
		
		String basesql = "select u.* from user1 u, user_role ur, role_tb r " +
				     " where u.user_code = ur.user_code and ur.role_uuid = r.role_uuid " +
				     " and ((r.role_quota >" + quota + "  or r.role_quota is null) or u.create_person='" + loggedUser.getUserId() +"') union " +
				     " select u1.* from user1 u1 " +
				     " left join user_role r1 on u1.user_code = r1.user_code where r1.user_code is null ";
		DBOperationDAO bdao = DBOperationDAO.getInstance();
		StringBuilder sb = new StringBuilder();
		
		if (user.getUserId() != null && !"".equals(user.getUserId())) {
			sb.append(" and user_id='" + user.getUserId() + "' ");
		}
		
		if (user.getUserName() != null && !"".equals(user.getUserName())) {
			sb.append(" and user_name='" + user.getUserName() + "' ");
		}
		
		String searchsql = "select count(1) from ("+ basesql + ") t where 1=1 " + sb.toString();
		String resultsql = "select t.* from ("+ basesql + ") t   where 1=1 " + sb.toString() + " limit ?, ? ";
		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
	}
	
	/**
	 * 保存用户角色表
	 * @param uid
	 * @param rid
	 */
	public void insertUserRole(String uid, String rid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_code", uid);
		map.put("role_uuid", rid);
		sess.insert("insertUserRole", map);
	}
	
	public User findById(String id) {
		List<Map<String, String>> rlist = sess.selectList("findUserByid", id);
		if (rlist == null || rlist.isEmpty()) {
			return null;
		}
		
		User u = new User();
		List<Role> roleList = new ArrayList<Role>();
		Map<String, String> map1 = rlist.get(0);
		
		try {
			BeanUtils.populate(u, map1);
			
			Role r = null;
			for (Map<String, String> map : rlist) {
				r = new Role();
				r.setRoleUuid(map.get("roleUuid"));
				roleList.add(r);
			}
			
			u.setRoleList(roleList);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	public void update(User user) {
		sess.update("updateUser", user);
	}
	
	/**
	 * 删除用户角色（级联关系）
	 * @param user
	 */
	public void deleteUserRole(User user) {
		sess.delete("deleteUserRoleByUserId", user.getUserCode());
	}
	
	public void updatePassword(User user) {
		sess.update("updateUserPassword", user);
	}
	
	public String findPwdByUserId(String id) {
		return sess.selectOne("findPasswordByUserId", id);
	}
}
