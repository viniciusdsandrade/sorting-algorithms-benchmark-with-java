package Tree.tests.Vector;

import java.util.*;

public class SortingMethodWithVector {

    /**
     * Ordena um vetor de números inteiros utilizando o algoritmo de ordenação por bolha (Bubble Sort).
     *
     * <p>O Bubble Sort é um algoritmo de ordenação simples que funciona repetidamente percorrendo o vetor,
     * comparando elementos adjacentes e trocando-os se estiverem na ordem incorreta. Esse processo "empurra"
     * os maiores elementos para o final do vetor em cada iteração, como bolhas que flutuam para a superfície.</p>
     *
     * <p>O algoritmo continua repetindo esse processo até que o vetor esteja ordenado. Uma otimização comum
     * é interromper o algoritmo se nenhuma troca for realizada durante uma iteração, o que indica que o vetor
     * já está ordenado.</p>
     *
     * <p>Embora o Bubble Sort seja fácil de entender e implementar, ele é ineficiente para grandes conjuntos de
     * dados devido à sua complexidade de tempo de {@code O(n²)}, onde {@code n} é o número de elementos no vetor.</p>
     *
     * @param vec O vetor de números inteiros a ser ordenado.
     */
    public static void bubbleSort(Vector<Integer> vec) {

        boolean swapped;
        for (int i = 0; i < vec.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < vec.size() - 1 - i; j++) {
                if (vec.get(j) > vec.get(j + 1)) {
                    int temp = vec.get(j);
                    vec.set(j, vec.get(j + 1));
                    vec.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        // Criando um vetor de inteiros desordenado
        Vector<Integer> vec = new Vector<>();
        vec.add(64);
        vec.add(34);
        vec.add(25);
        vec.add(12);
        vec.add(22);
        vec.add(11);
        vec.add(90);

        // Exibindo o vetor original
        System.out.println("Vetor original: " + vec);

        // Ordenando o vetor usando Bubble Sort
        bubbleSort(vec);

        // Exibindo o vetor ordenado
        System.out.println("Vetor ordenado: " + vec);
    }
}
