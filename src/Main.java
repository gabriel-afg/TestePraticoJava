import Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Funcionarios> listaFuncionarios = criarListaFuncionarios();
        Vendas vendas = DadosVendas.inicializarVendas();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o mês (como um número de 1 a 12):");
        int mes = scanner.nextInt();

        System.out.println("Digite o ano (como um número de 4 dígitos):");
        int ano = scanner.nextInt();

        if (!vendas.temDadosParaMesAno(mes, ano)) {
            System.out.println("Não existem dados para o mês e ano fornecidos.");
            return;
        }

        System.out.println("-------------------");
        //Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
        //pago (salário e benefício) a esses funcionários no mês.
        double totalPago = calcularTotalPago(listaFuncionarios, mes, ano, vendas);
        System.out.println("Total Pago para todos os funcionários: R$ " + totalPago);

        System.out.println("-------------------");

        //Um método que receba uma lista de funcionários, mês e ano e retorne o total pago
        //somente em salários no mês.
        double totalSalarios = calcularTotalSalarios(listaFuncionarios,mes, ano);
        System.out.println("Total Pago em Salários para todos os funcionários: R$ " + totalSalarios);

        System.out.println("-------------------");

        //Um método que receba uma lista somente com os funcionários que recebem
        //benefícios, mês e ano e retorne o total pago em benefícios no mês.
        double totalBeneficios = calcularTotalPagoBeneficios(listaFuncionarios, mes, ano, vendas);
        System.out.println("Total Pago em Benefícios para Secretários e Vendedores: R$ " + totalBeneficios);

        System.out.println("-------------------");

        // Um método que receba uma lista de funcionários, mês e ano e retorne o que
        //recebeu o valor mais alto no mês.
        Funcionarios funcionarioMaiorValor = encontrarFuncionarioMaiorValor(listaFuncionarios, mes, ano, vendas);

        if (funcionarioMaiorValor != null) {
            System.out.println("Funcionário que recebeu o valor mais alto: " + funcionarioMaiorValor.getNome());
        } else {
            System.out.println("Nenhum funcionário encontrado para o mês e ano especificados.");
        }

        System.out.println("-------------------");

        //Um método que receba uma lista somente com os funcionários que recebem
        //benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
        //alto em benefícios no mês.
        String funcionarioMaiorBeneficio = encontrarFuncionarioMaiorBeneficio(listaFuncionarios, mes, ano, vendas);
        System.out.println("Funcionário que recebeu o valor mais alto em Benefícios: " + funcionarioMaiorBeneficio);

        System.out.println("-------------------");

        // Um método que receba uma lista de vendedores, mês e ano e retorne o que mais
        //vendeu no mês.
        String vendedorMaisVendeu = encontrarVendedorMaisVendeu(listaFuncionarios, mes, ano, vendas);

        if (vendedorMaisVendeu != null) {
            System.out.println("Vendedor que mais vendeu: " + vendedorMaisVendeu);
        } else {
            System.out.println("Nenhum vendedor encontrado para o mês e ano especificados.");
        }

        System.out.println("-------------------");

        //Teste para verificar salario, beneficios e anos de serviço dos funcionarios
        for (Funcionarios funcionario : listaFuncionarios) {
            System.out.println(
                    funcionario.getNome() +
                            ", Salario: "+ funcionario.calcularSalario(mes, ano) +
                            ", Beneficio: " +  funcionario.calcularBeneficio(mes, ano, vendas) +
                            ", anos: " + funcionario.anosDeServico(mes, ano)
            );
        }
    }

    public static Funcionarios encontrarFuncionarioMaiorValor(List<Funcionarios> funcionarios, int mes, int ano, Vendas vendas) {
        double maiorValor = Double.MIN_VALUE;
        Funcionarios funcionarioMaiorValor = null;

        for (Funcionarios funcionario : funcionarios) {
            double totalPago = funcionario.calcularTotalPago(mes, ano, vendas);
            if (totalPago > maiorValor) {
                maiorValor = totalPago;
                funcionarioMaiorValor = funcionario;
            }
        }

        return funcionarioMaiorValor;
    }

    private static String encontrarFuncionarioMaiorBeneficio(List<Funcionarios> listaFuncionarios, int mes, int ano, Vendas vendas) {
        double maiorBeneficio = Double.MIN_VALUE;
        String funcionarioMaiorBeneficio = null;

        for (Funcionarios funcionario : listaFuncionarios) {
            if (!(funcionario instanceof Gerente)) {
                double beneficio = funcionario.calcularBeneficio(mes, ano, vendas);
                if (beneficio > maiorBeneficio) {
                    maiorBeneficio = beneficio;
                    funcionarioMaiorBeneficio = funcionario.getNome();
                }
            }
        }

        return funcionarioMaiorBeneficio;
    }

    public static double calcularTotalPago(List<Funcionarios> funcionarios, int mes, int ano, Vendas vendas) {
        double totalPago = 0.0;

        for (Funcionarios funcionario : funcionarios) {
            totalPago += funcionario.calcularTotalPago(mes, ano, vendas);
        }

        return totalPago;
    }

    public static double calcularTotalSalarios(List<Funcionarios> funcionarios, int mes, int ano) {
        double totalSalarios = 0.0;

        for (Funcionarios funcionario : funcionarios) {
            totalSalarios += funcionario.calcularSalario(mes, ano);
        }

        return totalSalarios;
    }

    public static double calcularTotalPagoBeneficios(List<Funcionarios> funcionarios, int mes, int ano, Vendas vendas){
        double totalPagoBeneficio = 0.0;
        for (Funcionarios funcionario : funcionarios) {
            if (!(funcionario instanceof Gerente)) {
                double beneficio = funcionario.calcularBeneficio(mes, ano, vendas);
                totalPagoBeneficio += beneficio;
            }
        }

        return totalPagoBeneficio;
    }

    public static String encontrarVendedorMaisVendeu(List<Funcionarios> vendedores, int mes, int ano, Vendas vendas) {
        double maiorValorVendido = Double.MIN_VALUE;
        String vendedorMaisVendeu = null;

        for (Funcionarios vendedor : vendedores) {
            if (vendedor instanceof Vendedor) {
                double valorVendido = vendas.calcularBeneficioVendedor(vendedor, mes, ano);
                if (valorVendido > maiorValorVendido) {
                    maiorValorVendido = valorVendido;
                    vendedorMaisVendeu = vendedor.getNome();
                }
            }
        }

        return vendedorMaisVendeu;
    }

    public static List<Funcionarios> criarListaFuncionarios(){
        List<Funcionarios> listaFuncionarios = new ArrayList<>();
        listaFuncionarios.add(new Secretario("Jorge Carvalho", "01/01/2018"));
        listaFuncionarios.add(new Secretario("Maria Souza", "01/12/2015"));
        listaFuncionarios.add(new Vendedor("Ana Silva", "01/12/2021"));
        listaFuncionarios.add(new Vendedor("João Mendes", "01/12/2021"));
        listaFuncionarios.add(new Gerente("Juliana Alves", "01/07/2017"));
        listaFuncionarios.add(new Gerente("Bento Albino", "01/03/2014"));
        return listaFuncionarios;
    }
}