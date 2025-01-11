class Solution {
    public void traverse(int[][] heights, boolean[][] visited, int r, int c, int prevh) {
        if(r >= heights.length || 
           r < 0 ||
           c >= heights[0].length || 
           c < 0 || 
           visited[r][c] == true || 
           heights[r][c] < prevh) {
            return;
           }
        visited[r][c] = true;
        traverse(heights, visited, r+1,c, heights[r][c]);
        traverse(heights, visited, r-1,c, heights[r][c]);
        traverse(heights, visited, r,c+1, heights[r][c]);
        traverse(heights, visited, r,c-1, heights[r][c]);
    }
    

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROW = heights.length;
        int COL = heights[0].length;

        boolean[][] pac = new boolean[ROW][COL];
        boolean[][] atl = new boolean[ROW][COL];
        List<List<Integer>> res = new ArrayList<>();

        for (int r = 0; r < ROW; r++) {
            traverse(heights, pac, r, 0, heights[r][0]);
            traverse(heights, atl, r, COL-1, heights[r][COL-1]);
        }

        for (int c = 0; c < COL; c++) {
            traverse(heights, pac, 0, c, heights[0][c]);
            traverse(heights, atl, ROW-1, c, heights[ROW-1][c]);
        }

        for(int r = 0;r < ROW; r++) {
            for(int c = 0; c < COL; c++) {
                if(atl[r][c]==true && pac[r][c]==true){
                    res.add(Arrays.asList(r,c));
                }
            }
        }
        return res;

    }
}