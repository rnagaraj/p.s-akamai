package com.sapient.akamai.cache.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response from Akamai when checking the status of a previously submitted purge request
 * 
 * Example:
 * 
 * {
	  "originalEstimatedSeconds": 420,
	  "originalQueueLength": 0,
	  "supportId": "17SY1405954814899441-292938848",
	  "httpStatus": 200,
	  "purgeId": "57799d8b-10e4-11e4-9088-62ece60caaf0",
	  "completionTime": "2014-07-21T14:42:18Z",
	  "submittedBy": "client_name",
	  "purgeStatus": "In-Progress",
	  "submissionTime": "2014-07-21T14:39:30Z"
	  "pingAfterSeconds": 60
	}
 */

public class AkamaiStatusResponse
{
	private static final String ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssX";
	
	@JsonIgnore(value=true)
	private Integer originalEstimatedSeconds;
	
	@JsonIgnore(value=true)
	private String progressUri;
	
	@JsonIgnore(value=true)
	private Integer originalQueueLength;
	
	private String supportId;
	private Integer httpStatus;

	@JsonIgnore(value=true)
	private String purgeId;
	
	@JsonIgnore(value=true)
	private String submittedBy;
	
	@JsonIgnore(value=true)
	private String purgeStatus;
	
	@JsonIgnore(value=true)
	private Integer pingAfterSeconds;
	
	private Integer queueLength;
	private String detail;
	
	@JsonFormat(pattern = ISO_DATE_TIME)
	private Date completionTime;
	
	@JsonFormat(pattern = ISO_DATE_TIME)
	private Date submissionTime;

	/**
	 * Returns the {@code originalEstimatedSeconds} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code originalEstimatedSeconds} of this {@link AkamaiStatusResponse}.
	 */
	public Integer getOriginalEstimatedSeconds() {
		return originalEstimatedSeconds;
	}

	/**
	 * Sets the {@code originalEstimatedSeconds} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param originalEstimatedSeconds The {@code originalEstimatedSeconds} to set.
	 */
	public void setOriginalEstimatedSeconds(Integer originalEstimatedSeconds) {
		this.originalEstimatedSeconds = originalEstimatedSeconds;
	}

	/**
	 * Returns the {@code originalQueueLength} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code originalQueueLength} of this {@link AkamaiStatusResponse}.
	 */
	public Integer getOriginalQueueLength() {
		return originalQueueLength;
	}

	/**
	 * Sets the {@code originalQueueLength} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param originalQueueLength The {@code originalQueueLength} to set.
	 */
	public void setOriginalQueueLength(Integer originalQueueLength) {
		this.originalQueueLength = originalQueueLength;
	}

	/**
	 * Returns the {@code supportId} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code supportId} of this {@link AkamaiStatusResponse}.
	 */
	public String getSupportId() {
		return supportId;
	}

	/**
	 * Sets the {@code supportId} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param supportId The {@code supportId} to set.
	 */
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

	/**
	 * Returns the {@code httpStatus} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code httpStatus} of this {@link AkamaiStatusResponse}.
	 */
	public Integer getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the {@code httpStatus} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param httpStatus The {@code httpStatus} to set.
	 */
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Returns the {@code purgeId} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code purgeId} of this {@link AkamaiStatusResponse}.
	 */
	public String getPurgeId() {
		return purgeId;
	}

	/**
	 * Sets the {@code purgeId} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param purgeId The {@code purgeId} to set.
	 */
	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	/**
	 * Returns the {@code completionTime} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code completionTime} of this {@link AkamaiStatusResponse}.
	 */
	public Date getCompletionTime() {
		return completionTime;
	}

	/**
	 * Sets the {@code completionTime} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param completionTime The {@code completionTime} to set.
	 */
	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	/**
	 * Returns the {@code submittedBy} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code submittedBy} of this {@link AkamaiStatusResponse}.
	 */
	public String getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * Sets the {@code submittedBy} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param submittedBy The {@code submittedBy} to set.
	 */
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * Returns the {@code purgeStatus} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code purgeStatus} of this {@link AkamaiStatusResponse}.
	 */
	public String getPurgeStatus() {
		return purgeStatus;
	}

	/**
	 * Sets the {@code purgeStatus} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param purgeStatus The {@code purgeStatus} to set.
	 */
	public void setPurgeStatus(String purgeStatus) {
		this.purgeStatus = purgeStatus;
	}

	/**
	 * Returns the {@code submissionTime} of this {@link AkamaiStatusResponse}.
	 *
	 * @return Returns the {@code submissionTime} of this {@link AkamaiStatusResponse}.
	 */
	public Date getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * Sets the {@code submissionTime} of this {@link AkamaiStatusResponse}.
	 * 
	 * @param submissionTime The {@code submissionTime} to set.
	 */
	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	/**
	 * @return the pingAfterSeconds
	 */
	public Integer getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	/**
	 * @param pingAfterSeconds the pingAfterSeconds to set
	 */
	public void setPingAfterSeconds(Integer pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

	/**
	 * @return the progressUri
	 */
	public String getProgressUri() {
		return progressUri;
	}

	/**
	 * @param progressUri the progressUri to set
	 */
	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	public Integer getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(Integer queueLength) {
		this.queueLength = queueLength;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
