package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.Movie;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MovieDao extends JpaRepository<Movie, Long> {
    Movie findById(long movieId);
    Movie findByName(String name);

    List<Movie> findAll();


}
