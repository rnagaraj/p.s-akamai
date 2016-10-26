package com.sapient.akamai.purge.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.sapient.akamai.cache.model.AkamaiPurgeRequest;
import com.sapient.akamai.cache.model.AkamaiPurgeResponse;
import com.sapient.akamai.cache.model.AkamaiStatusResponse;
import com.sapient.akamai.constants.GlobalConstants;
import com.sapient.akamai.edgegrid.signature.ClientCredential;
import com.sapient.akamai.edgegrid.signature.DefaultCredential;
import com.sapient.akamai.edgegrid.signature.EdgeGridV1Signer;
import com.sapient.akamai.edgegrid.signature.RequestSigner;
import com.sapient.akamai.exception.RequestSigningException;
import com.sapient.akamai.purge.service.AkamaiPurgeService;
import com.sapient.akamai.util.CommonUtil;
import com.sapient.akamai.util.HttpUtils;

/**
 * The Class AkamaiCacheServiceImpl.
 */
@Service(value=AkamaiPurgeService.class)
@Component(immediate=true, metatype=false)
public class AkamaiPurgeServiceImpl implements AkamaiPurgeService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AkamaiPurgeServiceImpl.class);

	/** The akamai url. */
	private static String AKAMAI_URL = null;

	/** The akamai client token. */
	private static String AKAMAI_CLIENT_TOKEN = null;

	/** The akamai access token. */
	private static String AKAMAI_ACCESS_TOKEN = null;

	/** The akamai client secret. */
	private static String AKAMAI_CLIENT_SECRET = null;

	/** The mapper. */
	private ObjectMapper mapper;

	/**
	 * Activate.
	 * 
	 * @param properties the properties
	 * @throws Exception the exception
	 */
	@Activate
	protected final void activate(final Map<String, String> properties) throws Exception {

		mapper = new ObjectMapper();
		AKAMAI_URL = CommonUtil.getConfiguration(GlobalConstants.AKAMAI_SERVICE_PID, GlobalConstants.AKAMAI_PURGE_HOST);
		AKAMAI_CLIENT_TOKEN = CommonUtil.getConfiguration(GlobalConstants.AKAMAI_SERVICE_PID,
				GlobalConstants.AKAMAI_PURGE_CLIENTTOKEN);
		AKAMAI_ACCESS_TOKEN = CommonUtil.getConfiguration(GlobalConstants.AKAMAI_SERVICE_PID,
				GlobalConstants.AKAMAI_PURGE_ACCESSTOKEN);
		AKAMAI_CLIENT_SECRET = CommonUtil.getConfiguration(GlobalConstants.AKAMAI_SERVICE_PID,
				GlobalConstants.AKAMAI_PURGE_CLIENTSECRET);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sapient.akamai.purge.service.AkamaiPurgeService#purgeCache(java.util.List)
	 */
	@Override
	public AkamaiPurgeResponse purgeCache(List<String> paths) {
		
		AkamaiPurgeResponse akamaiResponse = null;
		boolean requestSuccess = false;
		if(!isQueueFull()) {
			if(CollectionUtils.isNotEmpty(paths)) {
				AkamaiPurgeRequest purgeRequest = new AkamaiPurgeRequest(paths);
				akamaiResponse = makePostRequest(purgeRequest);
				requestSuccess = true;
			}
		} else {
			LOG.error("AKAMAI Queue Length is full.");
			requestSuccess = false;
		}
		
		
		if(!requestSuccess){
			akamaiResponse = new AkamaiPurgeResponse();
			akamaiResponse.setHttpStatus(500);
		} 
		
		return akamaiResponse;

	}

	/* (non-Javadoc)
	 * @see com.sapient.akamai.purge.service.AkamaiPurgeService#purgeCache(java.lang.String, java.util.List)
	 */
	@Override
	public AkamaiPurgeResponse purgeCache(String type, List<String> paths) {
		AkamaiPurgeResponse akamaiResponse = null;
		boolean requestSuccess = false;
		if(!isQueueFull()) {
			if(StringUtils.isNotEmpty(type) && CollectionUtils.isNotEmpty(paths)) {
				AkamaiPurgeRequest purgeRequest = new AkamaiPurgeRequest(paths);
				purgeRequest.setType(type);
				akamaiResponse = makePostRequest(purgeRequest);
			}
		} else {
			LOG.error("AKAMAI Queue Length is full.");
			requestSuccess = false;
		}
		
		
		if(!requestSuccess){
			akamaiResponse = new AkamaiPurgeResponse();
			akamaiResponse.setHttpStatus(500);
		} 
		return akamaiResponse;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sapient.akamai.purge.service.AkamaiPurgeService#checkStatus(java.lang.String)
	 */
	@Override
	public AkamaiStatusResponse checkStatus(String url) {

		AkamaiStatusResponse akamaiStatusResponse = makeGetRequest(url);
		return akamaiStatusResponse;
	}

	/**
	 * Make get request.
	 *
	 * @param url the url
	 * @return the akamai status response
	 */
	private AkamaiStatusResponse makeGetRequest(String url) {
		
		AkamaiStatusResponse akamaiStatusResponse = null;
		try {
			if(StringUtils.isNotEmpty(url)) {
				String responseString = null;
				ClientCredential credentials = new DefaultCredential(AKAMAI_URL, AKAMAI_CLIENT_TOKEN, AKAMAI_ACCESS_TOKEN,
						AKAMAI_CLIENT_SECRET);
				String requestUrl = String.format("%s%s%s", GlobalConstants.HTTPS, credentials.getHost(), url);
				HttpRequest request = HttpUtils.constructHttpRequest(HttpMethods.GET, requestUrl);

				HttpHeaders headers = request.getHeaders();
				headers.set(GlobalConstants.HTTP_HEADER_HOST, credentials.getHost());
				headers.setContentType(GlobalConstants.CONTENT_TYPE_APPLICATION_JSON);

				RequestSigner signer = new EdgeGridV1Signer(Collections.<String> emptyList(), 1024 * 20);
				HttpRequest signedRequest = signer.sign(request, credentials);
				HttpResponse response = signedRequest.execute();
				responseString = response.parseAsString();
				akamaiStatusResponse = mapper.readValue(responseString, AkamaiStatusResponse.class);
				if (HttpStatusCodes.isSuccess(response.getStatusCode())) {
					LOG.info("Status Code : {}", response.getStatusCode());
					LOG.info("Purge Status : {}" + akamaiStatusResponse.getPurgeStatus());
					LOG.info("Progress Uri : {}" + akamaiStatusResponse.getProgressUri());
					LOG.info("JSON: " + mapper.writeValueAsString(akamaiStatusResponse));
				}
				else {
					LOG.info("Status Code : {}", response.getStatusCode());
					LOG.info("Purge Status : {}" + akamaiStatusResponse.getPurgeStatus());
					LOG.info("Check After {} seconds" + akamaiStatusResponse.getPingAfterSeconds());
					LOG.info("JSON: " + mapper.writeValueAsString(akamaiStatusResponse));
				}
			}
		} catch (IOException e) {
			LOG.error("I/O exception : ", e);
		} catch (RequestSigningException e) {
			LOG.error("Error while getting signature : ", e);
		}
		return akamaiStatusResponse;
	}

	/**
	 * Make POST request.
	 *
	 * @param purgeRequest the purge request
	 * @return the akamai purge response
	 */
	private AkamaiPurgeResponse makePostRequest(AkamaiPurgeRequest purgeRequest) {
		
		AkamaiPurgeResponse akamaiResponse = null;
		try {
			String purgeBody = mapper.writeValueAsString(purgeRequest);
			ClientCredential credentials = new DefaultCredential(AKAMAI_URL, AKAMAI_CLIENT_TOKEN, AKAMAI_ACCESS_TOKEN,
					AKAMAI_CLIENT_SECRET);
			String requestUrl = String.format("%s%s%s", GlobalConstants.HTTPS, credentials.getHost(),
					GlobalConstants.AKAMAI_PURGE_URL_DEFAULT);
			HttpRequest request = HttpUtils.constructHttpRequest(HttpMethods.POST, requestUrl, purgeBody);

			HttpHeaders headers = request.getHeaders();
			headers.set(GlobalConstants.HTTP_HEADER_HOST, credentials.getHost());
			headers.setContentType(GlobalConstants.CONTENT_TYPE_APPLICATION_JSON);

			RequestSigner signer = new EdgeGridV1Signer(Collections.<String> emptyList(), 1024 * 20);
			HttpRequest signedRequest = signer.sign(request, credentials);
			HttpResponse response = signedRequest.execute();
			String responseString = response.parseAsString();
			akamaiResponse = mapper.readValue(responseString, AkamaiPurgeResponse.class);
			if (HttpStatusCodes.isSuccess(response.getStatusCode())) {
				LOG.info("Successfully purged Akamai cache!");
				LOG.info("Status Code {}", response.getStatusCode());
				LOG.info("JSON: " + mapper.writeValueAsString(akamaiResponse));
			}
			else {
				LOG.error("Error clearing Akamai cache!");
				LOG.error("JSON: " + mapper.writeValueAsString(akamaiResponse));
			}
		} catch(JsonMappingException e) {
			LOG.error("Error while mapping the response : ", e);
		} catch (IOException e) {
			LOG.error("I/O exception : ", e);
		} catch (RequestSigningException e) {
			LOG.error("Error while getting signature : ", e);
		}
		
		return akamaiResponse;
	}
	
	/**
	 * Checks AKAMAI Purge Queue Length
	 * Returns false if Queue has reached 10,000 limit
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean isQueueFull(){

		boolean isQueueFull = false;
		AkamaiStatusResponse akamaiStatusResponse = makeGetRequest(GlobalConstants.AKAMAI_CHECK_QUEUE);
		
		if(akamaiStatusResponse != null){
			if(akamaiStatusResponse.getHttpStatus() == HttpStatusCodes.STATUS_CODE_OK){
				if(akamaiStatusResponse.getQueueLength() > 10000){
					LOG.error("AKAMAI Queue Length is full. QueueLength: {}", akamaiStatusResponse.getQueueLength());
					isQueueFull = true;
				}
			}
		}
		return isQueueFull;
	}
	

}