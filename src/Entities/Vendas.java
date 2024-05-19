package Entities;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Vendas {
    private final Map<String, Map<LocalDate, Double>> vendasVendedores = new HashMap<>();

    public void registrarVendas(String nomeVendedor, YearMonth data, double valorVendido) {
        vendasVendedores.computeIfAbsent(nomeVendedor, k -> new HashMap<>())
                .put(data.atDay(1), valorVendido);
    }

    public double calcularBeneficioVendedor(Funcionarios funcionario, int mes, int ano) {
        if (funcionario instanceof Vendedor) {
            LocalDate data = LocalDate.of(ano, mes, 1);
            double valorVendido = vendasVendedores.getOrDefault(funcionario.getNome(), new HashMap<>())
                    .getOrDefault(data, 0.0);
            return 0.3 * valorVendido;
        }
        return 0.0; // Retorne 0.0 se o funcionário não for um vendedor
    }

    public boolean temDadosParaMesAno(int mes, int ano) {
        LocalDate data = LocalDate.of(ano, mes, 1);
        return vendasVendedores.values().stream().anyMatch(vendas -> vendas.containsKey(data));
    }
}
