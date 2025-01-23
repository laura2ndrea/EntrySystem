package campus.u2.entrysystem.carnet.domain;

import campus.u2.entrysystem.people.domain.People;
import jakarta.persistence.*;

@Entity 
public class Carnet {
    // Atributtes 
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCarnet;
    
    @Column(nullable = false, unique = true)
    private String code; 
    
    @Column(nullable = false)
    private boolean status;
    
    @OneToOne
    @JoinColumn(name = "id_people", nullable = false)
    private People people; 
    
    // Constructor
    public Carnet() {
        this.status = true;
        generateCode(); 
    }
    
    // Getters and setters 
    public Long getId(){
        return idCarnet; 
    }
    
    public void setId(Long idCarnet) {
        this.idCarnet = idCarnet; 
    }
    
    public String getCode(){
        return code; 
    }
    
    public void setCode(String code){
        this.code = code; 
    }
    
    public boolean getStatus(){
        return status; 
    }
    
    public void setStatus(boolean status){
        this.status = status; 
    }
    
    public People getPeople (){
        return people; 
    }
    
    public void setPeople (People people) {
        this.people = people; 
    }
    
    // Methods 
    @Override
    public String toString() {
        return "Carnet{" + "idCarnet=" + idCarnet + ", code=" + code + ", status=" + status +  '}';
    }
    
    private void generateCode(){
        if(idCarnet == null || people == null || people.getCedula() == null) {
            throw new IllegalStateException("Cannot generate code: idCarnet or people.id is null"); 
        }
        this.code = idCarnet + String.valueOf(people.getCedula());
    }
    
}

