class Solution {
    class Node {
        char ch;
        Map<Character, Node> children;
        String word;

        public Node(char ch) {
            this.ch = ch;
            children = new HashMap<>();
        }
    }

    char[][] board = null;
    Set<String> res = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return null;
        }

        Node root = new Node('#');

        for (int i = 0; i < words.length; i++) {
            Node curr = root;
            String w = words[i];
            for (int j = 0; j < w.length(); j++) {
                char ch = w.charAt(j);
                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new Node(ch));
                }
                curr = curr.children.get(ch);
            }
            curr.word = w;
        }

        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    go(root.children.get(board[i][j]), i, j);
                }
            }
        }

        return new ArrayList<>(res);
    }

    void go(Node curr, int i, int j) {
        if (curr.word != null) {
            res.add(curr.word);
        }

        int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        if (board[i][j] == curr.ch) {
            char temp = board[i][j];
            board[i][j] = '#';

            for (int[] move : moves) {
                int r = i + move[0];
                int c = j + move[1];
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
                    continue;
                }
                if (curr.children.containsKey(board[r][c])) {
                    go(curr.children.get(board[r][c]), r, c);
                }
            }

            board[i][j] = temp;
        }
        // Remove leaf nodes
        if (curr.children.isEmpty()) {
            curr.children.remove(board[i][j]);
        }
        return;
    }
}