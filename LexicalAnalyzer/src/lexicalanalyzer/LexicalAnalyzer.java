package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author adrisson
 */
public class LexicalAnalyzer {

    public static void main(String[] args) {
        List<Simbolo> simbolsTable = new ArrayList<>();
        simbolsTable.add(new Simbolo(1, "teste"));
        simbolsTable.add(new Simbolo(2, "99"));
        
        List<Integer> teste = Arrays.asList(1, 2, 3);

        printSimbolsTable(simbolsTable);
        printErrors(teste);
    }

    /*public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome do arquivo texto com seu caminho absoluto:\n");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
// a variável "linha" recebe o valor "null" quando o processo
// de repetição atingir o final do arquivo texto
            while (linha != null) {
                System.out.printf("%s\n", linha);

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }*/
    
    private static void printSimbolsTable(List<Simbolo> simbolsTable) {
        System.out.println("\nTabela de Símbolos");
        
        for (Simbolo simbolo : simbolsTable) {
            System.out.println(simbolo.toString());
        }
    }
    
    private static void printErrors(List<Integer> linesError) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nO programa possui erros nas linhas: ");
        for (int i = 0; i < linesError.size(); i++) {
            sb.append(linesError.get(i));

            if (i + 2 < linesError.size()) {
                sb.append(", ");
            } else if (i + 1 < linesError.size()) {
                sb.append(" e ");
            }
        }

        System.out.println(sb.toString());
    }

}
