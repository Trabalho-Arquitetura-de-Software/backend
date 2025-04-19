# 🧩 UML & C4-PlantUML - Sistema de Controle de Projetos

Este diretório contém a modelagem arquitetural do sistema de controle de projetos, utilizando **PlantUML com C4-PlantUML**. Os arquivos são compatíveis com o **IntelliJ IDEA** e organizados por nível do modelo C4.

---

## 📁 Arquivos Disponíveis

- `Level1and2.puml` → Diagrama de Contexto e Contêineres (Nível 1 e 2)
- `Level3.puml` → Diagrama de Componentes (Nível 3)
- `Level4fluxo_projeto.puml` → Diagrama de sequência: Criação de Projeto
- `Level4fluxo_grupo.puml` → Diagrama de sequência: Gestão de Grupos
- `Level4fluxo_usuario.puml` → Diagrama de sequência: Usuários
- `Level4fluxo_autenticacao.puml` → Diagrama de sequência: Login e Geração de Token JWT
- `Level4fluxo_token_validation.puml` → Diagrama de sequência: Validação de Token JWT

---

## 🧪 Pré-requisitos

### 🔹 Java
Certifique-se de ter o Java 11+ instalado:

```powershell
java -version
```

### 🔹 Graphviz
Necessário para renderização:

Baixe o instalador em: [https://graphviz.org/download/](https://graphviz.org/download/)

Após a instalação, adicione o caminho do `bin/` do Graphviz nas variáveis de ambiente do Windows (Path).

---

## 💡 PlantUML no IntelliJ IDEA

### 🔌 Integração

1. Instale o plugin **PlantUML integration**:
   - Vá em `File > Settings > Plugins > Marketplace`
   - Busque por `PlantUML` e clique em instalar.
   - Reinicie o IntelliJ após a instalação.

2. (Opcional) Instale também o plugin **Structurizr DSL** para syntax highlight em `.dsl`

---

## ⚙️ Live Templates para C4-PlantUML

### 📥 Instalação

1. Baixe o template: [`c4_live_template.zip`](https://github.com/plantuml-stdlib/C4-PlantUML?tab=readme-ov-file#live-templates-for-intellij)
2. No IntelliJ, vá em:
   ```
   File > Manage IDE Settings > Import Settings
   ```
3. Escolha o arquivo ZIP baixado.
4. Marque a opção `Live templates` e clique em OK.
5. Reinicie o IntelliJ.

### 🚀 Uso

- Crie um novo arquivo `.puml`
- Digite `c4_` e pressione `Tab` para abrir o menu de Live Templates
- Selecione o artefato desejado (Container, System, Person, etc)
- Substitua os valores stub (alias, label, tecnologia...)

---

## 📷 Visualização dos Diagramas

### ✅ Dentro do IntelliJ

- Abra qualquer `.puml` e clique em **Preview** no canto superior direito.
- Ou utilize o atalho `Alt + Enter` para renderizar com o plugin PlantUML.

### ✅ Via Prompt de Comando (CMD ou PowerShell)

```powershell
java -jar plantuml.jar uml\Level1and2.puml
java -jar plantuml.jar uml\*.puml
```

---

## 🧠 Créditos

Baseado na especificação oficial do [C4-PlantUML](https://github.com/plantuml-stdlib/C4-PlantUML)

---

## 📘 Licença

Este projeto é educacional e pode ser livremente adaptado e reutilizado para fins acadêmicos ou de aprendizado.
