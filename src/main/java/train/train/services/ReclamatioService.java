package train.train.services;

import train.train.entities.reclamation;

import java.util.List;
import java.util.Optional;

public interface ReclamatioService {
    List<reclamation> getAllreclamation();


    reclamation createreclamation(reclamation reclamation);
}
