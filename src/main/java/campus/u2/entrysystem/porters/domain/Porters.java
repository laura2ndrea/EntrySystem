package campus.u2.entrysystem.porters.domain;

import campus.u2.entrysystem.user.domain.User;
import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.utilities.BaseClassPeople;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
public class Porters extends BaseClassPeople {

    
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)

    private Date employmentDate;
    
    


    
    
    
    private Boolean position;

    @ManyToOne
    @JoinColumn(name = "id_jefe")
    private Porters id_jefe;
    
    
     @ManyToMany(mappedBy = "porters") 
    private Set<Access> accesses;
     
     
     @OneToOne(mappedBy = "porter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
     private User user;


    public Porters() {
    }

    public Porters(Date employmentDate, Boolean position, Porters id_jefe, String name, String cedula, String telefono) {
        super(name, cedula, telefono);
        this.employmentDate = employmentDate;
        this.position = position;
        this.id_jefe = id_jefe;
    }

    public Porters(Date employmentDate, Boolean position, Porters id_jefe, Long id, String name, String cedula, String telefono) {
        super(id, name, cedula, telefono);
        this.employmentDate = employmentDate;
        this.position = position;
        this.id_jefe = id_jefe;

    }

    public Porters(Date employmentDate, Boolean position, String name, String cedula, String telefono) {
        super(name, cedula, telefono);
        this.employmentDate = employmentDate;
        this.position = position;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Boolean getPosition() {
        return position;
    }

    public void setPosition(Boolean position) {
        this.position = position;
    }

    public Porters getId_jefe() {
        return id_jefe;
    }

    public void setId_jefe(Porters id_jefe) {
        this.id_jefe = id_jefe;
    }

  
    public Set<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(Set<Access> accesses) {
        this.accesses = accesses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Porters{" + "employmentDate=" + employmentDate + ", position=" + position + ", id_jefe=" + id_jefe + ", accesses=" + accesses + ", user=" + user + '}';
    }
    
    


 

}
