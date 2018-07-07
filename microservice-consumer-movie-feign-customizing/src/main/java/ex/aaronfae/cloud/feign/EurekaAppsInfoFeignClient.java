package ex.aaronfae.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import ex.aaronfae.config.FeignConfiguration;
import feign.Param;
import feign.RequestLine;

@FeignClient(value = "eureka-apps-info", url = "http://localhost:8761", configuration = FeignConfiguration.class)
public interface EurekaAppsInfoFeignClient {

	@RequestLine(value = "GET /eureka/apps/{serviceName}")
	String getServiceName(@Param("serviceName") String serviceName);
}
