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
<<<<<<< HEAD:library/src/main/java/com/google/android/exoplayer/hls/HlsMediaPlaylist.java
package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.C;
=======
package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.C;
>>>>>>> google/release-v2:library/src/main/java/com/google/android/exoplayer2/source/hls/playlist/HlsMediaPlaylist.java
import java.util.List;

/**
 * Represents an HLS media playlist.
 */
public final class HlsMediaPlaylist extends HlsPlaylist {

  /**
   * Media segment reference.
   */
  public static final class Segment implements Comparable<Long> {

    public final String url;
    public final double durationSecs;
    public final int discontinuitySequenceNumber;
    public final long startTimeUs;
    public final boolean isEncrypted;
    public final String encryptionKeyUri;
    public final String encryptionIV;
    public final long byterangeOffset;
    public final long byterangeLength;
<<<<<<< HEAD:library/src/main/java/com/google/android/exoplayer/hls/HlsMediaPlaylist.java
=======

    public Segment(String uri, long byterangeOffset, long byterangeLength) {
      this(uri, 0, -1, C.TIME_UNSET, false, null, null, byterangeOffset, byterangeLength);
    }
>>>>>>> google/release-v2:library/src/main/java/com/google/android/exoplayer2/source/hls/playlist/HlsMediaPlaylist.java

    public Segment(String uri, double durationSecs, int discontinuitySequenceNumber,
        long startTimeUs, boolean isEncrypted, String encryptionKeyUri, String encryptionIV,
        long byterangeOffset, long byterangeLength) {
      this.url = uri;
      this.durationSecs = durationSecs;
      this.discontinuitySequenceNumber = discontinuitySequenceNumber;
      this.startTimeUs = startTimeUs;
      this.isEncrypted = isEncrypted;
      this.encryptionKeyUri = encryptionKeyUri;
      this.encryptionIV = encryptionIV;
      this.byterangeOffset = byterangeOffset;
      this.byterangeLength = byterangeLength;
    }

    @Override
    public int compareTo(Long startTimeUs) {
      return this.startTimeUs > startTimeUs ? 1 : (this.startTimeUs < startTimeUs ? -1 : 0);
    }
  }

  public static final String ENCRYPTION_METHOD_NONE = "NONE";
  public static final String ENCRYPTION_METHOD_AES_128 = "AES-128";

  public final int mediaSequence;
  public final int targetDurationSecs;
  public final int version;
  public final Segment initializationSegment;
  public final List<Segment> segments;
  public final boolean live;
  public final long durationUs;

  public HlsMediaPlaylist(String baseUri, int mediaSequence, int targetDurationSecs, int version,
      boolean live, Segment initializationSegment, List<Segment> segments) {
    super(baseUri, HlsPlaylist.TYPE_MEDIA);
    this.mediaSequence = mediaSequence;
    this.targetDurationSecs = targetDurationSecs;
    this.version = version;
    this.live = live;
    this.initializationSegment = initializationSegment;
    this.segments = segments;

    if (!segments.isEmpty()) {
      Segment last = segments.get(segments.size() - 1);
      durationUs = last.startTimeUs + (long) (last.durationSecs * C.MICROS_PER_SECOND);
    } else {
      durationUs = 0;
    }
  }

}
