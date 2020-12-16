package com.virtualspacex.util.database.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.virtualspacex.util.common.CommonUtil;
import com.virtualspacex.util.database.DatabaseDAOInterface;


/**
 * 基本的なデータベース操作Dao
 *
 * @version 0.0.1
 * @since JDK8
 */
public class JdbcDao implements DatabaseDAOInterface<Statement>{
	
	public <T> List<T> selectBySql(Statement s, Class<T> clazz, String sql) throws Exception {
		List<Map<String, Object>> datas = selectBySql(s, sql);
		
		List<T> ret = new ArrayList<T>();
		for(Map<String, Object> m : datas) {
			ret.add(CommonUtil.mapToObject(m, clazz));
		}

        return ret;
    }

    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public int updateBySQL(Statement s, String sql) throws Exception {
        return s.executeUpdate(sql);
    }
    
    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public int executeBySQL(Statement s, String sql) throws Exception {
    	s.execute(sql);
        return s.getUpdateCount();
    }
    

    /**
     * マッピングクエリがありません
     *
     * @param sql クエリSQLステートメント
     * @return 結果セット
     * @since 0.0.1
     */
	private List<Map<String, Object>> selectBySql(Statement s, String sql) throws Exception {

		// 构造泛型结果集
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
    	try(ResultSet rs = s.executeQuery(sql);){
    		ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
    		Map<String, Object> data = null;

    		// 循环结果集
    		while (rs.next()) {
    			data = new HashMap<String, Object>();
    			// 每循环一条将列名和列值存入Map
    			for (int i = 1; i < columnCount; i++) {
    				data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd.getColumnLabel(i)));
    			}
    			// 将整条数据的Map存入到List中
    			datas.add(data);
    		}
    	}

        return datas;
    }
}
