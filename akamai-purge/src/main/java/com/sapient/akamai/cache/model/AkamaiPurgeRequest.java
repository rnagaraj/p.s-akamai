package com.sapient.akamai.cache.model;

import java.util.List;

/**
 * Representation of JSON sent to Akamai to purge URLs from the cache
 * 
 * Example:
 * 
 * {
	  "objects": [
	    "/f/4/6848/4h/www.foofoofoo.com/index.php",
	    "/f/4/6848/4h/www.oofoofoof.com/index2.php",
	    "http://www.example.com/graphics/picture.gif",
	    "http://www.example.com/documents/brochure.pdf"
	  ],
	  "action": "remove",
	  "type": "arl",
	  "domain": "production"
	}
 */
public class AkamaiPurgeRequest
{
	private List<String> objects;
	private String action = "remove";
	private String type = "arl";
	private String domain = "production";

	public AkamaiPurgeRequest() {
	
	}
	
	public AkamaiPurgeRequest(List<String> objects) {
		this.objects = objects;
	}
	
	public AkamaiPurgeRequest(String type, List<String> objects) {
		this.objects = objects;
		this.type = type;
	}
	
	/**
	 * Returns the {@code objects} of this {@link AkamaiPurgeRequest}.
	 *
	 * @return Returns the {@code objects} of this {@link AkamaiPurgeRequest}.
	 */
	public List<String> getObjects() {
		return objects;
	}

	/**
	 * Sets the {@code objects} of this {@link AkamaiPurgeRequest}.
	 * 
	 * @param objects The {@code objects} to set.
	 */
	public void setObjects(List<String> objects) {
		this.objects = objects;
	}

	/**
	 * Returns the {@code action} of this {@link AkamaiPurgeRequest}.
	 *
	 * @return Returns the {@code action} of this {@link AkamaiPurgeRequest}.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the {@code action} of this {@link AkamaiPurgeRequest}.
	 * 
	 * @param action The {@code action} to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Returns the {@code type} of this {@link AkamaiPurgeRequest}.
	 *
	 * @return Returns the {@code type} of this {@link AkamaiPurgeRequest}.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the {@code type} of this {@link AkamaiPurgeRequest}.
	 * 
	 * @param type The {@code type} to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the {@code domain} of this {@link AkamaiPurgeRequest}.
	 *
	 * @return Returns the {@code domain} of this {@link AkamaiPurgeRequest}.
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the {@code domain} of this {@link AkamaiPurgeRequest}.
	 * 
	 * @param domain The {@code domain} to set.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
}
