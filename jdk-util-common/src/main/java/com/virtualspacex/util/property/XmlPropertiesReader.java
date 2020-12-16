/*
 * @fileName: PropertiesAdapter.java
 * @Description: ログメッセージ処理用クラスのファイル
 * @date: 2020/3/17
 * @Copyright: Copyright (c) 2020,Fuji Electric (Hangzhou) Software Co., Ltd. All rights reserved.
 */
package com.virtualspacex.util.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: PropertiesAdapter
 * @Description: ログメッセージ処理用クラス
 * @author: wei-yushan
 */
public class XmlPropertiesReader implements PropertiesReaderInterface {
    
    private Properties properties;
    
    private final String FILE_SUFFIX = "xml";
    
    /**   
     * @Title: PropertiesAdapter
     * @Description:ログメッセージ処理用クラスの構築方法
     */ 
    public XmlPropertiesReader() {
        this.properties = new Properties();
    }
    
	public boolean loadPropertyFile(String propertyFilePath) throws IOException {
        boolean ret = false;
        String propertyFileType = "";
        if (propertyFilePath != null) {
            propertyFileType = propertyFilePath.substring(propertyFilePath.lastIndexOf(".") + 1);
        }
        
    	if ((FILE_SUFFIX).equals(propertyFileType)) {
            this.properties.loadFromXML(new FileInputStream(propertyFilePath));
            ret = true;
        }
        return ret;
	}
	
    /**   
     * @Title: loadPropertyFile
     * @Description: プロパティファイルの読み込み
     * @param: propertyFilePath(配置ファイルの位置) 
     * @return: boolean(true:読み取り成功,false:読み取り失敗)
     * @throws：   FileNotFoundException(ファイルが見つかりません), IOException(データの読み取りと書き込みの異常)
     * @throws IOException 
     * @throws Exception 
     */ 
    public boolean loadPropertyFile(String propertyFilePath, String charset) throws IOException {
        return loadPropertyFile(propertyFilePath);
    }
    
    /**   
     * @Title: getProperty  
     * @Description: 配置名で配置値を見つける
     * @param: propertyName(配置名)
     * @return: String(配置値)
     */ 
    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
    
    /**   
     * @Title: setProperty
     * @Description: 配置名と配置値を設定する
     * @param: propertyName(配置名), propertyValue(配置値)
     * @return: void
     */ 
    public void setProperty(String propertyName, String propertyValue) {
        this.properties.setProperty(propertyName, propertyValue);
    }
}
