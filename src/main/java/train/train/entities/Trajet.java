package train.train.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import train.train.status.StatutTrajet;

@Entity
@Table(name = "Trajet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lieuDepart;
    private String lieuArrivee;
    private String heureDepart;
    private String heureArrivee;
    private double prixTicket;
    private int nombrePlaces;
    @Enumerated(EnumType.STRING)
    private StatutTrajet statut;

}
