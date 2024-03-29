/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <openssl/aes.h>
#include <malloc.h>
#include <string.h>
#include <android/log.h>
#include <openssl/rsa.h>
#include <openssl/md5.h>
#include <fcntl.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>
#include "myrsa.h"
#include "myaes.h"
#include "mymd5.h"
#define LOG_TAG "HelloJni"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOGD类型
/* Header for class wc_ym_jniencrypt_Cmethod */


char* Jstring2CStr(JNIEnv*   env,   jstring   jstr);//java String to C string
static int base64_encode(char *str,int str_len,char *encode,int encode_len);
static int base64_decode(char *str,int str_len,char *decode,int decode_buffer_len);
#ifndef _Included_wc_ym_jniencrypt_Cmethod
#define _Included_wc_ym_jniencrypt_Cmethod
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     wc_ym_jniencrypt_Cmethod
 * Method:    aes_Encode
 * Signature: ()Ljava/lang/String;
 */

char data_encrypt_aes[AES_BLOCK_SIZE];
char data_encrypt_rsa[128];
int rsa_data_len = 0;
jbyteArray Java_wc_ym_jniencrypt_Cmethod_aes_1Encode(JNIEnv *env, jclass this,jstring prompt){
    char *data = Jstring2CStr(env, prompt);//userdata
    if (aes_encrypt(data, data_encrypt_aes) == 1) {
        jbyteArray array = (*env)->NewByteArray(env, sizeof(data_encrypt_aes));
        (*env)->SetByteArrayRegion(env,array,0, sizeof(data_encrypt_aes),(jbyte*)data_encrypt_aes);
        return array;
    }
    else {
        jbyteArray array1 = (*env)->NewByteArray(env, 40);
        char a[40] = {"failed"};
        (*env)->SetByteArrayRegion(env,array1,0,40,(jbyte*)a);
        return array1;
    }
}

/*
 * Class:     wc_ym_jniencrypt_Cmethod
 * Method:    aes_Dncode
 * Signature: ()I
 */
jstring Java_wc_ym_jniencrypt_Cmethod_aes_1Dncode(JNIEnv *env, jclass this){
    char data[AES_BLOCK_SIZE];
    if (aes_decrypt(data_encrypt_aes,data) == 1) {
        char *tips_success = "AES decrypt success :";
        char *total = (char *)malloc(strlen(data) + strlen(tips_success) + 1);
        strcpy(total,tips_success);
        strcat(total,data);
        LOGI("data = %s",data);
        return (*env)->NewStringUTF(env,total);
    }
    else
        return (*env)->NewStringUTF(env,"AES decrypt fail");
}

/*
 * Class:     wc_ym_jniencrypt_Cmethod
 * Method:    rsa_Encode
 * Signature: ()I
 */
jbyteArray Java_wc_ym_jniencrypt_Cmethod_rsa_1Encode(JNIEnv *env, jclass this,jstring prompt){
    char *userdata = Jstring2CStr(env,prompt);//源数据

    rsa_data_len = (int)strlen(userdata) + 1;//!!!!!!!!!!!!!!!!!!!!!!!!!

    if(my_rsa_encrypt(rsa_data_len,userdata,data_encrypt_rsa) != -1) {
        jbyteArray array = (*env)->NewByteArray(env, sizeof(data_encrypt_rsa));
        (*env)->SetByteArrayRegion(env, array, 0, sizeof(data_encrypt_rsa),
                                   (jbyte *) data_encrypt_rsa);
        LOGI("rsa_encrypt = %s",data_encrypt_rsa);
        return array;
    }else{
        jbyteArray array1 = (*env)->NewByteArray(env, 40);
        char a[40] = {"failed"};
        (*env)->SetByteArrayRegion(env,array1,0,40,(jbyte*)a);
        return array1;
    }

}

/*
 * Class:     wc_ym_jniencrypt_Cmethod
 * Method:    rsa_Dncode
 * Signature: ()I
 */
jstring Java_wc_ym_jniencrypt_Cmethod_rsa_1Dncode(JNIEnv *env, jclass this){
    char data_decrypt[rsa_data_len];//解密后的数据存放
        LOGI("len = %d",rsa_data_len);
    if(my_rsa_decrypt(128,data_encrypt_rsa,data_decrypt) != -1){
        LOGI("secret = %s",data_encrypt_rsa);
        LOGI("secret_decrypt = %s",data_decrypt);
        char *tips_success = "RSA decrypt success :";
        char *total = (char *)malloc(rsa_data_len + strlen(tips_success) + 1);
        strcpy(total,tips_success);
        strcat(total,data_decrypt);
        return (*env)->NewStringUTF(env, total);
        }
    else
        return (*env)->NewStringUTF(env, "RSA decrypt fail");
}

jbyteArray Java_wc_ym_jniencrypt_Cmethod_md5_1Encode(JNIEnv *env, jclass this,jstring prompt) {
    unsigned char *userdata = (unsigned char *)Jstring2CStr(env,prompt);
    unsigned char hex[16];
    md5_encrypt((const unsigned char*)userdata,hex);
    LOGI("md5 encrypt = %s",hex);
//    for(int i = 0; i < 16; i ++){
//        LOGI("%x",hex[i]);
//    }
    jbyteArray array = (*env)->NewByteArray(env, sizeof(hex));
    (*env)->SetByteArrayRegion(env, array, 0, sizeof(hex),
                               (jbyte *) hex);
    return array;

}

jbyteArray Java_wc_ym_jniencrypt_Cmethod_md5_1fileEncode(JNIEnv *env, jclass this,jstring filepath){
    const char * path =(*env)->GetStringUTFChars(env,filepath,NULL);
    LOGI("%s",path);
    int size = (jsize)strlen(path);
    LOGI("%d",size );
    FILE* file = fopen("/sdcard/1.txt","r");//FILE CANT OPEN
    if(file == NULL){
        LOGI("file can't open!");
    }
    jbyteArray array = (*env)->NewByteArray(env,size);
    (*env)->SetByteArrayRegion(env, array, 0, size,
                               (jbyte *) path);
    return array;
}

char encrypt_base64[1024];
int length_base64data;
jbyteArray Java_wc_ym_jniencrypt_Cmethod_base64_1encode(JNIEnv *env, jclass this,jstring prompt) {
    const char *userdata = (*env)->GetStringUTFChars(env,prompt,NULL);
    LOGI("base64_userdata = %s" ,userdata);
    int size = (jsize)strlen(userdata);
    length_base64data = size;
    base64_encode(userdata,strlen(userdata),encrypt_base64,1024);
    LOGI("base64 = %s",encrypt_base64);
    jbyteArray array = (*env)->NewByteArray(env,1024);
    (*env)->SetByteArrayRegion(env, array, 0, 1024,
                               (jbyte *) encrypt_base64);
    return array;
}

jstring Java_wc_ym_jniencrypt_Cmethod_base64_1decode(JNIEnv *env, jclass this) {
    char decrypt[length_base64data];
    base64_decode(encrypt_base64,1024,decrypt,length_base64data);
    LOGI("base64 = %s",decrypt);
    return (*env)->NewStringUTF(env,decrypt);
}

static int base64_encode(char *str,int str_len,char *encode,int encode_len){
    BIO *bmem,*b64;
    BUF_MEM *bptr;
    b64=BIO_new(BIO_f_base64());
    bmem=BIO_new(BIO_s_mem());
    b64=BIO_push(b64,bmem);
    BIO_write(b64,str,str_len); //encode
    BIO_flush(b64);
    BIO_get_mem_ptr(b64,&bptr);
    if(bptr->length > encode_len){
        LOGI("encode_len too small\n");
        return -1;
    }
    encode_len=bptr->length;
    memcpy(encode,bptr->data,bptr->length);
    //  write(1,encode,bptr->length);
    BIO_free_all(b64);
    return encode_len;
}

static int base64_decode(char *str,int str_len,char *decode,int decode_buffer_len){
    int len=0;
    BIO *b64,*bmem;
    b64=BIO_new(BIO_f_base64());
    bmem=BIO_new_mem_buf(str,str_len);
    bmem=BIO_push(b64,bmem);
    len=BIO_read(bmem,decode,str_len);
    decode[len]=0;
    BIO_free_all(bmem);
    return 0;
}

char* Jstring2CStr(JNIEnv* env,jstring jstr)
{
    char* rtn = NULL;
    //1:先找到字节码文件
    jclass clsstring = (*env)->FindClass(env,"java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env,"UTF-8");
    //2:通过字节码文件找到方法ID
    jmethodID mid = (*env)->GetMethodID(env,clsstring, "getBytes", "(Ljava/lang/String;)[B");
    //3:通过方法id，调用方法
    jbyteArray barr= (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
    //4:得到数据的长度
    jsize alen = (*env)->GetArrayLength(env,barr);
    //5：得到数据的首地址
    jbyte* ba = (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
    //6:得到C语言的字符串
    if(alen > 0)
    {
        rtn = (char*)malloc(alen+1);         //"\0"
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
    return rtn;
}

#ifdef __cplusplus
}
#endif
#endif
