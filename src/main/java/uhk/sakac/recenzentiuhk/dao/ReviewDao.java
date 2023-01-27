package uhk.sakac.recenzentiuhk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uhk.sakac.recenzentiuhk.model.Review;
import uhk.sakac.recenzentiuhk.model.User;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReviewDao extends JpaRepository<Review, Long> {

    Review findByReviewId(long reviewId);

    //Review findByComment(String comment);

    List<Review> findByUserId(long userId);
    List<Review> findAllByUser(User user);

}
