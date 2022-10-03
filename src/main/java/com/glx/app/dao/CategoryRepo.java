package com.glx.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glx.app.dto.Category;

@Repository
public interface CategoryRepo extends JpaRepository <Category, Integer> {
	Boolean existsByName(String name);
}
