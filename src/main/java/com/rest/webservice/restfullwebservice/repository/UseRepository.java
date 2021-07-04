package com.rest.webservice.restfullwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservice.restfullwebservice.dto.User;

@Repository
public interface UseRepository extends JpaRepository<User, Integer>{

}
