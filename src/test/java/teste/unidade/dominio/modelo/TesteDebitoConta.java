package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Dédito de Conta")
public class TesteDebitoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Jean");
    }

    @Test
    @DisplayName("valor débito nulo como obrigatório ")
    void teste1(){
        try {
            contaValida.debitar(null);
            fail("valor débito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor débito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor débito negativo como obrigatório ")
    void teste2(){
        try {
            contaValida.debitar(new BigDecimal(-100));
            fail("valor débito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor débito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor débito zero como obrigatório ")
    void teste3(){
        try {
            contaValida.debitar(new BigDecimal(0));
            fail("valor débito é obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "valor débito é obrigatório");
        }
    }

    @Test
    @DisplayName("valor debito acima do saldo ")
    void teste4(){
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("valor debito acima do saldo");

        } catch (NegocioException e){
            assertEquals(e.getMessage(), "Saldo insuficiente");
        }
    }
    @Test
    @DisplayName("deve zera a conta após realizar um deposito ")
    void teste5(){
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "saldo deve zerar");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "Deve debitar com sucesso" + e.getMessage());
        }
    }
    @Test
    @DisplayName("deve debitar um valor menor que o saldo ")
    void teste6(){
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal =  cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "saldo deve zerar");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "Deve debitar com sucesso" + e.getMessage());
        }
    }
}
