package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.dto.AdoptionDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Adoption {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adopter_id", nullable = false)
    private Adoptant adopter;

    @OneToOne(mappedBy = "adoption")
    private Animal animal;

    @Builder
    public Adoption(AdoptionDTO adoptionDTO) {
        this.adopter = adoptionDTO.adoptant();
        this.animal = adoptionDTO.animal();
    }
}
