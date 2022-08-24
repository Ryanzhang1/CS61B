
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        Deque<Character> ans = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }

    private boolean isPalindromehelper(Deque q) {
        while (q.size() > 1) {
            if (q.removeFirst() != q.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word == null) {
            return true;
        }
        Deque ans = wordToDeque(word);
        return isPalindromehelper(ans);
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word == null) {
            return true;
        }
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
