import Trie.WordSearchII;


public class TestClass {
    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();
        char[][] board = {{'a', 'b'}};
        String[] words = {"ba"};

        System.out.println(wordSearchII.findWords(board, words));

    }
}
