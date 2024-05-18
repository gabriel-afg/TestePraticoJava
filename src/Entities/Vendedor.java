package Entities;

public class Vendedor extends Funcionarios {

    public Vendedor() {
    }

    public Vendedor(String nome, String dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double calcularSalario(int mes, int ano) {
        int anosDeServico = anosDeServico(mes, ano);
        return 12000.0 + (1800.0 * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int mes, int ano, Vendas vendas) {
        return vendas.calcularBeneficioVendedor(this, mes, ano);
    }
}
