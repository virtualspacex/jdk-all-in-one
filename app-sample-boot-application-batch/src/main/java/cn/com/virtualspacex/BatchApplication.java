package cn.com.virtualspacex;

import java.util.ServiceLoader;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
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
// @EnableJMXMonitor(port = 7788)
@BatchExecutor(SaleInfosSerialConfirmTask.class)
public class BatchApplication {
  /**
   * main方法
   * 
   * @throws MalformedObjectNameException
   */
  public static void main(String[] args) {
    // ServiceLoader.load(BatchApplication.class);
    ApplicationBoot.run(BatchApplication.class);
  }
}

