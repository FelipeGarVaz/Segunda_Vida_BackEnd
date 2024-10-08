package com.servicios_web.segunda_vida_backend.Controller;

import com.servicios_web.segunda_vida_backend.Model.Review;
import com.servicios_web.segunda_vida_backend.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Review", description = "Methods and documentation for reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Obtener todas las reseñas
    @Operation(summary = "Get all reviews")
    @ApiResponse(responseCode = "200", description = "Found reviews", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) })
    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    // Crear una nueva reseña
    @Operation(summary = "Register a review")
    @ApiResponse(responseCode = "201", description = "Review registered", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) })
    @PostMapping
    public void createReview(@RequestBody Review review) {
        reviewService.save(review);
    }

    // Buscar reseña por ID
    @Operation(summary = "Get a review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid review id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content) })
    @GetMapping("{id_review}")
    public ResponseEntity<Review> getByID_Review(@PathVariable Integer id_review) {
        Review review = reviewService.getByID_Review(id_review);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    // Actualizar una reseña
    @Operation(summary = "Update a review")
    @ApiResponse(responseCode = "200", description = "Review updated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) })
    @PutMapping("{id_review}")
    public ResponseEntity<?> update(@RequestBody Review review, @PathVariable Integer id_review) {
        try {
            Review auxReview = reviewService.getByID_Review(id_review);
            review.setId_review(auxReview.getId_review());
            reviewService.save(review);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the provided id is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una reseña
    @Operation(summary = "Delete a review")
    @ApiResponse(responseCode = "204", description = "Review deleted")
    @DeleteMapping("{id_review}")
    public void delete(@PathVariable Integer id_review) {
        reviewService.delete(id_review);
    }
}
