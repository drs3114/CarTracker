package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDao extends JpaRepository<Email, String> {
}
