package com.wovenreviews.java.repo;

import com.wovenreviews.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("""
                update User u set u.dailyEmailUpdates = :emailUpdates where u.email = :email
            """)
    int setEmailUpdates(@Param("email") String email, @Param("emailUpdates") Boolean emailUpdates);
}
