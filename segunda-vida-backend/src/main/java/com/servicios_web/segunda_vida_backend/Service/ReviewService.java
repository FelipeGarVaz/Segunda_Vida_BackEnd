package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Review;
import com.servicios_web.segunda_vida_backend.Repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save (Review review) {
        reviewRepository.save(review);
    }

    public Review getByID_Review(Integer id_review){
        return reviewRepository.findById(id_review).get();
    }

    public void delete(Integer id_review){
        reviewRepository.deleteById(id_review);
    }
}
