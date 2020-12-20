package com.virtualspacex.util.io.file;

import java.util.List;

public interface FileUtilInterface {
	
    /**   
     * @Title: getAbsolutePath  
     * @Description: ファイルの絶対パスを取得
     * @param: path(文件名)
     * @return: String(ファイルの絶対パス)
     */ 
	String getAbsolutePath(String path);
	
    /**   
     * @Title: isExist  
     * @Description: ファイルが存在するかどうかを検出します。
     * @param: path(ファイルパス)
     * @return: boolean(true:ファイルが存在します, false:ファイルは存在しません)
     */ 
	boolean isExist(String path);
	
    /**   
     * @Title: isDirectory  
     * @Description: フォルダかどうかを判断する
     * @param: directory(フォルダのパス)
     * @return: boolean(true:フォルダです, false:フォルダではありません)
     */ 
	boolean isDirectory(String directory);
	
    /**   
     * @Title: create  
     * @Description: ファイルを作成
     * @param: path(ファイルパス)
     * @return: boolean(true:作成成功, false:作成に失敗しました)
     */ 
	boolean create(String path);
	
    /**   
     * @Title: readFileByBytes  
     * @Description: バイトでファイルを読み込みます。
     * @param: path(ファイルパス)
     * @return: byte[](読み出しバイト配列)
     */ 
	byte[] readFileByBytes(String path);
	
    /**   
     * @Title: readFileByChars  
     * @Description: ファイルをchar配列として読み込む
     * @param: path(ファイルパス)
     * @return: char[](読み出しchar配列)
     */ 
	char[] readFileByChars(String path);
	
    /**   
     * @Title: delete  
     * @Description: ファイルを削除
     * @param: path(ファイルパス)
     * @return: boolean(true:削除に成功しました, false:削除に失敗しました)
     */ 
	boolean delete(String path);
	
    /**   
     * @Title: move  
     * @Description: ファイルを移動
     * @param: src(ソースファイル), target(ターゲットファイル)
     * @return: boolean(true:移動成功, false:移動に失敗しました)
     */ 
	boolean move(String src, String target);
	
    /**   
     * @Title: copy  
     * @Description: ファイルコピー
     * @param: src(ソースファイル), target(ターゲットファイル)
     * @return: boolean(true:コピー成功, false:コピー失敗)
     */ 
	boolean copy(String src, String target);
	
    /**   
     * @Title: List  
     * @Description: ファイルリストを取得
     * @param: path(フォルダのパス)
     * @return: List<String>(戻ってきたファイルリスト)
     */ 
	List<String> list(String path);
}
