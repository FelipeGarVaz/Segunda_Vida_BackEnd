package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Review;
import com.servicios_web.segunda_vida_backend.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //Endpoint que maneja solicitudes HHTP GET y devuelve una lista de productos
    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    //Crear resenia
    @PostMapping
    public void createReview (@RequestBody Review review) {
        reviewService.save(review);
    }

    //Buscar pod ID
    @GetMapping("{id_review}")
    public ResponseEntity<Review> getByID_Review(@PathVariable Integer id_review) {
        Review review = reviewService.getByID_Review(id_review);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    //Actualizar resenia
    @PutMapping("{id_review}")
    public ResponseEntity<?> update(@RequestBody Review review, @PathVariable Integer id_review) {
        try {
            Review auxReview = reviewService.getByID_Review(id_review);
            review.setId_review(auxReview.getId_review());
            reviewService.save(review);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }
    //Eliminar resenia
    @DeleteMapping("{id_review}")
    public void delete(@PathVariable Integer id_review) {
        reviewService.delete(id_review);
    }
}
