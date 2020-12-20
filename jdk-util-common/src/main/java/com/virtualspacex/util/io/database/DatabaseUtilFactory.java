package com.virtualspacex.util.io.database;

import java.lang.reflect.Method;

public class DatabaseUtilFactory {
	private static DatabaseUtilInterface<Object, Object> dbuf;
	
	public static void register(DatabaseUtilInterface<Object, Object> databaseUitl) {
		dbuf = databaseUitl;
	}
	
	public static Object connect() throws Exception{
		return dbuf.connect();
	}
	
	public static void disConnect(Object c) throws Exception{
		dbuf.disConnect(c);
	}
	
	public static Object transaction(Object c) throws Exception{
		return dbuf.transaction(c);
	}
	
	public static void commit(Object t) throws Exception{
		Method commitMethod = t.getClass().getDeclaredMethod("commit");
		commitMethod.invoke(t);
	}
	
	public static void rollback(Object t) throws Exception{
		Method commitMethod = t.getClass().getDeclaredMethod("rollback");
		commitMethod.invoke(t);
	}
	
	public static Class<?> getExecutorType() {
		return dbuf.getExecutorType();
	}
	
	public static Class<?> getTransactionType() {
		return dbuf.getTransactionType();
	}
	
}
