package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptionDTO;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.mappers.AdoptionMapper;
import br.com.amigofiel.repositories.AdoptionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;

    @Transactional
    public Adoption doAdoption(AdoptionDTO adoptionDTO) {
        Adoption adoption = adoptionMapper.toEntity(adoptionDTO);

        if (validateAdoptions() <= 3) {
            return adoptionRepository.save(adoption);
        } else{
            throw new RuntimeException("Only three adoptions are allowed at a time.");
        }
    }

    private int validateAdoptions() {
        List<Adoption> adoptions = adoptionRepository.findAll();
        return adoptions.size();
    }
    public List<AdoptionDTO> findAllAdoptions() {
        List<Adoption> adoptions = adoptionRepository.findAll();
        return adoptions.stream().map(AdoptionDTO::new).collect(Collectors.toList());
    }
}
