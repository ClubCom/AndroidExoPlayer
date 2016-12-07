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
<<<<<<< HEAD:library/src/androidTest/java/com/google/android/exoplayer/extractor/ExtractorTest.java
package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.C;
=======
package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.C;
>>>>>>> google/release-v2:library/src/androidTest/java/com/google/android/exoplayer2/extractor/ExtractorTest.java
import junit.framework.TestCase;

/**
 * Unit test for {@link Extractor}.
 */
public class ExtractorTest extends TestCase {

  public static void testConstants() {
    // Sanity check that constant values match those defined by {@link C}.
    assertEquals(C.RESULT_END_OF_INPUT, Extractor.RESULT_END_OF_INPUT);
    // Sanity check that the other constant values don't overlap.
    assertTrue(C.RESULT_END_OF_INPUT != Extractor.RESULT_CONTINUE);
    assertTrue(C.RESULT_END_OF_INPUT != Extractor.RESULT_SEEK);
  }

}