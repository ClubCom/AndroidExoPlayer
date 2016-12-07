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
package com.google.android.exoplayer2.metadata.id3;

/**
 * Base class for ID3 frames.
 */
<<<<<<< HEAD:extensions/opus/src/main/java/com/google/android/exoplayer/ext/opus/OpusDecoderException.java
public final class OpusDecoderException extends Exception {

  /* package */ OpusDecoderException(String message) {
    super(message);
=======
public abstract class Id3Frame {

  /**
   * The frame ID.
   */
  public final String id;

  public Id3Frame(String id) {
    this.id = id;
>>>>>>> google/release-v2:library/src/main/java/com/google/android/exoplayer2/metadata/id3/Id3Frame.java
  }

}
