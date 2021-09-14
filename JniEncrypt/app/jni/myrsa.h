//
// Created by server1 on 21-5-27.
//

#ifndef JNIENCRYPT_MYRSA_H
#define JNIENCRYPT_MYRSA_H
int  my_rsa_encrypt(int flen,const char * data, char* out);
int  my_rsa_decrypt(int len,const char * secret,char* out);
#endif //JNIENCRYPT_MYRSA_H
