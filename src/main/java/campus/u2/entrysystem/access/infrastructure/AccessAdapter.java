package campus.u2.entrysystem.access.infrastructure;

import campus.u2.entrysystem.access.application.AccessRepository;
import campus.u2.entrysystem.utilities.exceptions.GlobalException;
import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.accessnotes.domain.AccessNote;
import org.springframework.stereotype.Service;
import java.util.Date;
import campus.u2.entrysystem.porters.domain.Porters;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccessAdapter implements AccessRepository{
    
    //Atributtes 
    private final AccessJpaRepository accessRepository; 
    
    // Constructor 
    public AccessAdapter(AccessJpaRepository accessRepository){
        this.accessRepository = accessRepository; 
    }
    
    //Methods 

    // To create an ancces
    @Override 
    @Transactional
    public Access createAccess(Access access) {
        return accessRepository.save(access);
    }
    
   
    @Transactional
    // To delete an access
    public void deletePeople(String cedula) {

        if (cedula == null) {
            throw new GlobalException("Incorrect cedula, please try again");

        }

        Optional<People> existingPeopleOpt = peopleRepository.getPeopleByCedula(cedula);
        if (existingPeopleOpt.isPresent()) {
            peopleRepository.deletePeople(existingPeopleOpt.get().getId());
        } else {
            throw new GlobalException("Unexpected error, please try again");
        }

    }


    // To get all accesses
    public List<Access> getAllAccesses() {
        return accessRepository.getAllAccesses();
    }

    // To get an access by id
    public Access getAccessById(Long id) {
        if (id == null) {
            throw new GlobalException("Id not Valid please try again" + id);
        }
        Optional<Access> accessOpt = accessRepository.getAccessById(id);
        if (accessOpt.isPresent()) {
            return accessOpt.get();
        } else {
            throw new GlobalException("Id is not valid please try again ");
        }
    }

    // To add a note to an access
    public Access addAccessNoteToAccess(Long accessId, AccessNote accessNote) {
        if (accessId == null || accessNote == null) {
            throw new GlobalException("Error with the inputs please try again");
        }
        Optional<Access> accessOpt = accessRepository.getAccessById(accessId);
        if (accessOpt.isPresent()) {
            accessOpt.get().addAccessNotes(accessNote);
            return accessRepository.createAccess(accessOpt.get());

        } else {
            throw new GlobalException("Access note with Id" + accessId + "  Not Found");
        }

    }

    // To add a porter to an access
    public Access addPorterToAccess(Long accessId, Long porterId) {
        Access access = accessRepository.getAccessById(accessId)
                .orElseThrow(() -> new RuntimeException("Access with ID " + accessId + " not found."));
        Porters porter = portersRepository.getPorterById(porterId)
                .orElseThrow(() -> new RuntimeException("Porter with ID " + porterId + " not found."));
        access.getPorters().add(porter);
        porter.getAccesses().add(access);
        accessRepository.createAccess(access);
        portersRepository.savePorter(porter);
        return access;
    }
    
    // To remove a porter from an access
    public Access removePorterFromAccess(Long accessId, Long porterId) {
        Optional<Access> accessOpt = accessRepository.getAccessById(accessId);
        if (accessOpt.isPresent()) {
            Access access = accessOpt.get();
            Optional<Porters> porterOpt = portersRepository.getPorterById(porterId);
            if (porterOpt.isPresent()) {
                Porters porter = porterOpt.get();
                access.getPorters().remove(porter);  // Delete the porter in the access
                porter.getAccesses().remove(access);  // Delete the access in the porter
                accessRepository.createAccess(access);  // Save the update access
                portersRepository.savePorter(porter);  // Save the update porter
                return access;
            }
        }
        return null;  // If the porter or the access is not found
    }
    
    // To find all the access between two dates
    public List<Access> findAccessBetweenDates(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new GlobalException("Date is not Valid");
        }
        return accessRepository.findAccessBetweenDates(startDate, endDate);
    }

}
