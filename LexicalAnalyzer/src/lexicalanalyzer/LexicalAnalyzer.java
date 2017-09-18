package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lexicalanalyzer.enums.ReservedWordEnum;
import lexicalanalyzer.enums.SaidaEnum;

/**
 *
 * @author adrisson
 */
public class LexicalAnalyzer {

    public final static List<String> VALID_STARTS = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "_");
    public final static List<String> NUMEROS = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    public final static String COMENTARIO = "//";

    public static void main(String[] args) {
        Map<Integer, String> mapArchive = new HashMap<Integer, String>();
        List<Saida> saidas = new ArrayList<>();
        List<Simbolo> simbolsTable = new ArrayList<>();
        List<Integer> linesError = new ArrayList<>();

//        simbolsTable.add(new Simbolo(1, "teste"));
//        simbolsTable.add(new Simbolo(2, "99"));
//        List<Integer> teste = Arrays.asList(1, 2, 3);
        printArchiveContent(mapArchive);

        makeThingsHappen(mapArchive, saidas, simbolsTable, linesError);

        System.out.println("Leituras:\n");
        for (Saida saida : saidas) {
            System.out.println(saida.toString());
        }

        if (simbolsTable.size() > 0) {
            printSimbolsTable(simbolsTable);
        }

        if (linesError.size() > 0) {
            printErrors(linesError);
        }
    }

    private static void makeThingsHappen(Map<Integer, String> mapArchive, List<Saida> saidas, List<Simbolo> simbolsTable, List<Integer> linesError) {
        for (Map.Entry<Integer, String> entry : mapArchive.entrySet()) {

            Integer key = entry.getKey();
            String value = entry.getValue();
            boolean isSimbol = false;

            //SE NÃO HOUVER NADA NA LINHA ADICIONA AOS ERROS
            if (value.trim().length() == 0) {

                linesError.add(key);
                continue;

            }

            String firstLetter = Character.toString(value.charAt(0));
            //SE O INÍCIO FOR VÁLIDO
            if (VALID_STARTS.contains(firstLetter.toUpperCase())) {

                //SE FOR MENOR QUE 3, NÃO HÁ RISCO DE SER UMA PALAVRA RESERVADA
                if (value.length() < 3) {
                    Integer idSimbol = findSimbolId(simbolsTable, value);
                    if (idSimbol != null) {
                        saidas.add(new Saida(key, SaidaEnum.IDENTIFICADOR, idSimbol));
                    } else {
                        saidas.add(new Saida(key, SaidaEnum.IDENTIFICADOR, simbolsTable.size() + 1));
                    }
                    isSimbol = true;

                } else {

                    String stringAvaliada = new String();
                    SaidaEnum saida = null;
                    boolean isIdentificador = true;
                    for (int i = 0; i < value.length(); i++) {
                        String charAt = Character.toString(value.charAt(i));

                        if (VALID_STARTS.contains(charAt.toUpperCase()) || NUMEROS.contains(charAt)) {

                            stringAvaliada += charAt;
                            for (ReservedWordEnum word : ReservedWordEnum.values()) {
                                if (word.getWord().equalsIgnoreCase(stringAvaliada)) {

                                    saida = SaidaEnum.valueOf(word.getWord().toUpperCase());
                                    isIdentificador = false;
                                }
                            }

                        } else {

                            linesError.add(key);
                            saida = null;
                            isIdentificador = false;
                            break;

                        }

                    }

                    if (saida != null) {
                        saidas.add(new Saida(key, saida));
                    }

                    if (isIdentificador) {
                        Integer idSimbol = findSimbolId(simbolsTable, value);
                        if (idSimbol != null) {
                            saidas.add(new Saida(key, SaidaEnum.IDENTIFICADOR, idSimbol));
                        } else {
                            saidas.add(new Saida(key, SaidaEnum.IDENTIFICADOR, simbolsTable.size() + 1));
                        }
                        isSimbol = true;
                    }
                }

            } else {

                //SE O INÍCIO FOR INVÁLIDO, PRIMEIRAMENTE VERIFICA SE NÃO É COMENTÁRIO, SENÃO ADICIONA AOS ERROS
                if (firstLetter.equals("/") && value.trim().length() > 1) {

                    String twoLettersStart = Character.toString(value.charAt(0)) + Character.toString(value.charAt(1));
                    if (twoLettersStart.equals(COMENTARIO)) {

                        saidas.add(new Saida(key, SaidaEnum.COMENTARIO));
                        continue;

                    }
                } else if (firstLetter.equals("\"")) {

                    String lastLetter = Character.toString(value.charAt(value.length() - 1));
                    if (lastLetter.equals("\"")) {

                        saidas.add(new Saida(key, SaidaEnum.STRING));
                        continue;

                    }

                } else if (NUMEROS.contains(firstLetter)) {

                    int count = value.length() - value.replace(".", "").length();
                    String lastLetter = Character.toString(value.charAt(value.length() - 1));

                    if (count <= 1 && !lastLetter.equals(".")) {
                        if (count == 0) {
                            saidas.add(new Saida(key, SaidaEnum.NUMERO_INTEIRO, simbolsTable.size() + 1));
                        } else {
                            saidas.add(new Saida(key, SaidaEnum.NUMERO_REAL, simbolsTable.size() + 1));
                        }

                        addSimbol(simbolsTable, value);
                        continue;
                    }

                }

                linesError.add(key);
                continue;
            }

            if (isSimbol) {
                addSimbol(simbolsTable, value);
            }

        }

    }

    private static void addSimbol(List<Simbolo> simbolsTable, String value) {
        boolean deveInserir = true;
        for (Simbolo simbolo : simbolsTable) {
            if (simbolo.getDescricao().equals(value)) {
                deveInserir = false;
                break;
            }
        }

        if (deveInserir) {
            simbolsTable.add(new Simbolo(simbolsTable.size() + 1, value));
        }
    }

    private static Integer findSimbolId(List<Simbolo> simbolsTable, String value) {
        Integer simbolId = null;

        for (Simbolo simbolo : simbolsTable) {
            if (simbolo.getDescricao().equals(value)) {
                simbolId = simbolo.getId();
            }
        }

        return simbolId;
    }

    private static void printArchiveContent(Map<Integer, String> mapArchive) {
        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome do arquivo texto com seu caminho absoluto:\n");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            Integer numLinha = 1;
            while (linha != null) {
                mapArchive.put(numLinha, linha);

                System.out.printf("%s\n", linha);

                linha = lerArq.readLine();

                numLinha++;
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        System.out.println();
    }

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
