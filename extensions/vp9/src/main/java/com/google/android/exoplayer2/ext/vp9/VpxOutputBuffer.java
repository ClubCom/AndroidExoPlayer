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
package com.google.android.exoplayer2.ext.vp9;

<<<<<<< HEAD:extensions/vp9/src/main/java/com/google/android/exoplayer/ext/vp9/VpxOutputBuffer.java
import com.google.android.exoplayer.util.extensions.OutputBuffer;
=======
import com.google.android.exoplayer2.decoder.OutputBuffer;
>>>>>>> google/release-v2:extensions/vp9/src/main/java/com/google/android/exoplayer2/ext/vp9/VpxOutputBuffer.java
import java.nio.ByteBuffer;

/**
 * Output buffer containing video frame data, populated by {@link VpxDecoder}.
 */
<<<<<<< HEAD:extensions/vp9/src/main/java/com/google/android/exoplayer/ext/vp9/VpxOutputBuffer.java
public final class VpxOutputBuffer extends OutputBuffer {
=======
/* package */ final class VpxOutputBuffer extends OutputBuffer {
>>>>>>> google/release-v2:extensions/vp9/src/main/java/com/google/android/exoplayer2/ext/vp9/VpxOutputBuffer.java

  public static final int COLORSPACE_UNKNOWN = 0;
  public static final int COLORSPACE_BT601 = 1;
  public static final int COLORSPACE_BT709 = 2;

  private final VpxDecoder owner;

  public int mode;
  /**
   * RGB buffer for RGB mode.
   */
  public ByteBuffer data;
  public int width;
  public int height;
  /**
   * YUV planes for YUV mode.
   */
  public ByteBuffer[] yuvPlanes;
  public int[] yuvStrides;
  public int colorspace;

<<<<<<< HEAD:extensions/vp9/src/main/java/com/google/android/exoplayer/ext/vp9/VpxOutputBuffer.java
  /* package */ VpxOutputBuffer(VpxDecoder owner) {
=======
  public VpxOutputBuffer(VpxDecoder owner) {
>>>>>>> google/release-v2:extensions/vp9/src/main/java/com/google/android/exoplayer2/ext/vp9/VpxOutputBuffer.java
    this.owner = owner;
  }

  @Override
  public void release() {
    owner.releaseOutputBuffer(this);
<<<<<<< HEAD:extensions/vp9/src/main/java/com/google/android/exoplayer/ext/vp9/VpxOutputBuffer.java
=======
  }

  /**
   * Initializes the buffer.
   *
   * @param timeUs The presentation timestamp for the buffer, in microseconds.
   * @param mode The output mode. One of {@link VpxDecoder#OUTPUT_MODE_NONE},
   *     {@link VpxDecoder#OUTPUT_MODE_RGB} and {@link VpxDecoder#OUTPUT_MODE_YUV}.
   */
  public void init(long timeUs, int mode) {
    this.timeUs = timeUs;
    this.mode = mode;
>>>>>>> google/release-v2:extensions/vp9/src/main/java/com/google/android/exoplayer2/ext/vp9/VpxOutputBuffer.java
  }

  /**
   * Resizes the buffer based on the given dimensions. Called via JNI after decoding completes.
   */
  public void initForRgbFrame(int width, int height) {
    this.width = width;
    this.height = height;
    this.yuvPlanes = null;

    int minimumRgbSize = width * height * 2;
    initData(minimumRgbSize);
  }

  /**
   * Resizes the buffer based on the given stride. Called via JNI after decoding completes.
   */
  public void initForYuvFrame(int width, int height, int yStride, int uvStride,
      int colorspace) {
    this.width = width;
    this.height = height;
    this.colorspace = colorspace;

    int yLength = yStride * height;
    int uvLength = uvStride * ((height + 1) / 2);
    int minimumYuvSize = yLength + (uvLength * 2);
    initData(minimumYuvSize);

    if (yuvPlanes == null) {
      yuvPlanes = new ByteBuffer[3];
    }
    // Rewrapping has to be done on every frame since the stride might have changed.
    yuvPlanes[0] = data.slice();
    yuvPlanes[0].limit(yLength);
    data.position(yLength);
    yuvPlanes[1] = data.slice();
    yuvPlanes[1].limit(uvLength);
    data.position(yLength + uvLength);
    yuvPlanes[2] = data.slice();
    yuvPlanes[2].limit(uvLength);
    if (yuvStrides == null) {
      yuvStrides = new int[3];
    }
    yuvStrides[0] = yStride;
    yuvStrides[1] = uvStride;
    yuvStrides[2] = uvStride;
  }

  private void initData(int size) {
    if (data == null || data.capacity() < size) {
      data = ByteBuffer.allocateDirect(size);
    } else {
      data.position(0);
      data.limit(size);
    }
  }

}
