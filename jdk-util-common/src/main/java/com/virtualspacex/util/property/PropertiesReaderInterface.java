/*
 * @fileName: AbstractPropertiesAdapter.java
 * @Description: 抽象的な配置ファイル読み込み用クラスのファイル
 * @date: 2020/3/17
 * @Copyright: Copyright (c) 2020,Fuji Electric (Hangzhou) Software Co., Ltd. All rights reserved.
 */
package com.virtualspacex.util.property;

import java.io.IOException;

/**
 * @ClassName: AbstractPropertiesAdapter
 * @Description: 抽象的な配置ファイル読み込み用クラス
 * @author: wei-yushan
 */
public interface PropertiesReaderInterface {
    
    /**   
     * @Title: loadPropertyFile   
     * @Description: プロパティファイルの読み込み
     * @param: propertyFilePath(配置ファイルの位置) 
     * @return: boolean(true:読み取り成功,false:読み取り失敗)
     * @throws: FileNotFoundException(ファイルが見つかりません), IOException(データの読み取りと書き込みの異常)
     */ 
    boolean loadPropertyFile(String propertyFilePath) throws IOException;
    
    /**   
     * @Title: loadPropertyFile   
     * @Description: プロパティファイルの読み込み
     * @param: propertyFilePath(配置ファイルの位置) 
     * @return: boolean(true:読み取り成功,false:読み取り失敗)
     * @throws: FileNotFoundException(ファイルが見つかりません), IOException(データの読み取りと書き込みの異常)
     */ 
    boolean loadPropertyFile(String propertyFilePath, String charset) throws IOException;
    
    /**   
     * @Title: getProperty  
     * @Description: 配置名で配置値を見つける
     * @param: propertyName(配置名)
     * @return: String(配置値)
     */ 
    String getProperty(String propertyName);
    
    /**   
     * @Title: setProperty
     * @Description: 配置名と配置値を設定する
     * @param: propertyName(配置名), propertyValue(配置値)
     * @return: void
     */ 
    void setProperty(String propertyName, String propertyValue);
}