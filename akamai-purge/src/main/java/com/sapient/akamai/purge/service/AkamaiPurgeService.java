package com.sapient.akamai.purge.service;

import java.util.List;

import com.sapient.akamai.cache.model.AkamaiPurgeResponse;
import com.sapient.akamai.cache.model.AkamaiStatusResponse;


/**
 * REST services that call to Akamai to clear pages from cache.
 */
public interface AkamaiPurgeService {
	
	/**
	 * Calls Akamai to clear the cache of specified URLs.
	 * Default type is ARL
	 *
	 * @param paths the URLs to clear from cache
	 * @return the {@link AkamaiPurgeResponse} sent back from Akamai indicating success or failure
	 */
	public AkamaiPurgeResponse purgeCache(List<String> paths);
	
	/**
	 * Calls Akamai to clear the cache of specified URLs.
	 * Type can be 
	 * 1. ARL (lowercase)
	 * 2. CPCODE (lowercase)
	 *
	 * @param type the type
	 * @param paths the URLs to clear from cache
	 * @return the {@link AkamaiPurgeResponse} sent back from Akamai indicating success or failure
	 */
	public AkamaiPurgeResponse purgeCache(String type, List<String> paths);
	
	/**
	 * Checks the status of a previously submitted {@link AkamaiPurgeRequest}.
	 * The URL to call is contained in the {@link AkamaiPurgeResponse} returned from calling {@link
	 * AkamaiPurgeService.purgeCache()}
	 * 
	 * @param url the Akamai URL to check the status
	 * @return the {@link AkamaiStatusResponse} sent back from Akamai indicating if it's complete or pending
	 * @throws Exception if there was an error calling Akamai
	 */
	public AkamaiStatusResponse checkStatus(String url);

}
