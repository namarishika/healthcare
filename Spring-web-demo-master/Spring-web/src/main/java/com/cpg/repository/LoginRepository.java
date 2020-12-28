package com.cpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpg.models.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

}
