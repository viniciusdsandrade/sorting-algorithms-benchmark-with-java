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

    /**
     * Ordena uma lista de elementos utilizando o algoritmo de ordenação por inserção (Insertion Sort).
     *
     * <p>O Insertion Sort funciona dividindo a lista em duas partes: uma parte ordenada e uma parte não ordenada.
     * Ele itera pela parte não ordenada, pegando um elemento de cada vez e inserindo-o na posição correta
     * dentro da parte ordenada.</p>
     *
     * <p>O algoritmo é eficiente para listas pequenas ou quase ordenadas, com uma complexidade de tempo média de
     * {@code O(n²)}, mas pode ser ineficiente para grandes conjuntos de dados desordenados.</p>
     *
     * @param list A lista de elementos a ser ordenada. Os elementos devem implementar a interface {@code Comparable}.
     * @param <T>  O tipo dos elementos na lista.
     */
    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;

            // Move elementos maiores que key para a direita
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        // Testando com números inteiros
        List<Integer> array_list = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original (ArrayList): " + array_list);
        insertionSort(array_list);
        System.out.println("Lista ordenada (ArrayList): " + array_list);

        List<Integer> vector_list = new Vector<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original (Vector): " + vector_list);
        insertionSort(vector_list);
        System.out.println("Lista ordenada (Vector): " + vector_list);

        List<Integer> linked_list = new LinkedList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        System.out.println("Lista original (LinkedList): " + linked_list);
        insertionSort(linked_list);
        System.out.println("Lista ordenada (LinkedList): " + linked_list);

        List<Integer> stack_list = new Stack<>();
        stack_list.add(64);
        stack_list.add(34);
        stack_list.add(25);
        stack_list.add(12);
        stack_list.add(22);
        stack_list.add(11);
        stack_list.add(90);
        System.out.println("Lista original (Stack): " + stack_list);
        insertionSort(stack_list);
        System.out.println("Lista ordenada (Stack): " + stack_list);
    }
}
