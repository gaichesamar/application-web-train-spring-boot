package train.train.entities;

import jakarta.persistence.*;
import train.train.status.StatutTrajet;

public class reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lieuDepart;
    private String lieuArrivee;
    private String heureDepart;
    private String heureArrivee;
    private String Description;


}
