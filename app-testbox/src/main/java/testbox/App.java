package testbox;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try {
            //BASE64Encoder encoder = new BASE64Encoder();
            Base64.Encoder encoder = Base64.getEncoder();
            //读取文件内容
            FileInputStream is = new FileInputStream("E:/1A/server.keystore");
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(is, "server".toCharArray());
            PrivateKey key = (PrivateKey) ks.getKey("server", "server".toCharArray());
            String encoded = encoder.encodeToString(key.getEncoded());
            System.out.println(encoded);
            is.close();
        } catch (Exception e){
        }
    }
}
