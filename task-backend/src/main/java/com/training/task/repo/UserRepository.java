package com.training.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.task.entity.AppUsers;

@Repository
public interface UserRepository extends JpaRepository<AppUsers, Long> {

	AppUsers findByUsername(String username);

}
