package org.javarush.maksym;

import java.util.ArrayList;

public class CaesarCipher {

    private ArrayList<Character> alphabet; //алфавіт для кодування

    public CaesarCipher(ArrayList<Character> alphabet) {
        this.alphabet = alphabet;
    }
    public String encode(String textToEncode, int key) {
        StringBuilder encodedText = new StringBuilder();

        for(Character symbol : textToEncode.toCharArray()) {
            //якщо алфавіт містить символ, який хочемо закодувати
            if(alphabet.contains(symbol)) {
                // шукаємо індекс нового символу
                // повертаємо остачу від дільння на розмір алфавіту, бо ключ може бути більший за його розмір
                int index = (alphabet.indexOf(symbol) + key) % alphabet.size();
                //отримуємо новий символ з алфавіту
                Character encodedSymbol = alphabet.get(index);
                //додаємо до закодованого тексту
                encodedText.append(encodedSymbol);
            } else {
                //якщо такого символа немає в алфавіті, то просто записуємо його, ніяк не змінюючи
                encodedText.append(symbol);
            }
        }
        return encodedText.toString(); //повертаємо закодований текст
    }

    public String decode(String textToDecode, int key) {
        //декодування - це те саме кодування, але ключ береться зі знаком мінус
        //оскільки ми робимо циклічний зсув, то можна створити новий ключ:
        int newKey = (alphabet.size() - key) % alphabet.size();
        return encode(textToDecode,newKey);
    }

    public ArrayList<Character> getAlphabet() {
        return alphabet;
    }
}
