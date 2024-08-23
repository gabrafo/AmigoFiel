package br.com.amigofiel.services;

import br.com.amigofiel.clients.ViaCepClient;
import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.exceptions.ClientException;
import br.com.amigofiel.exceptions.InvalidEntryException;
import br.com.amigofiel.mappers.AddressMapper;
import br.com.amigofiel.repositories.AddressRepository;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private Validator validator;

    @Autowired
    private AddressMapper addressMapper;

    public Address findAddress(String zip) {

        // Tira qualquer caractere que não for um número
        zip = zip.replaceAll("\\D", "");

        if (zip.length()!=8) throw new InvalidEntryException("CEP inválido");

        // Tenta fazer a requisição e se ela der certo salva o endereço no banco de dados e retorna os valores cadastrados
        try{
            AddressDTO addressDTO = viaCepClient.findAddressByZipCode(zip);

            // Valida o EnderecoDTO e retorna uma exceção com todos os campos inválidos
            Set<ConstraintViolation<AddressDTO>> violations = validator.validate(addressDTO);
            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                logger.error("Dados inválidos recebidos da API ViaCep: {}", errorMessage);
                throw new InvalidEntryException("Dados inválidos! Erros: " + errorMessage);
            }

            Address address = addressMapper.toEntity(addressDTO);
            repository.save(address);
            logger.info("Endereço salvo com sucesso: {}", address);
            return address;
        } catch (HttpClientErrorException.BadRequest e){
            logger.error("CEP não encontrado: {}", zip, e);
            throw new InvalidEntryException("CEP não encontrado");
        } catch (FeignException.FeignClientException e){
            logger.error("Erro ao consultar a API ViaCep para o CEP: {}", zip, e);
            throw new ClientException("Erro ao consultar o CEP");
        }
    }
}
