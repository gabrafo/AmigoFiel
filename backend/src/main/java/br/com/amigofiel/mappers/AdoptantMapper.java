package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdoptantMapper {


    public AdoptantDTO toDTO(Adoptant adoptant){
        return AdoptantDTO.builder().adoptant(adoptant).build();
    }

    public Adoptant toEntity(AdoptantDTO adoptantDTO){
        return Adoptant.builder().dto(adoptantDTO).build();
    }
}
