# Use uma imagem base do Maven para construir o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo pom.xml e os arquivos de configuração necessários
COPY pom.xml .

# Baixe as dependências do Maven
RUN mvn dependency:go-offline -B

# Copie todo o código fonte do projeto para o container
COPY src ./src

# Construa o projeto e empacote-o como um JAR
RUN mvn clean package -Dmaven.test.skip=true

# Use uma imagem base do JDK para executar o JAR
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o JAR gerado do estágio anterior para o container
COPY --from=build /app/target/*.jar app.jar

# Defina o comando padrão para executar o JAR
CMD ["java", "-jar", "app.jar"]