LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := crypto
LOCAL_SRC_FILES := libcrypto.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := ssl
LOCAL_SRC_FILES := libssl.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := jni-demo
LOCAL_SRC_FILES := jnidemo.c myrsa.c myaes.c mymd5.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_SHARED_LIBRARIES := ssl crypto
LOCAL_EXPORT_LDLIBS := -lz
LOCAL_LDLIBS := -lz -ldl -llog
include $(BUILD_SHARED_LIBRARY)
