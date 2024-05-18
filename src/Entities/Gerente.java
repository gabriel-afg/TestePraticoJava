package Entities;

public class Gerente extends Funcionarios{

    public Gerente() {
    }

    public Gerente(String nome, String dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double calcularSalario(int mes, int ano) {
        int anosDeServico = anosDeServico(mes, ano);
        return 20000.0 + (3000.0 * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int mes, int ano, Vendas vendas) {
        return 0.0; // Gerentes não têm benefícios
    }
}
