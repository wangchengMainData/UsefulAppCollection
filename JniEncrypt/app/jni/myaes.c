//
// Created by server1 on 21-5-27.
//
#include <jni.h>
#include "include/openssl/aes.h"
#include <android/log.h>

#define LOG_TAG "HelloJni"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOGD类型

unsigned char aes_key[] = {0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB,
                           0xCC,
                           0xDD, 0xEE, 0xFF};

int aes_encrypt(const char *data, char *data_encrypt){
    AES_KEY aesKey_encrypt;
    int result_encrypt = AES_set_encrypt_key(aes_key,128,&aesKey_encrypt);
    unsigned int* aes_int_key_encrypt = aesKey_encrypt.rd_key;
    LOGI("encrypt key = %d",*aes_int_key_encrypt);//加密密钥
    LOGI("result = %d", result_encrypt);
    if(result_encrypt == 0) {
        AES_ecb_encrypt((const unsigned char *) data, (unsigned char *) data_encrypt, &aesKey_encrypt,AES_ENCRYPT);
        LOGI("after encrypted = %s",data_encrypt);
        return 1;
    }else{
        return 0;
    }
}

int aes_decrypt(const char *data_encrypt,char *data){
    AES_KEY aesKey_decrypt;
    int result_decrypt = AES_set_decrypt_key(aes_key,128,&aesKey_decrypt);
    unsigned int* aes_int_key_decrypt = aesKey_decrypt.rd_key;
    LOGI("decrypt key = %d",*aes_int_key_decrypt);//解密密钥
    LOGI("result = %d",result_decrypt);
    if(result_decrypt == 0){
        AES_ecb_encrypt((const unsigned char *) data_encrypt,(unsigned char *) data,&aesKey_decrypt,AES_DECRYPT);
        LOGI("after decrypt = %s",data);
        return 1;
    }else{
        return 0;
    }
}