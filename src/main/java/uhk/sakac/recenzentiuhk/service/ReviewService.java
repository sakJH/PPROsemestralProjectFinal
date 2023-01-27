package uhk.sakac.recenzentiuhk.service;

import org.springframework.stereotype.Service;
import uhk.sakac.recenzentiuhk.model.Review;

public interface ReviewService {
    public void saveNewReview(Review review);

    public void deleteReview(Long id);

    public Review getSingleReview(Long id);
}
