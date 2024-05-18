package Entities;

public class Secretario extends Funcionarios {
    public Secretario() {
    }

    public Secretario(String nome, String dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double calcularSalario(int mes, int ano) {
        int anosDeServico = anosDeServico(mes, ano);
        return 7000.0 + (1000.0 * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int mes, int ano, Vendas vendas) {
        return 0.2 * calcularSalario(mes, ano);
    }
}
