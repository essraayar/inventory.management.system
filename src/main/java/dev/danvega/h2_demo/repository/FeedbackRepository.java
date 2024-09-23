package dev.danvega.h2_demo.repository;
import dev.danvega.h2_demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}



/*
package dev.danvega.h2_demo.repository;

import dev.danvega.h2_demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // Tüm geri bildirimleri listele
    List<Feedback> findAll();

    // Geri bildirim ID'sine göre geri bildirim bul
    Feedback findByFeedbackId(Long feedbackId);

    // Müşteri ID'sine göre geri bildirimleri bul
    @Query("SELECT f FROM Feedback f WHERE f.customer.customerId = :customerId")
    List<Feedback> findByCustomerId(@Param("customerId") Long customerId);

    // Belirli bir yoruma göre geri bildirim bul
    @Query("SELECT f FROM Feedback f WHERE f.comment LIKE %:comment%")
    List<Feedback> findByCommentContaining(@Param("comment") String comment);

    // Derecelendirmeye göre geri bildirimleri bul
    @Query("SELECT f FROM Feedback f WHERE f.rating = :rating")
    List<Feedback> findByRating(@Param("rating") Integer rating);
}
*/
