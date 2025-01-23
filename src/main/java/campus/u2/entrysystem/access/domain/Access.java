package campus.u2.entrysystem.access.domain;

import campus.u2.entrysystem.accessnotes.domain.AccessNote;
import campus.u2.entrysystem.people.domain.People;
import campus.u2.entrysystem.porters.domain.Porters;
import campus.u2.entrysystem.vehicle.domain.Vehicle;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;



@Entity
@Table(name = "access")

public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccess;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date entryAccess;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date exitAccess;

    @Column(nullable = true)
    private boolean accessType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_people", nullable = true)
    private People people;

    @OneToMany(mappedBy = "access", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "access", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AccessNote> accessNotes = new ArrayList<>();
    
    
    
    @ManyToMany
    @JoinTable(
        name = "access_porters",
        joinColumns = @JoinColumn(name = "access_id"), 
        inverseJoinColumns = @JoinColumn(name = "porters_id") 
    )
    private Set<Porters> porters;

    
    

    public Access() {
    }

    public Access(Date entryAccess, Date exitAccess, boolean accessType) {
        this.entryAccess = entryAccess;
        this.exitAccess = exitAccess;
        this.accessType = accessType;
    }

    public Long getIdAccess() {
        return idAccess;
    }

    public void setIdAccess(Long idAccess) {
        this.idAccess = idAccess;
    }

    public Date getEntryAccess() {
        return entryAccess;
    }

    public void setEntryAccess(Date entryAccess) {
        this.entryAccess = entryAccess;
    }

    public Date getExitAccess() {
        return exitAccess;
    }

    public void setExitAccess(Date exitAccess) {
        this.exitAccess = exitAccess;
    }

    public boolean isAccessType() {
        return accessType;
    }

    public void setAccessType(boolean accessType) {
        this.accessType = accessType;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicles(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setAccess(this);
    }

    public void removeVehicles(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setAccess(null);
    }

    public List<AccessNote> getAccessNotes() {
        return accessNotes;
    }

    public void addAccessNotes(AccessNote accessNote) {
        this.accessNotes.add(accessNote);
        accessNote.setAccess(this);
    }

    public void removeAccessNotes(AccessNote accessNote) {
        this.accessNotes.remove(accessNote);
        accessNote.setAccess(null);
    }
    
    
     public Set<Porters> getPorters() {
        return porters;
    }

    public void setPorters(Set<Porters> porters) {
        this.porters = porters;
    }

    @Override
    public String toString() {
        return "Access{" + "idAccess=" + idAccess
                + ", entryAccess=" + entryAccess
                + ", exitAccess=" + exitAccess
                + ", accessType=" + accessType
                + '}';
    }

}
