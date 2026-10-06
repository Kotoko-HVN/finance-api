# finance-api

API REST de finanças pessoais em **Quarkus** + **PostgreSQL** (Hibernate ORM com Panache).
Atividade prática de Programação Orientada a Objetos — EC04 / 2026 — Setrem.

Baseado em: https://github.com/tiagoseibel/finance-api

## Estrutura

```
src/main/java/
├── entity/
│   ├── Categoria.java     -> tabela categoria
│   ├── Conta.java         -> tabela conta
│   └── Transacao.java     -> tabela transacao (FK para conta e categoria)
└── controller/
    ├── CategoriaController.java   GET, GET/{id}, POST, PUT/{id}, DELETE/{id}
    ├── ContaController.java       GET, GET/{id}, POST, PUT/{id}, DELETE/{id}
    └── TransacaoController.java   GET, GET/{id}, POST, PUT/{id}, DELETE/{id}
database/financeiro.sql            script de criação das tabelas
```

As entidades usam `PanacheEntityBase` com `@GeneratedValue(strategy = GenerationType.IDENTITY)`
porque as colunas `id` no banco são do tipo `serial` (o Postgres gera o id).

## Como rodar

1. Criar o banco `financeiro` no PostgreSQL e executar `database/financeiro.sql`.
2. Conferir usuário/senha em `src/main/resources/application.properties`.
3. Rodar em modo dev:

```
./mvnw quarkus:dev        (Linux/Mac)
mvnw.cmd quarkus:dev      (Windows)
```

A API sobe em http://localhost:8080.

## Endpoints

| Verbo  | Rota              | O que faz            |
|--------|-------------------|----------------------|
| GET    | /categoria        | lista todas          |
| GET    | /categoria/{id}   | busca uma            |
| POST   | /categoria        | cria                 |
| PUT    | /categoria/{id}   | edita                |
| DELETE | /categoria/{id}   | exclui               |

O mesmo vale para `/conta` e `/transacao`.

## Exemplos de JSON

Categoria / Conta:

```json
{ "descricao": "Alimentação" }
```

Transação (`tipo`: `R` = receita, `D` = despesa):

```json
{
  "descricao": "Almoço",
  "valor": 35.90,
  "tipo": "D",
  "dataTransacao": "2026-10-05",
  "conta": { "id": 1 },
  "categoria": { "id": 1 }
}
```

O arquivo `requisicoes.http` tem todas as chamadas prontas para testar no IntelliJ / VS Code (extensão REST Client).
