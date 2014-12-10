package cn.joymates.jxc.dao.ecside;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ecside.common.log.LogHandler;
import org.ecside.easydataaccess.ConnectionUtils;
import org.ecside.table.limit.FilterSet;
import org.ecside.table.limit.Limit;
import org.ecside.table.limit.Sort;
import org.ecside.util.RequestUtil;

/**
 * ecside数据分页
 * 
 * @author Jackie Hou
 */
public class DBOperationDAO extends BaseDAO {
	private Log logger = LogFactory.getLog(BaseDAO.class);
	private static DBOperationDAO instance = null;
	
	private DBOperationDAO() {
	}

	public synchronized static DBOperationDAO getInstance() {
		if (instance == null) {
			instance = new DBOperationDAO();
		}
		return instance;
	}
	
	/**
	 * 获取分页的总条数
	 * @param CountSql
	 * @return number
	 */
	public int getAllInfoNumber(String CountSql) {
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(CountSql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		int number = -1;
		
		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn, bufSql.toString());
			rest = pstmt.executeQuery();
			
			if (rest!=null && rest.next()) {
				number=rest.getInt(1);
			}
			
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			number=-1;
		} finally {
			try {
				close(rest);
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return number;
	}
	
		
	/**
	 * 大数据量详细结果集  获取数据进行分页
	 * param startRow 开始数   endRow结束数  
	 * return list
	 */
	public List getInfomation(int startRow, int endRow, 
			Map sortValueMap, Map filterPropertyMap, String ResultSql){
		StringBuffer bufSql = new StringBuffer();
		int size = endRow - startRow;
		bufSql.append(ResultSql);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		List list = null;
		
		try {
			int end = size + startRow;
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn, bufSql.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, end);
			rest = pstmt.executeQuery();
			
			String[] columnName = getColumnName(rest);
			list = new ArrayList();
			Map Info = null;
			
			while (rest!=null && rest.next()) {
				Info = new HashMap();
				buildRecord(rest, columnName, Info);
				list.add(Info);
			}
			Info=null;
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			list = null;
		} finally {
			try {
				close(rest);
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List getEcsideList(String ec_rd, String searchsql, String resultsql,
			HttpServletRequest req) {
		int ecRd = 12;
		if (ec_rd != null && !ec_rd.equals("0")) {
			ecRd = Integer.parseInt(ec_rd);
		}
		
		//get count
		int totalRows = RequestUtil.getTotalRowsFromRequest(req);
		if (totalRows < 0) {
			totalRows = getAllInfoNumber(searchsql);
		}
		
		Limit limit = RequestUtil.getLimit(req, totalRows, ecRd);
		int offset = 0;
		int[] rowStartEnd = new int[] { 
				limit.getRowStart() + offset,
				limit.getRowEnd() + offset };
		Sort sort = limit.getSort();
		
		Map sortValueMap = sort.getSortValueMap(); 
		FilterSet filterSet = limit.getFilterSet();
		Map filterPropertyMap = filterSet.getPropertyValueMap();
		
		List rslist = getInfomation(rowStartEnd[0], rowStartEnd[1],
				sortValueMap, filterPropertyMap, resultsql);
		return rslist;
	}
}