package Tree.tests.Vector;

import java.text.DecimalFormat;
import java.util.*;

import static Tree.tests.Vector.SortingMethodWithVector.bubbleSort;
import static java.lang.System.nanoTime;

public class RuntimeTestWithVector {
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
        test_vetor_10_aleatorio(ONE_HUNDRED);
    }

    public enum SORT_ALGORITHM {
        BUBBLE_SORT,
        // ... outros algoritmos
    }

    public static void test_vetor_10_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        List<Integer> originalList = new Vector<>();
        Map<SORT_ALGORITHM, Vector<Integer>> listCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            originalList = generateRandomVector(10, 0, 10);
            listCopies = generateVectorCopies(originalList);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                Vector<Integer> listCopy = listCopies.get(algorithm);
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
                listCopies = generateVectorCopies(originalList);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, listCopies.get(algorithm));
                printLists(listCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    /**
     * Gera um Vector de números inteiros aleatórios em um intervalo especificado.
     *
     * @param qnt A quantidade de números inteiros a serem gerados.
     * @param min O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max O valor máximo (inclusive) que um número gerado pode ter.
     * @return Um Vector de números inteiros aleatórios com a quantidade especificada.
     */
    public static Vector<Integer> generateRandomVector(int qnt, int min, int max) {
        Vector<Integer> list = new Vector<>();
        Random random = new Random();
        for (int i = 0; i < qnt; i++)
            list.add(random.nextInt(max - min + 1) + min);
        return list;
    }

    /**
     * Mede o tempo de execução de um algoritmo de ordenação específico em um Vector.
     *
     * @param algorithm o algoritmo de ordenação a ser executado.
     * @param list      o Vector de inteiros sobre o qual o algoritmo de ordenação será executado.
     */
    private static void measureExecutionTime(SORT_ALGORITHM algorithm, Vector<Integer> list) {
        long startTime = nanoTime();
        switch (algorithm) {
            case BUBBLE_SORT -> bubbleSort(list);
            // ... outros algoritmos para Vector
        }
        nanoTime();
    }

    /**
     * Gera cópias de um Vector original para cada algoritmo de ordenação disponível.
     *
     * @param originalList o Vector original de inteiros que será copiado.
     * @return um mapa onde as chaves são os algoritmos de ordenação e os valores são cópias do Vector original.
     */
    private static Map<SORT_ALGORITHM, Vector<Integer>> generateVectorCopies(List<Integer> originalList) {
        Map<SORT_ALGORITHM, Vector<Integer>> listCopies = new EnumMap<>(SORT_ALGORITHM.class);
        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            listCopies.put(algorithm, new Vector<>(originalList));
        }
        return listCopies;
    }

    /**
     * Imprime os conteúdos de um mapa de Vectors inteiros.
     *
     * @param lists um mapa contendo pares chave-valor, onde a chave é um enum
     *              identificando o Vector e o valor é o Vector de inteiros
     *              a ser impresso.
     */
    public static void printLists(Map<SORT_ALGORITHM, Vector<Integer>> lists) {
        int maxKeyLength = 0;
        for (SORT_ALGORITHM key : lists.keySet()) {
            if (key.name().length() > maxKeyLength)
                maxKeyLength = key.name().length();
        }

        for (Map.Entry<SORT_ALGORITHM, Vector<Integer>> entry : lists.entrySet()) {
            String formattedKey = String.format("%-" + maxKeyLength + "s", entry.getKey().name());
            System.out.println(formattedKey + ": " + entry.getValue());
        }
    }

    /**
     * Verifica se um Vector de inteiros está ordenado em ordem crescente.
     *
     * @param list O Vector de inteiros a ser verificado.
     * @return {@code true} se o Vector estiver ordenado em ordem crescente,
     * {@code false} caso contrário.
     */
    private static boolean isSorted(Vector<Integer> list) {
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
     * Verifica se o Vector de inteiros está ordenado em ordem crescente.
     *
     * @param list o Vector de inteiros a ser verificado.
     * @return {@code true} se o Vector estiver ordenado em ordem crescente;
     * {@code false} caso contrário.
     */
    public static boolean isIncreasing(Vector<Integer> list) {
        if (list == null || list.isEmpty() || list.size() == 1)
            return true; // Listas vazias ou com um elemento são consideradas ordenadas

        Enumeration<Integer> elements = list.elements();
        Integer previous = elements.nextElement(); // Obtém o primeiro elemento

        while (elements.hasMoreElements()) {
            Integer current = elements.nextElement();
            if (previous > current)
                return false; // Se o elemento atual for menor que o anterior, não está em ordem crescente
            previous = current;
        }

        return true; // Se percorreu toda a lista sem encontrar elementos fora de ordem, está em ordem crescente
    }

    /**
     * Verifica se o Vector de inteiros está ordenado em ordem decrescente.
     *
     * @param list o Vector de inteiros a ser verificado.
     * @return {@code true} se o Vector estiver ordenado em ordem decrescente;
     * {@code false} caso contrário.
     */
    public static boolean isDecreasing(Vector<Integer> list) {
        if (list == null || list.isEmpty() || list.size() == 1)
            return true; // Listas vazias ou com um elemento são consideradas ordenadas

        Enumeration<Integer> elements = list.elements();
        Integer previous = elements.nextElement(); // Obtém o primeiro elemento

        while (elements.hasMoreElements()) {
            Integer current = elements.nextElement();
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

