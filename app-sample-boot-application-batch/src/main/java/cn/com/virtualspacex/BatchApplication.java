/*
 * @Author: keiki
 * @Date: 2020-12-24 20:56:56
 * @LastEditTime: 2021-01-01 15:25:27
 * @LastEditors: keiki
 * @Description: 
 */
package cn.com.virtualspacex;

import javax.management.MalformedObjectNameException;

import com.virtualspacex.annotation.BatchExecutor;
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
    ApplicationBoot.run(BatchApplication.class);
  }
}

