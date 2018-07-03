package ex.aaronfae.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ex.aaronfae.cloud.entity.User;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findOneById(@PathVariable("id") Long id);
}
