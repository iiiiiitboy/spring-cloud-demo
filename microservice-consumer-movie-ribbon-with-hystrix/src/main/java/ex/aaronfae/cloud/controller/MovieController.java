package ex.aaronfae.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ex.aaronfae.cloud.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Registration registration;

	@GetMapping("/movie/user/{id}")
	@HystrixCommand(fallbackMethod = "defaultUser")
	public User findOneById(@PathVariable Long id) {
		return this.restTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/user/" + id, User.class);
	}

	/**
	 * 方法签名与返回值要跟原方法一样
	 * @param id
	 * @return
	 */
	public User defaultUser(Long id) {
		User user = new User();
		user.setId(0L);
		user.setName("用户跑丢了");
		return user;
	}

	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-CONSUMER-MOVIE-RIBBON", false);
		return instance.getHomePageUrl();
	}

	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		String serviceId = this.registration.getServiceId();
		List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
		ServiceInstance instance = instances.get(0);
		return instance;
	}
}
