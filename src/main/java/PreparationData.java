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
        int[][] arr = new int[size][size];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Enter element of array [ %d ][ %d ]=>", i, j);
                arr[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println("\n");
            for (int j = 0; j < size; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
        }
        System.out.println();
        return arr;
    }

    static void startPoint(int[][] arrStart) {
        size = arrStart.length;
        count = size * size;
        Graph graph = new Graph(count, size, arrStart[0][0]);
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
                graph.addEdge(i, i, massTemp[i]);
                graph.addEdge(i, i + 1, massTemp[i + 1]);
                graph.addEdge(i, i + size + 1, arrStart[i + 1][i + 1]);
            } else {
                if (i % size == 0)
                    graph.addEdge(i, i, massTemp[i]);
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
        findDist(graph);
    }

    static void findDist(Graph graph) {
        int[][] resultArr = new int[count][count];
        int[] resultDist = new int[count];
        for (int i = 0; i < count; i++) {
            resultDist[i] = 0;
            resultArr[i] = graph.longestPath(i);
        }
        for (int i = 0; i < count; i++) {
            for (int k = 0; k < count; k++) {
                if (resultDist[i] < resultArr[k][i]) {
                    resultDist[i] = resultArr[k][i];
                }
            }
        }
        printResult(resultDist);
    }

    static void printResult(int[] resultDist) {
        System.out.print("The longest path from the original 'initialVertex' \n");
        int maxDist = 0;
        int size = (int) Math.sqrt(resultDist.length);
        int i = 0;
        for (int k = 0; k < size; k++)
            for (int m = 0; m < size; m++) {
                System.out.println();
                if (resultDist[i] == Integer.MIN_VALUE)
                    System.out.print("NoWay ");
                else {
                    if (maxDist < resultDist[i])
                        maxDist = resultDist[i];
                    System.out.printf("initialVertex->[%d][%d]=%d ", k, m, resultDist[i++]);
                }
            }
        System.out.printf("\nLongest path tree from vertex 'initial Vertex' =%d ", maxDist);
    }
}