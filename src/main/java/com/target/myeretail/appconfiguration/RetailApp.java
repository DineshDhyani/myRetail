package com.target.myeretail.appconfiguration;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.eclipse.jetty.server.session.SessionHandler;

import com.target.myeretail.resources.ProductAPI;

import java.lang.reflect.*;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;


public class RetailApp extends Application<RetailAppConfig> {

	public static void main(String[] args) throws Exception {
		new RetailApp().run(args);

		System.setProperty("file.encoding", "UTF-8");
		Field charset = Charset.class.getDeclaredField("defaultCharset");
		charset.setAccessible(true);
		charset.set(null, null);
	}

	/**
	 * initalize the swagger configuration
	 */
	@Override
	public void initialize(Bootstrap<RetailAppConfig> bootstrap) {

		bootstrap.addBundle(new SwaggerBundle<RetailAppConfig>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(RetailAppConfig configuration) {
				// this would be the preferred way to set up swagger, you can
				// also construct the object here programtically if you want
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

	/**
	 * run method
	 */
	@Override
	public void run(RetailAppConfig config, Environment env) throws UnknownHostException {
		env.jersey().register(new ProductAPI());

		env.servlets().setSessionHandler(new SessionHandler());
		final RetailAppHealthCheck healthCheck = new RetailAppHealthCheck(config.getVersion());
		env.healthChecks().register("template", healthCheck);
		env.jersey().register(healthCheck);

	}

}