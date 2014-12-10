package cn.joymates.jxc.utils;

import java.util.List;
import java.util.Map;

import cn.joymates.jxc.domain.Category;
import cn.joymates.jxc.domain.Goods;

/**
 * jxc缓存工具。 所有业务中只允许读取，不允许被写入！
 * 
 * @author Jackie Hou
 *
 */
public final class CommonDataCache {
	/**
	 * 商品分类
	 * key : 商品分类id
	 * value ： 商品分类名称
	 */
	public static Map<String, Category> CATEGORY ;
	
	/**
	 * 商品
	 * key ： 商品分类id
	 * value ： 商品list
	 */
	public static Map<String, List<Goods>> GOODS;
	
	/**
	 * 会员等级
	 * key : 等级id
	 * value : 等级名称
	 */
	public static Map<String, String> MEMBER_GRADE;
	
}
