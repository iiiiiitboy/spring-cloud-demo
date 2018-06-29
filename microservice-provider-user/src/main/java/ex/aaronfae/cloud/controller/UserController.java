package ex.aaronfae.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import ex.aaronfae.cloud.entity.User;
import ex.aaronfae.cloud.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Registration registration;

	@GetMapping("/user/{id}")
	public User findOneById(@PathVariable Long id) {
		return this.userRepository.findOne(id);
	}

	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
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
