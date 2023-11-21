import Entities.Funcionarios;
import Entities.Vendas;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Funcionarios> listaFuncionarios = criarListaFuncionarios();


        System.out.println("-------------------");
        //Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
        //pago (salário e benefício) a esses funcionários no mês.
        double totalPago = calcularTotalPago(listaFuncionarios, 4, 2022);
        System.out.println("Total Pago para todos os funcionários: R$ " + totalPago);

        System.out.println("-------------------");

        //Um método que receba uma lista de funcionários, mês e ano e retorne o total pago
        //somente em salários no mês.
        double totalSalarios = calcularTotalSalarios(listaFuncionarios,4, 2022);
        System.out.println("Total Pago em Salários para todos os funcionários: R$ " + totalSalarios);

        System.out.println("-------------------");

        //Um método que receba uma lista somente com os funcionários que recebem
        //benefícios, mês e ano e retorne o total pago em benefícios no mês.
        double totalBeneficios = calcularTotalPagoBeneficios(listaFuncionarios, 4, 2022);
        System.out.println("Total Pago em Benefícios para Secretários e Vendedores: R$ " + totalBeneficios);

        System.out.println("-------------------");

        // Um método que receba uma lista de funcionários, mês e ano e retorne o que
        //recebeu o valor mais alto no mês.
        Funcionarios funcionarioMaiorValor = encontrarFuncionarioMaiorValor(listaFuncionarios, 4, 2022);

        if (funcionarioMaiorValor != null) {
            System.out.println("Funcionário que recebeu o valor mais alto: " + funcionarioMaiorValor.getNome());
        } else {
            System.out.println("Nenhum funcionário encontrado para o mês e ano especificados.");
        }

        System.out.println("-------------------");

        //Um método que receba uma lista somente com os funcionários que recebem
        //benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
        //alto em benefícios no mês.
        String funcionarioMaiorBeneficio = encontrarFuncionarioMaiorBeneficio(listaFuncionarios, 4, 2022);
        System.out.println("Funcionário que recebeu o valor mais alto em Benefícios: " + funcionarioMaiorBeneficio);

        System.out.println("-------------------");

        // Um método que receba uma lista de vendedores, mês e ano e retorne o que mais
        //vendeu no mês.
        String vendedorMaisVendeu = encontrarVendedorMaisVendeu(listaFuncionarios, 4, 2022);

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
                            ", Salario: "+ funcionario.calcularSalario(4, 2022) +
                            ", Beneficio: " +  funcionario.calcularBeneficio(4, 2022) +
                            ", anos: " + funcionario.anosDeServico(4, 2022)
            );
        }
    }

    public static double calcularTotalPago(List<Funcionarios> funcionarios, int mes, int ano) {
        double totalPago = 0.0;

        for (Funcionarios funcionario : funcionarios) {
            totalPago += funcionario.calcularTotalPago(mes, ano);
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

    public static double calcularTotalPagoBeneficios(List<Funcionarios> funcionarios, int mes, int ano){
        double totalPagoBeneficio = 0.0;
        for (Funcionarios funcionario : funcionarios) {
            double beneficio = funcionario.calcularBeneficio(mes, ano);
            if (!funcionario.getCargo().equalsIgnoreCase("Gerente")) {
                totalPagoBeneficio += beneficio;
            }
        }

        return totalPagoBeneficio;
    }

    private static String encontrarFuncionarioMaiorBeneficio(List<Funcionarios> listaFuncionarios, int mes, int ano) {
        double maiorBeneficio = Double.MIN_VALUE;
        String funcionarioMaiorBeneficio = null;

        for (Funcionarios funcionario : listaFuncionarios) {
            double beneficio = funcionario.calcularBeneficio(mes, ano);
            if (beneficio > maiorBeneficio) {
                maiorBeneficio = beneficio;
                funcionarioMaiorBeneficio = funcionario.getNome();
            }
        }

        return funcionarioMaiorBeneficio;
    }

    public static Funcionarios encontrarFuncionarioMaiorValor(List<Funcionarios> funcionarios, int mes, int ano) {
        double maiorValor = Double.MIN_VALUE;
        Funcionarios funcionarioMaiorValor = null;

        for (Funcionarios funcionario : funcionarios) {
            double totalPago = funcionario.calcularTotalPago(mes, ano);
            if (totalPago > maiorValor) {
                maiorValor = totalPago;
                funcionarioMaiorValor = funcionario;
            }
        }

        return funcionarioMaiorValor;
    }

    public static String encontrarVendedorMaisVendeu(List<Funcionarios> vendedores, int mes, int ano) {
        double maiorValorVendido = Double.MIN_VALUE;
        String vendedorMaisVendeu = null;

        for (Funcionarios vendedor : vendedores) {
            if (vendedor.getCargo().equalsIgnoreCase("vendedor")) {
                double valorVendido = Vendas.calcularBeneficioVendedor(vendedor, mes, ano);
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
        listaFuncionarios.add(new Funcionarios("Jorge Carvalho", "Secretario", "01/01/2018"));
        listaFuncionarios.add(new Funcionarios("Maria Souza", "Secretario", "01/12/2015"));
        listaFuncionarios.add(new Funcionarios("Ana Silva", "Vendedor", "01/12/2021"));
        listaFuncionarios.add(new Funcionarios("João Mendes", "Vendedor", "01/12/2021"));
        listaFuncionarios.add(new Funcionarios("Juliana Alves", "Gerente", "01/07/2017"));
        listaFuncionarios.add(new Funcionarios("Bento Albino", "Gerente", "01/03/2014"));
        return listaFuncionarios;
    }
}