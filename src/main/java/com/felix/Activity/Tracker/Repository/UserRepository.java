package com.felix.Activity.Tracker.Repository;

import com.felix.Activity.Tracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT * FROM users WHERE email = ?1" , nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
