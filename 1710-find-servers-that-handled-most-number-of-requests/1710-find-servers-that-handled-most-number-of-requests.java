class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        TreeSet<Integer> freeServers = new TreeSet<>();  
        for (int i = 0; i < k; i++) freeServers.add(i);
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1])); // [sv, sv busy till]
        Map<Integer, Integer> reqHitServers = new HashMap<>(); // sv vs req hit

        int max = -1;
        for (int r = 0; r < n; r++) {
            // check for free servers
            while (!busyServers.isEmpty() && busyServers.peek()[1] <= arrival[r]) {
                freeServers.add(busyServers.poll()[0]);
            } 

            // no sv empty        
            if (freeServers.isEmpty()) continue;

            // check for i % k or closest
            Integer sv = r % k;
            if (!freeServers.contains(sv)) {
                Integer ceil = freeServers.ceiling(sv);
                sv = ceil != null ? ceil : freeServers.first(); 
                // couldnt find, continue
                if (sv == null) continue;
            }
            
            // add in busyServers and remove from freeeeeee
            busyServers.add(new int[]{sv, arrival[r] + load[r]});
            freeServers.remove(sv);

            // hit with req
            reqHitServers.put(sv, reqHitServers.getOrDefault(sv, 0) + 1);
            max = Math.max(max, reqHitServers.get(sv));
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : reqHitServers.entrySet()) {
            if (entry.getValue() == max) list.add(entry.getKey());
        }

        return list;
    }
}