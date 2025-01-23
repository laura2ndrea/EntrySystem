package campus.u2.entrysystem.carnet.application;

import campus.u2.entrysystem.Utilities.exceptions.GlobalException;
import campus.u2.entrysystem.carnet.domain.Carnet;
import campus.u2.entrysystem.people.domain.People;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarnetService {

    private final CarnetRepository carnetRepository;

    public CarnetService(CarnetRepository carnetRepository) {
        this.carnetRepository = carnetRepository;
    }

    //To create a carnet 
    public Carnet saveCarnet(Carnet carnet) {
        if (carnet == null) {
            throw new GlobalException("Empty object, please try again");
        }
        return carnetRepository.saveCarnet(carnet);
    }

    // To create a carnet for a person
    public Carnet saveCarnet(People people, Carnet carnet) {
        carnet.setPeople(people);
        people.setCarnet(carnet);
        return carnetRepository.saveCarnet(carnet);
    }

    // To show all the carnets 
    public List<Carnet> getAllCarnets() {
        return carnetRepository.getAllCarnets();
    }

    // To find a carnet for the id
    public Carnet getCarnetById(Long id) {
        if (id == null) {
            throw new GlobalException("ID cannot be empty");
        }
        return carnetRepository.getCarnetById(id)
                .orElseThrow(() -> new GlobalException("Not found ID"));
    }
    
    // To find a carnet by the people 
    public Carnet findCarnetByPeople(People people) {
        return carnetRepository.findCarnetByPeople(people)
                .orElseThrow(() -> new GlobalException("Notfound carnet")); 
    }
}
