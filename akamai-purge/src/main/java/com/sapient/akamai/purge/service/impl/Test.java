package com.sapient.akamai.purge.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.sapient.akamai.cache.model.AkamaiStatusResponse;
import com.sapient.akamai.edgegrid.signature.ClientCredential;
import com.sapient.akamai.edgegrid.signature.DefaultCredential;
import com.sapient.akamai.edgegrid.signature.EdgeGridV1Signer;
import com.sapient.akamai.edgegrid.signature.RequestSigner;
import com.sapient.akamai.exception.RequestSigningException;
import com.sapient.akamai.util.HttpUtils;

public class Test {

	public static void main(String[] args) throws URISyntaxException, IOException, RequestSigningException {
		String host = "akab-wolvn3rputjmj56x-772dllwjosr7rfst.purge.akamaiapis.net";
		String clienttoken = "akab-oizq6vxv422di2hz-rj3ztuc2abqv3bbz";
		String accesstoken = "akab-lxkcnmolvvsaqzee-uyadyf7jiixhbymt";
		String clientsecret = "qCj2cnZ20UvVccYokpOPiV2U4qI1ANIfy8oee0+i/0A=";
		RequestSigner signer = new EdgeGridV1Signer(Collections.<String> emptyList(), 1024 * 2);
		
		long start = System.currentTimeMillis();
		final HttpRequest req = HttpUtils.constructHttpRequest(HttpMethods.GET, "https://akab-wolvn3rputjmj56x-772dllwjosr7rfst.purge.akamaiapis.net/ccu/v2/queues/default");
		HttpHeaders headers = req.getHeaders();
		headers.set("Host", "akab-wolvn3rputjmj56x-772dllwjosr7rfst.purge.akamaiapis.net");
		headers.setContentType("application/json");
		ClientCredential credential = new DefaultCredential(host, clienttoken, accesstoken, clientsecret);
		HttpRequest signedRequest = signer.sign(req, credential);
		HttpResponse response = signedRequest.execute();
		long end = System.currentTimeMillis();
		System.out.println("Total time :"+ (end - start));
				
		/*PrintStream out = new PrintStream(new FileOutputStream("D://test-papi.txt"));
		System.setOut(out);*/
		
		System.out.println(response.getStatusCode());
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		AkamaiStatusResponse res = mapper.readValue(response.parseAsString(), AkamaiStatusResponse.class);
		System.out.println(res.toString());
		//System.out.println(response.parseAsString());
		
	}
}
