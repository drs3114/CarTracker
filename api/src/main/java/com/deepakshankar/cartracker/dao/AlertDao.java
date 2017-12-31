package com.deepakshankar.cartracker.dao;

import com.deepakshankar.cartracker.entity.Alert;
import com.deepakshankar.cartracker.entity.AlertType;
import com.deepakshankar.cartracker.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a JPA repository that provides the necessary function to perform CRUD operations on the {@link Alert} object.
 *
 * @author Deepak Shankar
 */
@Repository
public interface AlertDao extends JpaRepository<Alert, String> {

    /**
     * Used to find all the alerts for a given {@link Vehicle}
     *
     * @param vehicle the vehicle for which the alerts are needed.
     * @return list of {@link Alert}s for the vehicle
     */
    public List<Alert> findAlertByVehicle(final Vehicle vehicle);

    /**
     * Used to find all the alerts for a given {@link Vehicle} and {@link Alert} priority.
     *
     * @param vehicle the vehicle for which the alerts are needed.
     * @param type    the priority of the alerts needed.
     * @return list of {@link Alert}s for the vehicle.
     */
    public List<Alert> findAlertByVehicleAndAndType(final Vehicle vehicle, final AlertType type);

    /**
     * Used to find the alerts for all the vehicles for a given {@link Alert} type
     *
     * @param type the type of {@link Alert}s needed
     * @return list of {@link Alert}s for the given type.
     */
    public List<Alert> findAlertByType(final AlertType type);
}
