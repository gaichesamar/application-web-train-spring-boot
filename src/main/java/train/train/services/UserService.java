package train.train.services;



import train.train.dto.UserDto;
import train.train.entities.Reservation;
import train.train.entities.Role;
import train.train.entities.User;

import java.util.List;

public interface UserService {
    User register(UserDto userDto);
    User update( UserDto userDto);
    void delete(Long id);
    List<User> getAllUsers();
    User getUser(Long id);
    User getUserByemail(String email);
    // List<Role> getRolesByUserId(Long userId);

}
