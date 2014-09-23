package com.softserveinc.softtour.repository;

import com.softserveinc.softtour.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
