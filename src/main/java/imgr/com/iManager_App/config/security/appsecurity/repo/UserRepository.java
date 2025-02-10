package imgr.com.iManager_App.config.security.appsecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import imgr.com.iManager_App.config.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

    Optional<User> findByUsername(String userName);
}
