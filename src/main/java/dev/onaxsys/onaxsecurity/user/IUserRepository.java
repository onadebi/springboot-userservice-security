package dev.onaxsys.onaxsecurity.user;
import java.util.*;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserProfile, Integer> {

    Optional<UserProfile> findByEmail(String email);
    
} 