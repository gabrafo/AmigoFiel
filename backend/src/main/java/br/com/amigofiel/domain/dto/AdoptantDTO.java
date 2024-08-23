package br.com.amigofiel.domain.dto;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Residency;
import br.com.amigofiel.domain.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


import java.sql.Date;
import java.util.List;

public record AdoptantDTO(
        @NotNull String name,
        @NotNull Date birthDate,
        @NotNull Gender gender,
        @NotNull Residency residency,
        @NotNull String phoneNumber,
        @NotNull String reasonForAdoption,
        @NotNull boolean agreesWithVisit,
        @NotNull boolean agreesWithResponsabilityTerms,
        @NotNull boolean declaresTruthfulInformation,
        List<Adoption> adoptions
) {
    @Builder
    public AdoptantDTO(Adoptant adoptant) {
        this(
                adoptant.getName(),
                adoptant.getBirthDate(),
                adoptant.getGender(),
                adoptant.getResidency(),
                adoptant.getPhoneNumber(),
                adoptant.getReasonForAdoption(),
                adoptant.isAgreesWithVisit(),
                adoptant.isAgreesWithResponsabilityTerms(),
                adoptant.isDeclaresTruthfulInformation(),
                adoptant.getAdoptions()
        );
    }
}
