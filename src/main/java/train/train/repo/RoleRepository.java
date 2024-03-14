package train.train.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import train.train.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {


    //    Role findByLibelle(String libelle);
    Optional<Role> findById(Long id);
    Role findByLibelle(String libelle);


}

