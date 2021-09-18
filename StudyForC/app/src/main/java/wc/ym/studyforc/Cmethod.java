package wc.ym.studyforc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Cmethod {
    private static SecretKey key;
    private static AlgorithmParameterSpec spec;
    private static Cipher cipher;
    private static byte[] keyvalue;//密钥
    private static byte[] iv;//初始化向量

    static {
        System.loadLibrary("jni-demo");
        keyvalue = getKeyValue();
        iv = getIv();
        if(keyvalue != null && iv != null){
            KeyGenerator kgen;
            try{
                kgen = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(keyvalue);
                kgen.init(128,secureRandom);
                key = kgen.generateKey();
                spec = new IvParameterSpec(iv);
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//加密方式／分组密码工作模式／填充模式
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
//            catch (NoSuchProviderException e){
//                e.printStackTrace();
//            }
            catch (NoSuchPaddingException e){
                e.printStackTrace();
            }
        }
    }
    public static native String method();
    public static native String EncodeString(String str,int length);
    public static native String DecodeString(String str,int length);
    public static native byte[] getIv();
    public static native byte[] getKeyValue();

    public static String encode(String msg){
        String str = "";
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            str = asHex(cipher.doFinal(msg.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public static String decode(String msg){
        try{
            cipher.init(Cipher.DECRYPT_MODE,key,spec);
            return new String(cipher.doFinal(asBin(msg)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private static String asHex(byte buf[]) {
        StringBuffer strbuf =new StringBuffer(buf.length * 2);
        int i;
        for (i = 0;i <buf.length;i++) {
            if (((int)buf[i] & 0xff) < 0x10)//小于十前面补零
                strbuf.append("0");
            strbuf.append(Long.toString((int)buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    private static byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);//取高位字节
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);//取低位字节
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }
}
