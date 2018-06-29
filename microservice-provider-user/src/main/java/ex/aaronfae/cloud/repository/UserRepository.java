package ex.aaronfae.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ex.aaronfae.cloud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
