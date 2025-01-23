package campus.u2.entrysystem.carnet.infrastructure;

import campus.u2.entrysystem.carnet.application.CarnetRepository;
import campus.u2.entrysystem.carnet.domain.Carnet;
import campus.u2.entrysystem.people.domain.People;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CarnetAdapter implements CarnetRepository{
    
    //Atributtes 
    private final CarnetJpaRepository carnetRepository; 
    
    // Constructor 
    public CarnetAdapter(CarnetJpaRepository carnetRepository){
        this.carnetRepository = carnetRepository; 
    }
    
    //Methods 
    
    //To create a carnet 
    @Override
    public Carnet saveCarnet(Carnet carnet) {
        return carnetRepository.save(carnet);
    }

    // To create a carnet for a person
    @Override
    public Carnet saveCarnet(People people, Carnet carnet) {
        carnet.setPeople(people);
        people.setCarnet(carnet);
        return carnetRepository.save(carnet);
    }

    // To show all the carnets 
    @Override
    public List<Carnet> getAllCarnets() {
        return carnetRepository.findAll();
    }

    // To find a carnet for the id
    @Override
    public Optional<Carnet> getCarnetById(Long id) {
        return carnetRepository.findById(id);
    }
    
    // To find a carnet by the people 
    @Override
    public Optional<Carnet> findCarnetByPeople(People people) {
        return carnetRepository.findByPeople(people);
    }
    
}
