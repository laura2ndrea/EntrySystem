package campus.u2.entrysystem.porters.application;

import campus.u2.entrysystem.Utilities.exceptions.GlobalException;
import campus.u2.entrysystem.porters.domain.Porters;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PortersService {

    private final PortersRepository portersRepository;

    @Autowired
    public PortersService(PortersRepository portersRepository) {
        this.portersRepository = portersRepository;
    }

    // To save a porter
    @Transactional
    public Porters savePorter(String name, String cedula, String telefono, Date employmentDate, Boolean position, Porters jefe) {
        Porters porter = new Porters(employmentDate, position, jefe, name, cedula, telefono);
        return portersRepository.savePorter(porter);

    }

    @Transactional

    public Porters savePorter(Porters Porters) {
        if (Porters == null) {
            throw new GlobalException("Porter not valid");
        }
        return portersRepository.savePorter(Porters);

    }

    @Transactional
    public Porters savePorter(String name, String cedula, String telefono, Date employmentDate, Boolean position) {
        Porters porter = new Porters(employmentDate, position, name, cedula, telefono);
        return portersRepository.savePorter(porter);
    }

    @Transactional
    public void addBossToPorter(Long porters, Long boss) {

        Optional<Porters> porterOpt = portersRepository.getPorterById(porters);
        Optional<Porters> bossOpt = portersRepository.getPorterById(boss);
        if (porterOpt.isPresent() || bossOpt.isPresent()) {
            Porters porterObjt = porterOpt.get();
            Porters bossObjt = bossOpt.get();
            porterObjt.setId_jefe(bossObjt);
            portersRepository.savePorter(porterObjt);
        } else {
            throw new GlobalException("Id is not Valid please try again");
        }

    }

    // To delete a porter
    @Transactional
    public void deletePorter(Long id) {

        Optional<Porters> portersOpt = portersRepository.getPorterById(id);
        if (portersOpt.isPresent()) {
            Porters portersToEliminate = portersOpt.get();
            portersRepository.deletePorter(portersToEliminate.getId());

        } else {
            throw new GlobalException("Id is not Valid please try again");
        }

    }

    // To list all porters
    public List<Porters> listAllPorters() {
        return portersRepository.listAllPorters();
    }

    // To get a porter by ID
    public Porters getPorterById(Long id) {
        if (id == null) {
            throw new GlobalException("Id is not Valid please try again");
        }

        return portersRepository.getPorterById(id)
                .orElseThrow(() -> new GlobalException("Id no valido"));     
       
    }

    // To update a porter
//    @Transactional
//    public Porters updatePorter(Porters updatedPorter) {
//        Optional<Porters> existingPorterOpt = portersRepository.getPorterById(updatedPorter.getId());
//        if (existingPorterOpt.isPresent()) {
//            Porters existingPorter = existingPorterOpt.get();
//            existingPorter.setName(updatedPorter.getName());
//            existingPorter.setCedula(updatedPorter.getCedula());
//            existingPorter.setTelefono(updatedPorter.getTelefono());
//            existingPorter.setEmploymentDate(updatedPorter.getEmploymentDate());
//            existingPorter.setPosition(updatedPorter.getPosition());
//            existingPorter.setId_jefe(updatedPorter.getId_jefe());
//            return portersRepository.save(existingPorter);
//        } else {
//            throw new RuntimeException("Porter with ID " + updatedPorter.getId() + " not found.");
//        }
//    }
}
