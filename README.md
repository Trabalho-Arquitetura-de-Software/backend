[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/fyEH3eV4)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=18569837&assignment_repo_type=AssignmentRepo)
# Desenvolvimento Back End

## Turma 01 - Noturno - Engenharia de Software

Deve ser utilizado obrigatoriamente a linguagem JAVA com Spring.
Necessário a identificação da tecnologia utilizada na entrega do formulário com o link desse projeto
___

# Management_Service
## 📌Descrição
Está é uma API GraphQL feita com Spring, que tem por objetivo gerenciar projetos para a fábrica de software e autenticar os usuários para que estes possam utilizá-la.

## ✅Como Usar!
- Para utilizar essa aplicação primeiro é necessário iniciar o banco de dados, que se encontra no container configurado abaixo:
  - Para iniciar o banco de dados basta executar o comando: `docker compose up -d`
  obs: precisa ter o docker instalado na máquina para que o comando funcione.
  <div>
    <p>Dica!: acesse <a href="https://www.docker.com/products/docker-desktop/">Docker</a> para baixa-lo em sua máquina!</p>
  </div>

- Após iniciar o banco, já podemos startar a aplicação. Para isso, basta inicia-la com o IntelliJ, ou com a IDE de sua preferência.

- Após iniciar a aplicação, acesse: `localhost:8080/graphiql`
É neste endereço que a aplicação roda localmente.

- Dentro do playground da aplicação, utilize este email e password na consulta de login para conseguir seu JWT, e assim, realizar as requisições que desejar:
Exemplo da consulta de Login:
```
mutation Login{
  login(email: null, password: null){
    token
  }
}
```

Email e Password:
```
email: "admin@test.com"
password: "admin123"
```

- Agora com seu Token em mãos, realize as consultas que desejar. Para isso basta adicionar o token ao campo Header que fica logo abaixo do campo que recebe as consultas.
JSON para o token:
```
{
  "Authorization": "Bearer SEU_JWT_AQUI"
}
```
- Ex:
![image](https://github.com/user-attachments/assets/f1bddbc8-d5bf-44e0-b108-43ceea93a112)
