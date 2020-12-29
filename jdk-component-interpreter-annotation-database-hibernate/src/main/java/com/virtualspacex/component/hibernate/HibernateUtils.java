/*
 * @Author: your name
 * @Date: 2020-12-21 10:39:58
 * @LastEditTime: 2020-12-29 11:00:42
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \jdk-all-in-one\jdk-component-interpreter-annotation-database-hibernate\src\main\java\com\virtualspacex\component\hibernate\HibernateUtils.java
 */
package com.virtualspacex.component.hibernate;

import java.io.File;

import com.virtualspacex.util.io.file.FileUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernateツールクラス
 *
 * @version 0.0.1
 * @since JDK8
 */
public class HibernateUtils{

    private static SessionFactory sessionFactory = null;
    
    private final String DEFAULT_CONFIG_FILENAME = FileUtil.getClassPath() 
    		+ File.separator 
    		+ "config" 
    		+ File.separator 
    		+ "hibernate.cfg.xml";
    
    public HibernateUtils(String configFilename) throws HibernateException {
        Configuration configuration = new Configuration();
        String filename = ("".equals(configFilename) || configFilename == null ) ? DEFAULT_CONFIG_FILENAME : configFilename;  
        configuration.configure(new File(filename));
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

	public static Session openSession() throws Exception {
        Session session = null;

        if (sessionFactory != null) {
            session = sessionFactory.openSession();
        } else {
            throw new Exception("failed to init org.hibernate.SessionFactory!");
        }

        return session;
	}

	public static void closeSession(Session session) throws Exception {
        if (session != null && session.isOpen()) {
            session.close();
        }
	}
}
