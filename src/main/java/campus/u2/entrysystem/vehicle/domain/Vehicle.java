package campus.u2.entrysystem.vehicle.domain;


import campus.u2.entrysystem.people.domain.People;
import campus.u2.entrysystem.access.domain.Access;
import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;

    @Column(unique = true, nullable = false)
    private String plate;

    private boolean vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_people", nullable = false)
    private People people;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAccess", nullable = true)
    private Access access;

    public Vehicle() {
    }

    public Vehicle(String plate, boolean vehicleType) {
        this.plate = plate;
        this.vehicleType = vehicleType;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long id_Vehicle) {
        this.idVehicle = id_Vehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(boolean vehicleType) {
        this.vehicleType = vehicleType;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "idVehicle=" + idVehicle
                + ", plate=" + plate
                + ", vehicleType=" + vehicleType
                + '}';
    }

}
