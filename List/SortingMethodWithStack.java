package Tree.tests;

import java.util.Stack;

public class SortingMethodWithStack {

    /**
     * Ordena uma pilha de inteiros utilizando o algoritmo de ordenação por bolha (Bubble Sort).
     *
     * <p>O Bubble Sort é um algoritmo de ordenação simples que funciona repetidamente percorrendo a lista,
     * comparando elementos adjacentes e trocando-os se estiverem na ordem incorreta. Esse processo "empurra"
     * os maiores elementos para o final da lista em cada iteração, como bolhas que flutuam para a superfície.</p>
     *
     * <p>O algoritmo continua repetindo esse processo até que a pilha esteja ordenada. Uma otimização comum
     * é interromper o algoritmo se nenhuma troca for realizada durante uma iteração, o que indica que a pilha
     * já está ordenada.</p>
     *
     * <p>Embora o Bubble Sort seja fácil de entender e implementar, ele é ineficiente para grandes conjuntos de
     * dados devido à sua complexidade de tempo de {@code O(n²)}, onde {@code n} é o número de elementos na pilha.</p>
     *
     * @param stack A pilha de inteiros a ser ordenada.
     */
    public static void bubbleSort(Stack<Integer> stack) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < stack.size() - 1; i++) {
                if (stack.get(i) > stack.get(i + 1)) {
                    int temp = stack.get(i);
                    stack.set(i, stack.get(i + 1));
                    stack.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.push(9);
        stack.push(3);

        System.out.println("Pilha antes da ordenação: " + stack);
        SortingMethodWithStack.bubbleSort(stack);
        System.out.println("Pilha depois da ordenação: " + stack);
    }
}
