package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.model.Movie;

import java.util.List;

public interface MovieService {
    void saveMovie(Movie movie);

    List<Movie> getMovies();

    void deleteMovie(Movie movie);

    Movie getMovieById(Long id) throws Exception;


}
