package com.virtualspacex.util.io.database;

import java.sql.Statement;
import java.util.List;

public interface DatabaseDAOInterface<T> {
	
	<Y> List<Y> selectBySql(Statement s, Class<Y> clazz, String sql) throws Exception;

    int updateBySQL(Statement s, String sql) throws Exception;

    int executeBySQL(Statement s, String sql) throws Exception;
}
