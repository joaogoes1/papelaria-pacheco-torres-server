# 🏪 Sistema Papelaria Pacheco Torres

Sistema de gestão completo para a Papelaria Pacheco Torres, desenvolvido em Java com Spring Boot. O sistema oferece APIs REST para gerenciamento de clientes, produtos, estoque, vendas e relatórios.

Projeto desenvolvido para a goDigital Code: Fundamentos para o Sucesso Digital (Cap 1) do curso de Desenvolvimento de Sistemas da FIAP

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [APIs Disponíveis](#apis-disponíveis)
- [Como Executar](#como-executar)
- [Exemplos de Uso](#exemplos-de-uso)
- [Funcionalidades](#funcionalidades)

## 🎯 Visão Geral

O sistema foi desenvolvido para atender às necessidades de gestão da Papelaria Pacheco Torres, oferecendo:

- **Gestão de Clientes**: Cadastro, consulta, atualização e exclusão de clientes
- **Gestão de Produtos**: Controle completo do catálogo de produtos
- **Controle de Estoque**: Acompanhamento de quantidades e alertas de estoque mínimo
- **Gestão de Vendas**: Registro de vendas com itens e valores
- **Relatórios**: Exportação de dados em CSV e importação de clientes
- **Arquitetura Modular**: Componentes desacoplados e reutilizáveis

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
┌─────────────────────────────────────────────────────────────┐
│                    Controllers (REST)                       │
├─────────────────────────────────────────────────────────────┤
│                    Services (Lógica)                        │
├─────────────────────────────────────────────────────────────┤
│                Mock Repositories (Dados)                    │
├─────────────────────────────────────────────────────────────┤
│                    Models (Entidades)                       │
└─────────────────────────────────────────────────────────────┘
```

### Padrões Utilizados

- **MVC (Model-View-Controller)**: Separação clara entre dados, lógica e apresentação
- **Repository Pattern**: Abstração do acesso a dados
- **Service Layer**: Encapsulamento da lógica de negócio
- **RESTful APIs**: Endpoints padronizados seguindo convenções REST

## 🛠️ Tecnologias

- **Java 17**: Linguagem principal
- **Spring Boot 3.5.3**: Framework para desenvolvimento de aplicações
- **Gradle**: Gerenciador de dependências e build
- **Apache Commons CSV**: Manipulação de arquivos CSV
- **Spring Web**: Suporte a APIs REST
- **Spring Security**: Segurança e autenticação (configurado mas não ativo)

## 📁 Estrutura do Projeto

```
src/main/java/org/papelariapachecotorres/
├── PapelariaPachecoTorresApplication.java    # Classe principal
├── clientes/                                 # Módulo de Clientes
│   ├── Cliente.java                         # Model
│   ├── ClienteController.java               # REST Controller
│   ├── ClienteService.java                  # Service
│   └── ClienteMockRepository.java           # Mock Repository
├── produtos/                                 # Módulo de Produtos
│   ├── Produto.java                         # Model
│   ├── ProdutoController.java               # REST Controller
│   ├── ProdutoService.java                  # Service
│   └── ProdutoMockRepository.java           # Mock Repository
├── estoque/                                  # Módulo de Estoque
│   ├── Estoque.java                         # Model
│   ├── EstoqueController.java               # REST Controller
│   ├── EstoqueService.java                  # Service
│   └── EstoqueMockRepository.java           # Mock Repository
├── vendas/                                   # Módulo de Vendas
│   ├── Venda.java                           # Model
│   ├── VendaController.java                 # REST Controller
│   ├── VendaService.java                    # Service
│   └── VendaMockRepository.java             # Mock Repository
└── relatorios/                               # Módulo de Relatórios
    ├── ReportController.java                # REST Controller
    └── ReportService.java                   # Service
```

## 🔌 APIs Disponíveis

### Clientes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/clientes` | Lista todos os clientes |
| GET | `/clientes/{id}` | Busca cliente por ID |
| POST | `/clientes` | Cria novo cliente |
| PUT | `/clientes/{id}` | Atualiza cliente |
| DELETE | `/clientes/{id}` | Remove cliente |

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca produto por ID |
| POST | `/produtos` | Cria novo produto |
| PUT | `/produtos/{id}` | Atualiza produto |
| DELETE | `/produtos/{id}` | Remove produto |

### Estoque
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/estoque` | Lista todo o estoque |
| GET | `/estoque?produtoId={id}` | Busca estoque por produto |
| GET | `/estoque/{id}` | Busca estoque por ID |
| POST | `/estoque` | Cria novo registro de estoque |
| PUT | `/estoque/{id}` | Atualiza estoque |
| DELETE | `/estoque/{id}` | Remove registro de estoque |

### Vendas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/vendas` | Lista todas as vendas |
| GET | `/vendas/{id}` | Busca venda por ID |
| POST | `/vendas` | Cria nova venda |
| PUT | `/vendas/{id}` | Atualiza venda |
| DELETE | `/vendas/{id}` | Remove venda |

### Relatórios
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/relatorios/vendas/exportar` | Exporta relatório de vendas (CSV) |
| GET | `/relatorios/clientes/exportar` | Exporta relatório de clientes (CSV) |
| GET | `/relatorios/estoque/exportar` | Exporta relatório de estoque (CSV) |
| POST | `/relatorios/clientes/importar` | Importa clientes de arquivo CSV |

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Gradle (opcional, o projeto inclui o wrapper)

### Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd papelaria-pacheco-torres
   ```

2. **Execute o projeto**
   ```bash
   # Usando o wrapper do Gradle
   ./gradlew bootRun
   
   # Ou compile e execute
   ./gradlew build
   java -jar build/libs/papelaria-pacheco-torres-0.0.1-SNAPSHOT.jar
   ```

3. **Acesse a aplicação**
   - A aplicação estará disponível em: `http://localhost:8080`
   - Swagger UI (se configurado): `http://localhost:8080/swagger-ui.html`

### Comandos Úteis

```bash
# Compilar o projeto
./gradlew build

# Executar testes
./gradlew test

# Limpar build
./gradlew clean

# Ver dependências
./gradlew dependencies
```

## 📝 Exemplos de Uso

### Criar um Cliente

```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "endereco": "Rua das Flores, 123",
    "telefone": "(11) 99999-9999",
    "email": "joao@email.com"
  }'
```

### Criar um Produto

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Caneta Azul",
    "codigo": "CAN001",
    "preco": 2.50,
    "categoria": "Escritório",
    "descricao": "Caneta esferográfica azul"
  }'
```

### Criar uma Venda

```bash
curl -X POST http://localhost:8080/vendas \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": 1,
    "itens": [
      {
        "produtoId": 1,
        "quantidade": 2,
        "precoUnitario": 2.50
      }
    ],
    "total": 5.00
  }'
```

### Exportar Relatório

```bash
curl -X GET http://localhost:8080/relatorios/clientes/exportar
```

### Importar Clientes

```bash
curl -X POST http://localhost:8080/relatorios/clientes/importar \
  -H "Content-Type: application/json" \
  -d '{
    "path": "clientes-para-importar.csv"
  }'
```

## 📊 Funcionalidades

### Gestão de Clientes
- Cadastro completo com dados pessoais
- Consulta por ID ou listagem geral
- Atualização e exclusão de registros
- Importação em lote via CSV

### Gestão de Produtos
- Catálogo com código, nome, preço e categoria
- Descrição detalhada dos produtos
- Controle de preços

### Controle de Estoque
- Acompanhamento de quantidades
- Estoque mínimo configurável
- Última atualização registrada
- Busca por produto específico

### Gestão de Vendas
- Registro de vendas com múltiplos itens
- Cálculo automático de totais
- Associação com cliente
- Histórico completo

### Relatórios
- Exportação de dados em formato CSV
- Relatórios de vendas, clientes e estoque
- Importação de clientes via arquivo CSV
- Arquivos salvos na pasta `reports/`

## 🔧 Configurações

### application.properties
```properties
# Configurações básicas do Spring Boot
server.port=8080
spring.application.name=papelaria-pacheco-torres
```

### Estrutura de Dados Mockados

O sistema vem com dados de exemplo pré-carregados:

- **3 Clientes**: João Góes, Pedro Arenas, Frederico
- **3 Produtos**: Caneta Azul, Caderno Universitário, Lápis HB
- **3 Registros de Estoque**: Quantidades e mínimos configurados
- **2 Vendas**: Exemplos de transações realizadas

## 🚧 Considerações Técnicas

### Persistência
- Atualmente utiliza repositórios mockados em memória
- Dados são perdidos ao reiniciar a aplicação
- Preparado para futura integração com banco de dados

### Segurança
- Endpoints públicos (sem autenticação)
- Preparado para implementação de JWT (dependências já incluídas)

### Escalabilidade
- Arquitetura modular permite fácil expansão
- Separação clara de responsabilidades
- Código reutilizável e testável

## 🤝 Contribuição

Para contribuir com o projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Implemente suas mudanças
4. Execute os testes
5. Faça commit e push
6. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 📞 Suporte

Para dúvidas ou suporte:
- Abra uma issue no repositório
- Entre em contato com a equipe de desenvolvimento

---

**Desenvolvido para a Papelaria Pacheco Torres** 🏪 