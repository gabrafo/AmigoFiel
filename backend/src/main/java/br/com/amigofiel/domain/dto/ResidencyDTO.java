package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Residency;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record ResidencyDTO(
        @NotBlank Address address,
        @NotBlank String residenceType,
        @NotBlank String ownershipStatus,
        @NotBlank Boolean hasPermissionForAnimals,
        @NotBlank String availableSpace,
        @NotBlank Boolean hasSafetyNets
) {
    @Builder
    public ResidencyDTO(Residency residency) {
        this(
                residency.getAddress(),
                residency.getResidenceType(),
                residency.getOwnershipStatus(),
                residency.getHasPermissionForAnimals(),
                residency.getAvailableSpace(),
                residency.getHasSafetyNets()
        );
    }
}
