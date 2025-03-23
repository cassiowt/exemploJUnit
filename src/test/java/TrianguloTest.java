package exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrianguloTest {

    private Triangulo triangulo;


    @BeforeEach
    public void init() {
    //    triangulo = new Triangulo(2,2,3);
    }

    @ParameterizedTest
    @EnumSource (TipoTriangulo.class)
    void testTipoTriangulos(TipoTriangulo tipoTriangulo) {
        assertNotNull(tipoTriangulo);
    }

    @DisplayName(value = " Exemplo ")
    @ParameterizedTest (name = "Lado A = {0}, Lado B = {1}, Lado C = {2} :: {3}")
    @CsvSource({"1,2,-2, INEXISTENTE",
               "1,1,2, ESCALENO",
               "2,2,2, EQUILATERO "})
    public void validaTriangulo(int um, int dois, int tres, TipoTriangulo tipoTriangulo){
        triangulo = new Triangulo(um,dois,tres);
        System.out.println(triangulo.validaTriangulo());
        assertTrue( triangulo.validaTriangulo().equals(tipoTriangulo));
    }

    @ParameterizedTest (name = "Lado A = {0}, Lado B = {1}, Lado C = {2} :: {3}")
    @CsvSource({"1,2,-2, INEXISTENTE",
                "1,3,2, ESCALENO",
                "2,2,2, EQUILATERO "})
    public void validaTrianguloNew(int um, int dois, int tres, TipoTriangulo tipoTriangulo){
        triangulo = new Triangulo(um,dois,tres);
        System.out.println(triangulo.identificaTipoTriangulo());
        assertTrue( triangulo.identificaTipoTriangulo().equals(tipoTriangulo));
    }


}
