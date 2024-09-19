import java.util.Arrays;

public class BruteForce {
    public static final int MAX_KEY = Cipher.ALPHABET.length;

    public static String decryptByBruteForce(String encryptedText) {
       /* String[] last = new String[MAX_KEY];
        for (int k = 0; k < MAX_KEY; k++) {
            StringBuilder result = new StringBuilder();
            encryptedText = encryptedText.toLowerCase();
            for (int i = 0; i < encryptedText.length(); i++) {
                char ch = encryptedText.charAt(i);
                for (int j = 0; j < MAX_KEY; j++) {
                    if (ch == Cipher.ALPHABET[j]) {
                        result.append(Cipher.ALPHABET[(j - i) % MAX_KEY]);
                    }
                }
            }
            last[k] = result.toString();
        }
        return Arrays.toString(last); 
    } */
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < MAX_KEY; i++) {
            result.append(Cipher.decrypt(encryptedText, i));
            result.append(" Ключ:" + i);
            result.append(System.lineSeparator());
            
        }
        return result.toString();
    }
}