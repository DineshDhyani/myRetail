package com.target.myeretail.appconfiguration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class RetailAppConfig extends Configuration {
	
	/**
	 * version
	 */
	@NotEmpty
	private String version;

	/**
	 * version 
	 * @return
	 */
	@JsonProperty
	public String getVersion() {
		return version;
	}

	/**
	 * sets the version
	 * @param version
	 */
	@JsonProperty
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * swagger configuration
	 */
	  @JsonProperty("swagger")
	    public SwaggerBundleConfiguration swaggerBundleConfiguration;
	  
	/* @Override
	  @JsonProperty("logging")
	    public LoggingFactory getLoggingFactory() {
	        return new LogbackAutoConfigLoggingFactory();
	    }
	  
	  */

}