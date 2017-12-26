package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertDao extends JpaRepository<Alert, String> {
}
