package campus.u2.entrysystem.membership.application;

import campus.u2.entrysystem.membership.domain.Membership;
import java.util.List;
import java.util.Optional;

public interface MembershipRepository {
    
    Membership save(Membership membership);
    Optional<Membership> findById(Long idMembership); // Cambiar el nombre del método para que coincida con el uso en MembershipService
    List<Membership> findAll(); // Método para listar todas las membresías
    void delete(Membership membership); // Método para eliminar una membresía
    boolean existsById(Long idMembership); // Método para comprobar si una membresía existe por ID
    
    List<Membership> findByDuration(Integer duration); // Método para encontrar membresías por duración
    List<Membership> findByPrice(Double price); // Método para encontrar membresías por precio
    List<Membership> findByPriceLessThan(Double price); // Método para encontrar membresías con precio menor que
}
