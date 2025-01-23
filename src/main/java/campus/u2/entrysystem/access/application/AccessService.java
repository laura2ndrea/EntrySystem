package campus.u2.entrysystem.access.application;

import campus.u2.entrysystem.utilities.exceptions.GlobalException;
import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.accessnotes.domain.AccessNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import campus.u2.entrysystem.porters.application.PortersRepository;
import campus.u2.entrysystem.porters.domain.Porters;

import java.util.List;
import java.util.Optional;

@Service
public class AccessService {

    // Attributes 
    private final AccessRepository accessRepository;
    private final PortersRepository portersRepository;

    // Constructor
    @Autowired
    public AccessService(AccessRepository accessRepository, PortersRepository portersRepository) {
        this.accessRepository = accessRepository;
        this.portersRepository = portersRepository;
    }

    // To create an anccess
    public Access createAccess(Access access) {
        if (access == null) {
            throw new GlobalException("Empty object, please try again ");
        }
        return accessRepository.createAccess(access);
    }

    // To delete an access
    public void deleteAccess(Long id) {
        if (id == null) {
            throw new GlobalException("Invalid Id try again");
        }
        accessRepository.getAccessById(id);
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
