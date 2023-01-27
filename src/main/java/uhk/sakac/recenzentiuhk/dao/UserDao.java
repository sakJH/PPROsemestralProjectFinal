package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.User;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(long userID);

    User findByEmail(String email);

    boolean existsById(long userID);

    @Query(value = "SELECT users.* FROM users WHERE  user_id > 1  ORDER BY user_id ASC", nativeQuery = true)
    List<User> findAllUser();

}
