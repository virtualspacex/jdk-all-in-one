package com.virtualspacex.util.io.file.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


/**
 * @ClassName: CsvFileReader
 * @Description: CSV文件读取操作类
 * @author: 
 */
public class CsvFileReader {
	
	String csvFilePath = "";
	String csvFilecharsetName = "";
	int fileRowsCount = 0;
	int fileColumnCount = 0;
	String[] keys = null;
	String[][] csvMap = null;
	
	public CsvFileReader(String filePath, String charsetName) {
		csvFilePath = filePath;
		csvFilecharsetName = charsetName;
	}
	
	public void load() throws IOException {
		FileReader filereader =  null;
		LineNumberReader linereader = null;
		
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        
		try {
			filereader = new FileReader(csvFilePath);
			linereader = new LineNumberReader(filereader);
			linereader.skip(Long.MAX_VALUE);
			fileRowsCount = linereader.getLineNumber();
		} finally {
			if(filereader != null) {
				filereader.close();
			}
			if(linereader != null) {
				linereader.close();
			}
		}
		
		try {
	        fileInputStream = new FileInputStream(csvFilePath);
	        inputStreamReader = new InputStreamReader(fileInputStream, csvFilecharsetName);
	        bufferedReader = new BufferedReader(inputStreamReader);
	        csvMap = new String[fileRowsCount][];
	        String line = "";
	        if ((line=bufferedReader.readLine())!=null) {
	        	keys = line.split(",", -1);
	        	
	        	//20200426 FHS）桂　ADD　半角スペースが含まれる
	        	for(int i = 0; i < keys.length; i++) {
	        		keys[i] = keys[i].trim();
	        	}
	        	//20200426 FHS）桂　ADD　半角スペースが含まれる
	        	
	        	csvMap[0] = keys;
	        }
	        
	        if(keys != null) {
	        	fileColumnCount = keys.length;
	        }
	        
	        int lineIndex = 1;
	        while ((line=bufferedReader.readLine())!=null) {
        		String[] arrs = line.split(",",-1);
        		csvMap[lineIndex] = arrs;
	        	lineIndex ++;
			}
	        
		} finally {
			if(fileInputStream != null) {
				fileInputStream.close();
			}
			if(inputStreamReader != null) {
				inputStreamReader.close();
			}
			if(bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
	
    /**   
     * @Title: getRowCounts   
     * @Description: 获得CSV文件行数
     * @return: int(行数)
     */ 
	public int getRowCounts() {
		 return fileRowsCount;
	}
	
	/**
	 * @Title
	 * @Description: 获取key值在CSV文件第几列
	 * @param KeyValue
	 * @return
	 */
	public int getKeyColumn(String KeyValue) {
		int column = -1;
		
    	if(keys != null) {
    		for (int i = 0; i < keys.length; i++) {
				if(KeyValue.equals(keys[i])) {
					column = i;
					break;
				}
			}
    	}
    	
		return column;
	}
	
	
    /**   
     * @Title: getRowData   
     * @Description: 获得CSV文件指定某一行，某个键值对应的数据数据，以String返回
     * @param: rowCount(第几行，数据从第1行开始，0行是键值不是数据) KeyValue(对应的键值)
     * @return: String(如果字段为空，则值为"")
     */ 
	public String getRowData(int row, String KeyValue) {
		String ret = "";
		
        int column = getKeyColumn(KeyValue);
        if(column >= 0 && row >= 0 && csvMap != null) {
        	if (row < csvMap.length && column < csvMap[row].length && csvMap[row] != null) {
        		ret = csvMap[row][column];
            }
        }
        
		return ret;
	}
	
	
    /**   
     * @Title: getRowData   
     * @Description: 获得CSV文件一行数据，以String数组返回，长度是列的数量，如果字段为空，则值为""
     * @param: rowCount(第几行，数据从第1行开始，0行是键值不是数据) KeyValue 对应的键值
     * @return: String[]()
     */ 
	public String[] getRowData(int row) {
		String ret[] = null;
		
        if(row >= 0 && csvMap != null && row < csvMap.length) {
        	ret = csvMap[row];
        }
        
		return ret;
	}
	
    /**   
     * @Title: getColumnCounts   
     * @Description: 获得CSV文件列数
     * @return: int(列数)
     */ 
	public int getColumnCounts() {
		return fileColumnCount;
	}
}
