package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "resenas")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull
    @Column(name = "id_resena")
    private int idReview;

    @NotNull
    @Column(name = "id_compra")
    private int idShopping;

    @NotBlank(message = "Date of purchase cannot be blank")    
    @Size(min = 1, message = "The comment must have at least 1 character")
    @Column(name = "comentario")
    private String commentary;

    @Min(value = 1, message = "The score must be at least 1")
    @Max(value = 5, message = "The score cannot exceed 5")
    @Column(name = "puntuacion")
    private int score;

    @NotNull
    @CreationTimestamp
    @Column(name = "fecha_resena", nullable = false, updatable = false)
    private LocalDateTime reviewDate;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Shopping shopping;

    //GET AND SET
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int id_review) {
        this.idReview = idReview;
    }

    public int getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(int idShopping) {
        this.idShopping = idShopping;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
