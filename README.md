
# 🚀 Cineflix Automation Project - RecordTV

Este projeto é uma suíte de testes automatizados **End-to-End (E2E)** para a plataforma **Cineflix**, desenvolvida durante minha atuação na **RecordTV**. O foco principal é validar fluxos críticos de negócio, como o gerenciamento de departamentos e perfis de acesso, garantindo a estabilidade e qualidade das entregas.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework de Teste:** Selenium WebDriver
* **BDD (Behavior Driven Development):** Cucumber
* **Gerenciamento de Dependências:** Maven
* **Padrão de Projeto:** Page Objects (POM)
* **Relatórios:** ExtentReports / Cucumber Reports

## 🏗️ Arquitetura do Projeto

O projeto foi estruturado seguindo as melhores práticas de Clean Code e reutilização de componentes:



* **Features:** Arquivos `.feature` escritos em Gherkin (Português/Inglês) descrevendo o comportamento esperado.
* **Steps:** Camada que traduz a linguagem de negócio para chamadas de métodos técnicos.
* **Pages:** Implementação do padrão **Page Objects**, onde cada página da aplicação tem sua classe correspondente, isolando os seletores (XPath/CSS) da lógica de teste.
* **Support/Utils:** Classes auxiliares para tratamento de esperas explícitas, interações via JavaScript e utilitários globais.

## 🧪 Diferenciais Técnicos

* **Resiliência:** Implementação de estratégias de *Retry* e esperas dinâmicas (`WebDriverWait`) para lidar com o comportamento assíncrono do Angular Material.
* **Autenticação Automatizada:** Script PowerShell integrado para obtenção de tokens OAuth2 via Azure AD, permitindo testes em ambientes com segurança rigorosa.
* **Escalabilidade:** Lógica preparada para processamento em lote (ex: exclusão massiva de registros) com tratamento de exceções robusto.

## 🚀 Como Executar

1.  **Pré-requisitos:**
    * JDK 17+
    * Maven instalado
    * ChromeDriver compatível com sua versão do Chrome.

2.  **Instalação:**
    ```bash
    git clone https://github.com/SEU_USUARIO/cineflix-automation-java.git
    cd cineflix-automation-java
    mvn install
    ```

3.  **Execução via Terminal:**
    ```bash
    mvn test -Dcucumber.options="classpath:features"
    ```

## 📊 Relatórios

Ao final de cada execução, o framework gera relatórios detalhados que permitem a análise rápida de falhas, capturando evidências e logs de erro do Selenium.