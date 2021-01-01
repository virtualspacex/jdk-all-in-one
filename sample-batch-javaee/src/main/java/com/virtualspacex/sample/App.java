/*
 * @Author: keiki
 * @Date: 2020-12-30 16:14:51
 * @LastEditTime: 2020-12-30 18:23:43
 * @LastEditors: keiki
 * @Description: 
 */
package com.virtualspacex.sample;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;

import com.virtualspacex.sample.phonebilling.tools.CallRecordLogCreator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CallRecordLogCreator logtool = new CallRecordLogCreator();
        logtool.writeToFile("log1.txt");
        
        System.out.println("Hello World!");
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long execID = jobOperator.start("phonebilling", null);
    }
}
