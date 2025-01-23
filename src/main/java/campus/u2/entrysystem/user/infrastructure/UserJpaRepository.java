
package campus.u2.entrysystem.user.infrastructure;


import campus.u2.entrysystem.porters.domain.Porters;
import campus.u2.entrysystem.user.domain.User;


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User, Long> {
    
    User findByPorter(Porters porter);
    
}
