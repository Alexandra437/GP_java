import java.util.ArrayList;
import java.util.Stack;

class Graph {
    int count;
    int size;

    ArrayList<ArrayList<ListNode>> listGraph;

    Graph(int count, int size) {
        this.count = count;
        this.size = size;
        listGraph = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            listGraph.add(new ArrayList<>());
        }
    }

    void addEdge(int start, int end, int weight) {
        ListNode node = new ListNode(end, weight);
        listGraph.get(start).add(node);
    }

    void topologicalSort(int v, boolean[] visited,
                         Stack<Integer> stack) {
        visited[v] = true;
        for (int i = 0; i < listGraph.get(v).size(); i++) {
            ListNode node = listGraph.get(v).get(i);
            if (!visited[node.getVertex()])
                topologicalSort(node.getVertex(), visited, stack);
        }
        stack.push(v);
    }

    void longestPath(int start) {
        Stack<Integer> stack = new Stack<>();
        int[] dist = new int[count];
        boolean[] visited = new boolean[count];
        for (int i = 0; i < count; i++)
            visited[i] = false;
        for (int i = 0; i < count; i++)
            if (!visited[i])
                topologicalSort(i, visited, stack);
        for (int i = 0; i < count; i++)
            dist[i] = Integer.MIN_VALUE;
        dist[start] = 0;
        while (!stack.isEmpty()) {
            int current = stack.peek();
            stack.pop();
            if (dist[current] != Integer.MIN_VALUE) {
                for (int i = 0; i < listGraph.get(current).size(); i++) {
                    ListNode node = listGraph.get(current).get(i);
                    if (dist[node.getVertex()] < dist[current] + node.getWeight())
                        dist[node.getVertex()] = dist[current] + node.getWeight();
                }
            }
        }

        int i = 0;
        int maxDist=0;

        for (int k = 0; k < size; k++)
            for (int m = 0; m < size; m++) {

                System.out.println();
                if (dist[i] == Integer.MIN_VALUE)
                    System.out.print("INF ");
                else if (i != 0) {
                    dist[i] -= dist[0];
                    System.out.printf("initialVertex->[%d][%d]=%d ", k, m, dist[i++]);
                } else
                    System.out.printf("initialVertex->[%d][%d]=%d ", k, m, dist[i++]);
                if(dist[i-1]>maxDist)
                    maxDist=dist[i-1];
            }

        System.out.printf("\nLongest path tree from vertex 'initial Vertex' =%d ", maxDist);
    }

    static class ListNode {
        int vertex;
        int weight;


        ListNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        int getVertex() {
            return vertex;
        }

        int getWeight() {
            return weight;
        }
    }
}