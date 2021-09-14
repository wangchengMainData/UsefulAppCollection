//
// Created by server1 on 21-6-1.
//

#include "include/openssl/md5.h"
#include "../../../../../Ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/sysroot/usr/include/string.h"

void md5_encrypt(const unsigned char *userdata,unsigned char* hex) {
    MD5_CTX md5Ctx;
    MD5_Init(&md5Ctx);
    MD5_Update(&md5Ctx, userdata, strlen((char *) userdata));
    MD5_Final(hex, &md5Ctx);
}

void md5_fileEncode(){

}