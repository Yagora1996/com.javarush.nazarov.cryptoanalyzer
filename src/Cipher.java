
public class Cipher {
    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            for (int j = 0; j < ALPHABET.length; j++) {
                if (ch == ALPHABET[j]) {
                    result.append(ALPHABET[(j + shift) % ALPHABET.length]);
                }
            }
        }
        return result.toString();
    }
    public static String decrypt(String encryptedText, int shift) {

        return encrypt(encryptedText, ALPHABET.length - shift);
    }
}