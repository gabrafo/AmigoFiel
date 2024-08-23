package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.Residency;
import br.com.amigofiel.exceptions.NotFoundException;
import br.com.amigofiel.mappers.AdoptantMapper;
import br.com.amigofiel.repositories.AdoptantRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdoptantService {

    private final AdoptantRepository adoptantRepository;
    private final AdoptantMapper adoptantMapper;

    @Transactional
    public Adoptant createAdoptant(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        adoptantRepository.save(newAdoptant);
        return newAdoptant;
    }

    public AdoptantDTO findAdoptantById(Long id) {
        return adoptantMapper.toDTO(adoptantRepository.findById(id).orElseThrow(() -> new NotFoundException("Adoptant not found")));
    }

    public List<Adoptant> findAllAdoptants() {
        return adoptantRepository.findAll();
    }

    public AdoptantDTO updateAdoptant(Adoptant adoptant){
        Optional<Adoptant> adoptantFound = adoptantRepository.findById(adoptant.getId());

        if (adoptantFound.isPresent()) {
            Adoptant existingAdoptant = adoptantFound.get();
            existingAdoptant.setName(adoptant.getName());
            existingAdoptant.setGender(adoptant.getGender());
            existingAdoptant.setResidency(adoptant.getResidency());
            existingAdoptant.setPhoneNumber(adoptant.getPhoneNumber());
            existingAdoptant.setReasonForAdoption(adoptant.getReasonForAdoption());
            existingAdoptant.setAgreesWithVisit(adoptant.isAgreesWithVisit());
            existingAdoptant.setAgreesWithResponsabilityTerms(adoptant.isAgreesWithResponsabilityTerms());
            existingAdoptant.setDeclaresTruthfulInformation(adoptant.isDeclaresTruthfulInformation());

            return adoptantMapper.toDTO(adoptantRepository.save(existingAdoptant));
        }
        else {
            throw new RuntimeException("Adoptant not found.");
        }
    }

    public void deleteAdoptantById(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        adoptantRepository.deleteById(newAdoptant.getId());
    }
}
