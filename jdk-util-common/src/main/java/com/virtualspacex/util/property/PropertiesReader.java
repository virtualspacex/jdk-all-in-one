/*
 * @fileName: PropertiesAdapter.java
 * @Description: ログメッセージ処理用クラスのファイル
 * @date: 2020/3/17
 * @Copyright: Copyright (c) 2020,Fuji Electric (Hangzhou) Software Co., Ltd. All rights reserved.
 */
package com.virtualspacex.util.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * @ClassName: PropertiesAdapter
 * @Description: ログメッセージ処理用クラス
 * @author: wei-yushan
 */
public class PropertiesReader implements PropertiesReaderInterface {
    
    private Properties properties;
    
    private final String FILE_SUFFIX = "properties";
    
    private final String DEFAULT_ENCODING = "UTF-8";
    
    /**   
     * @Title: PropertiesAdapter
     * @Description:ログメッセージ処理用クラスの構築方法
     */ 
    public PropertiesReader() {
        this.properties = new Properties();
    }
    
	@Override
	public boolean loadPropertyFile(String propertyFilePath) throws IOException {
		return loadPropertyFile(propertyFilePath, DEFAULT_ENCODING);
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
        boolean ret = false;
        Reader reader = null;
        
        String propertyFileType = "";
        if (propertyFilePath != null) {
            propertyFileType = propertyFilePath.substring(propertyFilePath.lastIndexOf(".") + 1);
        }
        
        try {
        	reader = new InputStreamReader(new FileInputStream(propertyFilePath), charset);
            if ((FILE_SUFFIX).equals(propertyFileType)) {
                this.properties.load(reader);
                ret = true;
            } 
            return ret;
        } finally {
    		if (reader != null) {
    			reader.close();
    		} 
        }
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
