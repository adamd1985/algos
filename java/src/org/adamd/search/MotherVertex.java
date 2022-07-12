package org.adamd.search;

import java.util.*;

public class MotherVertex {

    private static Integer dfsCyclicRecurs(int[][] vertices, int vertexIdx, Set<Integer> visited){
        if (!visited.contains(vertexIdx)){
            visited.add(vertexIdx);
            System.out.println(vertexIdx);
            Arrays.stream(vertices[vertexIdx]).forEach(x -> {
                if (!visited.contains(x)) {
                    dfsCyclicRecurs(vertices, x, visited);
                }
            });
        }

        return vertexIdx;
    }

    private static boolean dfsCyclicRecursFind(int[][] vertices, int vertexIdx, Set<Integer> visited, int what){
        if (what == vertexIdx){
            return true;
        }
        visited.add(vertexIdx);

        Arrays.stream(vertices[vertexIdx]).forEach(x -> {
            if (!visited.contains(x)) {
                dfsCyclicRecursFind(vertices, x, visited, what);
            }
        });

        return false;
    }

    public static int dfsCyclic(int[][] vertices){
        Set<Integer> visited = new HashSet<>();
        var mother = -1;

        mother = dfsCyclicRecurs(vertices, 0, visited);
        List<String> disc = new ArrayList<>();

        for (var i = 0; i < vertices.length; ++i){
            if (!visited.contains(i)){
                Set<Integer> innerVisited = new HashSet<>();
                if (dfsCyclicRecursFind(vertices, i, innerVisited, mother)){
                    mother = -1;
                    break;
                }
                if (vertices[i].length > 0) {
                    disc.add(i + " : " + Arrays.toString(vertices[i]));
                }
            }
        }
        if (mother > -1){
            System.out.println("We have disconnected graphs: %s".formatted(disc));
        }

        return mother;
    }

}
