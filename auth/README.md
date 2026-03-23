```text name=project-structure.txt
src/main/java/com/acme/users
  ├─ domain/
  │   ├─ model/
  │   │   ├─ User.java
  │   │   └─ UserId.java
  │   ├─ service/
  │   │   └─ UserDomainService.java (opcional)
  │   └─ exception/
  │       └─ DomainException.java
  │
  ├─ application/
  │   ├─ port/
  │   │   ├─ in/
  │   │   │   ├─ CreateUserUseCase.java
  │   │   │   └─ GetUserUseCase.java
  │   │   └─ out/
  │   │       └─ UserRepositoryPort.java
  │   ├─ service/
  │   │   ├─ CreateUserService.java
  │   │   └─ GetUserService.java
  │   └─ dto/
  │       ├─ CreateUserCommand.java
  │       └─ UserResult.java
  │
  ├─ adapters/
  │   ├─ in/
  │   │   └─ web/
  │   │       ├─ UserController.java
  │   │       ├─ request/
  │   │       │   └─ CreateUserRequest.java
  │   │       ├─ response/
  │   │       │   └─ UserResponse.java
  │   │       └─ mapper/
  │   │           └─ UserWebMapper.java
  │   │
  │   └─ out/
  │       └─ persistence/
  │           ├─ UserJpaEntity.java
  │           ├─ SpringDataUserJpaRepository.java
  │           ├─ UserPersistenceAdapter.java
  │           └─ mapper/
  │               └─ UserPersistenceMapper.java
  │
  └─ config/
      └─ BeanConfig.java
```

## O que é cada pasta (camadas e responsabilidades)

### `domain/` (o “núcleo” do negócio)
Aqui fica a regra de negócio pura, sem Spring, sem HTTP, sem JPA.

- `domain/model/`
  - `User.java`: **Entidade de domínio**. Contém invariantes e comportamento (ex.: validar e-mail, mudar senha, ativar/desativar).
  - `UserId.java`: **Value Object** para identificador (evita usar `String/UUID` “solto” em todo lugar).
- `domain/service/` (opcional)
  - `UserDomainService.java`: regras que não pertencem naturalmente a uma entidade só (ex.: regra que cruza 2 entidades).
- `domain/exception/`
  - `DomainException.java`: exceções específicas do domínio (ex.: `InvalidEmailException`, `UserAlreadyActiveException`).

**Dependências permitidas:** Java puro (e no máximo libs “neutras” como `javax/ jakarta validation` se você quiser, mas muitos preferem nem isso no domínio).

---

### `application/` (casos de uso / orquestração)
Aqui ficam os **use cases**: “Criar usuário”, “Buscar usuário”, “Atualizar usuário”, etc.  
É onde você coordena domínio + portas de saída.

- `application/port/in/`
  - `CreateUserUseCase.java`, `GetUserUseCase.java`: interfaces do que seu sistema **oferece** para o mundo externo (entrada).
- `application/port/out/`
  - `UserRepositoryPort.java`: interface do que seu sistema **precisa** do mundo externo (ex.: persistência).
- `application/service/`
  - `CreateUserService.java`, `GetUserService.java`: implementações dos use cases. Aqui você:
    - valida regras de aplicação (ex.: “email deve ser único”)
    - chama o domínio para criar/alterar entidade
    - chama ports out (repositório) para salvar/buscar
- `application/dto/`
  - `CreateUserCommand.java`: DTO de entrada do use case (não é HTTP DTO; é “comando” da aplicação).
  - `UserResult.java`: DTO de saída do use case (não é response HTTP ainda).

**Dependências permitidas:** pode usar Spring (`@Service`) se você aceitar; se quiser mais “clean”, pode deixar sem Spring e registrar beans no `config/`.

---

### `adapters/` (mundo externo plugado por adapters)
Aqui ficam as integrações: web, banco, mensageria, etc.

#### `adapters/in/` (entrada)
Exemplo: HTTP REST.

- `adapters/in/web/UserController.java`
  - recebe request HTTP
  - converte para `CreateUserCommand` (via mapper)
  - chama `CreateUserUseCase`
  - converte `UserResult` para `UserResponse` e devolve HTTP
- `adapters/in/web/request/`
  - `CreateUserRequest.java`: DTO do JSON de entrada (`@RequestBody`)
- `adapters/in/web/response/`
  - `UserResponse.java`: DTO do JSON de saída
- `adapters/in/web/mapper/`
  - `UserWebMapper.java`: conversões request→command e result→response

**Regra:** controller não deveria ter regra de negócio; só orchestration de HTTP.

#### `adapters/out/` (saída)
Exemplo: persistência com Postgres/JPA.

- `adapters/out/persistence/UserJpaEntity.java`
  - entidade JPA (mapeada com `@Entity`) — **não é** a entidade de domínio.
- `adapters/out/persistence/SpringDataUserJpaRepository.java`
  - interface do Spring Data (extends `JpaRepository`)
- `adapters/out/persistence/UserPersistenceAdapter.java`
  - implementa `UserRepositoryPort` usando JPA repository
- `adapters/out/persistence/mapper/UserPersistenceMapper.java`
  - converte Domain↔JPA Entity

**Regra:** JPA fica “do lado de fora”; o domínio não depende disso.

---

### `config/` (wiring / configuração)
- `BeanConfig.java`: onde você pode “ligar os cabos”:
  - declarar beans manualmente
  - escolher implementação de ports
  - manter o core sem anotações Spring (se desejar)

---

## Fluxo completo de uma requisição (exemplo: criar usuário)

### Cenário: `POST /api/v1/users`
1) **HTTP entra no Adapter In**
- `UserController.create()` recebe:
  - `CreateUserRequest` (JSON → objeto)
  - validação básica (Bean Validation, se usar)

2) **Mapper Web transforma para linguagem da aplicação**
- `UserWebMapper.toCommand(request)` cria um `CreateUserCommand`
  - aqui você “traduza” o mundo HTTP para o mundo do seu caso de uso

3) **Controller chama o Port In (interface do caso de uso)**
- Controller depende de `CreateUserUseCase` (interface), não de classe concreta.

4) **Application Service executa o caso de uso**
- `CreateUserService.create(command)`:
  - regra de aplicação: “email já existe?” → chama `UserRepositoryPort.existsByEmail(email)`
  - cria entidade de domínio: `User.createNew(...)`
  - salva: `UserRepositoryPort.save(user)`

5) **Adapter Out implementa o Port Out**
- `UserPersistenceAdapter.save(user)`:
  - converte domínio → `UserJpaEntity` (mapper)
  - `SpringDataUserJpaRepository.save(entity)`
  - converte entity → domínio e retorna

6) **Use case retorna DTO de saída**
- `CreateUserService` devolve `UserResult` (id, username, email etc.)

7) **Controller monta resposta HTTP**
- `UserWebMapper.toResponse(result)` cria `UserResponse`
- retorna `ResponseEntity.created(location).body(userResponse)`

### Resumo visual (setas)
- **HTTP → Controller (adapter in)**
- Controller → **CreateUserUseCase (port in)**
- Use case → **UserRepositoryPort (port out)**
- Port out → Persistence Adapter → Spring Data JPA → Postgres
- volta tudo ao contrário até **HTTP Response**

---

## Dicas práticas (para ficar “hexagonal de verdade”)
- Evite retornar `UserEntity` do domínio direto no controller. Prefira:
  - Use case retorna `UserResult`
  - Controller retorna `UserResponse`
- Não reutilize `UserJpaEntity` no domínio ou application.
- Se usar Spring Security, ele fica como adapter/in (cross-cutting), mas sem “invadir” domínio.

Se você quiser, eu escrevo um exemplo de classes mínimo (controller + use case + ports + persistence adapter) seguindo exatamente essa estrutura, com `POST /api/v1/users` e `GET /api/v1/users/{id}`.
