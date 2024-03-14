package train.train.services;



import train.train.configemail.MailService;
import train.train.dto.UserDto;
import train.train.entities.User;
import train.train.entities.Role;

import train.train.repo.RoleRepository;
import train.train.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MailService mailService;

    @Override
    public User register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        } else {
            User user = new User();
            user.setNom(userDto.getNom());
            user.setPrenom(userDto.getPrenom());
            user.setEmail(userDto.getEmail());
            String password = createRandomPassword();
            user.setPassword(passwordEncoder.encode(password));
            user.setDateNaissance(userDto.getDateNaissance());
            user.setTelephone(userDto.getTelephone());
            user.setPhoto(userDto.getPhoto());

            mailService.sendTextmailupdateregister(user.getEmail(), "معلومات تسجيل الدخول: ","البريد الإلكتروني: "+user.getEmail()+"رمز الدخول:\n "+password);

            return userRepository.save(user);
        }
    }

    @Override
    public User update(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));

        existingUser.setEmail(userDto.getEmail());
        existingUser.setNom(userDto.getNom());
        existingUser.setPrenom(userDto.getPrenom());
        existingUser.setPhoto(userDto.getPhoto());
        existingUser.setDateNaissance(userDto.getDateNaissance());

        String password = createRandomPassword();
        existingUser.setPassword(passwordEncoder.encode(password));

        mailService.sendTextmailupdateregister(existingUser.getEmail(), "معلومات تسجيل الدخول: ","البريد الإلكتروني: "+existingUser.getEmail()+"رمز الدخول:\n "+password);

        return userRepository.save(existingUser);
    }

    public String createRandomPassword() {
        Random random = new Random();
        StringBuilder oneTimePassword = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            oneTimePassword.append(randomNumber);
        }
        return oneTimePassword.toString().trim();

    }




    public String createRandomOneTimePassword(String username) {
        Random random = new Random();
        StringBuilder oneTimePassword = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            oneTimePassword.append(randomNumber);
        }
        User user = userRepository.findByEmail(username);
        user.setOtp(passwordEncoder.encode(oneTimePassword.toString().trim()));
        userRepository.save(user);

        return oneTimePassword.toString().trim();

    }

    public Boolean checkEncodedPassword(String username, String OtpEntred) {
        User user = userRepository.findByEmail(username);
        System.out.println(user.getOtp() + "--------" + passwordEncoder.matches(OtpEntred.trim(), user.getOtp()));

        return passwordEncoder.matches(OtpEntred.trim(), user.getOtp());
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    public User getUserByemail(String email){
        return userRepository.findByEmail(email);
    }

}
