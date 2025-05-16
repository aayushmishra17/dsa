package recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M 
 * colors such that no two adjacent vertices of the graph are colored with the same color.
 */
public class MColoring {
    public static void main(String[] args) {
        int n = 4;
        int m = 3;
        List < Integer > [] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList < > ();
        }
        graph[0].add(1);
        graph[1].add(0);
        graph[1].add(2);
        graph[2].add(1);
        graph[2].add(3);
        graph[3].add(2);
        graph[3].add(0);
        graph[0].add(3);
        graph[0].add(2);
        graph[2].add(0);
        int[] color = new int[n];
        MColoring obj = new MColoring();
        boolean ans = obj.graphColoring(graph, color, 0, m);
        if (ans == true)
            System.out.println("1");
        else
            System.out.println("0");
    }
    private boolean graphColoring(List<Integer>[] graph, int[] color, int startingNode, int m) {
        int n = graph.length;
        if(color(0, graph, color, n, m)) {
            return true;
        }
        return false;
    }
    /*
     * Time - O(M ^ N)
     * Space - O(N)[color array] + O(N)[aux. stack space]
     */
    private boolean color(int node, List<Integer>[] graph, int[] color, int n, int m) {
        if(node == n) {
            return true;
        }

        for(int c = 1; c <= m; c++) {
            if(isSafe(node, c, graph, color, n)) {
                color[node] = c;
                if(color(node + 1, graph, color, n, m)) {
                    return true;
                }
                color[node] = 0;
            }
        }

        return false;
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private boolean isSafe(int node, int currentColor, List<Integer>[] graph, int[] color, int n) {
        for(int adjacentNode : graph[node]) {
            if(color[adjacentNode] == currentColor) {
                return false;
            }
        }
        return true;
    }
}
