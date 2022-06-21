package org.adamd.search;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class GraphSearch {
    public static void bfs(int[][] vertices, int startVertex){
        Queue<Integer> nextVertices = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        nextVertices.add(startVertex);
        while(!nextVertices.isEmpty()){
            var vertex = nextVertices.poll();

            if (!visited.contains(vertex)){
                System.out.println(vertex);

                visited.add(vertex);
                Arrays.stream(vertices[vertex]).forEach(x -> nextVertices.add(x));
            }
        }
    }

    private static void dfs_recur(int[][] vertices, int curVertexIdx, List<Integer> dag, Set<Integer> visited){
        Arrays.stream(vertices[curVertexIdx]).filter(x -> !visited.contains(x)).forEach(x -> {
            visited.add(x);
            dfs_recur(vertices, x, dag, visited);
        });

        dag.add(curVertexIdx);
    }

    public static void dfs(int[][] vertices, int startVertex){
        Set<Integer> visited = new HashSet<>();
        List<Integer> dag = new ArrayList<>();

        for (var i = 0; i < vertices.length; ++i){
            if (!visited.contains(i)) {
                dfs_recur(vertices, i, dag, visited);
            }
        };

        System.out.println(dag);
    }
}
