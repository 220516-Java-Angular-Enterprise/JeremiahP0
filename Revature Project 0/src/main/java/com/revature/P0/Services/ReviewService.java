package com.revature.P0.Services;

import com.revature.P0.DataBase.ReviewDAO;
import com.revature.P0.models.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public List<Review> getReviewByGame(String id){return reviewDAO.getAll();}


}
