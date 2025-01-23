
package campus.u2.entrysystem.porters.application;



import campus.u2.entrysystem.porters.domain.Porters;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface PortersRepository {
    
    
Porters savePorter(Porters porters);
Porters savePorter(String name, String cedula, String telefono, Date employmentDate, Boolean position);
Porters updatePorter(Porters updatedPorter);
void deletePorter(Long id);
List<Porters> listAllPorters();
Optional<Porters> getPorterById(long id);
void addBossToPorter(Long porters, Long boss);
 
    
}
