package train.train.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = true)
    private String nom;

    @Column(name = "prenom", nullable = true)
    private String prenom;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telephone", nullable = true)
    private String telephone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_naissance", nullable = true)
    private Date dateNaissance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_inscription", nullable = true)
    private Date dateInscription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_Supp", nullable = true)
    private Date dateSupp;

//    @JsonIgnore
    //@ManyToMany(fetch=FetchType.EAGER)
    //  @JoinTable(name="USERS_ROLES",  joinColumns= {@JoinColumn(name="USER_ID")},   inverseJoinColumns= {@JoinColumn(name="ROLE_ID")})
    //  private List<Role> roles;



    private String otp ;
    @Column(name = "photo", nullable = true, columnDefinition = "TEXT")
    private String photo;

}
