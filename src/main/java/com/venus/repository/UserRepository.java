package com.venus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.venus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);

	List<User> findByRule(int rule);

	@Query("Select u from User u where u.fullName like '%:1%' or u.email like '%?1%' or u.id like '%?1%' ")
	List<User> search(String keyword);
}
