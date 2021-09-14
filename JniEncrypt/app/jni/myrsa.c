
//
// Created by server1 on 21-5-27.
//
#include "include/openssl/rsa.h"
#include "include/openssl/cms.h"
#include "include/openssl/ossl_typ.h"

RSA *key;

int  my_rsa_encrypt(int flen,const char * data, char* out){
    key = RSA_generate_key(1024,65537,NULL,NULL);
    if(key != NULL) {
        int result =  RSA_public_encrypt(flen, (unsigned char *) data, (unsigned char *) out, key,
                                  RSA_PKCS1_PADDING);
        return result;
    }else{
        return -1;
    }
}

int  my_rsa_decrypt(int len,const char * secret,char* out){
    if(key != NULL) {
        int result = RSA_private_decrypt(len, (unsigned char *) secret, (unsigned char *) out, key,
                                         RSA_PKCS1_PADDING);
//        free(key);
        return result;
    }else{
        return -1;
    }
}
