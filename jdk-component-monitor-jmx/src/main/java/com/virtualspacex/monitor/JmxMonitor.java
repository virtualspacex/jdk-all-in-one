/*
 * @Author: keiki
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2021-01-01 15:39:04
 * @LastEditors: keiki
 * @Description: 
 */
package com.virtualspacex.monitor;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import com.virtualspacex.middleware.SYS;
import com.virtualspacex.middleware.handler.EventHandlerInterface;
import com.virtualspacex.middleware.listener.EventCenter;


public class JmxMonitor {
	
	private static MBeanServer server;
	
	private static JMXConnectorServer cs;
	
	public static void startJmxMonitor(int port) throws Exception{
		
		EventCenter.register(SYS.OFF, listen());
		
		server = ManagementFactory.getPlatformMBeanServer();
        /**
         * JMXConnectorServer service 
         */
        //这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer 
        LocateRegistry.createRegistry(port);
         
        //构造JMXServiceURL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi");
        
        //创建JMXConnectorServer
        cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);  
        
        //启动
        cs.start();
	}
	
	public static void registMBean(Object bean) throws Exception{
		server.registerMBean(bean, new ObjectName("jmxBean:name=" + bean.getClass().getSimpleName()));
	}
	
	
	public static EventHandlerInterface listen() {
		return () -> {
			try {
				cs.stop();
			} catch (Exception e) {
				//do nothing
			}
		};
	}
}
