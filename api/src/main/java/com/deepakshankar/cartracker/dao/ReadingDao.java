package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingDao extends JpaRepository<Reading,String> {
}
