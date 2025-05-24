class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int totalCost = 0;
        int sr = startPos[0], sc = startPos[1];
        int hr = homePos[0], hc = homePos[1];

        // Calculate row costs
        if (sr < hr) {
            for (int r = sr + 1; r <= hr; r++) {
                totalCost += rowCosts[r];
            }
        } else {
            for (int r = sr - 1; r >= hr; r--) {
                totalCost += rowCosts[r];
            }
        }

        // Calculate column costs
        if (sc < hc) {
            for (int c = sc + 1; c <= hc; c++) {
                totalCost += colCosts[c];
            }
        } else {
            for (int c = sc - 1; c >= hc; c--) {
                totalCost += colCosts[c];
            }
        }

        return totalCost;
    }
}