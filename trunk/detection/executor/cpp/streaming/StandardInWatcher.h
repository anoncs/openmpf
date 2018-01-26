/******************************************************************************
 * NOTICE                                                                     *
 *                                                                            *
 * This software (or technical data) was produced for the U.S. Government     *
 * under contract, and is subject to the Rights in Data-General Clause        *
 * 52.227-14, Alt. IV (DEC 2007).                                             *
 *                                                                            *
 * Copyright 2018 The MITRE Corporation. All Rights Reserved.                 *
 ******************************************************************************/

/******************************************************************************
 * Copyright 2018 The MITRE Corporation                                       *
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


#ifndef MPF_STANDARDINWATCHER_H
#define MPF_STANDARDINWATCHER_H

#include <atomic>


namespace MPF { namespace COMPONENT {

    class StandardInWatcher {
    public:
        bool QuitReceived() const;

        // Singleton to prevent more than one thread from reading from standard in.
        static StandardInWatcher* GetInstance();

    private:
        StandardInWatcher();

        static StandardInWatcher* instance_;

        // static because in the event of an error elsewhere, the detached thread will still be running and may access
        // is_time_to_quit_ and error_message_.
        static std::atomic_bool quit_received_;
        static std::string error_message_;

        static void Watch();
        static void SetError(std::string &&error_message);

    };
}}



#endif //MPF_STANDARDINWATCHER_H
