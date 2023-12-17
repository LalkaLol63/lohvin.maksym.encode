package org.javarush.maksym;


public class BruteForce {
    private final CaesarCipher caesarCipher;

    private static final int SUFFICIENT_NUM_OF_MATCHES = 3;
    public BruteForce(CaesarCipher caesarCipher) {
        this.caesarCipher = caesarCipher;
    }

    public String crackCipher(String textToCrack){
        //взлом на основі того, що після коми повине йти пробіл або перенос рядка
        //проходимо по всім можливим ключам
        for (int i = 1; i < caesarCipher.getAlphabet().size(); i++) {
            String decodedText = caesarCipher.decode(textToCrack, i);
            if(findMatchesInText(decodedText)) {
                return("Шифр зламано. Ключ: " + i + "\n\n" + decodedText);
            }
        }
        //якщо співпадінь недостатьньо то віддаємо корисутвачу всі можливі варіанти,
        // аби він сам вирішив, де правильний ключ
        return crackSemiautomatic(textToCrack);
    }
    private boolean findMatchesInText(String textToFind) {
        int matchCounter = 0; //кількість співпадінь
        int currentFindIndex = -1;
        while(matchCounter < SUFFICIENT_NUM_OF_MATCHES) {
            currentFindIndex = textToFind.indexOf(',', currentFindIndex + 1);
            if (currentFindIndex != -1) {
                if (textToFind.charAt(currentFindIndex + 1) == ' ' ||
                        textToFind.charAt(currentFindIndex + 1) == '\r') { //перенос рядка починається з перенесу каретки
                    matchCounter++;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    private String crackSemiautomatic(String textToCrack) { //записуємо у текст всі варіанти
        StringBuilder result = new StringBuilder("Шифр не зламано. Спробуйте зробити це самостійно:");
        for (int i = 1; i < caesarCipher.getAlphabet().size(); i++) {
            result.append("\n\nКлюч:").append(i);
            result.append("\n\n").append(caesarCipher.decode(textToCrack, i));
        }
        return result.toString();
    }
}
