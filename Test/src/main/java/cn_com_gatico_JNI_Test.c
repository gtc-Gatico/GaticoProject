#include <stdio.h>
#include "cn_com_gatico_JNI_Test.h"
/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    testHello
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_cn_com_gatico_JNI_Test_testHello
  (JNIEnv *env, jobject obj)
  {
	printf("Hello Native !!\n");
  }

/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    mathAdd
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cn_com_gatico_JNI_Test_mathAdd
  (JNIEnv *env, jobject obj, jint i, jint j){
    return i + j;
  }

/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    mathSub
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cn_com_gatico_JNI_Test_mathSub
  (JNIEnv *env, jobject obj, jint i, jint j){
      return i - j;
    }

/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    testMul
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_cn_com_gatico_JNI_Test_testMul
  (JNIEnv *env, jobject obj, jint i, jint j){
      return i * j;
    }

/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    testDiv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cn_com_gatico_JNI_Test_testDiv
  (JNIEnv *env, jobject obj, jdouble i, jdouble j){
      return i / j;
    }

const char *getStrParam(JNIEnv *env, jclass paramInClass, jobject paramIn, char *param) {
    // 获取字段id
    jfieldID fieldID = (*env)->GetFieldID(env, paramInClass, param, "Ljava/lang/String;");
    // 获取对象的字段
    jstring fieldValue = (jstring)(*env)->GetObjectField(env, paramIn, fieldID);
    // 将jstring转char *
    const char *charValue = (*env)->GetStringUTFChars(env, fieldValue, 0);
    printf("==========param %s=%s\n", param, charValue);
    return charValue;
}


int getIntParam(JNIEnv *env, jclass paramInClass, jobject paramIn, char *param) {
    // 获取字段id
    jfieldID fieldID = (*env)->GetFieldID(env, paramInClass, param, "I");
    // 获取对象的字段值
    jint fieldValue = (int)(*env)->GetIntField(env, paramIn, fieldID);
    // 将jint转int
    int value = (int)fieldValue;
    printf("==========param %s=%dn", param, value);
    return value;
}

/*
 * Class:     cn_com_gatico_JNI_Test
 * Method:    getRes
 * Signature: (Lcn/com/gatico/JNI/Req;)Lcn/com/gatico/JNI/Res;
 */
JNIEXPORT jobject JNICALL Java_cn_com_gatico_JNI_Test_getRes
  (JNIEnv *env, jobject obj, jobject req){

        // 获取参数类
        jclass paramInClass = (*env)->GetObjectClass(env, req);
        const char *dsc = getStrParam(env, paramInClass, req, "body");
        printf(dsc);
        // 定义返回参数 ReturnParam
        jclass cls = (*env)->FindClass(env, "cn/com/gatico/JNI/Res");
        jmethodID methodID = (*env)->GetMethodID(env, cls, "<init>", "()V");
        // 生成返回结果对象
        jobject returnResult = (*env)->NewObjectA(env, cls, methodID, 0);

        // 获取参数字段id,不同类型用不同方式
//        jfieldID resultInt = (*env)->GetFieldID(env, cls, "code", "I");
        jfieldID resultCode = (*env)->GetFieldID(env, cls, "code", "Ljava/lang/String;");
        jfieldID resultMsg = (*env)->GetFieldID(env, cls, "msg", "Ljava/lang/String;");

        // 填充返回结果参数
        // 这里是将 a + b 赋值给 ReturnParam的 c; 将 ReturnParam的的msg填充指定字串
//        (*env)->SetIntField(env, returnResult, resultCode, 200);
        (*env)->SetObjectField(env, returnResult, resultCode, (*env)->NewStringUTF(env, "200"));
        (*env)->SetObjectField(env, returnResult, resultMsg, (*env)->NewStringUTF(env, dsc));


        return returnResult;
  }