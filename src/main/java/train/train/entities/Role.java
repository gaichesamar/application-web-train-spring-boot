package train.train.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "libelle",nullable = true)
    private String libelle;


}
