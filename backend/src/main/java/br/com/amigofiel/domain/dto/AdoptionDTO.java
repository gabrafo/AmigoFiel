package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Animal;
import lombok.Builder;

public record AdoptionDTO (
        Adoptant adoptant,
        Animal animal){

    @Builder
    public AdoptionDTO(Adoption adoption) {
        this(
                adoption.getAdopter(),
                adoption.getAnimal()
        );
    }
}
