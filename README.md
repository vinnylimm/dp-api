# Employee & Department Manager (API REST)

Este projeto é uma **API REST** desenvolvida com Spring Boot para gerenciar o relacionamento entre Funcionários (Employees) e Departamentos (Departments). O foco principal foi a implementação de relacionamentos **Many-to-Many** e o uso do padrão **DTO** para transferência de dados.

## 🚀 Tecnologias

* **Java 21**
* **Spring Boot 3**
* **Spring Data JPA**
* **H2 Database** (Memória)
* **Maven**

## 🛠️ Funcionalidades

* **CRUD Completo**: Gestão de funcionários e departamentos.
* **Paginação**: Endpoints de listagem preparados para grandes volumes de dados.
* **Associação Many-to-Many**: Vínculo flexível entre funcionários e múltiplos setores.
* **Database Seeding**: População automática de dados via `import.sql`.

## 🔌 Endpoints da API

### Departamentos (`/department`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/department` | Lista departamentos (paginado) |
| GET | `/department/{id}` | Busca departamento por ID |
| POST | `/department` | Cria um novo departamento |
| PUT | `/department/{id}` | Atualiza um departamento existente |
| DELETE | `/department/{id}` | Remove um departamento |

### Funcionários (`/employee`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/employee` | Lista funcionários (paginado) |
| GET | `/employee/{id}` | Busca funcionário com seus departamentos |
| POST | `/employee` | Cria funcionário e associa a departamentos |
| PUT | `/employee/{id}` | Atualiza dados e vínculos do funcionário |
| DELETE | `/employee/{id}` | Remove um funcionário |

## 📐 Como rodar o projeto

1. Clone o repositório.
2. Certifique-se de estar usando o **JDK 21**.
3. Execute a aplicação via IntelliJ ou terminal: `./mvnw spring-boot:run`.
4. O console do banco de dados estará disponível em: `http://localhost:8080/h2-console`
   * **JDBC URL**: `jdbc:h2:mem:testdb`
   * **User**: `sa`

---
Desenvolvido por **Vinícios Lima** 🚀
