package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle,String> {
}
