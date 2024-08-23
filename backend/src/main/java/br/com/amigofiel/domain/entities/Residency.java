package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.dto.ResidencyDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Residency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "residence_type", nullable = false)
    private String residenceType;

    @Column(name = "ownership_status", nullable = false)
    private String ownershipStatus;

    @Column(name = "has_permission_for_animals")
    private Boolean hasPermissionForAnimals;

    @Column(name = "available_space", nullable = false, columnDefinition = "TEXT")
    private String availableSpace;

    @Column(name = "has_safety_nets", nullable = false)
    private Boolean hasSafetyNets;

    @Column(name = "other_pets", nullable = false)
    private int otherPets;

    @Column(name = "number_of_residents", nullable = false)
    @Positive
    private int numberOfResidents;

    @Builder
    public Residency(ResidencyDTO dto){
        this.address = dto.address();
        this.residenceType = dto.residenceType();
        this.ownershipStatus = dto.ownershipStatus();
        this.hasPermissionForAnimals = dto.hasPermissionForAnimals();
        this.availableSpace = dto.availableSpace();
        this.hasSafetyNets = dto.hasSafetyNets();
    }
}
