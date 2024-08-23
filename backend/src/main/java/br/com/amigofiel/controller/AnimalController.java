package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.services.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
@Tag(name = "Animal", description = "Endpoints relacionados a Animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Operation(summary = "Lista os animais",
            description = "Lista todos os animais do Banco de Dados.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value="/all",
            produces = "application/json")
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAllAnimals());
    }

    @Operation(summary = "Busca um animal por ID",
            description = "Busca um animal no Banco de Dados usando o ID como parâmetro.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value="/{id}",
            produces = "application/json")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAnimalById(id));
    }

    @Operation(summary = "Cria um animal",
            description = "Cria um novo animal no Banco de Dados.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<AnimalDTO> createAnimal(@Valid @RequestBody AnimalDTO animalDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.createAnimal(animalDTO));
    }
    
    @Operation(summary = "Atualiza um animal",
            description = "Atualiza algum dos animais do Banco de Dados.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{id}",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<AnimalDTO> updateAnimal(@PathVariable Long id, @Valid @RequestBody AnimalDTO animalDTO){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.updateAnimal(id, animalDTO));
    }

    @Operation(summary = "Exclui um animal",
            description = "Exclui um dos animais do Banco de Dados.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.deleteAnimalById(id));
    }
}
