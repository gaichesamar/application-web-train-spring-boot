package train.train.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import train.train.entities.reclamation;

public interface ReclamationRepository extends JpaRepository<reclamation,Long> {

}