/*
 * @Author: your name
 * @Date: 2020-12-16 23:28:54
 * @LastEditTime: 2020-12-20 17:56:56
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: /jdk-all-in-one/jdk-util-common/src/main/java/com/virtualspacex/util/io/file/json/JsonFileReader.java
 */
// package com.virtualspacex.util.io.file.json;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;

// import com.alibaba.fastjson.JSONObject;
// import com.alibaba.fastjson.util.IOUtils;

// public class JsonFileReader {

// 	/**
//      * .jsonファイル中のオブジェクトを取得する
//      * 
//      * @param filePath ファイルパス
// 	 * @return
// 	 * @throws IOException 
// 	 */
// 	public static JSONObject getJSONObject(String filePath) throws IOException {
// 		JSONObject result = null;
//     	File file = new File(filePath);
//     	if (file.isFile() && file.exists()) {
//     		BufferedReader reader = null;
//     		try {
//     			reader = new BufferedReader(new FileReader(file));
//     			StringBuffer sb = new StringBuffer();
//     			String line = null;    			
//     			while ((line = reader.readLine()) !=null) {
//     				sb.append(line);
//     			}
//     			result = JSONObject.parseObject(sb.toString());
// 			}  finally {				
// 				IOUtils.close(reader);
// 			}			
// 		}	
//     	return result;
// 	}	
// }
