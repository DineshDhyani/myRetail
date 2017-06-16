package com.target.myeretail.appconfiguration;

import com.codahale.metrics.health.HealthCheck;

/**
 * dropwizard health check configuration
 * @author ramans
 *
 */
public class RetailAppHealthCheck extends HealthCheck {
    private final String version;
 
    public RetailAppHealthCheck(String version) {
        this.version = version;
    }
 
    /**
     * check the health of application
     */
    @Override
    protected Result check() throws Exception {
 
        return Result.healthy();
    }
}