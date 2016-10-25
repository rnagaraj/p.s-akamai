package com.sapient.akamai.edgegrid.signature;


import com.google.api.client.http.HttpRequest;
import com.sapient.akamai.exception.RequestSigningException;

/**
 * Interface describing a request signer that signs service requests.
 *
 */
public interface RequestSigner {
	
	/**
	 * Signs a request with the client credential.
	 * 
	 * @param request the request to sign.
	 * @param credential the credential used in the signing.
	 * @return the signed request.
	 * @throws RequestSigningException
	 */
	public HttpRequest sign(HttpRequest request, ClientCredential credential) throws RequestSigningException;
}
