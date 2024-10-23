package com.servicios_web.segunda_vida_backend.Model;

public class ReviewRequest {
    private Integer idShopping;
    private String commentary;
    private int score;

    public Integer getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(Integer idShopping) {
        this.idShopping = idShopping;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}

