package view;

import java.util.Scanner;
import model.classes.BacktrackingSolver;
import model.classes.BranchAndBoundSolver;

public class NQueens {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nPara iniciar digite um valor N ( ou 0 para sair): ");
            int N = sc.nextInt();

            if (N <= 0) break;

            System.out.println("\nEscolha o algoritmo:");
            System.out.println("1 - Backtracking");
            System.out.println("2 - Branch and Bound");
            System.out.println("3 - Comparar ambos");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    new BacktrackingSolver(N).run("Backtracking");
                    break;

                case 2:
                    new BranchAndBoundSolver(N).run("Branch and Bound");
                    break;

                case 3:
                    new BacktrackingSolver(N).run("Backtracking");
                    System.out.println("\n=============================");
                    new BranchAndBoundSolver(N).run("Branch and Bound");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}