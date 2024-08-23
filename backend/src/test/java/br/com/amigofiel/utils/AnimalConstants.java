package br.com.amigofiel.utils;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;

import java.sql.Date;

public class AnimalConstants {

   public static final Address ADDRESS = new Address (new AddressDTO("zipCode","street", "neighbourhood",
           "city", "MG"));

   public static final Animal ANIMAL =  new Animal(
           "name",
           Specie.DOG,
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           Date.valueOf("2021-06-15"),
           CurrentStatus.AVAILABLE,
           null // Supondo que não há adoção inicialmente
   );

   public static final AnimalDTO ANIMAL_DTO = new AnimalDTO(
           "name",
           Specie.DOG,
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           ADDRESS,
           Date.valueOf("2021-06-15"),
           CurrentStatus.AVAILABLE,
           null // Supondo que não há adoção inicialmente
   );

   public static final Animal UPDATED_ANIMAL = new Animal(
           "name",
           Specie.CAT, // Dado alterado
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           Date.valueOf("2021-06-15"),
           CurrentStatus.ADOPTED, // Dado alterado
           new Adoption()
   );

   public static final AnimalDTO UPDATED_ANIMAL_DTO = new AnimalDTO(
           "name",
           Specie.CAT, // Dado alterado
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           ADDRESS,
           Date.valueOf("2021-06-15"),
           CurrentStatus.ADOPTED, // Dado alterado
           new Adoption()
   );

   public static final Animal INVALID_ANIMAL = new Animal(
            "",
           null,
           "",
           null,
           'M',
           -1.0,
           null,
           true,
           null,
           null,
           null
   );

   public static final AnimalDTO INVALID_ANIMAL_DTO = new AnimalDTO(
           "",
           null,
           "",
           null,
           'M',
           -1.0,
           null,
           true,
           null,
           null,
           null,
           null
   );

}
