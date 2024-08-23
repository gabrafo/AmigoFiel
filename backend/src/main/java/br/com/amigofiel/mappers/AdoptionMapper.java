package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AdoptionDTO;
import br.com.amigofiel.domain.entities.Adoption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdoptionMapper {

    public AdoptionDTO toDTO(Adoption adoption){
        return AdoptionDTO.builder().adoption(adoption).build();
    }

    public Adoption toEntity(AdoptionDTO adoptionDTO){
        return Adoption.builder().adoptionDTO(adoptionDTO).build();
    }
}
