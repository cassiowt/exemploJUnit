import com.example.Baskhara;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BaskharaTest {

    private Baskhara baskharaIguais, baskharaDeltaMenorZero, baskharaDuasRaizes;

    @BeforeEach
    public void init(){
        baskharaIguais = new Baskhara(1,2,1);
        baskharaDuasRaizes = new Baskhara(2,2,-1);
        baskharaDeltaMenorZero = new Baskhara(1,1,1);
    }

    @Test
    public void testeVerificaExisteDelta(){
         assertTrue( baskharaDuasRaizes.calculaDelta() >= 0);
    }

    @Test
    public void testeVerificaRaizesIguaisEmDelta(){
         assertTrue( baskharaIguais.calculaDelta() == 0);
    }

    @Test
    public void testeVerificaNaoExisteDelta(){
         assertTrue( baskharaDeltaMenorZero.calculaDelta() < 0);
    }

    @Test
    public void testRaizesIguais(){
        baskharaIguais.calculaDelta();
        float x1 = baskharaIguais.calculaRaizUm();
        float x2 = baskharaIguais.calculaRaizDois();
        assertTrue(x2 == x1);
    }
 @Test
    public void testRaizesDiferentes(){
        baskharaDuasRaizes.calculaDelta();
        float x1 = baskharaDuasRaizes.calculaRaizUm();
        float x2 = baskharaDuasRaizes.calculaRaizDois();
        assertTrue(x2 != x1);
    }


}
