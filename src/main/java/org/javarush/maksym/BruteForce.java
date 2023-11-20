package org.javarush.maksym;


public class BruteForce {
    private CaesarCipher caesarCipher;

    public BruteForce(CaesarCipher caesarCipher) {
        this.caesarCipher = caesarCipher;
    }

    public String crackCipher(String textToCrack){
        //взлом на основі того, що після коми повине йти пробіл або перенос рядка
        //проходимо по всім можливим ключам
        for (int i = 1; i < caesarCipher.getAlphabet().size(); i++) {
            String decodedText = caesarCipher.decode(textToCrack, i);
            int matchCounter = 0; //кількість співпадінь
            int currentFindIndex = -1;
            //якщо буде три таких співпадіння з високою вірогідністю ключ підібрано правильно
            while(matchCounter < 3) {
                currentFindIndex = decodedText.indexOf(',', currentFindIndex + 1);
                if (currentFindIndex != -1) {
                    if (decodedText.charAt(currentFindIndex + 1) == ' ' ||
                            decodedText.charAt(currentFindIndex + 1) == '\r') { //перенос рядка починається з перенесу каретки
                        matchCounter++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            if(matchCounter == 3) {
                return("Шифр зламано. Ключ: " + i + "\n\n" + decodedText);
            }
        }
        //якщо співпадінь недостатьньо то віддаємо корисутвачу всі можливі варіанти,
        // аби він сам вирішив, де правильний ключ
        return crackSemiautomatic(textToCrack);
    }

    private String crackSemiautomatic(String textToCrack) { //записуємо у текст всі варіанти
        StringBuilder result = new StringBuilder("Шифр не зламано. Спробуйте зробити це самостійно:");
        for (int i = 1; i < caesarCipher.getAlphabet().size(); i++) {
            result.append("\n\nКлюч:" + i);
            result.append("\n\n" + caesarCipher.decode(textToCrack, i));
        }
        return result.toString();
    }
}
