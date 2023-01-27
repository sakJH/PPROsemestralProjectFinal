package uhk.sakac.recenzentiuhk.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity(name = "movie")
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(name = "movie_name_unique", columnNames = "name"))
public class Movie {

    @Id
    @SequenceGenerator(name = "movie_id_sequence", sequenceName = "movie_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "release_date", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd") // default html date type input date pattern "yyyy-mm-dd"
    private Date premiere;

    @Column(name = "rating", nullable = true, columnDefinition = "Decimal(10,2)")
    private Double rating;

    @Column(name = "genre", nullable = false, columnDefinition = "TEXT")
    private String genre;

    @Column(name = "poster", nullable = true, columnDefinition = "TEXT")
    private String poster;


    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "movie_actor", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "actor_id") })
    private Set<Actor> actors;


    public Movie() {
    }

    public Movie(String name, Date releaseDate, Double rating, String genre, String poster, String description,
                 Set<Actor> actors ){
        this.name = name;
        this.premiere = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.poster = poster;
        this.description = description;
        this.actors = actors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPremiere() {
        return premiere;
    }

    public void setPremiere(Date premiere) {
        this.premiere = premiere;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }


    public void isUserAvailableInFavouriteMovie(){

    }

    public void isUserAvailableInMovieWatchlist(){

    }



    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", releaseDate=" + premiere + ", rating=" + rating + ", genre="
                + genre + ", poster=" + poster + ", description=" + description + ", actors=" + actors + "]";
    }
}

