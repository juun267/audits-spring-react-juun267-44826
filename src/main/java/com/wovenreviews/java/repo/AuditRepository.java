package com.wovenreviews.java.repo;

import com.wovenreviews.java.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<Audit, Integer> {

    List<Audit> findByMessageLikeIgnoreCase(String message);
}
