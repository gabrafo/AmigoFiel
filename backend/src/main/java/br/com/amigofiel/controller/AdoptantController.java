package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.services.AdoptantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptants")
@Tag(name = "Adoptant", description = "Endpoints relacionados a Adoptant")
public class AdoptantController {

    @Autowired
    private AdoptantService adoptantService;
    @Operation(summary = "Cria um adotante",
            description = "Cria um novo adotante no banco de dados.",
            tags = {"Adoptant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AdoptantDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    @PostMapping(value = "/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Adoptant> saveAdoptant(@Valid @RequestBody AdoptantDTO adoptantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptantService.createAdoptant(adoptantDTO));
    }


    @Operation(summary = "Lista os adotantes",
            description = "Lista todos os adotantes do Banco de Dados.",
            tags = {"Adoptant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AdoptantDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "list_adoptants",
            produces = "application/json")
    public ResponseEntity<List<Adoptant>> listAdoptants() {
        return ResponseEntity.status(HttpStatus.OK).body(adoptantService.findAllAdoptants());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Adoptant> findAdoptantById(@PathVariable("id") Long id) {
        AdoptantDTO adoptantDTO = adoptantService.findAdoptantById(id);

        if (adoptantDTO != null) {
            Adoptant adoptant = new Adoptant();

            adoptant.setName(adoptantDTO.name());
            adoptant.setBirthDate(adoptantDTO.birthDate());
            adoptant.setGender(adoptantDTO.gender());
            adoptant.setResidency(adoptantDTO.residency());
            adoptant.setAdoptions(adoptantDTO.adoptions());

            return ResponseEntity.ok(adoptant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/adoptants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteAdoptantById(@PathVariable("id") Long id) {
        AdoptantDTO adoptantDTO = adoptantService.findAdoptantById(id);

        if (adoptantDTO != null) {
            Adoptant adoptant = new Adoptant();
            adoptantService.deleteAdoptantById(adoptantDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Adoptant " + adoptant.getName() + " deleted successfully!");
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptantDTO> updateAdoptant(@RequestBody Adoptant adoptant) {
        try {
            AdoptantDTO updatedAdoptant = adoptantService.updateAdoptant(adoptant);
            return ResponseEntity.ok(updatedAdoptant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
