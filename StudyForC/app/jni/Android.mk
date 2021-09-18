LOCAL_PATH := $(call my-dir)

#include $(CLEAR_VARS)
#LOCAL_MODULE := crypto
#LOCAL_SRC_FILES := jnilibs/libcrypto.so
# APP_ABI := x86-64
#include $(PREBUILT_SHARED_LIBRARY)

#include $(CLEAR_VARS)
#LOCAL_MODULE := ssl
#LOCAL_SRC_FILES := jnilibs/libssl.so
#APP_ABI := x86-64
#include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := jni-demo
LOCAL_SRC_FILES += demo.c node.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_LDLIBS +=-llog
APP_ALLOW_MISSING_DEPS=true
#LOCAL_SHARED_LIBRARIES += crypto ssl
include $(BUILD_SHARED_LIBRARY)



