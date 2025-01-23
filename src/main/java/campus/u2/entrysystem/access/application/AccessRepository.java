
package campus.u2.entrysystem.access.application;


import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.accessnotes.domain.AccessNote;
import campus.u2.entrysystem.porters.domain.Porters;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface AccessRepository {


    Access createAccess(Access access);
    Access updateAccess(Long id, Access updatedAcces);
    void deleteAccess(Long id);
    List<Access> getAllAccesses();
    Optional<Access> getAccessById(Long id);
    Access addAccessNoteToAccess(Long accessId, AccessNote accessNote);
    Access addPorterToAccess(Long accessId, Long porterId);
    Access removePorterFromAccess(Long accessId, Long porterId);
    Access updatePorterInAccess(Long accessId, Long porterId, Porters updatedPorter);
    List<Access> findAccessBetweenDates(Date startDate, Date endDate);
    
    
    
    
    
    
    
    
    
    
    
    
}
