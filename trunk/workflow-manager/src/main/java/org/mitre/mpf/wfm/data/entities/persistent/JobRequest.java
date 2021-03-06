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

package org.mitre.mpf.wfm.data.entities.persistent;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.mitre.mpf.wfm.enums.BatchJobStatusType;

/**
 * This class includes the essential information which describes a batch job. Instances of this class are stored in a
 * persistent data store (as opposed to a transient data store).
 */
@Entity
public class JobRequest {

	public JobRequest() { }

	/** The unique numeric identifier for this job.
	 * Using SEQUENCE rather than IDENTITY to avoid conflicts between batch and streaming job Ids
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	public long getId() { return id; }

	/** The timestamp indicating when the server received this job. */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeReceived;
	public Date getTimeReceived() { return timeReceived; }
	public void setTimeReceived(Date timeReceived) { this.timeReceived = timeReceived; }

	/** The timestamp indicating when the server completed this job.*/
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeCompleted;
	public Date getTimeCompleted() { return timeCompleted; }
	public void setTimeCompleted(Date timeCompleted) { this.timeCompleted = timeCompleted; }
	
	/** The priority of the job set when creating the job.*/
	@Column	
	private int priority;
	public int getPriority() { return  priority; }
	public void setPriority(int priority) { this.priority = priority; }

	/** The current status of this batch job. */
	@Column
	@Enumerated(EnumType.STRING)
	private BatchJobStatusType status;
	public BatchJobStatusType getStatus() { return status; }
	public void setStatus(BatchJobStatusType status) { this.status = status; }

	@Column
	@Lob
	private byte[] inputObject;
	public byte[] getInputObject() { return inputObject; }
	public void setInputObject(byte[] inputObject) { this.inputObject = inputObject; }

	@Column
	private String outputObjectPath;
	public String getOutputObjectPath() { return outputObjectPath; }
	public void setOutputObjectPath(String outputObjectPath) { this.outputObjectPath = outputObjectPath; }

	@Column
	private String pipeline;
	public String getPipeline() { return pipeline; }
	public void setPipeline(String pipeline) { this.pipeline = pipeline; }

	/** The version of the output object. */
	@Column
	private String outputObjectVersion;
	public void setOutputObjectVersion(String outputObjectVersion) { this.outputObjectVersion = outputObjectVersion; }

	public String toString() { return String.format("%s#<id='%d'>", this.getClass().getSimpleName(), getId()); }
}
