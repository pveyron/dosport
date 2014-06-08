package com.dosport.spring.beans.factory.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 
 * @author pwl
 * 
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private boolean localOverride = false;

	private Properties[] localProperties;

	private Resource[] locations;

	private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

	private boolean ignoreResourceNotFound = false;

	public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}

	public void setProperties(Properties properties) {
		this.localProperties = new Properties[] { properties };
		super.setProperties(properties);
	}

	public void setPropertiesArray(Properties[] propertiesArray) {
		this.localProperties = propertiesArray;
	}

	public void setLocation(Resource location) {
		this.locations = new Resource[] { location };
		super.setLocation(location);
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
		super.setLocations(locations);
	}

	public void setLocalOverride(boolean localOverride) {
		this.localOverride = localOverride;
	}

	public Properties mergeProperties() throws IOException {
		Properties result = new Properties();

		if (this.localOverride) {
			loadProperties(result);
		}

		if (this.localProperties != null) {
			for (Properties localProp : this.localProperties) {
				CollectionUtils.mergePropertiesIntoMap(localProp, result);
			}
		}

		if (!this.localOverride) {
			loadProperties(result);
		}

		return result;
	}

	@Override
	protected void loadProperties(Properties props) throws IOException {

		if (this.locations != null) {
			for (int i = 0; i < this.locations.length; i++) {
				Resource location = this.locations[i];
				if (logger.isInfoEnabled()) {
					logger.info("Loading properties file from " + location);
				}
				InputStream is = null;
				try {
					String runEnv = "dev";
					if (location.getFilename().indexOf("." + runEnv + ".") > 0) {
						is = location.getInputStream();
						this.propertiesPersister.load(props, is);
						break;
					}

				} catch (IOException ex) {
					if (this.ignoreResourceNotFound) {
						if (logger.isWarnEnabled()) {
							logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
						}
					} else {
						throw ex;
					}
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}
}
