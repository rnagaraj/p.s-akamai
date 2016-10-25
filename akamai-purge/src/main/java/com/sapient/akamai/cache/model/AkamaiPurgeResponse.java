package com.sapient.akamai.cache.model;

/**
 * 
 * Example:
 * 
 * {
	  "estimatedSeconds": 420,
	  "progressUri": "/ccu/v2/purges/57799d8b-10e4-11e4-9088-62ece60caaf0",
	  "purgeId": "57799d8b-10e4-11e4-9088-62ece60caaf0",
	  "supportId": "17PY1405953363409286-284546144",
	  "httpStatus": 201,
	  "detail": "Request accepted.",
	  "pingAfterSeconds": 420
	}
	
	{
	  "supportId": "17PY1405953590785649-285344864",
	  "title": "queue is full",
	  "httpStatus": 507,
	  "detail": "User's queue is full",
	  "describedBy": "https://api.ccu.akamai.com/ccu/v2/errors/queue-is-full"
	}
 */
public class AkamaiPurgeResponse
{
	private Integer estimatedSeconds;
	private String progressUri;
	private String purgeId;
	private String supportId;
	private Integer httpStatus;
	private String detail;
	private String describedBy;
	private Integer pingAfterSeconds;
	private String title;

	/**
	 * Returns the {@code estimatedSeconds} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code estimatedSeconds} of this {@link AkamaiPurgeResponse}.
	 */
	public Integer getEstimatedSeconds() {
		return estimatedSeconds;
	}

	/**
	 * Sets the {@code estimatedSeconds} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param estimatedSeconds The {@code estimatedSeconds} to set.
	 */
	public void setEstimatedSeconds(Integer estimatedSeconds) {
		this.estimatedSeconds = estimatedSeconds;
	}

	/**
	 * Returns the {@code progressUri} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code progressUri} of this {@link AkamaiPurgeResponse}.
	 */
	public String getProgressUri() {
		return progressUri;
	}

	/**
	 * Sets the {@code progressUri} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param progressUri The {@code progressUri} to set.
	 */
	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	/**
	 * Returns the {@code purgeId} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code purgeId} of this {@link AkamaiPurgeResponse}.
	 */
	public String getPurgeId() {
		return purgeId;
	}

	/**
	 * Sets the {@code purgeId} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param purgeId The {@code purgeId} to set.
	 */
	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	/**
	 * Returns the {@code supportId} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code supportId} of this {@link AkamaiPurgeResponse}.
	 */
	public String getSupportId() {
		return supportId;
	}

	/**
	 * Sets the {@code supportId} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param supportId The {@code supportId} to set.
	 */
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

	/**
	 * Returns the {@code httpStatus} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code httpStatus} of this {@link AkamaiPurgeResponse}.
	 */
	public Integer getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the {@code httpStatus} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param httpStatus The {@code httpStatus} to set.
	 */
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Returns the {@code detail} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code detail} of this {@link AkamaiPurgeResponse}.
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Sets the {@code detail} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param detail The {@code detail} to set.
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * Returns the {@code describedBy} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return	Returns the {@code describedBy} of this {@link AkamaiPurgeResponse}.
	 */
	public String getDescribedBy() {
		return describedBy;
	}

	/**
	 * Sets the {@code describedBy} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param describedBy	The {@code describedBy} to set.
	 */
	public void setDescribedBy(String describedBy) {
		this.describedBy = describedBy;
	}

	/**
	 * Returns the {@code pingAfterSeconds} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return Returns the {@code pingAfterSeconds} of this {@link AkamaiPurgeResponse}.
	 */
	public Integer getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	/**
	 * Sets the {@code pingAfterSeconds} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param pingAfterSeconds The {@code pingAfterSeconds} to set.
	 */
	public void setPingAfterSeconds(Integer pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

	/**
	 * Returns the {@code title} of this {@link AkamaiPurgeResponse}.
	 *
	 * @return	Returns the {@code title} of this {@link AkamaiPurgeResponse}.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the {@code title} of this {@link AkamaiPurgeResponse}.
	 * 
	 * @param title	The {@code title} to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
