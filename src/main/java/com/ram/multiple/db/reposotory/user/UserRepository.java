package com.ram.multiple.db.reposotory.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.multiple.db.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
