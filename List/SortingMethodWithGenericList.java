package Tree.tests;

import java.util.*;

public class SortingMethodWithGenericList {

    /**
     * Ordena uma lista de elementos utilizando o algoritmo de ordenação por bolha (Bubble Sort).
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
     * @param list A lista de elementos a ser ordenada. Os elementos devem implementar a interface {@code Comparable}.
     * @param <T>  O tipo dos elementos na lista.
     */
    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        boolean swapped;
        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        // Testando com números inteiros
        List<Integer> array_list = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original: " + array_list);
        bubbleSort(array_list);
        System.out.println("Lista ordenada: " + array_list);

        List<Integer> vector_list = new Vector<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original: " + vector_list);
        bubbleSort(vector_list);
        System.out.println("Lista ordenada: " + vector_list);

        List<Integer> linked_list = new LinkedList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original: " + linked_list);
        bubbleSort(linked_list);
        System.out.println("Lista ordenada: " + linked_list);

        List<Integer> stack_list = new Stack<>();
        stack_list.add(64);
        stack_list.add(34);
        stack_list.add(25);
        stack_list.add(12);
        stack_list.add(22);
        stack_list.add(11);
        stack_list.add(90);
        System.out.println("Lista original: " + stack_list);
        bubbleSort(stack_list);
        System.out.println("Lista ordenada: " + stack_list);
    }
}
