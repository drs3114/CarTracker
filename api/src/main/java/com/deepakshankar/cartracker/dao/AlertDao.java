package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a JPA repository that provides the necessary function to perform CRUD operations on the {@link Alert} object.
 *
 * @author Deepak Shankar
 */
@Repository
public interface AlertDao extends JpaRepository<Alert, String> {
}
