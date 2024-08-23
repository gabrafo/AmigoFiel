package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.dto.ResidencyDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Residency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResidencyMapper {

    public ResidencyDTO toDTO(Residency residency){
        return ResidencyDTO.builder().residency(residency).build();
    }

    public Residency toEntity(ResidencyDTO residencyDTO){
        return Residency.builder().dto(residencyDTO).build();
    }
}
