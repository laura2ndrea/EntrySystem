
package campus.u2.entrysystem.user.application;

import campus.u2.entrysystem.porters.domain.Porters;
import campus.u2.entrysystem.user.domain.User;
import java.util.List;


public interface UserRepository {

    User createUser(Porters porter, User user);
    User getUsuariopoPorter(Porters porter);
    User updateUser(Porters porters, User userupdated);
    void deleteUser(Porters porters);
    List<User> listAllUser();

}
