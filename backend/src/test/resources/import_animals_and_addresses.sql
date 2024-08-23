-- src/test/resources/import_animals_and_addresses.sql

-- Inserir dados na tabela residency
INSERT INTO address (id, zip_code, street, neighbourhood, city, federal_unit) VALUES
                                                                                  (1, '12345-678', 'Rua das Flores', 'Centro', 'Cidade Exemplo', 'MG'),
                                                                                  (2, '23456-789', 'Avenida Central', 'Bairro Novo', 'Outra Cidade', 'MG'),
                                                                                  (3, '34567-890', 'Praça da Liberdade', 'Liberdade', 'Mais uma Cidade', 'MG');

-- Inserir dados na tabela animal
INSERT INTO animal (id, name, specie, breed, birth_date, sex, weight, size, neutered, address_id,
                    registration_date, current_status, adoption_id) VALUES
                                                                        (1, 'Rex', 'DOG', 'Labrador', '2020-01-01', 'M', 30.0, 'LARGE', true, 1, '2023-01-01', 'AVAILABLE', null),
                                                                        (2, 'Luna', 'CAT', 'Siamese', '2019-06-15', 'F', 5.0, 'SMALL', true, 2, '2023-06-15', 'ADOPTED', null),
                                                                        (3, 'Max', 'DOG', 'Bulldog', '2018-09-23', 'M', 20.0, 'MEDIUM', false, 3, '2023-09-23', 'AVAILABLE', null);
