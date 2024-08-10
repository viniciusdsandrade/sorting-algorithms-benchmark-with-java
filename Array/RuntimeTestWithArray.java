package Tree.tests;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.arraycopy;
import static java.lang.System.nanoTime;
import static java.util.Arrays.fill;

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
            arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
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
        int uniqueValues = Math.min(qnt / repeticoes, max - min + 1);

        for (int i = 0; i < qnt; i++)
            arr[i] = ThreadLocalRandom.current().nextInt(min, min + uniqueValues);

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
     * Ordena um array de números inteiros usando o algoritmo de ordenação por seleção (Selection Sort).
     *
     * <p>O Selection Sort é um algoritmo de ordenação simples que funciona dividindo o array em duas partes:
     * a parte ordenada no início e a parte não ordenada no restante. A cada iteração, o algoritmo seleciona o
     * menor (ou maior, dependendo da ordem) elemento da parte não ordenada e o troca com o primeiro elemento
     * não ordenado, movendo o limite entre as duas partes para frente.</p>
     *
     * <p>O processo continua até que todos os elementos estejam na parte ordenada. Como o algoritmo sempre
     * percorre o array inteiro para encontrar o próximo menor elemento, ele é menos eficiente para grandes
     * conjuntos de dados, com complexidade de tempo de {@code O(n²)}.</p>
     *
     * <p>Embora seja mais eficiente que o Bubble Sort em termos de número de trocas, o Selection Sort ainda
     * não é adequado para ordenar grandes arrays em comparação com algoritmos mais eficientes como Quicksort
     * ou Merge Sort.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            int aux = arr[i];
            arr[i] = arr[min];
            arr[min] = aux;
        }
    }

    /**
     * Ordena um array de números inteiros utilizando o metodo {@code Arrays.sort()} da classe {@code Arrays},
     * que implementa o algoritmo de ordenação Dual-Pivot Quicksort.
     *
     * <p>O Dual-Pivot Quicksort é uma versão otimizada do Quicksort tradicional, que utiliza dois pivôs em vez de um.
     * O algoritmo divide o array em três partes:
     * <ul>
     *   <li>Elementos menores que o primeiro pivô;</li>
     *   <li>Elementos entre os dois pivôs;</li>
     *   <li>Elementos maiores que o segundo pivô.</li>
     * </ul>
     * Cada uma dessas partes é ordenada recursivamente, resultando em um desempenho eficiente na maioria dos casos.
     * O Dual-Pivot Quicksort é conhecido por sua rapidez e eficiência, tendo complexidade de tempo média de
     * {@code O(n log n)}.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void dualPivotQuickSort(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * Ordena um array de números inteiros utilizando o algoritmo de ordenação por bolha (Bubble Sort).
     *
     * <p>O Bubble Sort é um algoritmo de ordenação simples que funciona repetidamente percorrendo o array,
     * comparando elementos adjacentes e trocando-os se estiverem na ordem incorreta. Esse processo "empurra"
     * os maiores elementos para o final do array em cada iteração, como bolhas que flutuam para a superfície.</p>
     *
     * <p>O algoritmo continua repetindo esse processo até que o array esteja ordenado. Uma otimização comum
     * é interromper o algoritmo se nenhuma troca for realizada durante uma iteração, o que indica que o array
     * já está ordenado.</p>
     *
     * <p>Embora o Bubble Sort seja fácil de entender e implementar, ele é ineficiente para grandes conjuntos de
     * dados devido à sua complexidade de tempo de {@code O(n²)}, onde {@code n} é o número de elementos no array.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void bubbleSort(int[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Ordena um array de números inteiros utilizando o algoritmo Merge Sort.
     *
     * <p>O Merge Sort é um algoritmo de ordenação que segue a abordagem "divisão e conquista". Ele divide o array
     * em duas metades, ordena cada metade recursivamente e, em seguida, mescla as duas metades ordenadas.</p>
     *
     * <p>Esse algoritmo tem uma complexidade de tempo de {@code O(n log n)}, sendo mais eficiente do que algoritmos
     * como Bubble Sort e Selection Sort, especialmente para grandes conjuntos de dados.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;

            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    /**
     * Ordena um array de números inteiros utilizando o algoritmo Heap Sort.
     *
     * <p>O Heap Sort é um algoritmo de ordenação que utiliza uma estrutura de dados chamada heap. O array é
     * transformado em um heap (max-heap para ordenação crescente), e então o maior elemento é extraído e
     * colocado na posição correta no array.</p>
     *
     * <p>O Heap Sort tem uma complexidade de tempo de {@code O(n log n)} e é eficiente em termos de espaço,
     * pois ordena o array in-place, sem necessidade de espaço adicional significativo.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    /**
     * Realiza a ordenação de um array de números inteiros utilizando o algoritmo Insertion Sort.
     *
     * <p>O Insertion Sort é um algoritmo simples que constrói o array ordenado um elemento por vez. É eficiente
     * para pequenos conjuntos de dados e tem uma complexidade de tempo de {@code O(n^2)} no pior caso.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Ordena um array de inteiros usando o algoritmo Counting Sort.
     * <p>
     * Este metodo utiliza um array de contagem para ordenar o array de entrada.
     * O array de contagem deve ser grande o suficiente para acomodar o maior valor do array de entrada.
     * </p>
     *
     * @param array o array de inteiros a ser ordenado.
     * @param range o intervalo máximo dos valores no array de entrada.
     */
    private static void countingSort(int[] array, int range) {
        int n = array.length;

        // O valor máximo pode ser maior que 'range', então calculamos dinamicamente
        int maxValue = Arrays.stream(array).max().orElse(range);

        // Ajusta o tamanho do array de contagem com base no valor máximo
        int[] count = new int[maxValue + 1];
        int[] output = new int[n];

        // Conta a ocorrência de cada valor
        for (int j : array) count[j]++;

        // Calcula a posição de cada elemento no array de saída
        for (int i = 1; i <= maxValue; i++) count[i] += count[i - 1];

        // Constroi o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copia o array de saída para o array original
        arraycopy(output, 0, array, 0, n);
    }

    /**
     * Ordena um array de inteiros utilizando o algoritmo Radix Sort.
     *
     * <p>O Radix Sort é um algoritmo de ordenação não comparativo que ordena os elementos por dígitos,
     * do menos significativo para o mais significativo. Ele utiliza o Counting Sort como sub-rotina
     * para ordenar os dígitos.</p>
     *
     * @param arr O array de inteiros a ser ordenado.
     */
    public static void radixSort(int[] arr) {
        // Encontra o valor máximo absoluto no array
        int max = 0;
        for (int value : arr) {
            if (Math.abs(value) > max) max = Math.abs(value);
        }

        // Determina o número de dígitos do valor máximo
        int maxDigit = 0;
        while (max > 0) {
            max /= 10;
            maxDigit++;
        }

        // Executa o Radix Sort
        for (int exp = 1; exp <= Math.pow(10, maxDigit); exp *= 10)
            countingSortForRadix(arr, exp);
    }

    /**
     * Metodo auxiliar para o Radix Sort que utiliza o Counting Sort para ordenar os elementos
     * com base em um dígito específico.
     *
     * @param arr O array de inteiros a ser ordenado.
     * @param exp O expoente que representa o dígito atual sendo considerado (unidades, dezenas, centenas, etc.).
     */
    private static void countingSortForRadix(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // Array de saída
        int[] count = new int[20]; // Contagem de ocorrências (10 dígitos + 10 para sinal)
        fill(count, 0);

        // Armazena a contagem de ocorrências em count[]
        for (int j : arr) {
            int digit = ((j / exp) % 10) + 10; // Deslocamento para tratar como positivo
            count[digit]++;
        }

        // count[i] agora contém a posição real deste dígito em output[]
        for (int i = 1; i < 20; i++) count[i] += count[i - 1];

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            int digit = ((arr[i] / exp) % 10) + 10; // Deslocamento para tratar como positivo
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copia o array de saída para arr[]
        arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Ordena um array de números inteiros utilizando o algoritmo Bucket Sort.
     *
     * <p>O Bucket Sort distribui os elementos do array em vários 'baldes' (ou buckets), e então ordena cada balde
     * individualmente utilizando um algoritmo de ordenação, como o Insertion Sort. É eficiente para arrays com
     * distribuição uniforme de valores.</p>
     *
     * @param arr        O array de números inteiros a ser ordenado.
     * @param bucketSize O tamanho de cada balde.
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length == 0) return;

        int minValue = arr[0];
        int maxValue = arr[0];

        // Find the minimum and maximum values in the array
        for (int value : arr) {
            if (value < minValue) minValue = value;
            if (value > maxValue) maxValue = value;
        }

        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][0];

        // Distribute the elements into buckets
        for (int j : arr) {
            int index = (j - minValue) / bucketSize;
            buckets[index] = append(buckets[index], j);
        }

        // Sort each bucket and place it back into the original array
        int currentIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length > 0) {
                insertionSort(bucket);
                for (int value : bucket) {
                    arr[currentIndex++] = value;
                }
            }
        }
    }

    private static int[] append(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * Realiza a ordenação de um array de números inteiros utilizando o algoritmo Quick Sort.
     *
     * <p>O Quick Sort é um algoritmo de ordenação eficiente, baseado no conceito de divisão e conquista. Ele seleciona
     * um elemento como pivô e particiona o array em duas subpartes, colocando os elementos menores que o pivô à esquerda
     * e os maiores à direita. A ordenação é realizada recursivamente em cada subparte.</p>
     *
     * <p>O Quick Sort tem uma complexidade de tempo média de {@code O(n log n)} e, no pior caso, {@code O(n^2)}, mas
     * é amplamente utilizado por ser rápido e eficiente na prática.</p>
     *
     * @param arr  O array de números inteiros a ser ordenado.
     * @param low  O índice inicial da subparte do array a ser ordenado.
     * @param high O índice final da subparte do array a ser ordenado.
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Certifique-se de que os índices estão corretos
            if (pi > low) quickSort(arr, low, pi - 1);  // Antes do pivô
            if (pi < high) quickSort(arr, pi + 1, high); // Depois do pivô
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Índice do elemento menor

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // Troca arr[i] e arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Troca arr[i + 1] e arr[high] (ou pivô)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Ordena um array de números inteiros utilizando o algoritmo de ordenação Shell Sort.
     *
     * <p>O Shell Sort é uma melhoria do Insertion Sort que permite a troca de elementos distantes
     * para reduzir o número total de movimentos necessários. Ele funciona dividindo o array em
     * sublist baseadas em um "gap" (distância entre elementos), que é reduzido progressivamente
     * até que seja 1, momento em que o algoritmo efetivamente realiza um Insertion Sort.</p>
     *
     * <p>O algoritmo começa com um gap grande, geralmente metade do tamanho do array, e vai reduzindo
     * esse gap pela metade a cada iteração. Para cada gap, o algoritmo realiza uma espécie de
     * Insertion Sort nos elementos que estão a essa distância um do outro. Essa abordagem permite
     * que os elementos "caminhem" mais rapidamente em direção à sua posição correta, tornando o
     * processo de ordenação mais eficiente.</p>
     *
     * <p>Embora o Shell Sort seja mais eficiente que o Insertion Sort, sua complexidade de tempo
     * pode variar dependendo da sequência de gaps utilizada. Uma sequência comum é dividir o gap
     * por 2 a cada iteração, mas outras sequências podem ser empregadas para otimizar ainda mais o
     * algoritmo.</p>
     *
     * @param arr O array de números inteiros a ser ordenado.
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;

        // Começa com um grande gap, então reduz o gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Faz um gapped insertion sort para esse gap tamanho
            for (int i = gap; i < n; i++) {
                // Guarda o elemento atual em uma variável temporária
                int temp = arr[i];

                // Desloca os elementos do array até o local correto para o elemento temporário
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                // Coloca temp na sua posição correta
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
//        test_vetor_10_aleatorio(ONE_HUNDRED_MILLION);
          test_vetor_100_aleatorio(TEN_MILLION);
//        test_vetor_1_000_aleatorio(ONE_MILLION);
//        test_vetor_10_000_aleatorio(TEN_THOUSAND);
//        test_vetor_100_000_aleatorio(TEN);
//        test_vetor_250_000_aleatorio(THREE);
//
//        test_vetor_10_decrescente(TEN_MILLION);
//        test_vetor_100_decrescente(ONE_MILLION);
//        test_vetor_1_000_decrescente(ONE_HUNDRED_THOUSAND);
//        test_vetor_10_000_decrescente(FIVE_THOUSAND);
//        test_vetor_20_000_decrescente(ONE_THOUSAND);
//        test_vetor_22_200_decrescente(ONE_THOUSAND);
    }

    public enum SORT_ALGORITHM {
        SELECTION_SORT,
        BUBBLE_SORT,
        MERGE_SORT,
        HEAP_SORT,
        INSERTION_SORT,
        COUNTING_SORT,
        RADIX_SORT,
        BUCKET_SORT,
        QUICK_SORT,
        SHELL_SORT,
        DUAL_PIVOT_QUICK_SORT
    }

    /**
     * Mede o tempo de execução de um algoritmo de ordenação específico em um array.
     * <p>
     * Esta função recebe um algoritmo de ordenação e um array, executa o algoritmo no array e retorna o tempo de execução em nanosegundos.
     * </p>
     *
     * @param algorithm o algoritmo de ordenação a ser executado.
     *                  Este parâmetro é um enum {@link SORT_ALGORITHM} que indica o tipo de algoritmo.
     * @param array     o array de inteiros sobre o qual o algoritmo de ordenação será executado.
     */
    private static void measureExecutionTime(SORT_ALGORITHM algorithm, int[] array) {
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
     * Esta função recebe um array original e cria cópias deste array para cada algoritmo de ordenação definido no enum {@link SORT_ALGORITHM}.
     * As cópias são armazenadas em um mapa, onde a chave é o algoritmo e o valor é a cópia do array.
     * </p>
     *
     * @param originalArray o array original de inteiros que será copiado.
     * @return um mapa {@link Map} onde as chaves são os algoritmos de ordenação e os valores são cópias do array original.
     */
    private static Map<SORT_ALGORITHM, int[]> generateArrayCopies(int[] originalArray) {
        Map<SORT_ALGORITHM, int[]> arrayCopies = new EnumMap<>(SORT_ALGORITHM.class);
        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values())
            arrayCopies.put(algorithm, Arrays.copyOf(originalArray, originalArray.length));
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
            String formattedKey = String.format("%-" + maxKeyLength + "s", entry.getKey().name());
            System.out.println(formattedKey + ": " + Arrays.toString(entry.getValue()));
        }
    }

    public static void test_vetor_10_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;
        long totalCycleTime = 0;
        long totalTime = System.nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(10, 0, 10);
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
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = System.nanoTime() - totalTime;

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
        long totalTime = System.nanoTime();

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(100, 0, 100);
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
            totalCycleTime += cycleDuration; // Acumula o tempo de cada ciclo
        }
        long totalTimeElapsed = System.nanoTime() - totalTime;

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
                for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
                    measureExecutionTime(algorithm, arrayCopies.get(algorithm));
                }
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

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(1000, 0, 1000);
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

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
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

    public static void test_vetor_10_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(10000, 0, 10000); // Gera array de 10.000 elementos
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

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
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


    public static void test_vetor_100_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(100_000, 0, 100_000); // Gera array de 100.000 elementos
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

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

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

    public static void test_vetor_250_000_aleatorio(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateRandom(250_000, 0, 250_000);
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

        System.out.println("Array Original (última iteração): " + Arrays.toString(arrOriginal));

        for (SORT_ALGORITHM algorithm : SORT_ALGORITHM.values()) {
            long avgRuntime = totalRuntimes[algorithm.ordinal()] / ciclos;
            System.out.printf("Média Runtime %-23s: %d ns%n", algorithm.name(), avgRuntime);
        }

        System.out.print("Ver todos os arrays? 1 - Sim, 0 - Não: ");
        if (scanner.hasNextInt()) {
            boolean seeAllArrays = scanner.nextInt() == 1;
            scanner.nextLine(); // Consumir a quebra de linha

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

    public static void test_vetor_10_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(10, 0, 10);
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

    public static void test_vetor_100_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(100, 0, 100);
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

    public static void test_vetor_20_000_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(20_000, 0, 20_000);
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

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(10_000, 0, 10_000); // Gera array de 10.000 elementos
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

    public static void test_vetor_22_200_decrescente(int ciclos) {
        long[] totalRuntimes = new long[SORT_ALGORITHM.values().length];
        int[] arrOriginal = new int[0];
        Map<SORT_ALGORITHM, int[]> arrayCopies = null;

        for (int i = 0; i < ciclos; i++) {
            arrOriginal = generateDescendingRandom(22_200, 0, 22_200);
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
}
