/*
 * @Author: your name
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2020-12-20 16:25:52
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /jdk-all-in-one/jdk-util-common/src/main/java/com/virtualspacex/util/io/database/jdbc/JdbcDao.java
 */
package com.virtualspacex.util.io.database.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 基本的なデータベース操作Dao
 *
 * @version 0.0.1
 * @since JDK8
 */
// implements DatabaseDAOInterface<Statement>
public class JdbcDao {

    public <T> List<T> query(Statement s, String sql, Class<T> clazz)
            throws InstantiationException, IllegalAccessException, SQLException {
		return JdbcUtils.ResultSetToObjectList(JdbcUtils.query(s, sql), clazz);
    }

    public ResultSet query(Statement s, String sql)
            throws InstantiationException, IllegalAccessException, SQLException {
        return JdbcUtils.query(s, sql);
    }

    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public int update(Statement s, String sql) throws Exception {
        return JdbcUtils.update(s, sql);
    }
    
    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public boolean execute(Statement s, String sql) throws Exception {
    	return JdbcUtils.execute(s, sql);
    }
}
