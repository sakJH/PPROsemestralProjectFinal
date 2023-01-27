package uhk.sakac.recenzentiuhk.model;

import jakarta.persistence.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.*;


@Entity
@Table(name = "review", uniqueConstraints = @UniqueConstraint(name = "review_content_unique", columnNames = "content"))
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(800)")
    private String content;

    @Column(name = "movieRating", nullable = false, columnDefinition = "INTEGER")
    private Integer movieRating;
    @Column(name = "dateTimeMilli", nullable = false, columnDefinition = "DATETIME")
    private Long dateTimeMilli;
    @Column(name = "likes", nullable = true, columnDefinition = "INTEGER")
    private Integer likes;
    @ElementCollection
    private List<String> comments;

    @OneToOne
    private User user;

    @ManyToOne
    private Movie movie;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Integer movieRating) {
        this.movieRating = movieRating;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Movie getMovie() {
        return movie;
    }

    public Long getDateTimeMilli() {
        return dateTimeMilli;
    }

    public void setDateTimeMilli(Long dateTimeMilli) {
        this.dateTimeMilli = dateTimeMilli;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDateTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(getDateTimeMilli()), ZoneId.systemDefault());
        return ldt.format(formatter);
    }
}
