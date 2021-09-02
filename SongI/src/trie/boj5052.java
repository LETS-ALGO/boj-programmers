package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class boj5052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(sc.nextLine());
            String[] a = new String[n];
            Trie trie = new Trie();
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLine();
                trie.insert(a[i]);
            }
            String answer = "YES";
            for (int i = 0; i < n; i++) {
                Trie.TrieNode last = trie.findLastNode(a[i]);
                if (last.isLastChar && last.childNodes.size() != 0) {
                    answer = "NO";
                }
            }
            System.out.println(answer);
        }
    }

    public static class Trie {
        public class TrieNode {
            private Map<Character, TrieNode> childNodes = new HashMap<>();
            private boolean isLastChar;

            public Map<Character, TrieNode> getChildNodes() {
                return childNodes;
            }

            public void setChildNodes(Map<Character, TrieNode> childNodes) {
                this.childNodes = childNodes;
            }

            public boolean isLastChar() {
                return isLastChar;
            }

            public void setLastChar(boolean lastChar) {
                isLastChar = lastChar;
            }
        }

        private TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        void insert(String word) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                thisNode = thisNode.getChildNodes()
                        .computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            thisNode.setLastChar(true);
        }

        TrieNode findLastNode(String word) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                TrieNode node = thisNode.getChildNodes().get(character);

                if (node == null) {
                    return node;
                }
                thisNode = node;
            }
            return thisNode;
        }

        boolean contains(String word) {
            TrieNode thisNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                TrieNode node = thisNode.getChildNodes().get(character);

                if (node == null) {
                    return false;
                }
                thisNode = node;
            }
            return thisNode.isLastChar();
        }
    }

}
