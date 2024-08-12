package Tree.tests.ArrayList;

import java.text.DecimalFormat;
import java.util.*;

import static Tree.tests.ArrayList.SortingMethodWithArrayList.bubbleSort;

public class RuntimeTestWithArrayList {
    private static final Scanner scanner = new Scanner(System.in);

    private static final int ONE_BILLION = 1_000_000_000;
    private static final int ONE_HUNDRED_MILLION = 100_000_000;
    private static final int TEN_MILLION = 10_000_000;
    private static final int ONE_MILLION = 1_000_000;
    private static final int TWO_HUNDRED_THOUSAND = 200_000;
    private static final int ONE_HUNDRED_THOUSAND = 100_000;
    private static final int TEN_THOUSAND = 10_000;
    private static final int FIVE_THOUSAND = 5_000;
    private static final int ONE_THOUSAND = 1_000;
    private static final int FIVE_HUNDRED = 500;
    private static final int ONE_HUNDRED = 100;
    private static final int FIFTY = 50;
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final int TWO = 2;
    private static final int ONE = 1;

    public static void main(String[] args) {
        test_vetor_10_aleatorio(ONE_MILLION);
    }

    public enum SORT_ALGORITHM {
        BUBBLE_SORT,
        // ... outros algoritmos
    }

    public static void test_vetor_10_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        List<Integer> originalList = new ArrayList<>();
        Map<SORT_ALGORITHM, ArrayList<Integer>> listCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            originalList = generateRandomArrayList(10, 0, 10);
            listCopies = generateArrayListCopies(originalList);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                ArrayList<Integer> listCopy = listCopies.get(algorithm);
                long startTime = nanoTime();
                measureExecutionTime(algorithm, listCopy);
                long duration = nanoTime() - startTime;
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration;
        }
        long totalTimeElapsed = nanoTime() - totalTime;

        // Validação da lista original
        if (!isValidList(originalList)) System.err.println("Erro: A lista original não é válida.");

        System.out.println("Lista Original: " + originalList);

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todas as listas? 1 - Sim, 0 - Não : ");
        if (scanner.hasNextInt()) {
            boolean seeAllLists = scanner.nextInt() == 1;
            scanner.nextLine();

            if (seeAllLists && listCopies != null) {
                listCopies = generateArrayListCopies(originalList);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, listCopies.get(algorithm));
                printLists(listCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    /**
     * Gera uma ArrayList de números inteiros aleatórios em um intervalo especificado.
     *
     * @param qnt A quantidade de números inteiros a serem gerados.
     * @param min O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max O valor máximo (inclusive) que um número gerado pode ter.
     * @return Uma ArrayList de números inteiros aleatórios com a quantidade especificada.
     */
    public static ArrayList<Integer> generateRandomArrayList(int qnt, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < qnt; i++)
            list.add(random.nextInt(max - min + 1) + min);
        return list;
    }

    /**
     * Mede o tempo de execução de um algoritmo de ordenação específico em uma ArrayList.
     *
     * @param algorithm o algoritmo de ordenação a ser executado.
     * @param list      a ArrayList de inteiros sobre a qual o algoritmo de ordenação será executado.
     */
    private static void measureExecutionTime(SORT_ALGORITHM algorithm, ArrayList<Integer> list) {
        long startTime = nanoTime();
        switch (algorithm) {
            case BUBBLE_SORT -> bubbleSort(list);
            // ... outros algoritmos para ArrayList
        }
        nanoTime();
    }

    /**
     * Gera cópias de uma ArrayList original para cada algoritmo de ordenação disponível.
     *
     * @param originalList a ArrayList original de inteiros que será copiada.
     * @return um mapa onde as chaves são os algoritmos de ordenação e os valores são cópias da ArrayList original.
     */
    private static Map<SORT_ALGORITHM, ArrayList<Integer>> generateArrayListCopies(List<Integer> originalList) {
        Map<SORT_ALGORITHM, ArrayList<Integer>> listCopies = new EnumMap<>(SORT_ALGORITHM.class);
        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            listCopies.put(algorithm, new ArrayList<>(originalList));
        }
        return listCopies;
    }

    /**
     * Imprime os conteúdos de um mapa de ArrayLists inteiras.
     *
     * @param lists um mapa contendo pares chave-valor, onde a chave é um enum
     *              identificando a ArrayList e o valor é a ArrayList de inteiros
     *              a ser impressa.
     */
    public static void printLists(Map<SORT_ALGORITHM, ArrayList<Integer>> lists) {
        int maxKeyLength = 0;
        for (SORT_ALGORITHM key : lists.keySet()) {
            if (key.name().length() > maxKeyLength)
                maxKeyLength = key.name().length();
        }

        for (Map.Entry<SORT_ALGORITHM, ArrayList<Integer>> entry : lists.entrySet()) {
            String formattedKey = String.format("%-" + maxKeyLength + "s", entry.getKey().name());
            System.out.println(formattedKey + ": " + entry.getValue());
        }
    }

    /**
     * Verifica se uma ArrayList de inteiros está ordenada em ordem crescente.
     *
     * @param list A ArrayList de inteiros a ser verificada.
     * @return {@code true} se a ArrayList estiver ordenada em ordem crescente,
     * {@code false} caso contrário.
     */
    private static boolean isSorted(ArrayList<Integer> list) {
        return isIncreasing(list);
    }

    /**
     * Verifica se a lista original é válida.
     *
     * <p>Este metodo deve ser implementado com a lógica específica para validar a lista original.
     * Por exemplo, pode-se verificar se a lista não é nula, se contém os valores esperados, etc.</p>
     *
     * @param list A lista original a ser validada.
     * @return {@code true} se a lista original for válida, {@code false} caso contrário.
     */
    private static boolean isValidList(List<Integer> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * Verifica se a ArrayList de inteiros está ordenada em ordem crescente.
     *
     * @param list a ArrayList de inteiros a ser verificada.
     * @return {@code true} se a ArrayList estiver ordenada em ordem crescente;
     * {@code false} caso contrário.
     */
    public static boolean isIncreasing(ArrayList<Integer> list) {
        if (list == null || list.isEmpty() || list.size() == 1)
            return true; // Listas vazias ou com um elemento são consideradas ordenadas

        Iterator<Integer> iterator = list.iterator();
        Integer previous = iterator.next(); // Obtém o primeiro elemento

        while (iterator.hasNext()) {
            Integer current = iterator.next();
            if (previous > current)
                return false; // Se o elemento atual for menor que o anterior, não está em ordem crescente
            previous = current;
        }

        return true; // Se percorreu toda a lista sem encontrar elementos fora de ordem, está em ordem crescente
    }

    /**
     * Verifica se a ArrayList de inteiros está ordenada em ordem decrescente.
     *
     * @param list a ArrayList de inteiros a ser verificada.
     * @return {@code true} se a ArrayList estiver ordenada em ordem decrescente;
     * {@code false} caso contrário.
     */
    public static boolean isDecreasing(ArrayList<Integer> list) {
        if (list == null || list.isEmpty() || list.size() == 1)
            return true; // Listas vazias ou com um elemento são consideradas ordenadas

        Iterator<Integer> iterator = list.iterator();
        Integer previous = iterator.next(); // Obtém o primeiro elemento

        while (iterator.hasNext()) {
            Integer current = iterator.next();
            if (previous < current)
                return false; // Se o elemento atual for maior que o anterior, não está em ordem decrescente
            previous = current;
        }

        return true; // Se percorreu toda a lista sem encontrar elementos fora de ordem, está em ordem decrescente
    }

    private static long nanoTime() {
        return System.nanoTime();
    }
}