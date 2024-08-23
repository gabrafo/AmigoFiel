package br.com.amigofiel.repositories;

import br.com.amigofiel.domain.entities.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
