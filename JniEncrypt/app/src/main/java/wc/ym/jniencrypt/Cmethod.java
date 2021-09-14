package wc.ym.jniencrypt;


public class Cmethod {

    static {
        System.loadLibrary("crypto");
        System.loadLibrary("ssl");
        System.loadLibrary("jni-demo");
    }
    public static native byte[] aes_Encode(String str);
    public static native String aes_Dncode();
    public static native byte[] rsa_Encode(String str);
    public static native String rsa_Dncode();
    public static native byte[] md5_Encode(String str);
    public static native byte[] md5_fileEncode(String filepath);
    public static native byte[] base64_encode(String str);
    public static native String base64_decode();
}
