package conta.sistema.dominio.modelo;

public class Erro {
    public static void obrigatorio(String nome){
        throw new NegocioException(nome + "é obrigatório");
    }

    public static void inexistente(String nome){
        throw new NegocioException(nome + "é inexistente");
    }
    public static void saldoInsuficiente(String nome){
        throw new NegocioException("Saldo insuficiente");
    }

    public static void mesmaConta(String nome){
        throw new NegocioException("Conta debito e crédito devem ser distintas");
    }
}
