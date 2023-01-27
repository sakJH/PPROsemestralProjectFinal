package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.Role;

@Repository
@EnableJpaRepositories
public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
