package com.servicios_web.segunda_vida_backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resenas")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_resena")
    private int id_review;

    @Column(name = "id_compra")
    private int id_shopping;

    @Column(name = "comentario")
    private String commentary;

    @Column(name = "puntuacion")
    private int score;

    @Column(name = "fecha_resena")
    private String review_date;

    //GET AND SET
    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public int getId_shopping() {
        return id_shopping;
    }

    public void setId_shopping(int id_shopping) {
        this.id_shopping = id_shopping;
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

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }
}
