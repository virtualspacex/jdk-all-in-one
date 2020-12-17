package cn.com.virtualspacex;

import java.util.ArrayList;
import java.util.List;

import javax.management.MalformedObjectNameException;

import com.virtualspacex.annotation.BatchExecutor;
import com.virtualspacex.annotation.EnableJMXMonitor;
import com.virtualspacex.boot.ApplicationBoot;

import cn.com.virtualspacex.tasks.SaleInfosSerialConfirmTask;

/**
 * 程序入口类
 *
 * @author
 * @date 2020/11/09
 */
@EnableJMXMonitor(port=7788)
@BatchExecutor(SaleInfosSerialConfirmTask.class)
public class BatchApplication {
    /**
     * main方法
     * @throws MalformedObjectNameException 
     */
    public static void main(String[] args) {
    	A<? super BatchApplication> ab = new A<BatchApplication>();
    	ab.add(new BatchApplication());
    	List<Object> abc = new ArrayList<Object>();
    	abc.add(new BatchApplication());
    	Class<? extends Object> a = BatchApplication.class;
		ApplicationBoot.run(BatchApplication.class);
	}
}

class A<T extends Object>{
	T t;
	public void add(T tt) {
		t = tt;
	}
}
