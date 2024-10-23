package com.servicios_web.segunda_vida_backend.Service;

import com.servicios_web.segunda_vida_backend.Model.Product;
import com.servicios_web.segunda_vida_backend.Model.Review;
import com.servicios_web.segunda_vida_backend.Model.Shopping;
import com.servicios_web.segunda_vida_backend.Repository.ReviewRepository;
import com.servicios_web.segunda_vida_backend.Repository.ShoppingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ShoppingRepository shoppingRepository;

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

    public Review createReview(Integer idShopping, String commentary, int score) {
        // Obtener la compra usando el id de la compra
        Shopping shopping = shoppingRepository.findById(idShopping)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // Verificar que el producto pertenece al vendedor
        Product product = shopping.getProduct();
        Integer idSeller = product.getUser().getIdUser();

        Review review = new Review();
        review.setIdShopping(idShopping);
        review.setIdSeller(idSeller);
        review.setCommentary(commentary);
        review.setScore(score);
        review.setReviewDate(LocalDateTime.now());
        // Guardar y retornar la reseña
        return reviewRepository.save(review);
    }
}
