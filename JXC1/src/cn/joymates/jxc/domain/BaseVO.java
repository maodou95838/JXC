package cn.joymates.jxc.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 实体公共类
 * 
 * @author Jackie Hou
 *
 */
public class BaseVO {

	/**
     * 注销
     */
    public final static String LOGOUT = "1";
    public final static String NOT_LOGOUT = "0";
    public static Map<String, String> LOGOUT_MAP;
    
    static {
    	LOGOUT_MAP = new HashMap<String, String>();
    	LOGOUT_MAP.put(LOGOUT, "注销");
    	LOGOUT_MAP.put(NOT_LOGOUT, "未注销");
    	
    	LOGOUT_MAP.put("true", LOGOUT);
    	LOGOUT_MAP.put("false", NOT_LOGOUT);
    }
}
