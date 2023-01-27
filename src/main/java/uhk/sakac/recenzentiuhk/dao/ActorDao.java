package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.Actor;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ActorDao extends JpaRepository<Actor, Long> {
    Actor findActorById(long UserId);

    Actor findActorByActorName(String name);

    //List<Actor> findAllByActorName();
}
