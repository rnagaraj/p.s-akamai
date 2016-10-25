package com.sapient.akamai.edgegrid.signature;

import com.sapient.akamai.util.CommonUtil;

/**
 * Default implementation of the {@link ClientCredential}.
 *
 */
public class DefaultCredential implements ClientCredential {

	/**
	 * The client token.
	 */
	private final String clientToken;
	
	/**
	 * The access token.
	 */
	private final String accessToken;
	
	/**
	 * The secret associated with the client token.
	 */
	private final String clientSecret;
	
	/** The host. */
	private final String host;
	
	/**
	 * Constructor.
	 * 
	 * @param host the Akamai host, cannot be null or empty.
	 * @param clientToken the client token, cannot be null or empty.
	 * @param accessToken the access token, cannot be null or empty.
	 * @param clientSecret the client secret, cannot be null or empty.
	 * 
	 * @throws IllegalArgumentException if any of the parameters is null or empty.
	 */
	public DefaultCredential(String host, String clientToken, String accessToken, String clientSecret) {
		if (CommonUtil.isNullOrEmpty(clientToken)) {
			throw new IllegalArgumentException("clientToken cannot be empty.");
		}
		if (CommonUtil.isNullOrEmpty(accessToken)) {
			throw new IllegalArgumentException("accessToken cannot be empty.");
		}
		if (CommonUtil.isNullOrEmpty(clientSecret)) {
			throw new IllegalArgumentException("clientSecret cannot be empty.");
		}
		if (CommonUtil.isNullOrEmpty(host)) {
			throw new IllegalArgumentException("clientSecret cannot be empty.");
		}
		this.host = host;
		this.clientToken = clientToken;
		this.accessToken = accessToken;
		this.clientSecret = clientSecret;
	}
	
	/**
	 * Gets the client token.
	 * @return The client token.
	 */
	public String getClientToken() {
		return clientToken;
	}

	/**
	 * Gets the access token.
	 * @return the access token.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Gets the secret associated with the client token.
	 * @return the secret.
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	public String getHost() {
		return host;
	}

}
