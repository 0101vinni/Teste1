package testePratico1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        // 3.1 Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 Remover o funcionário “João” da lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 Imprimir todos os funcionários
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");

        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                f.getNome(),
                f.getDataNascimento().format(formatter),
                decimalFormatter.format(f.getSalario()),
                f.getFuncao()
            );
        }

        // 3.4 Aumentar salário em 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        // 3.5 Agrupar os funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir os funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(formatter),
                    decimalFormatter.format(f.getSalario()),
                    f.getFuncao()
                );
            }
        }

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionários que fazem aniversário em Outubro e Dezembro:");
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12) {
                System.out.printf("Nome: %s, Data de Nascimento: %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(formatter)
                );
            }
        }

        // 3.9 Imprimir o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(f -> Period.between(f.getDataNascimento(), LocalDate.now()).getYears()));
        System.out.printf("Funcionário mais velho: Nome: %s, Idade: %d%n",
            maisVelho.getNome(),
            Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears()
        );

        // 3.10 Imprimir a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários ordenados por nome:");
        for (Funcionario f : funcionariosOrdenados) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                f.getNome(),
                f.getDataNascimento().format(formatter),
                decimalFormatter.format(f.getSalario()),
                f.getFuncao()
            );
        }

        // 3.11 Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salários: %s%n", decimalFormatter.format(totalSalarios));

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("Nome: %s, Salário: %s, Salários mínimos: %s%n",
                f.getNome(),
                decimalFormatter.format(f.getSalario()),
                decimalFormatter.format(salariosMinimos)
            );
        }
    }
}
