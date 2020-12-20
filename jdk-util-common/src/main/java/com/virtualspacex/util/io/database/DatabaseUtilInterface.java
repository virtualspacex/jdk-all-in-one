package com.virtualspacex.util.io.database;

public interface DatabaseUtilInterface<E, T> {
	
	E connect() throws Exception;
	
	void disConnect(E c) throws Exception;
	
	T transaction(E c) throws Exception;
	
	void commit(T t) throws Exception;
	
	void rollback(T t) throws Exception;
	
	Class<E> getExecutorType();
	
	Class<T> getTransactionType();
	
}
