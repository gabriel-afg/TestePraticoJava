package Entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Funcionarios {
    private String nome;
    private String cargo;
    private String dataContratacao;

    public Funcionarios(){
    }

    public Funcionarios(String nome, String cargo, String dataContratacao) {
        this.nome = nome;
        this.cargo = cargo;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

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

    public double calcularSalario(int mes, int ano) {
        int anosDeServico = anosDeServico(mes, ano);

        switch (cargo.toLowerCase()) {
            case "secretario":
                return 7000.0 + (1000.0 * anosDeServico);
            case "vendedor":
                return 12000.0 + (1800.0 * anosDeServico);
            case "gerente":
                return 20000.0 + (3000.0 * anosDeServico);
            default:
                throw new IllegalArgumentException("Cargo desconhecido: " + cargo);
        }
    }

    public double calcularBeneficio(int mes, int ano) {
        return switch (cargo.toLowerCase()) {
            case "secretario" -> 0.2 * calcularSalario(mes, ano);
            case "vendedor" -> Vendas.calcularBeneficioVendedor(this, mes, ano);
            case "gerente" -> 0.0; // Gerentes não têm benefícios
            default -> throw new IllegalArgumentException("Cargo desconhecido: " + cargo);
        };
    }

    public double calcularTotalPago(int mes, int ano) {
        double salario = calcularSalario(mes, ano);
        double beneficio = calcularBeneficio(mes, ano);

        if (anosDeServico(mes, ano) == 0 && LocalDate.parse(this.dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getMonthValue() == mes && LocalDate.parse(this.dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear() == ano) {
            return salario;
        } else {
            return salario+beneficio;
        }
    }
}
