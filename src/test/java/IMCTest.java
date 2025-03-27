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