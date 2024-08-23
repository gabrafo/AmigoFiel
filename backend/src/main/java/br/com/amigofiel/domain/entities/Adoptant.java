package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Adoptant {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residency_id", nullable = false)
    private Residency residency;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "reason_for_adoption", nullable = false)
    private String reasonForAdoption;

    @Column(name = "agrees_with_visit", nullable = false)
    private boolean agreesWithVisit;

    @Column(name = "agrees_with_responsability_terms", nullable = false)
    private boolean agreesWithResponsabilityTerms;

    @Column(name = "declares_truthful_information", nullable = false)
    private boolean declaresTruthfulInformation;

    @OneToMany(mappedBy = "adopter")
    private List<Adoption> adoptions;

    @Builder
    public Adoptant(AdoptantDTO dto) {
        this.name = dto.name();
        this.birthDate = dto.birthDate();
        this.gender = dto.gender();
        this.residency = dto.residency();
        this.phoneNumber = dto.phoneNumber();
        this.reasonForAdoption = dto.reasonForAdoption();
        this.agreesWithVisit = dto.agreesWithVisit();
        this.agreesWithResponsabilityTerms = dto.agreesWithResponsabilityTerms();
        this.declaresTruthfulInformation = dto.declaresTruthfulInformation();
        this.adoptions = dto.adoptions();
    }
}
