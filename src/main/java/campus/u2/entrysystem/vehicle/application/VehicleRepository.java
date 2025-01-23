package campus.u2.entrysystem.vehicle.application;


import campus.u2.entrysystem.people.domain.People;
import campus.u2.entrysystem.vehicle.domain.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Vehicle saveVehicle(Vehicle vehicle);
    Vehicle savePeopleVehicle(People people, Vehicle vehicle);
    void deleteVehicle(Long id);
    Vehicle updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Optional<Vehicle> findVehicleByPlate(String plate);

}
