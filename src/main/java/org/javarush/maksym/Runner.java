package org.javarush.maksym;

import java.io.IOException;
import java.nio.file.Path;

class Runner {
    private CaesarCipher caesarCipher;
    private BruteForce bruteForce;

    Runner() {
        caesarCipher = new CaesarCipher(Alphabet.ALPHABET);
        bruteForce = new BruteForce(caesarCipher);
    }

    void run(String[] args) throws IOException{

        String command = args[0];
        Path src = Path.of(args[1]);
        int key;
        if(args.length == 3) {
            key = Integer.parseInt(args[2]);
        } else {
            key = -1;
        }
        String inputText = FileService.readFile(src);

        String mark = "_", outputText = "";
        switch(command) {
            case("ENCRYPT"):
                outputText = caesarCipher.encode(inputText, key);
                mark = "[ENCRYPT]";
                break;
            case("DECRYPT"):
                outputText = caesarCipher.decode(inputText, key);
                mark = "[DECRYPT]";
                break;
            case("BRUTE_FORCE"):
                outputText = bruteForce.crackCipher(inputText);
                mark = "[BRUTE_FORCE]";
                break;
        }
        Path newPath = FileService.addMarkToPath(src, mark); //додаємо марку до шляху файлу
        FileService.writeFile(newPath, outputText); //записуємо оброблений текст у файл
    }
}
