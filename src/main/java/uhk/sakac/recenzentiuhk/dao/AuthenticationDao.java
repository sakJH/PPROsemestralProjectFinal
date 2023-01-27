package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.User;

@Repository
@EnableJpaRepositories
public interface AuthenticationDao extends JpaRepository<User, Long> {

}
