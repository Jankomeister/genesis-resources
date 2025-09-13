# Genesis Resources

Registrační systém pro distribuci životně důložitých surovin v postapokalyptickém světě.
Projekt vznikl jako závěrečný projekt a simuluje správu uživatelů pro spravedlivé rozdělení surovin.

## Technologie a stack
- **Java 21**
- **Spring Boot 3.2**
- **Lombok** - minimalizace boilerplate kódu
- **Spring Data JPA** - komunikace s databází
- **H2 Database** - in-memory databáze pro vývoj a testování
- **Maven** - build a dependency management
- **Postman** - testování REST API

## Struktura projektu
- `entity/` - definice entit (User)
- `repository/` – JPA repository
- `service/` – business logika
- `controller/` – REST API endpointy
- `data/` – soubory jako `dataPersonId.txt` s personID
- `resources/` – konfigurace a databázové skripty

## REST API
### Uživatelské operace
| Operace                  | Endpoint                             | Request body                                                         | Popis                                            |
|--------------------------|--------------------------------------|----------------------------------------------------------------------|--------------------------------------------------|
| Založení uživatele       | POST `/api/v1/users`                 | `{ "name": "Jack", "surname": "Smith", "personID": "jXa4g3H7oPq2" }` | Vytvoří nového uživatele a vygeneruje UUID       |
| Info o jednom uživateli  | GET `/api/v1/users/{id}`             | -                                                                    | Vrací `{id, name, surname}`                      |
| Detail o uživateli       | GET `/api/v1/users/{id}?detail=true` | -                                                                    | Vrací `{id, name, surname, personID, uuid}`      |
| Info o všech uživatelích | GET `/api/v1/users`                  | -                                                                    | Vrací list `{id, name, surname}`                 |
| Detail všech uživatelů   | GET `/api/v1/users?detail=true`      | -                                                                    | Vrací list `{id, name, surname, personID, uuid}` |
| Úprava uživatele         | PUT `/api/v1/users`                  | `{ "id": 1, "name": "John", "surname": "Doe" }`                      | Upravuje jméno a příjmení uživatele              |
| Smazání uživatele        | DELETE `/api/v1/users/{id}`          | -                                                                    | Odstraní uživatele z databáze                    |

## Databáze
- Sloupce tabulky `users`:
    - `id` – Long, PK, auto increment
    - `name` – Varchar, NOT NULL
    - `surname` – Varchar
    - `personID` – Varchar, UNIQUE, NOT NULL
    - `uuid` – Varchar, UNIQUE, NOT NULL

- `dataPersonId.txt` – seznam dostupných personID, které lze použít při registraci uživatelů

## Spuštění projektu
1. Stáhni projekt nebo klonuj repo
```bash
git clone <repo-url>
cd genesis-resources
```
2. Postav projekt a spusť
```bash
mvn clean install
mvn spring-boot:run
```
3. Testuj API pomocí Postmana (importuj Postman collection GenesisResources.postman_collection.json)

## Git workflow
- Branch pro feature: feature/<co>
- Branch pro bugfix: fix/<co>
- Commit message: type: krátký popis (např.: feat: add user entity with lombok)
- Pull request: feature branch -> main
- Merge přes GitHub (squash nebo merge commit)

## Autor
- Projekt byl vytvořen jako závěrečný projekt pro kurz Java Akademie u společnosti Engeto.
- Autor: Ján Mišejka