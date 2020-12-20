package com.virtualspacex.component.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 * 基本的なデータベース操作Dao
 *
 * @version 0.0.1
 * @since JDK8
 */
public class HibernateDao{

    /**
     * データの挿入
     *
     * @param object 挿入するデータ
     * @return true : 正常に挿入されました<br/>false : 挿入できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> Serializable insertObject(Session session, T object) throws HibernateException {
        return session.save(object);
    }

    /**
     * 一括挿入
     *
     * @param objects     挿入するデータセット
     * @param commitLimit 数個のデータを送信する
     * @param decision    例外戦略
     * @return true : 正常に挿入されました<br/>false : 挿入できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> List<Serializable> insertObjects(Session session, List<T> objects) throws HibernateException {
    	List<Serializable> list = new ArrayList<Serializable>();
    	
        for (Object object : objects) {
        	list.add(session.save(object));
        }
        
        return list;
    }
    
    /**
     * 更新
     *
     * @param object 更新するデータ
     * @return true : 正常に更新されました<br/>false : 更新できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> void updateObject(Session session, T object) throws HibernateException {
        session.update(object);
    }

    /**
     * 一括更新
     *
     * @param objects     更新するデータ
     * @param commitLimit 数個のデータを送信する
     * @param decision    例外戦略
     * @return true : 正常に更新されました<br/>false : 更新できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> void updateObjects(Session session, List<T> objects) throws HibernateException {
        for (Object object : objects) {
            session.update(object);
        }
    }
    
    public <T> void upsertObjects(Session session, List<T> objects) throws HibernateException {
        for (Object object : objects) {
            session.saveOrUpdate(object);
        }
    }

    /**
     * 指定されたIDの行のデータを削除します
     *
     * @param clazz 削除するデータのマッピングされたJavaタイプ
     * @param id    削除するデータのID
     * @return true : 正常に削除されました<br/>false : 削除できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> void deleteObjectById(Session session, Class<T> clazz, int id) throws HibernateException {
        session.delete(session.get(clazz, id));
    }
    
    /**
     * 指定されたIDの行のデータを削除します
     *
     * @param clazz 削除するデータのマッピングされたJavaタイプ
     * @param id    削除するデータのID
     * @return true : 正常に削除されました<br/>false : 削除できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> void deleteObject(Session session, T object) throws HibernateException {
        session.delete(object);
    }
    
    /**
     * 指定されたIDの行のデータを削除します
     *
     * @param clazz 削除するデータのマッピングされたJavaタイプ
     * @param id    削除するデータのID
     * @return true : 正常に削除されました<br/>false : 削除できませんでした<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public <T> void deleteObjects(Session session, List<T> objects) throws HibernateException {
        for (Object object : objects) {
        	session.delete(object);
        }
    }

    /**
     * マッピングクエリ
     *
     * @param clazz マッピングオブジェクト
     * @param sql   クエリSQLステートメント
     * @return 結果セット
     * @since 0.0.1
     */
	@SuppressWarnings("unchecked")
	public <T> List<T> selectBySql(Session session, Class<T> clazz, String sql) throws HibernateException {
        List<T> result = new ArrayList<T>();

        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(clazz);
        result = query.list();

        return result;
    }
	
	@SuppressWarnings("unchecked")
	public <T> List<T> selectBySqlToObject(Session session, Class<T> clazz, String sql) throws HibernateException {
        List<T> result = new ArrayList<T>();

        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.aliasToBean(clazz));
        result = query.list();

        return result;
    }


    /**
     * マッピングクエリがありません
     *
     * @param sql クエリSQLステートメント
     * @return 結果セット
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> selectBySql(Session session, String sql) throws HibernateException {
        List<T> result = null;

        SQLQuery query = session.createSQLQuery(sql);
        result = query.list();

        return result;
    }

    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public int updateBySQL(Session session, String sql) throws HibernateException {
        return session.createSQLQuery(sql).executeUpdate();
    }
    
    /**
     * dmlステートメントを実行する
     *
     * @param sql dml sqlステートメント
     * @return true : 正常に実行<br/>false : 正常に実行されない<br/>errorCode : エラーコード<br/>
     * @since 0.0.1
     */
    public int executeBySQL(Session session, String sql) throws HibernateException {
        return session.createSQLQuery(sql).executeUpdate();
    }
}
