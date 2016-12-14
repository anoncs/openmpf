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

import java.io.IOException;

public class FrameExtractorJniException extends IOException {
    private int errorCode;
    public int getErrorCode() { return errorCode; }
    public void setErrorCode(int errorCode) { this.errorCode = errorCode; }

    public FrameExtractorJniException() { super(); }
    public FrameExtractorJniException(int errorCode) {
        this();
        this.errorCode = errorCode;
    }

    public FrameExtractorJniException(String message) { super(message); }
    public FrameExtractorJniException(String message, int errorCode) {
        this(message);
        this.errorCode = errorCode;
    }

    public FrameExtractorJniException(String message, Throwable cause) { super(message, cause); }
    public FrameExtractorJniException(String message, Throwable cause, int errorCode) {
        this(message, cause);
        this.errorCode = errorCode;
    }

    public FrameExtractorJniException(Throwable cause) { super(cause); }
    public FrameExtractorJniException(Throwable cause, int errorCode) {
        this(cause);
        this.errorCode = errorCode;
    }
}