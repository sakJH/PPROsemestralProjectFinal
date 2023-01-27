package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.model.Actor;

import java.util.List;

public interface ActorService {
    void saveActor(Actor actor);

    List<Actor> getActors();

    void deleteActor(Actor actor);

    Actor getActorById(Long id) throws Exception;

}
