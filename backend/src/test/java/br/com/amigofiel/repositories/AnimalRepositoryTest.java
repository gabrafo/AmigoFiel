package br.com.amigofiel.repositories;

import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.FederalUnit;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.amigofiel.utils.AnimalConstants.ADDRESS;
import static br.com.amigofiel.utils.AnimalConstants.ANIMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// TESTE DE INTEGRAÇÃO COM BD
@DataJpaTest
public class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TestEntityManager testEntityManager; // Interage com o BD sem usar o Repository, utilizamos ele pois queremos testar o Repository

    @AfterEach
    public void afterEach(){
        ANIMAL.setId(null);
        ADDRESS.setId(null);
    }

    @Test
    public void createAnimal_WithValidData_ReturnsAnimal() {

        Animal animal = animalRepository.save(ANIMAL);

        Animal sut = testEntityManager.find(Animal.class, ANIMAL.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(animal.getName());
        assertThat(sut.getSpecie()).isEqualTo(animal.getSpecie());
        assertThat(sut.getBreed()).isEqualTo(animal.getBreed());
        assertThat(sut.getBirthDate()).isEqualTo(animal.getBirthDate());
        assertThat(sut.getSex()).isEqualTo(animal.getSex());
        assertThat(sut.getWeight()).isEqualTo(animal.getWeight());
        assertThat(sut.getSize()).isEqualTo(animal.getSize());
        assertThat(sut.getAddress()).isEqualTo(animal.getAddress());
        assertThat(sut.getRegistrationDate()).isEqualTo(animal.getRegistrationDate());
        assertThat(sut.getCurrentStatus()).isEqualTo(animal.getCurrentStatus());
        assertThat(sut.getAdoption()).isEqualTo(animal.getAdoption());
    }

    @ParameterizedTest
    @MethodSource("providesInvalidAnimals")
    public void createAnimal_WithInvalidData_ThrowsException(Animal animal) {

        assertThatThrownBy(() -> animalRepository.save(animal)).isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> providesInvalidAnimals() {

        return Stream.of(

                // Specie NULL
                Arguments.of(new Animal("name", null, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, Size.MEDIUM, true,
                        new Date(System.currentTimeMillis()),
                        CurrentStatus.AVAILABLE, new Adoption())),

                // Weight não é positivo
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', -1.0, Size.MEDIUM, true,
                        new Date(System.currentTimeMillis()),
                        CurrentStatus.AVAILABLE, new Adoption())),

                // Size NULL
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, null, true,
                        new Date(System.currentTimeMillis()),
                        CurrentStatus.AVAILABLE, new Adoption())),

                // RegistrationDate NULL
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, Size.MEDIUM, true,
                        null, CurrentStatus.AVAILABLE, new Adoption())),

                // CurrentStatus NULL
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, Size.MEDIUM, true,
                        new Date(System.currentTimeMillis()),
                        null, new Adoption())),

                // Address vazio
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, Size.MEDIUM, true,
                        new Date(System.currentTimeMillis()),
                        CurrentStatus.AVAILABLE, new Adoption())),

                // Address NULL
                Arguments.of(new Animal("name", Specie.CAT, "breed", new Date(System.currentTimeMillis()),
                        'M', 10.0, Size.MEDIUM, true,
                        new Date(System.currentTimeMillis()),
                        CurrentStatus.AVAILABLE, new Adoption()))
        );
    }

    @Test
    public void findAnimalById_WithValidId_ReturnsAnimal(){

        Animal animal = testEntityManager.persistFlushFind(ANIMAL);

        Optional<Animal> sut = animalRepository.findById(animal.getId());

        assertThat(sut).isNotEmpty();
        assertThat(sut).isEqualTo(Optional.of(animal));
    }

    @Test
    public void findAnimalById_WithInvalidId_ReturnsEmpty(){

        Optional<Animal> sut = animalRepository.findById(1L);

        assertThat(sut).isEmpty();
    }

    @Sql("/import_animals_and_addresses.sql")
    @Test
    public void findAllAnimals_ReturnsAnimals() {

        List<Animal> sut = animalRepository.findAll();

        Animal rex = new Animal(1L, "Rex", Specie.DOG, "Labrador", Date.valueOf("2020-1-1"), 'M', 30.0,
                Size.LARGE, true, new Address(1L, "12345-678", "Rua das Flores", "Centro",
                "Cidade Exemplo", FederalUnit.MG), Date.valueOf("2023-1-1"), CurrentStatus.AVAILABLE, null);

        Animal luna = new Animal(2L, "Luna", Specie.CAT, "Siamese", Date.valueOf("2019-6-15"), 'F', 5.0,
                Size.SMALL, true, new Address(2L, "23456-789", "Avenida Central", "Bairro Novo",
                "Outra Cidade", FederalUnit.MG), Date.valueOf("2023-6-15"), CurrentStatus.ADOPTED, null);

        Animal max = new Animal(3L, "Max", Specie.DOG, "Bulldog", Date.valueOf("2018-9-23"), 'M', 20.0,
                Size.MEDIUM, false, new Address(3L, "34567-890", "Praça da Liberdade", "Liberdade",
                "Mais uma Cidade", FederalUnit.MG), Date.valueOf("2023-9-23"), CurrentStatus.AVAILABLE, null);

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(3);
        assertThat(sut).containsExactlyInAnyOrder(luna, max, rex);
    }

    @Test
    public void findAllAnimals_ReturnsEmpty() {
        List<Animal> sut = animalRepository.findAll();
        assertThat(sut).isEmpty();
    }

    @Test
    public void removeAnimal_WithValidId_RemovesFromDb(){

        Animal animal = testEntityManager.persistFlushFind(ANIMAL);

        animalRepository.deleteById(animal.getId());

        Animal removedAnimal = testEntityManager.find(Animal.class, animal.getId());
        assertThat(removedAnimal).isNull();
    }

    @Sql("/import_animals_and_addresses.sql")
    @Test
    public void removeAnimal_WithInvalidId_ReturnsEmpty(){
        assertThat(testEntityManager.find(Animal.class, 4L)).isNull();

        animalRepository.deleteById(4L);

        // Verifica que os animais com IDs 1L, 2L e 3L ainda existem
        assertThat(testEntityManager.find(Animal.class, 1L)).isInstanceOf(Animal.class);
        assertThat(testEntityManager.find(Animal.class, 2L)).isInstanceOf(Animal.class);
        assertThat(testEntityManager.find(Animal.class, 3L)).isInstanceOf(Animal.class);

        // Verifica que o animal com ID 4L ainda não existe após a tentativa de exclusão
        assertThat(testEntityManager.find(Animal.class, 4L)).isNull();
    }
}
