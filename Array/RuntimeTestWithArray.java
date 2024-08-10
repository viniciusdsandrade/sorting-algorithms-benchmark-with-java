package Tree.tests;

import java.text.DecimalFormat;
import java.util.*;

import static Tree.tests.Sorting.*;
import static java.lang.Math.*;
import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.util.Arrays.*;
import static java.util.concurrent.ThreadLocalRandom.current;

public class RuntimeTestWithArray {
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
//        test_vetor_10_aleatorio(TEN_MILLION);
//        test_vetor_100_aleatorio(ONE_MILLION);
//        test_vetor_1_000_aleatorio(ONE_MILLION);
//        test_vetor_10_000_aleatorio(TEN_THOUSAND);
//        test_vetor_100_000_aleatorio(ONE_HUNDRED);
//         test_vetor_250_000_aleatorio(FIVE);
//
//        test_vetor_10_decrescente(TEN_MILLION);
//        test_vetor_100_decrescente(ONE_MILLION);
//        test_vetor_1_000_decrescente(ONE_HUNDRED_THOUSAND);
//        test_vetor_10_000_decrescente(FIVE_THOUSAND);
//        test_vetor_20_000_decrescente(ONE_THOUSAND);
//        test_vetor_22_200_decrescente(ONE_THOUSAND);
    }

    public static void test_vetor_10_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(10, 0, 10);
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime();
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime;
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime;

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não : ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine();

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_100_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(100, 0, 100);
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime();
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime;
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime;

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_1_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(1_000, 0, 1_000);
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime();
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime;
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime;

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine();

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_10_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(10_000, 0, 10_000); // Gera array de 10.000 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime();
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime;
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime;

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine();

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_100_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(100_000, 0, 100_000); // Gera array de 100.000 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_250_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(250_000, 0, 250_000); // Gera array de 250.000 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_10_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(10, 0, 10); // Gera array decrescente de 10 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_100_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(100, 0, 100); // Gera array decrescente de 100 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_1_000_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(1_000, 0, 1_000);
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = System.nanoTime();
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = System.nanoTime();
                measureExecutionTime(algorithm, arrayCopy);
                long duration = System.nanoTime() - startTime;

                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = System.nanoTime() - cycleStartTime;
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
        }

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não : ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine();

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                }
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_10_000_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = System.nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(10_000, 0, 10_000); // Gera array decrescente de 10.000 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = System.nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = System.nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = System.nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = System.nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = System.nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_20_000_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = System.nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(20_000, 0, 20_000); // Gera array decrescente de 20.000 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = System.nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = System.nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = System.nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = System.nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = System.nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    public static void test_vetor_22_200_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = System.nanoTime(); // Início da medição do tempo total de execução

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(22_200, 0, 22_200); // Gera array decrescente de 22.200 elementos
            arrayCopies = generateArrayCopies(arrOriginal);

            long cycleStartTime = System.nanoTime(); // Início da medição do tempo do ciclo
            for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                int[] arrayCopy = arrayCopies.get(algorithm);
                long startTime = System.nanoTime(); // Início da medição do tempo de execução do algoritmo
                measureExecutionTime(algorithm, arrayCopy);
                long duration = System.nanoTime() - startTime; // Duração do tempo de execução do algoritmo
                totalRuntimes[algorithm.ordinal()] += duration;
            }
            long cycleDuration = System.nanoTime() - cycleStartTime; // Duração do tempo do ciclo
            System.out.printf("Ciclo %d: %d ns%n", (i + 1), cycleDuration);
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = System.nanoTime() - totalTime; // Tempo total de execução do programa

        System.out.println("Array Original: " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        // Criação do DecimalFormat para formatação
        DecimalFormat dfMillis = new DecimalFormat("#,###.######");
        DecimalFormat dfSeconds = new DecimalFormat("#,###.######");

        // Conversão para milissegundos e segundos
        double totalTimeMillis = totalCycleTime / 1_000_000.00;
        double totalTimeSeconds = totalCycleTime / 1_000_000_000.00;
        double totalTimeElapsedMillis = totalTimeElapsed / 1_000_000.00;
        double totalTimeElapsedSeconds = totalTimeElapsed / 1_000_000_000.00;

        // Impressão dos tempos em diferentes unidades com formatação
        System.out.println("Tempo total de todos os ciclos (ns):     " + totalCycleTime + " ns");
        System.out.println("Tempo total de todos os ciclos (ms):     " + dfMillis.format(totalTimeMillis) + " ms");
        System.out.println("Tempo total de todos os ciclos  (s):     " + dfSeconds.format(totalTimeSeconds) + " s");
        System.out.println("Tempo total de execução do programa (ns): " + totalTimeElapsed + " ns");
        System.out.println("Tempo total de execução do programa (ms): " + dfMillis.format(totalTimeElapsedMillis) + " ms");
        System.out.println("Tempo total de execução do programa  (s): " + dfSeconds.format(totalTimeElapsedSeconds) + " s");

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

            if (seeAllArrays && arrayCopies != null) {
                arrayCopies = generateArrayCopies(arrOriginal);
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                printArrays(arrayCopies);
            }
        } else {
            System.out.println("Entrada inválida. Por favor, digite 1 ou 0.");
        }
    }

    /**
     * Verifica se o vetor de inteiros está ordenado em ordem crescente.
     *
     * @param vetor o vetor de inteiros a ser verificado.
     * @return {@code true} se o vetor estiver ordenado em ordem crescente;
     * {@code false} caso contrário.
     */
    public static boolean isIncreasing(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i] > vetor[i + 1]) {
                return false; // Retorna false se encontrar algum elemento fora de ordem
            }
        }
        return true; // Retorna true se o vetor estiver ordenado em ordem crescente
    }

    /**
     * Verifica se o vetor de inteiros está ordenado em ordem decrescente.
     *
     * @param vetor o vetor de inteiros a ser verificado.
     * @return {@code true} se o vetor estiver ordenado em ordem decrescente;
     * {@code false} caso contrário.
     */
    public static boolean isDecreasing(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i] < vetor[i + 1]) {
                return false; // Retorna false se encontrar algum elemento fora de ordem
            }
        }
        return true; // Retorna true se o vetor estiver ordenado em ordem decrescente
    }

    /**
     * Gera um array de números inteiros aleatórios em um intervalo especificado.
     *
     * @param qnt A quantidade de números inteiros a serem gerados.
     * @param min O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max O valor máximo (inclusive) que um número gerado pode ter.
     * @return Um array de números inteiros aleatórios com a quantidade especificada.
     */
    public static int[] generateRandom(int qnt, int min, int max) {
        int[] arr = new int[qnt];
        for (int i = 0; i < qnt; i++)
            arr[i] = current().nextInt(min, max + 1);
        return arr;
    }

    /**
     * Gera um array de números inteiros aleatórios com muitos valores repetidos.
     * <p>
     * Este metodo cria um vetor onde um número limitado de valores se repete muitas vezes,
     * ideal para avaliar o desempenho de algoritmos de ordenação com esse tipo de distribuição.
     * </p>
     *
     * @param qnt        A quantidade de números inteiros a serem gerados.
     * @param min        O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max        O valor máximo (inclusive) que um número gerado pode ter.
     * @param repeticoes A quantidade de vezes que cada valor deve se repetir no array.
     * @return Um array de números inteiros aleatórios com muitos valores repetidos.
     */
    public static int[] generateRandomWithRepetitions(int qnt, int min, int max, int repeticoes) {
        int[] arr = new int[qnt];
        int uniqueValues = min(qnt / repeticoes, max - min + 1);

        for (int i = 0; i < qnt; i++)
            arr[i] = current().nextInt(min, min + uniqueValues);

        return arr;
    }

    /**
     * Gera um array de números inteiros aleatórios em ordem crescente.
     *
     * <p>O array gerado conterá números inteiros aleatórios dentro de um intervalo
     * especificado e será ordenado em ordem crescente, do menor para o maior.</p>
     *
     * @param size O tamanho do array a ser gerado.
     * @param min  O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max  O valor máximo (inclusive) que um número gerado pode ter.
     * @return Um array de números inteiros aleatórios ordenados em ordem crescente.
     */
    public static int[] generateAscendingRandom(int size, int min, int max) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // Fila de prioridade para ordem crescente (padrão)
        Random random = new Random();

        for (int i = 0; i < size; i++) queue.offer(random.nextInt(max - min + 1) + min);

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = queue.poll();

        return arr;
    }

    /**
     * Gera um array de números inteiros aleatórios em ordem decrescente.
     *
     * <p>O array gerado conterá números inteiros aleatórios dentro de um intervalo
     * especificado e será ordenado em ordem decrescente, do maior para o menor.</p>
     *
     * @param size O tamanho do array a ser gerado.
     * @param min  O valor mínimo (inclusive) que um número gerado pode ter.
     * @param max  O valor máximo (inclusive) que um número gerado pode ter.
     * @return Um array de números inteiros aleatórios ordenados em ordem decrescente.
     */
    public static int[] generateDescendingRandom(int size, int min, int max) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a); // Comparator para ordem decrescente
        Random random = new Random();

        for (int i = 0; i < size; i++) queue.offer(random.nextInt(max - min + 1) + min);

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = queue.poll();

        return arr;
    }

    /**
     * Mede o tempo de execução de um algoritmo de ordenação específico em um array.
     * <p>
     * Esta função recebe um algoritmo de ordenação e um array, executa o algoritmo no array e retorna o tempo de execução em nanosegundos.
     * </p>
     *
     * @param algorithm o algoritmo de ordenação a ser executado.
     *                  Este parâmetro é um enum {@link Sorting.SORT_ALGORITHM} que indica o tipo de algoritmo.
     * @param array     o array de inteiros sobre o qual o algoritmo de ordenação será executado.
     */
    private static void measureExecutionTime(Sorting.SORT_ALGORITHM algorithm, int[] array) {
        long startTime = nanoTime();
        switch (algorithm) {
            case SELECTION_SORT -> selectionSort(array);
            case BUBBLE_SORT -> bubbleSort(array);
            case MERGE_SORT -> mergeSort(array);
            case HEAP_SORT -> heapSort(array);
            case INSERTION_SORT -> insertionSort(array);
            case COUNTING_SORT -> countingSort(array, 10);
            case RADIX_SORT -> radixSort(array);
            case BUCKET_SORT -> bucketSort(array, 1);
            case QUICK_SORT -> quickSort(array, 0, array.length - 1);
            case SHELL_SORT -> shellSort(array);
            case DUAL_PIVOT_QUICK_SORT -> dualPivotQuickSort(array);
        }
        nanoTime();
    }

    /**
     * Gera cópias de um array original para cada algoritmo de ordenação disponível.
     * <p>
     * Esta função recebe um array original e cria cópias deste array para cada algoritmo de ordenação definido no enum {@link Sorting.SORT_ALGORITHM}.
     * As cópias são armazenadas em um mapa, onde a chave é o algoritmo e o valor é a cópia do array.
     * </p>
     *
     * @param originalArray o array original de inteiros que será copiado.
     * @return um mapa {@link Map} onde as chaves são os algoritmos de ordenação e os valores são cópias do array original.
     */
    private static Map<Sorting.SORT_ALGORITHM, int[]> generateArrayCopies(int[] originalArray) {
        Map<SORT_ALGORITHM, int[]> arrayCopies = new EnumMap<>(SORT_ALGORITHM.class);
        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
            arrayCopies.put(algorithm, copyOf(originalArray, originalArray.length));
        return arrayCopies;
    }

    /**
     * Imprime os conteúdos de um mapa de arrays inteiros.
     * <p>
     * Esta função recebe um mapa onde as chaves são enums que identificam
     * os arrays, e os valores são os arrays inteiros correspondentes. Para cada
     * entrada no mapa, a função imprime a chave formatada seguida pelo conteúdo do array.
     * </p>
     *
     * @param arrays um mapa contendo pares chave-valor, onde a chave é um enum
     *               identificando o array e o valor é o array de inteiros
     *               a ser impresso.
     */
    public static void printArrays(Map<SORT_ALGORITHM, int[]> arrays) {
        int maxKeyLength = 0;
        for (SORT_ALGORITHM key : arrays.keySet()) {
            if (key.name().length() > maxKeyLength)
                maxKeyLength = key.name().length();
        }

        for (Map.Entry<SORT_ALGORITHM, int[]> entry : arrays.entrySet()) {
            String formattedKey = format("%-" + maxKeyLength + "s", entry.getKey().name());
            System.out.println(formattedKey + ": " + Arrays.toString(entry.getValue()));
        }
    }
}
