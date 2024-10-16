package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Review;
import com.servicios_web.segunda_vida_backend.Repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Review> reviews = reviewRepository.findAll(pageReq);
        return reviews.getContent();
    }

    public void save (Review review) {
        reviewRepository.save(review);
    }

    public Review getByIdReview(Integer idReview){
        return reviewRepository.findById(idReview).get();
    }

    public void delete(Integer idReview){
        reviewRepository.deleteById(idReview);
    }

    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findAllByUserIdJPQL(userId);
    }

}
