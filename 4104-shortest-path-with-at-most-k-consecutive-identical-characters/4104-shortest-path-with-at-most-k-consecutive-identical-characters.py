import heapq

class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        INF = float('inf')
        graph = [[] for _ in range(n)]
        dij = [[INF]*(k+1) for _ in range(n)]
        dij[0][1] = 0
        pq = [] 
        heapq.heappush(pq, [0, 0, 1]) # wt, vtx, curr k run

        for u, v, w in edges:
            graph[u].append([v, w])

        ans = -1

        while pq:
            wt, vtx, ksf = heapq.heappop(pq)
            prev_char = labels[vtx]

            if (wt > dij[vtx][ksf]):
                continue

            if (vtx == n-1):
                ans = wt
                break

            for nbr_vtx, nbr_wt in graph[vtx]:
                new_wt = wt + nbr_wt
                curr_char = labels[nbr_vtx]
                new_cons_k = ksf + 1 if curr_char == prev_char else 1

                if (new_cons_k > k or new_wt >= dij[nbr_vtx][new_cons_k]): 
                    continue
                    
                dij[nbr_vtx][new_cons_k] = new_wt
                heapq.heappush(pq, [new_wt, nbr_vtx, new_cons_k])

        return ans



        



        