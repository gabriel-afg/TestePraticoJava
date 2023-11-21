package Entities;

import java.util.HashMap;
import java.util.Map;

public class Vendas {

    public Vendas(){
    }
    private static final Map<String, Map<String, Double>> vendasVendedores = new HashMap<>();

    public static void registrarVendas(String nomeVendedor, String mesAno, double valorVendido) {
        vendasVendedores.computeIfAbsent(nomeVendedor, k -> new HashMap<>())
                .put(mesAno, valorVendido);
    }

    public static double calcularBeneficioVendedor(Funcionarios funcionario, int mes, int ano) {
        registrarVendas("Ana Silva", "12/2021", 5200.0);
        registrarVendas("João Mendes", "12/2021", 3400.0);
        registrarVendas("Ana Silva", "01/2022", 4000.0);
        registrarVendas("João Mendes", "01/2022", 7700.0);
        registrarVendas("Ana Silva", "02/2022", 4200.0);
        registrarVendas("João Mendes", "02/2022", 5000.0);
        registrarVendas("Ana Silva", "03/2022", 5850.0);
        registrarVendas("João Mendes", "03/2022", 5900.0);
        registrarVendas("Ana Silva", "04/2022", 7000.0);
        registrarVendas("João Mendes", "04/2022", 6500.0);

        if (funcionario.getCargo().equalsIgnoreCase("vendedor")) {
            String mesAno = String.format("%02d/%d", mes, ano);
            double valorVendido = vendasVendedores.getOrDefault(funcionario.getNome(), new HashMap<>())
                    .getOrDefault(mesAno, 0.0);
            return 0.3 * valorVendido;
        }
        else{
            throw new IllegalArgumentException("Cargo desconhecido: " + funcionario.getCargo());
        }
    }
}
