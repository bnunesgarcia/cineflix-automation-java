🧪 Desafio de Automação Frontend - DemoQA
Este projeto contém a automação de testes para a plataforma DemoQA, utilizando Java, Selenium WebDriver e Cucumber (BDD).

🚀 Pré-requisitos
Para rodar este projeto, você precisará ter instalado em sua máquina:

Java JDK (versão 11 ou superior): Download aqui

Certifique-se de configurar a variável de ambiente JAVA_HOME.

Maven (versão 3.6+): Download aqui

Certifique-se de que o comando mvn está acessível no seu terminal/CMD.

Google Chrome: Os testes estão configurados para rodar no Chrome via ChromeDriver (gerenciado automaticamente pelo Selenium Manager).

🛠️ Tecnologias Utilizadas
Linguagem: Java

Gerenciador de Dependências: Maven

Automação Web: Selenium WebDriver (Page Object Model + PageFactory)

BDD / Escrita de Testes: Cucumber & Gherkin

Massa de Dados: Java Faker (Geração de dados aleatórios)

📁 Estrutura do Projeto
src/test/resources/features: Arquivos .feature com a descrição dos cenários em Gherkin.

src/test/java/steps: Definições dos passos (Step Definitions).

src/test/java/pages: Classes Page Objects com o mapeamento de elementos e ações.

src/test/java/support: Classes utilitárias e inicialização do Driver.

src/test/resources/application.properties: Configurações de URL, Navegador e Timeouts.