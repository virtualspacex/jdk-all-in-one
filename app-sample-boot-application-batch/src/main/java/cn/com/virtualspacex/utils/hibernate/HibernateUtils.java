package cn.com.virtualspacex.utils.hibernate;

import java.io.File;

import com.virtualspacex.batch.constant.Constants;
import com.virtualspacex.util.database.DatabaseUtilInterface;
import com.virtualspacex.util.file.FileUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernateツールクラス
 *
 * @version 0.0.1
 * @since JDK8
 */
public class HibernateUtils implements DatabaseUtilInterface<Session, Transaction>{

    private SessionFactory sessionFactory = null;
    
    private final String DEFAULT_CONFIG_FILENAME = FileUtil.getClassPath() 
    		+ File.separator 
    		+ Constants.CONFIG_FILE_PATH 
    		+ File.separator 
    		+ Constants.HIBERNATE_CONFIG_FILE;
    
    public HibernateUtils(String configFilename) throws HibernateException {
        Configuration configuration = new Configuration();
        String filename = ("".equals(configFilename) || configFilename == null ) ? DEFAULT_CONFIG_FILENAME : configFilename;  
        configuration.configure(new File(filename));
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

	@Override
	public Session connect() throws Exception {
        Session session = null;

        if (sessionFactory != null) {
            session = sessionFactory.openSession();
        }

        return session;
	}

	@Override
	public void disConnect(Session session) throws Exception {
        if (session != null && session.isOpen()) {
            session.close();
        }
	}

	@Override
	public Transaction transaction(Session c) throws Exception {
		return c.beginTransaction();
	}

	@Override
	public void commit(Transaction t) throws Exception {
		t.commit();
	}

	@Override
	public void rollback(Transaction t) throws Exception {
		t.rollback();
	}

	@Override
	public Class<Session> getExecutorType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<Transaction> getTransactionType() {
		// TODO Auto-generated method stub
		return null;
	}
}
