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

package org.mitre.mpf.wfm.data.entities.transients;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransientJob {
	private long id;
	public long getId() { return id; }

	private TransientPipeline pipeline;
	public TransientPipeline getPipeline() { return pipeline; }

	private int currentStage;
	public int getCurrentStage() { return currentStage; }
	public void setCurrentStage(int currentStage) { this.currentStage = currentStage; }

	private String externalId;
	public String getExternalId() { return externalId; }

	private int priority;
	public int getPriority() { return  priority; }

	private boolean outputEnabled;
	public boolean isOutputEnabled() { return outputEnabled; }

	private List<TransientMedia> media;
	public List<TransientMedia> getMedia() { return  media; }
	public void setMedia(List<TransientMedia> media) { this.media = media; }

	private Map<String, Map> overriddenAlgorithmProperties;
	public Map<String, Map> getOverriddenAlgorithmProperties() { return overriddenAlgorithmProperties; }
	public void setOverriddenAlgorithmProperties(Map<String, Map> overriddenAlgorithmProperties) { this.overriddenAlgorithmProperties = overriddenAlgorithmProperties; }

	private Map<String, String> overriddenJobProperties;
	public Map<String, String> getOverriddenJobProperties() { return overriddenJobProperties; }

    private boolean cancelled;
	public boolean isCancelled() { return cancelled; }

	private String callbackURL;
	public String getCallbackURL() { return callbackURL; }

	private String callbackMethod;
	public String getCallbackMethod() { return callbackMethod; }

	// Detection system properties for this job should be immutable, the detection system property values
	// shouldn't change once the job is created even if they are changed on the UI by an admin..
	// The detectionSystemPropertiesSnapshot contains the values of the detection system properties at the time this batch job was created.
    private TransientDetectionSystemProperties detectionSystemPropertiesSnapshot;
    public TransientDetectionSystemProperties getDetectionSystemPropertiesSnapshot() { return this.detectionSystemPropertiesSnapshot; }

	public TransientJob(long id,
						String externalId,
						TransientDetectionSystemProperties detectionSystemPropertiesSnapshot,
						TransientPipeline pipeline,
						int currentStage,
						int priority,
						boolean outputEnabled,
						boolean cancelled) {
		this.id = id;
		this.externalId = externalId;
		this.pipeline = pipeline;
		this.detectionSystemPropertiesSnapshot = detectionSystemPropertiesSnapshot;
		this.currentStage = currentStage;
		this.priority = priority;
		this.outputEnabled = outputEnabled;
		this.cancelled = cancelled;
		this.media = new ArrayList<>();
		this.overriddenAlgorithmProperties = new HashMap<>();
		this.overriddenJobProperties = new HashMap<>();
	}

	@JsonCreator
	public TransientJob(@JsonProperty("id") long id,
	                    @JsonProperty("externalId") String externalId,
                        @JsonProperty("detectionSystemPropertiesSnapshot") TransientDetectionSystemProperties detectionSystemPropertiesSnapshot,
                        @JsonProperty("pipeline") TransientPipeline pipeline,
	                    @JsonProperty("currentStage") int currentStage,
	                    @JsonProperty("priority") int priority,
	                    @JsonProperty("outputEnabled") boolean outputEnabled,
	                    @JsonProperty("cancelled") boolean cancelled,
						@JsonProperty("callbackURL") String callbackURL,
						@JsonProperty("callbackMethod") String callbackMethod) {
		this(id,externalId,detectionSystemPropertiesSnapshot,pipeline,currentStage,priority,outputEnabled,cancelled);
		this.callbackURL = callbackURL;
		this.callbackMethod = callbackMethod;
	}
}
