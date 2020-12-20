/*
 * @Author: your name
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2020-12-20 17:55:33
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /jdk-all-in-one/jdk-util-common/src/main/java/com/virtualspacex/util/io/database/jdbc/JdbcUtils.java
 */
package com.virtualspacex.util.io.database.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.virtualspacex.util.common.CommonUtil;
import com.virtualspacex.util.common.StringUtils;

/**
 * Hibernateツールクラス
 *
 * @version 0.0.1
 * @since JDK8
 */
//implements DatabaseUtilInterface<Statement, Connection>
public class JdbcUtils  {

	private static DataSource dataSource = null;

	private static JdbcMetaData jdbcMetaData;

	public static void from(JdbcMetaData metaData) throws ClassNotFoundException {
		jdbcMetaData = metaData;
		Class.forName(metaData.driver);
	}

	public static void from(DataSource connectionPool){
		dataSource = connectionPool;
	}
	



	/**
	* @description: 创建与数据库的链接
	* @param {*}
	* @return {*}
	*/
	public static Connection getConnection() throws SQLException {
		if(null == dataSource){
			return DriverManager.getConnection(jdbcMetaData.url, jdbcMetaData.username, jdbcMetaData.password);
		} else {
			return dataSource.getConnection();
		}
	}

	/**
	 * @description: 
	* @param {Connection} con
	* @return {*}
	*/
	public static void releaseConnect(Connection con) throws SQLException {
		if(null != con && !(con.isClosed())){
			con.close();
		}
	}


	

	
	/**
	* @description: 设置事务是否自动提交。
	* @param {Connection} con
	* @param {boolean} autoCommit
	* @return {*}
	*/
	public static void setAutoCommit(Connection con, boolean autoCommit) throws SQLException {
		con.setAutoCommit(autoCommit);
	}

	/**
	* @description: 在链接上提交事务。
	* @param {Connection} con
	* @return {*}
	*/
	public static void commit(Connection con) throws SQLException {
		con.commit();
	}

	/**
	 * @description: 在此链接上回滚事务。
	 * @param {Connection} con
	 * @return {*}
	 * @throws SQLException
	 */
	public static void rollback(Connection con) throws SQLException {
		con.rollback();
	}

	public static Savepoint setSavepoint(Connection con, String savepoint) throws SQLException {
		if(StringUtils.isBlank(savepoint)){
			return con.setSavepoint();
		} else {
			return con.setSavepoint(savepoint);
		}
	}

	public static void releaseSavepoint(Connection con, Savepoint savepoint) throws SQLException {
		con.releaseSavepoint(savepoint);
	}

	public static void rollback(Connection con, Savepoint savepoint) throws SQLException {
		con.rollback(savepoint);
	}




	/**
	* @description: 创建向数据库发送sql的statement对象。
	* @param {Connection} con
	* @return {*}
	*/
	public static Statement getStatement(Connection con) throws SQLException {
		return con.createStatement();
	}

	/**
	* @description: 创建向数据库发送预编译sql的PrepareSatement对象。
	* @param {Connection} con
	* @param {String} sql
	* @return {*}
	*/
	public static PreparedStatement getPrepareStatement(Connection con, String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	/**
	* @description: 创建执行存储过程的callableStatement对象。
	* @param {Connection} con
	* @param {String} sql
	* @return {*}
	*/
	public static CallableStatement getCallableStatement(Connection con, String sql) throws SQLException {
		return con.prepareCall(sql);
	}




	/**
	 * @description: 用于向数据发送查询语句。
	* @param {Statement} stm
	* @param {String} sql
	* @return {*}
	*/
	public static ResultSet query(Statement stm, String sql) throws SQLException {
		return stm.executeQuery(sql);
	}

	/**
	 * @description: 用于向数据库发送insert、update或delete语句。
	* @param {Statement} stm
	* @param {String} sql
	* @return {*}
	*/
	public static int update(Statement stm, String sql) throws SQLException {
		return stm.executeUpdate(sql);
	}
	
	/**
	 * @description: 用于向数据库发送任意sql语句。
	* @param {Statement} stm
	* @param {String} sql
	* @return {*}
	*/
	public static boolean execute(Statement stm, String sql) throws SQLException {
		return stm.execute(sql);
	}







	public static ResultSet getGeneratedKeys(Statement stm) throws SQLException {
		return stm.getGeneratedKeys();
	}

	/**
  * @description: 通过设置row fetch size，可以改变每次和数据库交互，提取出来的记录行总数。
  * @param {Statement} stm
  * @param {int} size
  * @return {*}
  */
	public static void setFetchSize(Statement stm, int size) throws SQLException {
		stm.setFetchSize(size);
	}

	/**
	* @description: 在模式匹配中使用：
					当逃逸字符设置开启时， “\%” and “\_”字符就是代表 “%” and “_” 字符本身，
					当逃逸字符设置关闭时， “%” and “_”作为通配符使用。
					当不在模式匹配中使用：
					如果  “\%” or “\_”  在模式匹配之外使用的时候，它们就是 “\%” and “\_”字符串本身，而不是 “%” and “_”字符
	* @param {Statement} stm
	* @param {boolean} enable
	* @return {*}
	*/
	public static void setEscapeProcessing(Statement stm, boolean enable) throws SQLException {
		stm.setEscapeProcessing(enable);
	}




	/**
	 * @description: 将查询结果转换为Map列表
	* @param {ResultSet} rs
	* @return {*}
	*/
	public static List<Map<String, Object>> ResultSetToMapList(ResultSet rs) throws SQLException {
		
		// 构造泛型结果集
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
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

		return datas;
	} 

	/**
	 * @description:
	 * @param {*}
	 * @return {*}
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> List<T> ResultSetToObjectList(ResultSet result, Class<T> clazz)
			throws SQLException, InstantiationException, IllegalAccessException {

		List<T> ret = new ArrayList<T>();
		List<Map<String, Object>> datas = ResultSetToMapList(result);
		
		for(Map<String, Object> m : datas) {
			ret.add(CommonUtil.mapToObject(m, clazz));
		}

        return ret;
	}

}
