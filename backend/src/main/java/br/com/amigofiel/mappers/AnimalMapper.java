package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AnimalMapper {

    public AnimalDTO toDTO(Animal animal){
        return AnimalDTO.builder().animal(animal).build();
    }

    public Animal toEntity(AnimalDTO animalDTO){
        return Animal.builder().animalDTO(animalDTO).build();
    }
}
