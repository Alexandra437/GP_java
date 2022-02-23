import java.util.ArrayList;
import java.util.Stack;

class Graph {
    int count;
    int size;
    int vertex;

    ArrayList<ArrayList<ListNode>> listGraph;

    Graph(int count, int size, int vertexStart) {
        this.count = count;
        this.vertex = vertexStart;
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

    int[] longestPath(int start) {
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
        dist[0] = vertex;
        return dist;
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