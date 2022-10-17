package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Crédito de Conta")
public class TesteCreditoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Jean");
    }

    @Test
    @DisplayName("valor credito nulo como obrigatório ")
    void teste1(){
        try {
            contaValida.creditar(null);
            fail("valor crédito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor crédito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor credito negativo como obrigatório ")
    void teste2(){
        try {
            contaValida.creditar(new BigDecimal(-100));
            fail("valor crédito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor crédito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor credito zero como obrigatório ")
    void teste3(){
        try {
            contaValida.creditar(new BigDecimal(0));
            fail("valor crédito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor crédito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor credito acima de zero ")
    void teste4(){
        try {
            contaValida.creditar( BigDecimal.ONE);
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal);
        } catch (NegocioException e){
           fail("Deve creditar com sucesso" + e.getMessage());
        }
    }
}
