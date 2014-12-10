package cn.joymates.jxc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Resource;

/**
 * 资源dao
 * @author Jackie Hou
 *
 */
public class ResourceDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public List<Resource> selectAll() {
		return sess.selectList("basic.selectAllResource");
	}
	
	public List<Resource> selectResourceByRoleId(String roleid) {
		return sess.selectList("basic.selectResourceByRoleid", roleid);
	}
	
	public void insertRolePrivilege(String roleId, String resourceId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("resourceId", resourceId);
		sess.insert("insertRolePrivilege", map);
	}
	
	public void deleteRolePrivilege(String roleId) {
		sess.delete("deleteRolePrivilege", roleId);
	}
}
