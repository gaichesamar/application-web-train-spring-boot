package train.train.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nbre_places_reservees")
    private int nbrePlacesReservees;
    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;
}