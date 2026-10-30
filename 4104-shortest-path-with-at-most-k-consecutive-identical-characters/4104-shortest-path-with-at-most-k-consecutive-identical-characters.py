import heapq

class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        INF = float('inf')
        graph = [[] for _ in range(n)]
        dist = [[INF] * (k + 1) for _ in range(n)]   # dist[node][run]
        dist[0][1] = 0
        pq = [(0, 0, 1)]  # wt, vtx, run length ending at vtx

        for u, v, w in edges:
            graph[u].append((v, w))

        while pq:
            wt, vtx, ksf = heapq.heappop(pq)
            if wt > dist[vtx][ksf]:
                continue
            if vtx == n - 1:
                return wt

            for nbr_vtx, nbr_wt in graph[vtx]:
                new_wt = wt + nbr_wt
                new_cons_k = ksf + 1 if labels[nbr_vtx] == labels[vtx] else 1

                if new_cons_k > k or new_wt >= dist[nbr_vtx][new_cons_k]:
                    continue

                dist[nbr_vtx][new_cons_k] = new_wt
                heapq.heappush(pq, (new_wt, nbr_vtx, new_cons_k))

        return -1