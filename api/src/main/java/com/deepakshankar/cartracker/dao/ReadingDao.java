package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a JPA repository that provides the necessary function to perform CRUD operations on the {@link Reading} object.
 *
 * @author Deepak Shankar
 */
@Repository
public interface ReadingDao extends JpaRepository<Reading, String> {
    public List<Reading> findReadingsByVin(final String vin);
}
