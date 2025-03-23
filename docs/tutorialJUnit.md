# Tutorial de Teste JUnit 5 para `Pessoa` e `IMC`

Este tutorial detalhado demonstra como testar as classes `Pessoa` e `IMC` usando JUnit 5.

## Pré-requisitos

* Java Development Kit (JDK) instalado
* Ambiente de desenvolvimento integrado (IDE) como IntelliJ IDEA, Eclipse ou Visual Studio Code com suporte a Java
* JUnit 5 adicionado ao seu projeto (geralmente gerenciado por Maven ou Gradle)

## Passo 1: Configuração do Projeto

1.  **Crie um Projeto Java:**
    * Em sua IDE, crie um novo projeto Java.
2.  **Adicione JUnit 5:**
    * Se você estiver usando Maven, adicione as seguintes dependências ao seu `pom.xml`:

    ```xml
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ```

    * Se estiver usando Gradle, adicione as seguintes dependências ao seu `build.gradle`:

    ```gradle
    dependencies {
        testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
    }

    test {
        useJUnitPlatform()
    }
    ```

3.  **Crie as Classes `Pessoa.java` e `IMC.java`:**
    * Crie os arquivos `Pessoa.java` e `IMC.java` no diretório `src/main/java/com/example/` e copie o seguinte código:

    `Pessoa.java`:

    ```java
    package com.example;

    public class Pessoa {

        private float altura;
        private float peso;

        public Pessoa(float altura, float peso) {
            this.altura = altura;
            this.peso = peso;
        }

        public float getPeso() {
            return peso;
        }

        public void setPeso(float peso) {
            this.peso = peso;
        }

        public float getAltura() {
            return altura;
        }

        public void setAltura(float altura) {
            this.altura = altura;
        }
    }
    ```

    `IMC.java`:

    ```java
    package com.example;

    public class IMC {
        public float calculaIMC(Pessoa pessoa) throws Exception {
            return pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
        }
    }
    ```

## Passo 2: Criando a Classe de Teste `IMCTest.java`

1.  **Crie a Classe de Teste:**
    * No diretório `src/test/java/com/example/`, crie o arquivo `IMCTest.java` e copie o seguinte código:

    ```java
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import com.example.IMC;
    import com.example.Pessoa;

    import static org.junit.jupiter.api.Assertions.*;

    public class IMCTest {

        private Pessoa pessoa, pessoaNula;
        private IMC imc;

        @BeforeEach
        public void init() {
            pessoa = new Pessoa(1.80f, 95f);
            pessoaNula = null;
            imc = new IMC();
        }

        @Test
        public void testVerificaIMCValido() throws Exception {
            assertEquals(29, Math.round(imc.calculaIMC(pessoa)));
        }

        @Test
        public void testVerificaIMCInvalido() throws Exception {
            assertFalse(10 == Math.round(imc.calculaIMC(pessoa)));
        }

        @Test
        public void testPessoaNull() {
            assertThrows(Exception.class, () -> imc.calculaIMC(pessoaNula));
        }
    }
    ```

## Passo 3: Entendendo o Código de Teste

* **`import` Statements:**
    * Importamos as classes JUnit 5 necessárias.
    * Importamos as classes `IMC` e `Pessoa` que vamos testar.
* **Declaração de Variáveis:**
    * `pessoa` e `pessoaNula` são instâncias da classe `Pessoa`, e `imc` é uma instância da classe `IMC`.
* **`@BeforeEach`:**
    * O método `init()` é executado antes de cada método de teste.
* **`@Test`:**
    * Métodos anotados com `@Test` são métodos de teste.
* **`testVerificaIMCValido()`:**
    * Verifica se o cálculo do IMC para uma pessoa válida está correto.
* **`testVerificaIMCInvalido()`:**
    * Verifica se o valor arredondado do IMC calculado não é igual a 10.
* **`testPessoaNull()`:**
    * Verifica se uma exceção é lançada quando o método `calculaIMC()` é chamado com uma pessoa nula.

## Passo 4: Executando os Testes

1.  **Executar Testes na IDE:**
    * Clique com o botão direito no arquivo `IMCTest.java` e selecione "Run" ou "Run Tests".
2.  **Executar Testes com Maven:**
    * `mvn test`
3.  **Executar Testes com Gradle:**
    * `gradle test`

## Passo 5: Analisando os Resultados

* A saída do teste mostrará se os testes passaram ou falharam.
* Se algum teste falhar, detalhes sobre a falha serão exibidos.

## Melhorias e Considerações Adicionais

* **Testes de Borda:** Adicione testes para casos de borda.
* **Testes Parametrizados:** Use testes parametrizados para executar o mesmo teste com diferentes dados.
* **Cobertura de Código:** Use ferramentas de cobertura de código para garantir uma boa cobertura.
* **Exceções Especificas:** Use exceções mais especificas no `assertThrows`.