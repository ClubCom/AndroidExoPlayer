/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <jni.h>
<<<<<<< HEAD

#include <android/log.h>

#include <cstdlib>

#include "include/flac_parser.h"

#define LOG_TAG "FlacJniJNI"
=======
#include <android/log.h>
#include <cstdlib>
#include "include/flac_parser.h"

#define LOG_TAG "flac_jni"
>>>>>>> google/release-v2
#define ALOGE(...) \
  ((void)__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__))
#define ALOGV(...) \
  ((void)__android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__))

<<<<<<< HEAD
#define FUNC(RETURN_TYPE, NAME, ...)                                       \
  extern "C" {                                                             \
  JNIEXPORT RETURN_TYPE                                                    \
      Java_com_google_android_exoplayer_ext_flac_FlacJni_##NAME( \
          JNIEnv *env, jobject thiz, ##__VA_ARGS__);                       \
  }                                                                        \
  JNIEXPORT RETURN_TYPE                                                    \
      Java_com_google_android_exoplayer_ext_flac_FlacJni_##NAME( \
=======
#define DECODER_FUNC(RETURN_TYPE, NAME, ...)                               \
  extern "C" {                                                             \
  JNIEXPORT RETURN_TYPE                                                    \
      Java_com_google_android_exoplayer2_ext_flac_FlacDecoderJni_##NAME( \
          JNIEnv *env, jobject thiz, ##__VA_ARGS__);                       \
  }                                                                        \
  JNIEXPORT RETURN_TYPE                                                    \
      Java_com_google_android_exoplayer2_ext_flac_FlacDecoderJni_##NAME( \
>>>>>>> google/release-v2
          JNIEnv *env, jobject thiz, ##__VA_ARGS__)

class JavaDataSource : public DataSource {
 public:
<<<<<<< HEAD
  void setFlacJni(JNIEnv *env, jobject flacJni) {
    this->env = env;
    this->flacJni = flacJni;
    if (mid == NULL) {
      jclass cls = env->GetObjectClass(flacJni);
=======
  void setFlacDecoderJni(JNIEnv *env, jobject flacDecoderJni) {
    this->env = env;
    this->flacDecoderJni = flacDecoderJni;
    if (mid == NULL) {
      jclass cls = env->GetObjectClass(flacDecoderJni);
>>>>>>> google/release-v2
      mid = env->GetMethodID(cls, "read", "(Ljava/nio/ByteBuffer;)I");
      env->DeleteLocalRef(cls);
    }
  }

  ssize_t readAt(off64_t offset, void *const data, size_t size) {
    jobject byteBuffer = env->NewDirectByteBuffer(data, size);
<<<<<<< HEAD
    int result = env->CallIntMethod(flacJni, mid, byteBuffer);
=======
    int result = env->CallIntMethod(flacDecoderJni, mid, byteBuffer);
>>>>>>> google/release-v2
    if (env->ExceptionOccurred()) {
      result = -1;
    }
    env->DeleteLocalRef(byteBuffer);
    return result;
  }

 private:
  JNIEnv *env;
<<<<<<< HEAD
  jobject flacJni;
=======
  jobject flacDecoderJni;
>>>>>>> google/release-v2
  jmethodID mid;
};

struct Context {
  JavaDataSource *source;
  FLACParser *parser;
<<<<<<< HEAD
};

FUNC(jlong, flacInit) {
  Context *context = new Context;
  context->source = new JavaDataSource();
  context->parser = new FLACParser(context->source);
  return reinterpret_cast<intptr_t>(context);
}

FUNC(jobject, flacDecodeMetadata, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacJni(env, thiz);
  if (!context->parser->init()) {
=======

  Context() {
    source = new JavaDataSource();
    parser = new FLACParser(source);
  }

  ~Context() {
    delete parser;
    delete source;
  }
};

DECODER_FUNC(jlong, flacInit) {
  Context *context = new Context;
  if (!context->parser->init()) {
    delete context;
    return 0;
  }
  return reinterpret_cast<intptr_t>(context);
}

DECODER_FUNC(jobject, flacDecodeMetadata, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacDecoderJni(env, thiz);
  if (!context->parser->decodeMetadata()) {
>>>>>>> google/release-v2
    return NULL;
  }

  const FLAC__StreamMetadata_StreamInfo &streamInfo =
      context->parser->getStreamInfo();

  jclass cls = env->FindClass(
<<<<<<< HEAD
      "com/google/android/exoplayer/util/"
=======
      "com/google/android/exoplayer2/util/"
>>>>>>> google/release-v2
      "FlacStreamInfo");
  jmethodID constructor = env->GetMethodID(cls, "<init>", "(IIIIIIIJ)V");

  return env->NewObject(cls, constructor, streamInfo.min_blocksize,
                        streamInfo.max_blocksize, streamInfo.min_framesize,
                        streamInfo.max_framesize, streamInfo.sample_rate,
                        streamInfo.channels, streamInfo.bits_per_sample,
                        streamInfo.total_samples);
}

<<<<<<< HEAD
FUNC(jint, flacDecodeToBuffer, jlong jContext, jobject jOutputBuffer) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacJni(env, thiz);
=======
DECODER_FUNC(jint, flacDecodeToBuffer, jlong jContext, jobject jOutputBuffer) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacDecoderJni(env, thiz);
>>>>>>> google/release-v2
  void *outputBuffer = env->GetDirectBufferAddress(jOutputBuffer);
  jint outputSize = env->GetDirectBufferCapacity(jOutputBuffer);
  return context->parser->readBuffer(outputBuffer, outputSize);
}

<<<<<<< HEAD
FUNC(jint, flacDecodeToArray, jlong jContext, jbyteArray jOutputArray) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacJni(env, thiz);
=======
DECODER_FUNC(jint, flacDecodeToArray, jlong jContext, jbyteArray jOutputArray) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->source->setFlacDecoderJni(env, thiz);
>>>>>>> google/release-v2
  jbyte *outputBuffer = env->GetByteArrayElements(jOutputArray, NULL);
  jint outputSize = env->GetArrayLength(jOutputArray);
  int count = context->parser->readBuffer(outputBuffer, outputSize);
  env->ReleaseByteArrayElements(jOutputArray, outputBuffer, 0);
  return count;
}

<<<<<<< HEAD
FUNC(jlong, flacGetLastTimestamp, jlong jContext) {
=======
DECODER_FUNC(jlong, flacGetDecodePosition, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
  return context->parser->getDecodePosition();
}

DECODER_FUNC(jlong, flacGetLastTimestamp, jlong jContext) {
>>>>>>> google/release-v2
  Context *context = reinterpret_cast<Context *>(jContext);
  return context->parser->getLastTimestamp();
}

<<<<<<< HEAD
FUNC(jlong, flacGetSeekPosition, jlong jContext, jlong timeUs) {
=======
DECODER_FUNC(jlong, flacGetSeekPosition, jlong jContext, jlong timeUs) {
>>>>>>> google/release-v2
  Context *context = reinterpret_cast<Context *>(jContext);
  return context->parser->getSeekPosition(timeUs);
}

<<<<<<< HEAD
FUNC(void, flacFlush, jlong jContext) {
=======
DECODER_FUNC(jstring, flacGetStateString, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
  const char *str = context->parser->getDecoderStateString();
  return env->NewStringUTF(str);
}

DECODER_FUNC(void, flacFlush, jlong jContext) {
>>>>>>> google/release-v2
  Context *context = reinterpret_cast<Context *>(jContext);
  context->parser->flush();
}

<<<<<<< HEAD
FUNC(void, flacRelease, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
  delete context->parser;
  delete context->source;
=======
DECODER_FUNC(void, flacReset, jlong jContext, jlong newPosition) {
  Context *context = reinterpret_cast<Context *>(jContext);
  context->parser->reset(newPosition);
}

DECODER_FUNC(void, flacRelease, jlong jContext) {
  Context *context = reinterpret_cast<Context *>(jContext);
>>>>>>> google/release-v2
  delete context;
}
