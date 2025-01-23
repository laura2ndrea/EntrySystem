package campus.u2.entrysystem.access.application;

import campus.u2.entrysystem.Utilities.exceptions.GlobalException;
import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.accessnotes.domain.AccessNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import org.springframework.transaction.annotation.Transactional;
import campus.u2.entrysystem.porters.application.PortersRepository;
import campus.u2.entrysystem.porters.domain.Porters;

import java.util.List;
import java.util.Optional;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private PortersRepository portersRepository;

    @Transactional

    public Access createAccess(Access access) {
        if (access == null) {
            throw new GlobalException("Empty object, please try again ");
        }
        return accessRepository.createAccess(access);
    }

    @Transactional

    public void deleteAccess(Long id) {

        if (id == null) {
            throw new GlobalException("Invalid Id try again");
        }
        accessRepository.getAccessById(id);
    }

    public List<Access> getAllAccesses() {
        return accessRepository.getAllAccesses();
    }

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

    @Transactional

    
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

//    @Transactional
//    
//    public Access addPorterToAccess(Long accessId, Long porterId) {
//        Optional<Access> accessOpt = accessRepository.findById(accessId);
//        if (accessOpt.isPresent()) {
//            Access access = accessOpt.get();
//            Optional<Porters> porterOpt = portersRepository.findById(porterId);
//            if (porterOpt.isPresent()) {
//                Porters porter = porterOpt.get();
//                access.getPorters().add(porter);
//                porter.getAccesses().add(access);
//                accessRepository.save(access);
//                portersRepository.save(porter);
//                return access;
//            }
//        }
//        return null;
//    }
    
    
    
        @Transactional
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

    
    
    

    @Transactional

    public Access removePorterFromAccess(Long accessId, Long porterId) {
        Optional<Access> accessOpt = accessRepository.getAccessById(accessId);
        if (accessOpt.isPresent()) {
            Access access = accessOpt.get();
            Optional<Porters> porterOpt = portersRepository.getPorterById(porterId);
            if (porterOpt.isPresent()) {
                Porters porter = porterOpt.get();
                access.getPorters().remove(porter);  // Eliminar el portero del acceso
                porter.getAccesses().remove(access);  // Eliminar el acceso del portero
                accessRepository.createAccess(access);  // Guardar el acceso actualizado
                portersRepository.savePorter(porter);  // Guardar el portero actualizado
                return access;
            }
        }
        return null;  // Si no se encuentra el acceso o el portero
    }

    
    
    @Transactional

    public Access updatePorterInAccess(Long accessId, Long porterId, Porters updatedPorter) {
        Optional<Access> accessOpt = accessRepository.getAccessById(accessId);
        if (accessOpt.isPresent()) {
            Access access = accessOpt.get();
            Optional<Porters> porterOpt = portersRepository.getPorterById(porterId);
            if (porterOpt.isPresent()) {
                Porters porter = porterOpt.get();
                // Actualizar el // Actualizarportero en el acceso (puedes agregar lógica para la actualización)
                porter.setName(updatedPorter.getName());
                porter.setPosition(updatedPorter.getPosition());
                porter.setEmploymentDate(updatedPorter.getEmploymentDate());
                portersRepository.savePorter(porter);  
                return access;
            }
        }
        return null;  // Si no se encuentra el acceso o el portero
    }

    
    
    public List<Access> findAccessBetweenDates(Date startDate, Date endDate) {
        if (startDate == null || endDate == null){
            throw  new GlobalException("Date is not Valid");
        }
        return accessRepository.findAccessBetweenDates(startDate, endDate);
    }

    //
//    @Transactional
//    
//    public Access updateAccess(Long id, Access updatedAccess)  {
//        Access accessOpt = accessRepository.getAccessById(id);
//        if (accessRepository.getAccessById(id)>=0) {
//            Access existingAccess = accessOpt.get();
//            existingAccess.setEntryAccess(updatedAccess.getEntryAccess());
//            existingAccess.setExitAccess(updatedAccess.getExitAccess());
//            existingAccess.setAccessType(updatedAccess.isAccessType());
//            existingAccess.setPeople(updatedAccess.getPeople());
//            return accessRepository.createAccess(existingAccess);
//        } else {
//            throw new RuntimeException("Access with ID " + id + " not found.");
//        }
//    }
}
