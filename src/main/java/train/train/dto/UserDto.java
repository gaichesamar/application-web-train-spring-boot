package train.train.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import train.train.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
    private String photo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    // private List<Role> roles;
    private Set<String> role;
}


