package campus.u2.entrysystem.porters.infrastructure;

import campus.u2.entrysystem.porters.domain.Porters;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PotersJpaRepository extends  JpaRepository<Porters, Long>{
    
    List<Porters> findByPosition(Boolean position);

    
    
}