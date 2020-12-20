package com.virtualspacex.util.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.virtualspacex.util.common.StringUtils;

public class FileUtil {

    /**
     * @Title: getAbsolutePath
     * @Description: ファイルの絶対パスを取得
     * @param: path(文件名)
     * @return: String(ファイルの絶対パス)
     */
    public String getAbsolutePath(String path) {
    	if(StringUtils.isBlank(path)) {
    		return null;
    	}
        return new File(path).getAbsolutePath();
    }

    /**
     * @Title: isExist
     * @Description: ファイルが存在するかどうかを検出します。
     * @param: path(ファイルパス)
     * @return: boolean(true : ファイルが存在します, false : ファイルは存在しません)
     */
    public boolean isExist(String path) {
    	if(StringUtils.isBlank(path)) {
    		return false;
    	}
        return new File(path).exists();
    }

    /**
     * @Title: isDirectory
     * @Description: フォルダかどうかを判断する
     * @param: directory(フォルダのパス)
     * @return: boolean(true : フォルダです, false : フォルダではありません)
     */
    public boolean isDirectory(String directory) {
        if (StringUtils.isBlank(directory)) {
            return false;
        }
        return new File(directory).isDirectory();
    }

    /**
     * @Title: create
     * @Description: ファイルを作成
     * @param: path(ファイルパス)
     * @return: boolean(true : 作成成功, false : 作成に失敗しました)
     * @throws IOException 
     */
    public boolean create(String path) throws IOException {
        if (StringUtils.isBlank(path)) {
            return false;
        }
        
        File file = new File(path);
        if (!file.exists()) {
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            return file.createNewFile();
        }
        
        return true;
    }

    /**
     * @Title: readFileByBytes
     * @Description: バイトでファイルを読み込みます。
     * @param: path(ファイルパス)
     * @return: byte[](読み出しバイト配列)
     * @throws IOException 
     */
    public byte[] readFileByBytes(String path) throws IOException {
        byte[] buffer = new byte[0];
        
        if (StringUtils.isBlank(path)) {
            return buffer;
        }
    	
        File file = new File(path);
        if (!file.exists()) {
            return buffer;
        }
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } finally {
            if (in != null) {
                in.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * @Title: readFileByChars
     * @Description: ファイルをchar配列として読み込む
     * @param: path(ファイルパス)
     * @return: char[](読み出しchar配列)
     * @throws IOException 
     */
    public char[] readFileByChars(String path) throws IOException {
    	
        if (StringUtils.isBlank(path)) {
            return new char[0];
        }
        
        File file = new File(path);
        if (!file.exists()) {
            return new char[0];
        }
        
        int len = 0;
        char[] charArray = new char[1024];
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            List<Character> list = new ArrayList<Character>();
            while ((len = reader.read(charArray)) != -1) {
                for (int i = 0; i < len; i++) {
                    list.add(charArray[i]);//char->string
                }
            }
            
            char[] newArray = new char[list.size()];
            for (int i = 0; i < list.size(); i++) {
                newArray[i] = list.get(i);
            }
            
            return newArray;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * @Title: delete
     * @Description: ファイルを削除
     * @param: path(ファイルパス)
     * @return: boolean(true : 削除に成功しました, false : 削除に失敗しました)
     */
    public boolean delete(String path) {
        if (StringUtils.isBlank(path)) {
            return false;
        }
        
        File file = new File(path);
        if (file.exists()) {
        	return file.delete();
        }
        return true;
    }

    /**
     * @Title: deleteDirectory
     * @Description: フォルダーとその内容を削除する
     * @param: path(フォルダーパス)
     * @return: CommonResult
     */
    public boolean deleteDirectory(String path) {
    	
        if (StringUtils.isBlank(path)) {
            return false;
        }
        
        File file = new File(path);
        File[] tempfiles = file.listFiles();
        for (File tempfile : tempfiles) {
            if (tempfile.isFile()) {
                tempfile.delete();
            } else if (tempfile.isDirectory()) {
                deleteDirectory(tempfile.getAbsolutePath());
            } else {
                // do nothing
            }
        }
        return file.delete();
    }

    /**
     * @Title: move
     * @Description: ファイルを移動
     * @param: src(ソースファイル), target(ターゲットファイル)
     * @return: boolean(true : 移動成功, false : 移動に失敗しました)
     */
    public boolean move(String src, String target) {
    	
        if (StringUtils.isBlank(src)) {
            return false;
        }
        
        if (StringUtils.isBlank(target)) {
            return false;
        }
        
        File sourceFile = new File(src);
        File destFile = new File(target);
        File fileParent = destFile.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        return sourceFile.renameTo(destFile);
    }

    /**
     * @Title: copy
     * @Description: ファイルコピー
     * @param: src(ソースファイル), target(ターゲットファイル)
     * @return: boolean(true : コピー成功, false : コピー失敗)
     * @throws IOException 
     */
    public boolean copy(String src, String target) throws IOException {
    	
        if (StringUtils.isBlank(src)) {
            return false;
        }
        
        if (StringUtils.isBlank(target)) {
            return false;
        }

        File srcFile = new File(src);
        File targetFile = new File(target);
        File fileParent = targetFile.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        Files.copy(srcFile.toPath(), targetFile.toPath());
        
        return true;
    }

    /**
     * @Title: listDirectory
     * @Description: フォルダーリストを取得
     * @param: path(フォルダーのパス)
     * @return: List<String>(戻ってきたフォルダーリスト)
     */
    public List<String> listDirectory(String path) {
        List<String> files = new ArrayList<String>();
        
        if (StringUtils.isBlank(path)) {
            return files;
        }
        
        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isDirectory()) {
                    files.add(tempList[i].toString());
                }
            }
        }

        return files;
    }

    /**
     * @Title: List
     * @Description: ファイルリストを取得
     * @param: path(フォルダのパス)
     * @return: List<String>(戻ってきたファイルリスト)
     */
    public List<String> listFile(String path) {
        List<String> files = new ArrayList<String>();
        
        if (StringUtils.isBlank(path)) {
            return files;
        }

        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    files.add(tempList[i].toString());
                }
            }
        }

        return files;
    }

    public static String getClassPath() {
        String javaclasspath = System.getProperty("java.class.path");

        if (javaclasspath.contains(File.separator + "target" + File.separator + "classes")) {
            javaclasspath = javaclasspath.substring(0, javaclasspath.indexOf(File.separator + "target" + File.separator + "classes"));
            javaclasspath = javaclasspath.substring(javaclasspath.lastIndexOf(";") + 1);
        }

        if (javaclasspath.contains("jar")) {
    		int separator = javaclasspath.lastIndexOf(File.separator);
    		if(separator != -1) {
    			javaclasspath = javaclasspath.substring(0, separator);
    		} else {
    			javaclasspath = "." + File.separator;
    		}
        }

        return javaclasspath;
    }

}
