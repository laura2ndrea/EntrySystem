/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campus.u2.entrysystem.access.infrastructure;

import campus.u2.entrysystem.access.domain.Access;
import campus.u2.entrysystem.porters.domain.Porters;
import java.util.Optional;

/**
 *
 * @author camper
 */
public class AccessController {
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
    
//     public Access updatePorterInAccess(Long accessId, Long porterId, Porters updatedPorter) {
//        Optional<Access> accessOpt = accessRepository.getAccessById(accessId);
//        if (accessOpt.isPresent()) {
//            Access access = accessOpt.get();
//            Optional<Porters> porterOpt = portersRepository.getPorterById(porterId);
//            if (porterOpt.isPresent()) {
//                Porters porter = porterOpt.get();
//                // Actualizar el // Actualizarportero en el acceso (puedes agregar lógica para la actualización)
//                porter.setName(updatedPorter.getName());
//                porter.setPosition(updatedPorter.getPosition());
//                porter.setEmploymentDate(updatedPorter.getEmploymentDate());
//                portersRepository.savePorter(porter);  
//                return access;
//            }
//        }
//        return null;  // Si no se encuentra el acceso o el portero
//    }
}
