/******************************************************************************
 * NOTICE                                                                     *
 *                                                                            *
 * This software (or technical data) was produced for the U.S. Government     *
 * under contract, and is subject to the Rights in Data-General Clause        *
 * 52.227-14, Alt. IV (DEC 2007).                                             *
 *                                                                            *
 * Copyright 2016 The MITRE Corporation. All Rights Reserved.                 *
 ******************************************************************************/

/******************************************************************************
 * Copyright 2016 The MITRE Corporation                                       *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/

package org.mitre.mpf.frameextractor;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FrameExtractorTests {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FrameExtractorTests.class);

    static {
        String libraryFile = new File("install/lib/libmpfopencvjni.so").getAbsolutePath();
        log.info("Loading libmpfopencvjni library from '{}'.", libraryFile);
        System.load(libraryFile);
    }

    @Test
    public void testFrameExtractor() {
        try {
            File sourceFile = new File("video-overlay/src/test/resources/samples/five-second-marathon-clip.mkv");

            if(!sourceFile.exists()) {
                throw new IOException(String.format("File not found %s.", sourceFile.getAbsolutePath()));
            }

            File outputDirectory = new File("/tmp", UUID.randomUUID().toString());
            outputDirectory.mkdir();

            FrameExtractor extractor = new FrameExtractor(sourceFile.toURI(), outputDirectory.toURI());
            extractor.getFrames().add(2);
            extractor.getFrames().add(4);
            extractor.getFrames().add(8);
            extractor.execute();
            // Assert.assertTrue("The size of the output video must be greater than 4096.", destinationFile.length() > 4096);

        } catch(IOException ioe) {
            Assert.fail(String.format("Encountered an exception when none was expected. %s", ioe));
        }
    }

}