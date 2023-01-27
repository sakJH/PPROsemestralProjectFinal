package uhk.sakac.recenzentiuhk.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "actor")
@Table(name = "actor", uniqueConstraints = @UniqueConstraint(name = "actor_name_unique", columnNames = "name"))
public class Actor {

    @Id
    @SequenceGenerator(name = "actor_id_sequence", sequenceName = "actor_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String actorName;

    @Column(name = "imageUrl", nullable = true, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(String name, String imageUrl, Set<Movie> movies) {
        this.actorName = name;
        this.imageUrl = imageUrl;
        this.movies = movies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Actor [id=" + id + ", name=" + actorName + ", imageUrl=" + imageUrl + "]";
    }

}