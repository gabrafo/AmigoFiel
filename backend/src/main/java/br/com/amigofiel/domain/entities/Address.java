package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.enums.FederalUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code", nullable = false)
    @NotEmpty()
    private String zipCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighbourhood", nullable = false)
    private String neighbourhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "federal_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private FederalUnit federalUnit;

    @Builder
    public Address(AddressDTO dto){
        this.zipCode = dto.zipCode();
        this.street = dto.street();
        this.neighbourhood = dto.neighbourhood();
        this.city = dto.city();
        this.federalUnit = FederalUnit.valueOf(dto.federalUnit());
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this); // Compara os objetos e não endereços de memória
    }
}
