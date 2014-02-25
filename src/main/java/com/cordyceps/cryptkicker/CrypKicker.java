package com.cordyceps.cryptkicker;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author zkropotkine
 */
public class CrypKicker {

    private static String PATTERN = "el veloz murciérlago hindú comía feliz cardillo y kiwi cuando la cigüeña tocaba el saxofón detrás del palenque de paja";

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        //System.getProperty("file.encoding","UTF-8");
        Scanner scanner = new Scanner(System.in);


        println("The pattern is: ");
        //String helperText = scanner.nextLine();


        //String helperText = "MESSAGE";
        
         String helperText = PATTERN;
        println(helperText);

        Map<Character, List<Integer>> charsPositions = new LinkedHashMap<>(helperText.length());

        int index = 0;
        for (Character currentChar : helperText.toLowerCase().toCharArray()) {
            List<Integer> positions = null;
            if (!charsPositions.containsKey(currentChar)) {
                positions = new ArrayList();
                charsPositions.put(currentChar, positions);
            } else {
                positions = charsPositions.get(currentChar);
            }

            positions.add(index++);
        }

        for (Map.Entry entry : charsPositions.entrySet()) {
            println(entry.getKey() + " " + entry.getValue().toString());
        }

        String fakeCase = "uÒou uÒ ub eceuhoc axrx yiu bcÒ vcekruÒ xqisxh x xtisxr x bx pfuÒox" +
                          " bx wcrrx qxpu rxafsxeuhou krfhqc Òckru ub aurrc humrc" +
                          " bcÒ qchqirÒcÒ su arcmrxexqfch Òch sfnurofscÒ";
        //String fakeCase =  "ABCCDEB";

        char[] caseArray = fakeCase.toCharArray();

        Resultado result = null;
        for (int i = 0; i <= caseArray.length - helperText.length(); i++) {

            println(fakeCase.substring(i, i + helperText.length()));
            result = processEntry(charsPositions, caseArray, i);

            if (result.isFoundPattern()) {
                println(result.isFoundPattern().toString());
                Map<Character, Character> dictionary = result.getDictionary();
                StringBuilder finalString = new StringBuilder(caseArray.length - helperText.length());

                for (int j = 0; j < caseArray.length; j++) {
                    //if (j == result.getPatternBeginIndex()) {
                    //    j = j + helperText.length();
                    //}
                    Character currentChar = caseArray[j];
                    Character sustitute = dictionary.get(currentChar);

                    finalString.append(sustitute);
                }
                println(finalString.toString());
                break;
            }
        }



    }

    /**
     *
     * @param text
     */
    private static void println(String text) {
        System.out.println(text);
    }

    private static Resultado processEntry(Map<Character, List<Integer>> patternPositions, char[] encryptedString, int beginIndex) {
        Resultado resultado = new Resultado();
        Map<Integer, Boolean> visitedPositions = new HashMap<>();
        Map<Character, Character> dictionay = new HashMap<>();
        int i = 0;

        encryptedString:
        for (Map.Entry<Character, List<Integer>> entry : patternPositions.entrySet()) {
            Character currentChar = encryptedString[beginIndex + i++];

            //if (visitedPositions.containsKey(i - 1)) {
            //    currentChar = encryptedString[beginIndex + i++];
            //}
            
//            if (dictionay.containsKey(currentChar)) {
//                
//                resultado.setFoundPattern(false);
//                break;
//            } else {
//                dictionay.put(currentChar, entry.getKey());
//            }
            
            while (dictionay.containsKey(currentChar)) {
                currentChar = encryptedString[beginIndex + i++];
            }
            
            dictionay.put(currentChar, entry.getKey());
            
            println(currentChar + " " + entry.getKey());
            boolean a = true;
            for (Integer currentPos : entry.getValue()) {
                 //println("" + i);
                 //println("" + currentPos);
                 //println("" + currentChar);
                 //println("" + encryptedString[currentPos]);

                if (currentChar.equals(encryptedString[currentPos + beginIndex])) {
                    //println("YES");
                    visitedPositions.put(currentPos, Boolean.TRUE);
                } else {
                    println("NO");
                    resultado.setFoundPattern(false);
                    break encryptedString;
                }
            }
        }


        if (resultado.isFoundPattern() == null) {
            resultado.setFoundPattern(true);
            resultado.setDictionary(dictionay);
            resultado.setPatternBeginIndex(i);
        }

        return resultado;
    }
}
