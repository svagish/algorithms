// keep marking 0's and its 0 nbrs 
// if 0 nbr is out of boundary or visited that island is bad, mark and break loop

class Solution {
    int m;
    int n;
    int[][] dir;
    Queue<int[]> qu;
    boolean[][] vis;
    int[][] grid;

    public boolean BFS() {
        boolean check = true;
        while(!qu.isEmpty()) {
            int size = qu.size();
            while(size-- > 0) {
                int[] src = qu.poll();
                int i = src[0];
                int j = src[1];
            
                // System.out.print("(remove: " + i +", " + j+")");
                    
                for (int d = 0; d < dir.length; d++) {
                    int nbr_i = i + dir[d][0];
                    int nbr_j = j + dir[d][1];
                    // if (d == 0) System.out.print(" up ");
                    // else if (d == 1) System.out.print(" left ");
                    // else if (d == 2)System.out.print(" down ");
                    // else System.out.print(" right ");

                    // valid coord
                    if (nbr_i >= 0 && nbr_j >= 0 && nbr_i < m && nbr_j < n) {
                        // if ((nbr_i == 0 || nbr_j == 0 || nbr_i == m-1 || nbr_j == n-1) && grid[nbr_i][nbr_j] == 0) return false;
                        // nbr 0
                        if (!vis[nbr_i][nbr_j] && grid[nbr_i][nbr_j] == 0) {
                            // nbr on boundary or vis
                            if (nbr_i == 0 || nbr_j == 0 || nbr_i == m-1 || nbr_j == n-1){
                                // System.out.println(" (invalid)");
                                check = false;  
                            } 
                            else {
                                // System.out.print(" (add in qu & mark)");
                                vis[nbr_i][nbr_j] = true;
                                qu.offer(new int[]{nbr_i, nbr_j});
                            }
                        }
                    }
                }
                // System.out.println();
            }
        }
        // System.out.println("VALID ISLAND");
        return check;
    }

    public int closedIsland(int[][] g) {
        this.grid = g;
        this.m = grid.length;
        this.n = grid[0].length;
        this.dir = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}};
        this.qu = new LinkedList<>();
        this.vis = new boolean[m][n];
        int ans = 0;

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(grid[i][j] +" ");
        //     }
        //     System.out.println();
        // }

        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (grid[i][j] == 0 && !vis[i][j]) {
                    vis[i][j] = true;
                    qu.offer(new int[]{i, j});
                    if (BFS()) ans++;
                }
            }
        }

        return ans;
    }
}