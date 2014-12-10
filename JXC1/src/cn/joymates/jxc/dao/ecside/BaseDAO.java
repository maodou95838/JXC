package cn.joymates.jxc.dao.ecside;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.ecside.common.log.LogHandler;
import org.ecside.easydataaccess.ConnectionUtils;

import cn.joymates.jxc.db.SessionFactoryUtil;


public class BaseDAO {
	private Log logger = LogFactory.getLog(BaseDAO.class);

	protected final void closeConnection(Connection conn) {
		ConnectionUtils.closeAllStatement(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rest,Statement pstmt,Connection conn){
		try {
			close(rest);
			close(pstmt,conn);
		} catch (SQLException e) {
			LogHandler.errorLog(logger, e);
		}
	}

	public void close(Statement pstmt,Connection conn){
		try {
			close(pstmt);
			close(conn);
		} catch (SQLException e) {
			LogHandler.errorLog(logger, e);
		}
	}
	
	public void close(ResultSet rest) throws SQLException{
		if(rest != null) rest.close();
	}
	
	public void close(Statement pstmt) throws SQLException{
		if(pstmt != null) pstmt.close();
	}
	
	public void close(Connection conn){
		if(conn != null) closeConnection(conn);
	}
	
	protected final Connection getConnection(){
		SqlSession sess = SessionFactoryUtil.getSession();
		Connection conn = sess.getConnection();
		ConnectionUtils.initStatementMap(conn);
		return conn;
	}
	
	public static String[] getColumnName(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols = metaData.getColumnCount();
		String[] columnName = new String[cols];
		
		for (int i=0; i<columnName.length; i++){
			columnName[i] = metaData.getColumnName(i+1).toUpperCase();
		}
		return columnName;
	}

//	public static int[] getColumnType(ResultSet resultSet) throws SQLException{
//		ResultSetMetaData metaData = resultSet.getMetaData();
//		int cols = metaData.getColumnCount();
//		int[] columnType=new int[cols];
//		for (int i=0;i<columnType.length;i++){
//			columnType[i]=metaData.getColumnType(i+1);
//		}
//		return columnType;
//	}
	
	public static void buildRecord(ResultSet resultSet, String[] columnName, Map<String, Object> map) throws SQLException{
		for (int i=0; i<columnName.length; i++){
			Object result = null;
			
			if (resultSet.getString(columnName[i]) != null) {
				String type = resultSet.getMetaData().getColumnTypeName(i + 1);
				if (type.indexOf("DECIMAL") >= 0 || type.indexOf("INT") >= 0 //为排序
						|| type.indexOf("DOUBLE") >= 0
						|| type.indexOf("FLOAT") >= 0) {
					result = new BigDecimal(resultSet.getString(columnName[i]));
				} else {
					result = resultSet.getString(columnName[i]);
				}
			}
			
			map.put(columnName[i], result);
			
		}
	}
}