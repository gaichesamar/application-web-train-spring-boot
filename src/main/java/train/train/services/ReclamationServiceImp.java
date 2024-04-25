package train.train.services;

import org.springframework.beans.factory.annotation.Autowired;
import train.train.entities.reclamation;
import train.train.repo.ReclamationRepository;


import java.util.List;
import java.util.Optional;

public class ReclamationServiceImp implements ReclamatioService  {



    @Autowired
    private ReclamationRepository reclamationRepository;



    @Override
    public List<reclamation> getAllreclamation() {
        return  reclamationRepository.findAll() ;
    }

    @Override
    public reclamation createreclamation(reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }
}
