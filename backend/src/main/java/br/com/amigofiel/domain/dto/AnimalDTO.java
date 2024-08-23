package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.sql.Date;

public record AnimalDTO(
        String name,
        @NotNull Specie specie,
        String breed,
        Date birthDate,
        char sex,
        @Positive double weight,
        @NotNull Size size,
        @NotNull boolean neutered,
        @NotNull Date registrationDate,
        @NotNull CurrentStatus currentStatus,
        Adoption adoption
        ){

    @Builder
    public AnimalDTO(Animal animal) {
        this(
                animal.getName(),
                animal.getSpecie(),
                animal.getBreed(),
                animal.getBirthDate(),
                animal.getSex(),
                animal.getWeight(),
                animal.getSize(),
                animal.isNeutered(),
                animal.getRegistrationDate(),
                animal.getCurrentStatus(),
                animal.getAdoption()
        );
    }
}