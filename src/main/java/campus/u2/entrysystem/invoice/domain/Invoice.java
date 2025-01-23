package campus.u2.entrysystem.invoice.domain;

import campus.u2.entrysystem.membership.domain.Membership;
import campus.u2.entrysystem.people.domain.People;
import campus.u2.entrysystem.porters.domain.Porters;
import jakarta.persistence.*;
import java.util.Date;



@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idMembership")
    private Membership membership;
    
    @ManyToOne
    @JoinColumn(name= "idPeople")
    private People people;
    
    @ManyToOne
    @JoinColumn(name= "idPorter")
    private Porters porter;

    public Invoice() {
    }

    public Invoice(Date date, Boolean status, Membership membership, People people, Porters porter) {
        this.date = date;
        this.status = status;
        this.membership = membership;
        this.people = people;
        this.porter = porter;
    }


    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Porters getPorter() {
        return porter;
    }

    public void setPorter(Porters porter) {
        this.porter = porter;
    }

    @Override
    public String toString() {
        return "Invoice{" + "idInvoice=" + idInvoice +
                ", date=" + date +
                ", status=" + status +
                ", membership=" + membership +
                ", people=" + people +
                ", porter=" + porter + '}';
    }
    
    
    
}
