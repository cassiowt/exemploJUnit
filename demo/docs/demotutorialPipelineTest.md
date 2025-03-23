# Roteiro: Testes Automatizados com Selenium (Java) e GitHub Actions (JUnit 5)

Este roteiro detalha como configurar testes automatizados com Selenium em Java e integrá-los no GitHub Actions, utilizando JUnit 5.

## Parte 1: Configuração do Projeto Java com Selenium

1.  **Criar Projeto Maven:**
    * Crie um projeto Maven em sua IDE preferida.
    * Adicione as dependências do Selenium, JUnit 5 e WebDriverManager ao `pom.xml`:

        ```xml
        <dependencies>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-java</artifactId>
                <version>4.16.0</version>
            </dependency>
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-api</artifactId>
                <version>5.8.1</version>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-engine</artifactId>
                <version>5.8.1</version>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>io.github.bonigarcia</groupId>
                <artifactId>webdrivermanager</artifactId>
                <version>5.6.3</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
        ```

        * `webdrivermanager` facilita o gerenciamento dos drivers dos navegadores.

2.  **Criar Classe de Teste:**
    * Crie uma classe Java para os testes Selenium, por exemplo, `WebsiteTest.java`, dentro do diretório `src/test/java/`.

3.  **Implementar Testes Selenium:**

        ```java
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.firefox.FirefoxOptions;
        import io.github.bonigarcia.wdm.WebDriverManager;

        import static org.junit.jupiter.api.Assertions.assertEquals;

        public class WebsiteTest {

            private WebDriver driver;

            @BeforeEach
            public void setUp() {
                String browser = System.getProperty("browser"); // browser será passado pelo comando mvn

                if(browser == null || browser.equalsIgnoreCase("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless"); // Executar em modo headless
                    driver = new ChromeDriver(chromeOptions);

                } else if (browser.equalsIgnoreCase("firefox")){
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                } else{
                    throw new IllegalArgumentException("Navegador não suportado. Use 'chrome' ou 'firefox'");

                }
                driver.manage().window().maximize();

            }

            @Test
            public void testWebsiteTitle() {
                driver.get("[https://hortti.com](https://hortti.com)");
                assertEquals("Hortti - Início", driver.getTitle());
            }

            @AfterEach
            public void tearDown() {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        ```

        
        * Este exemplo abre `hortti.com` e verifica o título da página.
        * Usa o WebdriverManager para baixar os drivers.
        * Executa os testes no Chrome e no Firefox.

4.  **Executar Testes Localmente:**
    * Execute os testes localmente para verificar se estão funcionando corretamente.
    * Utilize o comando `mvn test -Dbrowser=chrome` para rodar os testes no chrome.
    * Utilize o comando `mvn test -Dbrowser=firefox` para rodar os testes no firefox.
    * Se nenhum browser for passado por parâmetro o chrome será utilizado.

## Parte 2: Configuração do GitHub Actions

1.  **Criar Repositório GitHub:**
    * Crie um repositório no GitHub para o seu projeto.

2.  **Criar Workflow do GitHub Actions:**
    * No diretório raiz do seu projeto, crie a pasta `.github/workflows`.
    * Dentro da pasta `workflows`, crie um arquivo YAML, por exemplo, `selenium-test.yml`.

3.  **Configurar o Workflow:**

    ```yaml
    name: Selenium Tests

    on: [push]

    jobs:
      test:
        runs-on: ubuntu-latest
        strategy:
          matrix:
            browser: [chrome, firefox] # testaremos nos dois navegadores

        steps:
        - name: Checkout code
          uses: actions/checkout@v3

        - name: Set up JDK 11
          uses: actions/setup-java@v3
          with:
            java-version: '11'
            distribution: 'temurin'

        - name: Run Selenium Tests (Chrome)
          run: mvn test -Dbrowser=${{ matrix.browser }} # navegador passado por parametro
    ```

    * Este workflow:
        * É acionado em cada `push`.
        * Executa em um ambiente Ubuntu.
        * Utiliza uma matrix, para executar as tarefas em ambos os browsers, em paralelo.
        * Faz o checkout do código.
        * Configura o JDK 11.
        * Executa os testes Maven (`mvn test`) passando o navegador como parametro.

## Parte 3: Integrar e Executar no GitHub Actions

1.  **Enviar Código para o GitHub:**
    * Envie seu código para o repositório GitHub.

2.  **Verificar o GitHub Actions:**
    * Vá para a guia "Actions" no seu repositório GitHub.
    * Verifique a execução do workflow.
    * Se tudo estiver configurado corretamente, os testes Selenium serão executados automaticamente nos dois navegadores.

## Melhorias e Considerações Adicionais

* **Testes Mais Complexos:** Adicione mais testes para cobrir diferentes funcionalidades do site.
* **Relatórios de Teste:** Configure a geração e publicação de relatórios de teste.
* **Testes em Ambientes de Nuvem:** Explore a possibilidade de executar os testes em ambientes de nuvem, para melhorar a escalabilidade e reduzir custos.
