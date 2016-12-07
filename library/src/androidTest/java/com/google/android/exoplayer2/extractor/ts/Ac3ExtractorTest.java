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
package com.google.android.exoplayer2.extractor.ts;

<<<<<<< HEAD:library/src/androidTest/java/com/google/android/exoplayer/TimeRangeTest.java
import com.google.android.exoplayer.TimeRange.StaticTimeRange;
import junit.framework.TestCase;
=======
import android.test.InstrumentationTestCase;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.testutil.TestUtil;
>>>>>>> google/release-v2:library/src/androidTest/java/com/google/android/exoplayer2/extractor/ts/Ac3ExtractorTest.java

/**
 * Unit test for {@link Ac3Extractor}.
 */
<<<<<<< HEAD:library/src/androidTest/java/com/google/android/exoplayer/TimeRangeTest.java
public class TimeRangeTest extends TestCase {

  @SuppressWarnings("SelfEquals")
  public void testStaticEquals() {
    TimeRange timeRange1 = new StaticTimeRange(0, 30000000);
    assertTrue(timeRange1.equals(timeRange1));

    TimeRange timeRange2 = new StaticTimeRange(0, 30000000);
    assertTrue(timeRange1.equals(timeRange2));
=======
public final class Ac3ExtractorTest extends InstrumentationTestCase {
>>>>>>> google/release-v2:library/src/androidTest/java/com/google/android/exoplayer2/extractor/ts/Ac3ExtractorTest.java

  public void testSample() throws Exception {
    TestUtil.assertOutput(new TestUtil.ExtractorFactory() {
      @Override
      public Extractor create() {
        return new Ac3Extractor();
      }
    }, "ts/sample.ac3", getInstrumentation());
  }

}
