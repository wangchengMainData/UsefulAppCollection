//
// Created by server1 on 21-5-27.
//

#ifndef JNIENCRYPT_MYAES_H
#define JNIENCRYPT_MYAES_H
int aes_encrypt(const char *data,char *data_encrypt);
int aes_decrypt(const char *data_encrypt,char *data);
#endif //JNIENCRYPT_MYAES_H
