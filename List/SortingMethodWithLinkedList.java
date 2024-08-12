package Tree.tests;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public static void main(String[] args) {
        // Teste 1: Lista vazia
        LinkedList<Integer> emptyList = new LinkedList<>();
        System.out.println("Lista vazia antes da ordenação: " + emptyList);
        SortingMethodWithLinkedList.bubbleSort(emptyList);
        System.out.println("Lista vazia depois da ordenação: " + emptyList);

        // Teste 2: Lista com um elemento
        LinkedList<Integer> singleElementList = new LinkedList<>(List.of(5));
        System.out.println("\nLista com um elemento antes da ordenação: " + singleElementList);
        SortingMethodWithLinkedList.bubbleSort(singleElementList);
        System.out.println("Lista com um elemento depois da ordenação: " + singleElementList);

        // Teste 3: Lista já ordenada
        LinkedList<Integer> sortedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("\nLista já ordenada antes da ordenação: " + sortedList);
        SortingMethodWithLinkedList.bubbleSort(sortedList);
        System.out.println("Lista já ordenada depois da ordenação: " + sortedList);

        // Teste 4: Lista desordenada
        LinkedList<Integer> unsortedList = new LinkedList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("\nLista desordenada antes da ordenação: " + unsortedList);
        SortingMethodWithLinkedList.bubbleSort(unsortedList);
        System.out.println("Lista desordenada depois da ordenação: " + unsortedList);
    }
}
