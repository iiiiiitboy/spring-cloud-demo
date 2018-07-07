package ex.aaronfae.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import ex.aaronfae.config.FeignConfiguration;
import ex.aaronfae.cloud.entity.User;
import feign.Param;
import feign.RequestLine;

@FeignClient(value = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {

	@RequestLine(value = "GET /user/{id}")
	User findOneById(@Param("id") Long id);
}
