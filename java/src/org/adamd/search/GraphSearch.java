package org.adamd.search;
import java.util.*;
import java.util.stream.Collectors;

public class GraphSearch {
    public static void main(String[] args) {
        final Integer[][] graph = {{1, 2}, {0, 2, 4}, {0, 1, 3}, {2}, {1}};
        GraphSearch.bfsTabulation(graph, 0);

        final Integer[][] cycliGgraph = {{1, 2}, {0, 2, 4}, {0, 1, 3}, {2}, {5}};
        boolean isCyclic = GraphSearch.dfsCyclicWithRecursion(cycliGgraph, 0);
        System.out.println("Is cyclic: %b".formatted(isCyclic));
    }

    public static void bfsTabulation(Integer[][] vertices, int startVertex){
        Queue<Integer> nextVertices = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(startVertex);
        nextVertices.offer(startVertex);
        System.out.println(startVertex);

        while(!nextVertices.isEmpty()){
            var vertex = nextVertices.poll();
            Arrays.stream(vertices[vertex])
                    .filter(x -> !visited.contains(x))
                    .forEach(x -> {
                        System.out.println(x);
                        visited.add(x);
                        nextVertices.add(x);
                    });
        }
    }

    private static void bfsRecurs(final Integer[][] vertices, int curVertexIdx, final Set<Integer> visited){
        if (curVertexIdx >= vertices.length){
            return;
        }
        var unVisitedVertices = Arrays.stream(vertices[curVertexIdx])
            .filter(x -> !visited.contains(x))
            .collect(Collectors.toList());
        unVisitedVertices.stream().forEach(System.out::println);
        unVisitedVertices.stream().forEach(visited::add);
        unVisitedVertices
            .forEach(x -> {
                bfsRecurs(vertices, x, visited);
            });
}

    public static void bfsRecursive(Integer[][] vertices, int startVertex){
        Set<Integer> visited = new HashSet<>();
        visited.add(startVertex);
        System.out.println(startVertex);
        bfsRecurs(vertices, startVertex, visited);
    }

    static public void dfsTabulation(final Integer[][] graph){
        Stack<Integer> q = new Stack<>();
        Set<Integer> s = new HashSet<>();
        q.push(0);
        s.add(0);
        while (!q.empty()){
            var vertx = q.pop();
            System.out.println(vertx);
            Arrays.stream(graph[vertx]).forEach(edge -> {
                if (!s.contains(edge)) {
                    s.add(edge);
                    q.push(edge);
                }
            });
        }
    }

    private static void dfsRecurs(Integer[][] vertices, int curVertexIdx, List<Integer> dag, Set<Integer> visited){
        if (curVertexIdx >= vertices.length){
            return;
        }
        Arrays.stream(vertices[curVertexIdx]).filter(x -> !visited.contains(x)).forEach(x -> {
            visited.add(x);
            dfsRecurs(vertices, x, dag, visited);
        });
        dag.add(curVertexIdx);
    }

    public static void dfsWithRecursion(Integer[][] vertices, int startVertex){
        Set<Integer> visited = new HashSet<>();
        List<Integer> dag = new ArrayList<>();
        for (var i = 0; i < vertices.length; ++i){
            if (!visited.contains(i)) {
                dfsRecurs(vertices, i, dag, visited);
            }
        };
        System.out.println(dag);
    }

    public static boolean dfsCyclicWithRecursion(Integer[][] vertices, int startVertex){
        Set<Integer> visited = new HashSet<>();
        List<Integer> dag = new ArrayList<>();
        for (var i = startVertex; i < vertices.length; ++i){
            if (visited.contains(i)) {
                return true;
            }
            dfsRecurs(vertices, i, dag, visited);
        };
        return false;
    }
}
