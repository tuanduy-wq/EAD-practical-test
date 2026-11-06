package edu.hust.sis.repository;

import edu.hust.sis.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {

    @Query("select ss from StudentScore ss join fetch ss.student join fetch ss.subject order by ss.id")
    List<StudentScore> findAllWithDetails();
}