package ex.aaronfae.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ex.aaronfae.cloud.feign.EurekaAppsInfoFeignClient;

@RestController
public class EurekaAppsInfoController {

	@Autowired
	private EurekaAppsInfoFeignClient eurekaAppsInfoFeignClient;

	@RequestMapping(value = "/movie/eureka/apps/{serviceName}", method = RequestMethod.GET)
	public String getServiceName(@PathVariable("serviceName") String serviceName) {
		return eurekaAppsInfoFeignClient.getServiceName(serviceName);
	}
}
