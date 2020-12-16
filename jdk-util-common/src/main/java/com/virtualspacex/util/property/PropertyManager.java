/*
 * @Author: your name
 * @Date: 2020-10-11 14:52:32
 * @LastEditTime: 2020-10-11 15:03:23
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /batch-container/src/main/java/com/virtualspacex/batch/container/PropertyContainer.java
 */
package com.virtualspacex.util.property;

import java.util.*;

public class PropertyManager {
    
    private static List<PropertiesReaderInterface> readerList = new ArrayList<>();

    public static String getProperty(String key){
        String value = null;

        for(PropertiesReaderInterface reader : readerList){
            if(null != reader){
                value = reader.getProperty(key);
                if(null != value){
                    break;
                }
            }
        }

        return value;
    }

    public static void add(PropertiesReaderInterface reader){
        readerList.add(reader);
    }
}
