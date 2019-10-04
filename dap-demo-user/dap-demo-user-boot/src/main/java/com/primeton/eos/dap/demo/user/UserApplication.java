/**
 * 
 */
package com.primeton.eos.dap.demo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.primeton.eos.dap.sdk.api.bizflow.EnableSDKBizflows;

/**
 * @author wangwb
 *
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableSDKBizflows
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
