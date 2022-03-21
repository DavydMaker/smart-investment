# smart-investment
Aplicação para compra inteligente de Ações em múltiplas Empresas. 

## Requerimentos
Para compilar e rodar a aplicação você precisa de:

- [JDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Executando Testes Unitários
Para executar os testes unitario, rodar o comando:
```shell
mvn test
```

## Compilando e Executando
Para compilar a aplicação, rodar o comando:
```shell
mvn clean install
```

Para executar a aplicação, rodar o comando:
```shell
mvn spring-boot:run
```

### Acessando Swagger
Após aplicação iniciar, abrir no navegador a seguinte URL para acessar a documentação Swagger:
```
http://localhost:8080/swagger-ui.html
```

### Acessando Console do H2
Após aplicação iniciar, o console do banco H2 pode ser acessado navegando até:
```
http://localhost:8080/h2-console
```

## TODO
Tarefas principais:
- [X] Cadastro de Empresa
- [X] Cadastro de Ação para Empresa
- [X] Integração com Banco de Dados (H2)
- [X] Implementação dos Testes Unitários
- [X] Endpoints e services documentados
- [ ] Atualizar Preço de Ação
- [ ] Atualizar Status de Empresa (Ativa/Inativa)
- [ ] Excluir Empresa
- [ ] Consultar Lista de Empresas (incluir filtro de busca por Status)
- [ ] Consultar Lista de Investimentos Realizados
- [ ] Funcionalidade para Investir em Grupo de Ações

Em várias partes do código foi inserido notas de TODO para realizar a posterior.
Aqui vai constar os TODO de débitos adicionais para ser aplicado em contexto geral do sistema:
- criar campos de auditoria (inclusão, alteração e exclusão);

## Modelo de Dados
![Modelo de Dados](https://davydmaker.com/smart-investment-uml.jpg)

## Informações Adicionais
A aplicação utiliza Banco de Dados H2 integrado (os dados são resetados sempre ao iniciar aplicação).