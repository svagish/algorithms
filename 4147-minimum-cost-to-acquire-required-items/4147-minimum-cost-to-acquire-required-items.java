class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long onlyCostBoth = (long) Math.max(need1, need2) * costBoth;
        long onlyCost1AndCost2 = (long)cost1*need1 + (long)cost2*need2;

        int n1 = need1;
        int n2 = need2;

        long costMix = Math.min((long) Math.min(n1, n2) * (long)((long)cost1+cost2), (long) Math.min(n1, n2) * (long)(costBoth));
        n1 -= Math.min(need1, need2);
        n2 -= Math.min(need1, need2);

        if (n1 <= 0 && n2 <= 0) return Math.min(costMix, Math.min(onlyCostBoth, onlyCost1AndCost2));

        if (n1 > n2) {
            costMix += Math.min((long) n1*cost1, (long) n1 * costBoth);
        }
        else costMix += Math.min((long) n2*cost2, (long) n2 * costBoth);

    return Math.min(costMix, Math.min(onlyCostBoth, onlyCost1AndCost2));

    }
}