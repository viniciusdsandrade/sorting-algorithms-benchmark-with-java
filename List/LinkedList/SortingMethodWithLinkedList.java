package Tree.tests.LinkedList;

import java.util.LinkedList;

public class SortingMethodWithLinkedList {

    /**
     * Ordena uma lista encadeada de inteiros utilizando o algoritmo de ordenação por bolha (Bubble Sort).
     *
     * <p>O Bubble Sort é um algoritmo de ordenação simples que funciona repetidamente percorrendo a lista,
     * comparando elementos adjacentes e trocando-os se estiverem na ordem incorreta. Esse processo "empurra"
     * os maiores elementos para o final da lista em cada iteração, como bolhas que flutuam para a superfície.</p>
     *
     * <p>O algoritmo continua repetindo esse processo até que a lista esteja ordenada. Uma otimização comum
     * é interromper o algoritmo se nenhuma troca for realizada durante uma iteração, o que indica que a lista
     * já está ordenada.</p>
     *
     * <p>Embora o Bubble Sort seja fácil de entender e implementar, ele é ineficiente para grandes conjuntos de
     * dados devido à sua complexidade de tempo de {@code O(n²)}, onde {@code n} é o número de elementos na lista.</p>
     *
     * @param list A lista encadeada de inteiros a ser ordenada.
     */
    public static void bubbleSort(LinkedList<Integer> list) {
        boolean swapped;
        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Ordena uma lista encadeada de inteiros utilizando o algoritmo de ordenação por inserção (Insertion Sort).
     *
     * <p>O Insertion Sort é um algoritmo simples que constrói a lista ordenada um elemento por vez. É eficiente
     * para pequenos conjuntos de dados e tem uma complexidade de tempo de {@code O(n^2)} no pior caso.</p>
     *
     * @param list A lista encadeada de inteiros a ser ordenada.
     */
    public static void insertionSort(LinkedList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // Move elements of list[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }
}
