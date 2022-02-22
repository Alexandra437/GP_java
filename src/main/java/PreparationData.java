import java.util.Scanner;

class PreparationData {
    static int size;
    static int count;

    public static void enterSize() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of array");
        int temp = in.nextInt();
        if (temp > 0)
            size = temp;
        else {
            System.err.println("You entered invalid size of array, please try again");
            enterSize();
        }
        startPoint(creates());
        System.out.println();
    }

    static int[][] creates() {

        int[][] mass = new int[size][size];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Enter element of array [ %d ][ %d ]=>", i, j);
                mass[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println("\n");
            for (int j = 0; j < size; j++) {
                System.out.printf("%d\t", mass[i][j]);
            }
        }
        System.out.println();
        return mass;
    }
    static void startPoint(int[][] arrStart) {
        size = arrStart.length;
        count = size * size;
        Graph graph = new Graph(count, size);

        int[] massTemp = new int[count];
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                massTemp[index] = arrStart[i][j];
                index++;
            }
        }
        for (int i = 0; i < massTemp.length; i++) {
            if (i == 0) {
                graph.addEdge(i, i + 1, massTemp[i + 1]);
                graph.addEdge(i, i + size, massTemp[i + size]);
                graph.addEdge(i, i, arrStart[i][i]);
                for (int jm = 1; jm < size; jm++) {
                    graph.addEdge(i, size * jm, arrStart[jm][0]);
                }
            } else {
                if (i - size + 1 > 0 && !(i + 1 == size || (i + 1) % size == 0)) {
                    graph.addEdge(i, i - size + 1, massTemp[i - size + 1]);
                }
                if (i + 1 < count && !(i + 1 == size || (i + 1) % size == 0)) {
                    graph.addEdge(i, i + 1, massTemp[i + 1]);
                }
                if (i + size + 1 < count && !(i + 1 == size || (i + 1) % size == 0)) {
                    graph.addEdge(i, i + 1 + size, massTemp[i + size + 1]);
                }
            }
        }
        int start = 0;
        System.out.print("The longest path from the original 'initialVertex' \n");
        graph.longestPath(start);
    }

}