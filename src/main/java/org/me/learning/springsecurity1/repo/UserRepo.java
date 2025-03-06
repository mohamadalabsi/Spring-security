package org.me.learning.springsecurity1.repo;

import org.apache.catalina.User;
import org.me.learning.springsecurity1.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where u.username  =?1")
    Users findByUsername(String username);
}
