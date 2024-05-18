package Entities;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DadosVendas {
    public static Vendas inicializarVendas() {
        Vendas vendas = new Vendas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        vendas.registrarVendas("Ana Silva", YearMonth.parse("12/2021", formatter), 5200.0);
        vendas.registrarVendas("João Mendes", YearMonth.parse("12/2021", formatter), 3400.0);
        vendas.registrarVendas("Ana Silva", YearMonth.parse("01/2022", formatter), 4000.0);
        vendas.registrarVendas("João Mendes", YearMonth.parse("01/2022", formatter), 7700.0);
        vendas.registrarVendas("Ana Silva", YearMonth.parse("02/2022", formatter), 4200.0);
        vendas.registrarVendas("João Mendes", YearMonth.parse("02/2022", formatter), 5000.0);
        vendas.registrarVendas("Ana Silva", YearMonth.parse("03/2022", formatter), 5850.0);
        vendas.registrarVendas("João Mendes", YearMonth.parse("03/2022", formatter), 5900.0);
        vendas.registrarVendas("Ana Silva", YearMonth.parse("04/2022", formatter), 7000.0);
        vendas.registrarVendas("João Mendes", YearMonth.parse("04/2022", formatter), 6500.0);

        return vendas;
    }
}