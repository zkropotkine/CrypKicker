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

    private static String PATTERN = "el veloz murciélago hindú comía feliz cardillo y kiwi cuando la cigüeña tocaba el saxofón detrás del palenque de paja";

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
        
        String helperText = "el veloz murciélago hindú comía neliz cardillo y kiwi cuando la cigüeña tocaba el saxofón detrás del palenque de paja";
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

        /*String fakeCase = "uÒou uÒ ub eceuhoc axrx yiu bcÒ vcekruÒ xqisxh x xtisxr x bx pfuÒox" +
                          " bx wcrrx qxpu rxafsxeuhou krfhqc Òckru ub aurrc humrc" +
                          " bcÒ qchqirÒcÒ su arcmrxexqfch Òch sfnurofscÒ";*/
        
        
        String fakeCase = "cx kuyxnkfu úrj hcxujxqx hxlx jc qbx qj cx pdáx qj odókjlud ju hrjócx jc yjcdü árlskacxod mkuqt sdábx ujckü sxlqkccd z ikík srxuqd cx skovjex pdsxóx jc nxñdúfu qjplwn qjc hxcjuérj qj hxgx qj cdn wuojcjn sdu xrpdlküxskfu qjc jgjlskpd qj cx uxskfu sdu jc úku qj ljsrhjlxl cx hcxüx qj cdn ukedn";
        //String fakeCase =  "ABCCDEB";

        char[] caseArray = fakeCase.toCharArray();

        Resultado result = null;
        for (int i = 0; i <= caseArray.length - helperText.length(); i++) {

            println("\nPossiblePattern: " + fakeCase.substring(i, i + helperText.length()));
            result = processEntry(charsPositions, caseArray, i);

            if (result.isFoundPattern()) {
                println(result.isFoundPattern().toString());
                Map<Character, Character> dictionary = result.getDictionary();
                StringBuilder finalString = new StringBuilder(caseArray.length - helperText.length());

                for (int j = 0; j < caseArray.length; j++) {
                    
                    if (j == result.getPatternBeginIndex()) {
                        println(result.getPatternBeginIndex());
                        println(j);
                        println(helperText.length());
                        println(j + helperText.length());
                        
                        //j = j + helperText.length();
                    }
                    Character currentChar = caseArray[j];
                    Character sustitute = dictionary.get(currentChar);

                    finalString.append(j + " "+ sustitute);
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
    private static void println(Object text) {
        System.out.println(text.toString());
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
                if (i + 1 <= encryptedString.length) {
                    currentChar = encryptedString[beginIndex + i++];
                } else {
                    break encryptedString;
                }
            }
            
            dictionay.put(currentChar, entry.getKey());
            
            println(currentChar + " " + entry.getKey());

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
