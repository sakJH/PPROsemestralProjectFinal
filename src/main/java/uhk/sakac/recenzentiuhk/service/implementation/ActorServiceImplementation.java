package uhk.sakac.recenzentiuhk.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.dao.ActorDao;
import uhk.sakac.recenzentiuhk.model.Actor;
import uhk.sakac.recenzentiuhk.service.ActorService;

import java.util.List;

@Service
public class ActorServiceImplementation implements ActorService {
    @Autowired
    private ActorDao actorDao;

    @Override
    public void saveActor(Actor cast) {
        actorDao.save(cast);
    }

    @Override
    public List<Actor> getActors() {
        return actorDao.findAll();
    }

    @Override
    public void deleteActor(Actor actor) {
        actorDao.delete(actor);
    }

    @Override
    public Actor getActorById(Long id) {

        return actorDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Cast id: " + id));
    }

    public Actor findActorById(long userId){
        return  actorDao.findActorById(userId);
    }

    public Actor findActorByName(String name){
        return actorDao.findActorByActorName(name);
    }

    /*public List<Actor> findAll(){
        return actorDao.findAllByActorName();
    }*/

}
