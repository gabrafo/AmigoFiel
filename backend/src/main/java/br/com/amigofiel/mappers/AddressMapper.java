package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper {

    public AddressDTO toDTO(Address address){
        return AddressDTO.builder().address(address).build();
    }

    public Address toEntity(AddressDTO addressDTO){
        return Address.builder().dto(addressDTO).build();
    }
}
