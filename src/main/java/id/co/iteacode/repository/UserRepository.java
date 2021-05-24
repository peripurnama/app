package id.co.iteacode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import id.co.iteacode.model.User;

import java.lang.String;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameOrEmail(String username, String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Page<User> findByDeletedFalseAndUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(
			String username, String email, Pageable pageable);
	
	Page<User> findByDeletedFalse(Pageable pageable);
}