package br.com.amigofiel.repositories;

import br.com.amigofiel.domain.entities.Adoptant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptantRepository extends JpaRepository<Adoptant, Long> {
}
