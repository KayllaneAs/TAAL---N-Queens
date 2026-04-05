package view;

import java.util.Scanner;
import model.classes.BacktrackingSolver;
import model.classes.BranchAndBoundSolver;
import model.classes.DynamicProgrammingSolver;
import model.classes.GreedySolver;

public class NQueens {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nPara iniciar digite um valor N (ou 0 para sair): ");
            int N = sc.nextInt();

            if (N <= 0) break;

            System.out.println("\nEscolha o algoritmo:");
            System.out.println("1 - Estratégia Gulosa (Greedy)");
            System.out.println("2 - Programação Dinâmica (Dynamic Programming)");
            System.out.println("3 - Voltando Atrás (Backtracking)");
            System.out.println("4 - Ramificar e Limitar (Branch and Bound)");
            System.out.println("5 - Comparar TODOS");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    new GreedySolver(N).run("Estratégia Gulosa");
                    break;

                case 2:
                    new DynamicProgrammingSolver(N).run("Programação Dinâmica");
                    break;

                case 3:
                    new BacktrackingSolver(N).run("Backtracking");
                    break;

                case 4:
                    new BranchAndBoundSolver(N).run("Branch and Bound");
                    break;

                case 5:
                    System.out.println("\n=== COMPARATIVO GERAL ===");
                    new GreedySolver(N).run("Estratégia Gulosa");
                    System.out.println("\n-----------------------------");
                    new DynamicProgrammingSolver(N).run("Programação Dinâmica");
                    System.out.println("\n-----------------------------");
                    new BacktrackingSolver(N).run("Backtracking");
                    System.out.println("\n-----------------------------");
                    new BranchAndBoundSolver(N).run("Branch and Bound");
                    System.out.println("\n=============================");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}