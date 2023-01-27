package uhk.sakac.recenzentiuhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uhk.sakac.recenzentiuhk.model.Actor;
import uhk.sakac.recenzentiuhk.model.Movie;
import uhk.sakac.recenzentiuhk.service.implementation.ActorServiceImplementation;
import uhk.sakac.recenzentiuhk.service.implementation.MovieServiceImplementation;
import uhk.sakac.recenzentiuhk.utility.FileUpload;
import uhk.sakac.recenzentiuhk.utility.ImageType;

import java.util.List;


@Controller
public class MovieController {
    @Autowired
    private MovieServiceImplementation movieService;

    @Autowired
    private ActorServiceImplementation actorService;

    @RequestMapping("/movies")
    public String getHomeMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "user/home";
    }

    @RequestMapping("/movies/{id}")
    public String getMoviePreview(@PathVariable("id") long id, Model model) {
        try {
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movie", movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/movie/movie_preview";
    }

    @RequestMapping("/admin/movies")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @RequestMapping(value = "/admin/movies/add", method = RequestMethod.GET)
    public String addMovieForm(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/movie_form";
    }

    @RequestMapping(value = "/admin/movies/add", method = RequestMethod.POST)
    public String addMovie(Movie movie, BindingResult result, @RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(movie);
            return "admin/movie/movie_form";
        }
        try {
            String path = FileUpload.saveImage(ImageType.MOVIE_POSTER, movie.getName(), file);
            movie.setPoster(path);
            movieService.saveMovie(movie);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(movie);
            return "admin/movie/movie_form";
        }
        return "redirect:/admin/movies/";
    }

    // shows update form
    @RequestMapping(value = "/admin/movies/edit/{id}", method = RequestMethod.GET)
    public String updateMovieForm(@PathVariable("id") long id, Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        try {
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movieForm", movie);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "admin/movie/movie_form";
    }

}
