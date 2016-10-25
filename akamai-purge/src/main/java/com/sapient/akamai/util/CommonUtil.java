package com.sapient.akamai.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CommonUtil.
 */
public class CommonUtil {

	/** The Constant LOG. */
	public static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);
	
	/**
	 * Gets the configuration.
	 * 
	 * @param servicePID
	 *            the service pid
	 * @param key
	 *            the key
	 * @return the configuration
	 */
	public static String getConfiguration(final String servicePID, final String key) {
		return getConfig(servicePID, key);
	}

	/**
	 * Gets the configuration.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param pid
	 *            the pid
	 * @param key
	 *            the key
	 * @return the configuration
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getConfig(String pid, String key) {
		LOG.debug("getConfiguration('{}.xml','{}')", pid);
		Object propValues = null;
		if (null != pid && null != key) {
			try {
				BundleContext bundleContext = FrameworkUtil.getBundle(CommonUtil.class).getBundleContext();
				if (null != bundleContext) {
					ServiceReference configurationAdminReference = bundleContext.getServiceReference(ConfigurationAdmin.class.getName());
					if (null != configurationAdminReference) {
						ConfigurationAdmin confAdmin = (ConfigurationAdmin) bundleContext.getService(configurationAdminReference);
						Configuration configuration = confAdmin.getConfiguration(pid);
						if (null != configuration.getProperties()) {
							propValues = configuration.getProperties().get(key);
						}
						return (T) propValues;
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured in getConfigProps {}", e);
			}
		}
		return null;
	}
	
	/**
	 * Checks if is null or empty.
	 *
	 * @param s the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
}
