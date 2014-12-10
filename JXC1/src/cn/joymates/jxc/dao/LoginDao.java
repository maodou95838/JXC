package cn.joymates.jxc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.jxc.db.SessionFactoryUtil;
import cn.joymates.jxc.domain.Resource;
import cn.joymates.jxc.domain.Role;
import cn.joymates.jxc.domain.User;
import cn.joymates.jxc.utils.MD5Utils;

/**
 * 登录dao
 * @author Jackie Hou
 *
 */
public class LoginDao {
	
	public Map<String, String> findUserById(User user) {
		SqlSession session = SessionFactoryUtil.getSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user.getUserId());
		map.put("password1", MD5Utils.GetMD5Code(user.getPassword1()));
		
		return session.selectOne("basic.findByIdPassword", map);
		
	}
	
	public List<Resource> searchResourceByCode(User user) {
		SqlSession session = SessionFactoryUtil.getSession();
		return session.selectList("basic.searchResourceByUserCode", user.getUserCode());
	}
	
	public List<Role> findRole(User user) {
		SqlSession session = SessionFactoryUtil.getSession();
		return session.selectList("basic.searchRoleByUserCode", user.getUserCode());
	}
}
