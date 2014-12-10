package cn.joymates.jxc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;



/**
 * 获取数据库连接工具类
 * 
 * @author Jackie Hou
 *
 */
public class DbUtils {
	public static Log logger = LogFactory.getLog(DbUtils.class);
	
	private static DbUtils dbUtils = new DbUtils();
	
	private DbUtils() {
		
	}
	
	public static DbUtils getInstance() {
		return dbUtils;
	}
	
	/**
	 * 获取连接池连接数信息
	 */
	public static String getConnMsg(){
		SnapshotIF snapshot = null;
		StringBuffer str = new StringBuffer();
		
		try {
			snapshot = ProxoolFacade.getSnapshot("MySql", true);
			int curActiveCount=snapshot.getActiveConnectionCount();			
			int availableCount=snapshot.getAvailableConnectionCount();		
			int maxCount=snapshot.getMaximumConnectionCount() ;				
			str.append("活动连接数")
				.append(curActiveCount)
				.append("	可得到的连接数")
				.append(availableCount)
				.append("	允许最大连接数")
				.append(maxCount)
				.append("(max)");
		} catch (ProxoolException e) {
			//e.printStackTrace();
		} 
		
		return str.toString();
	}
	
	/**
	 * 手动获取数据库连接
	 * 此方法用于手动获取数据库连接，没有与第三方的工具集成
	 * @return Connection
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			conn = DriverManager.getConnection("proxool.MySql5");
		} catch (ClassNotFoundException e) {
			logger.error("数据库连接池驱动未找到！");
		} catch (SQLException e) {
			logger.error("数据库连接获取失败");
			throw e;
		}
		return conn;
	}
	
}
