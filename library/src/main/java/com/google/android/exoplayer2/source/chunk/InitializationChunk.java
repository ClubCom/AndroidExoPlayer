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
<<<<<<< HEAD:library/src/main/java/com/google/android/exoplayer/chunk/InitializationChunk.java
package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.chunk.ChunkExtractorWrapper.SingleTrackOutput;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
=======
package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper.SingleTrackMetadataOutput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
>>>>>>> google/release-v2:library/src/main/java/com/google/android/exoplayer2/source/chunk/InitializationChunk.java
import java.io.IOException;

/**
 * A {@link Chunk} that uses an {@link Extractor} to decode initialization data for single track.
 */
public final class InitializationChunk extends Chunk implements SingleTrackMetadataOutput,
    TrackOutput {

  private final ChunkExtractorWrapper extractorWrapper;

  // Initialization results. Set by the loader thread and read by any thread that knows loading
  // has completed. These variables do not need to be volatile, since a memory barrier must occur
  // for the reading thread to know that loading has completed.
  private Format sampleFormat;
  private SeekMap seekMap;

  private volatile int bytesLoaded;
  private volatile boolean loadCanceled;

  /**
   * @param dataSource The source from which the data should be loaded.
   * @param dataSpec Defines the data to be loaded.
   * @param trackFormat See {@link #trackFormat}.
   * @param trackSelectionReason See {@link #trackSelectionReason}.
   * @param trackSelectionData See {@link #trackSelectionData}.
   * @param extractorWrapper A wrapped extractor to use for parsing the initialization data.
   */
  public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format trackFormat,
      int trackSelectionReason, Object trackSelectionData,
      ChunkExtractorWrapper extractorWrapper) {
    super(dataSource, dataSpec, C.DATA_TYPE_MEDIA_INITIALIZATION, trackFormat, trackSelectionReason,
        trackSelectionData, C.TIME_UNSET, C.TIME_UNSET);
    this.extractorWrapper = extractorWrapper;
  }

  @Override
  public long bytesLoaded() {
    return bytesLoaded;
  }

  /**
   * Returns a {@link Format} parsed from the chunk, or null.
   * <p>
   * Should be called after loading has completed.
   */
  public Format getSampleFormat() {
    return sampleFormat;
  }

  /**
   * Returns a {@link SeekMap} parsed from the chunk, or null.
   * <p>
   * Should be called after loading has completed.
   */
  public SeekMap getSeekMap() {
    return seekMap;
  }

  // SingleTrackMetadataOutput implementation.

  @Override
  public void seekMap(SeekMap seekMap) {
    this.seekMap = seekMap;
  }

  // TrackOutput implementation.

  @Override
  public void format(Format format) {
    this.sampleFormat = format;
  }

  @Override
  public int sampleData(ExtractorInput input, int length, boolean allowEndOfInput)
      throws IOException, InterruptedException {
    throw new IllegalStateException("Unexpected sample data in initialization chunk");
  }

  @Override
  public void sampleData(ParsableByteArray data, int length) {
    throw new IllegalStateException("Unexpected sample data in initialization chunk");
  }

  @Override
  public void sampleMetadata(long timeUs, @C.BufferFlags int flags, int size, int offset,
      byte[] encryptionKey) {
    throw new IllegalStateException("Unexpected sample data in initialization chunk");
  }

  // Loadable implementation.

  @Override
  public void cancelLoad() {
    loadCanceled = true;
  }

  @Override
  public boolean isLoadCanceled() {
    return loadCanceled;
  }

  @SuppressWarnings("NonAtomicVolatileUpdate")
  @Override
  public void load() throws IOException, InterruptedException {
    DataSpec loadDataSpec = Util.getRemainderDataSpec(dataSpec, bytesLoaded);
    try {
      // Create and open the input.
      ExtractorInput input = new DefaultExtractorInput(dataSource,
          loadDataSpec.absoluteStreamPosition, dataSource.open(loadDataSpec));
      if (bytesLoaded == 0) {
        // Set the target to ourselves.
        extractorWrapper.init(this, this);
      }
      // Load and decode the initialization data.
      try {
        int result = Extractor.RESULT_CONTINUE;
        while (result == Extractor.RESULT_CONTINUE && !loadCanceled) {
          result = extractorWrapper.read(input);
        }
      } finally {
        bytesLoaded = (int) (input.getPosition() - dataSpec.absoluteStreamPosition);
      }
    } finally {
      dataSource.close();
    }
  }

}
