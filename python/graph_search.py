from collections import deque
from inspect import stack

RUNS = 0


def bfs_print(graph, start_vertex):
    global RUNS

    # set is used to mark visited vertices
    visited = set()
    queue = deque()

    # Mark the start vertex as visited and enqueue it
    visited.add(start_vertex)
    queue.appendleft(start_vertex)

    while queue:
        current_vertex = queue.pop()
        print(current_vertex)

        for v in graph[current_vertex]:
            RUNS += 1
            if v not in visited:
                visited.add(v)
                queue.appendleft(v)


def dfs_print(graph, start_vertex):
    # set is used to mark visited vertices
    visited = set()

    def recur(current_vertex):
        print(current_vertex)

        # mark it visited
        visited.add(current_vertex)

        # Recur for all the vertices adjacent to current_vertex
        for v in graph[current_vertex]:
            if v not in visited:
                recur(v)

    recur(start_vertex)


def dfs_order_dag(graph, start_vertex):
    # set is used to mark visited vertices
    visited = set()
    dag = []

    def recur(current_vertex):
        print(current_vertex)

        # mark it visited
        visited.add(current_vertex)

        # Recur for all the vertices adjacent to current_vertex
        for v in graph[current_vertex]:
            if v not in visited:
                recur(v)

        dag.append(current_vertex)

    for u in range(len(graph)):
        if u not in visited:
            recur(u)

    print(dag)


def _count_edges(graph):
    edges = 0

    for node in graph:
        edges += len(node)

    return edges


if __name__ == "__main__":
    graph = [[1, 2], [0, 2, 4], [0, 1, 3], [2], [1]]
    bfs_print(graph, 0)
    print(f'BFS: 0(n+k): {_count_edges(graph) + len(graph)} but was {RUNS}\n')
    dfs_print(graph, 0)
    print(f'DFS: 0(n+k): {_count_edges(graph) + len(graph)} but was {RUNS}\n')
    dfs_order_dag(graph, 0)
    print(f'DFS: 0(n+k): {_count_edges(graph) + len(graph)} but was {RUNS}\n')
