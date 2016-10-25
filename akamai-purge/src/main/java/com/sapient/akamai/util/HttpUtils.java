package com.sapient.akamai.util;

import java.io.IOException;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.StringUtils;

/**
 * The Class HttpUtils.
 */
public class HttpUtils {

	/** The Constant WEB_CLIENT. */
	private static final HttpRequestFactory WEB_CLIENT;

	static {
		WEB_CLIENT = new NetHttpTransport().createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(final HttpRequest request) {
				request.setThrowExceptionOnExecuteError(false);
				request.setReadTimeout(90000);
				request.setConnectTimeout(90000);
			}
		});
	}

	/**
	 * Instantiates a new http utils.
	 */
	private HttpUtils() {

	}

	/**
	 * Construct http request.
	 * 
	 * @param method
	 *            the method
	 * @param targetUrl
	 *            the target url
	 * @return the http request
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static HttpRequest constructHttpRequest(final String method, final String targetUrl) throws IOException {

		return WEB_CLIENT.buildRequest(method, new GenericUrl(targetUrl), null);
	}

	/**
	 * Construct http request.
	 * 
	 * @param method
	 *            the method
	 * @param targetUrl
	 *            the target url
	 * @param content
	 *            the content
	 * @return the http request
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static HttpRequest constructHttpRequest(final String method, final String targetUrl, final String content)
			throws IOException {

		return WEB_CLIENT.buildRequest(method, new GenericUrl(targetUrl),
				new ByteArrayContent(null, StringUtils.getBytesUtf8(content)));
	}

}
