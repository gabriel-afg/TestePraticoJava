package Entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Funcionarios {
    private String nome;
    private String dataContratacao;

    public Funcionarios() {
    }

    public Funcionarios(String nome, String dataContratacao) {
        this.nome = nome;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public abstract double calcularSalario(int mes, int ano);

    public abstract double calcularBeneficio(int mes, int ano, Vendas vendas);

    public int anosDeServico(int mes, int ano) {
        LocalDate dataContratacao = LocalDate.parse(this.dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataDesejada = LocalDate.of(ano, mes, 1);

        Period periodo = Period.between(dataContratacao, dataDesejada);

        // Verifica se a data desejada é anterior à data de contratação
        if (dataDesejada.isBefore(dataContratacao)) {
            return 0;
        }

        return periodo.getYears();
    }

    public double calcularTotalPago(int mes, int ano, Vendas vendas) {
        double salario = calcularSalario(mes, ano);
        double beneficio = calcularBeneficio(mes, ano, vendas);

        if (anosDeServico(mes, ano) == 0 && LocalDate.parse(this.dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getMonthValue() == mes && LocalDate.parse(this.dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear() == ano) {
            return salario;
        } else {
            return salario + beneficio;
        }
    }
}