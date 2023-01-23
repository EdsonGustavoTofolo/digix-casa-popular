# Casa Popular

## Desafio técnico por Digix

Link da descrição do [desafio técnico](https://www.notion.so/Desafio-t-cnico-2f53347cf16a418fb75c67c978ab1a0e).

### Stacks Projeto

- Spring Boot
- Spring Web
- Spring Validation
- Lombok
- Java 17
- jUnit 5
- Maven

### Estrutura

O projeto foi estruturado baseado na __Arquitetura Limpa__ do Uncle Bob. <br/>
Cada package corresponde a uma camada, conforme pode ser visto no link: [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

### Rodando projeto

Ao rodar o projeto são disponibilizados dois *endpoints*:

#### Adicionar famílias
POST http://localhost:8080/api/v1/casa-popular/familias
<p>Request body:</p>

```json
{
    "pai": {
        "nome": "Pedro",
        "idade": 59,
        "renda": 300.0
    },
    "mae": {
        "nome": "Paula",
        "idade": 50,
        "renda": 0.0
    },
    "dependentes": [    
        {
            "nome": "Lisa",
            "idade": 10,
            "renda": 0.0
        }
    ]
}
```

#### Processar pontos
POST http://localhost:8080/api/v1/casa-popular/familias/pontos
