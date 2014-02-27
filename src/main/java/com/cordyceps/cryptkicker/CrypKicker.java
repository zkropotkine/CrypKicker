package com.cordyceps.cryptkicker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zkropotkine
 */
public class CrypKicker {

    private static String PATTERN = "el veloz murciélago hindú comía feliz cardillo y kiwi cuando la cigüeña tocaba el saxofón detrás del palenque de paja";
    private static String TEST_CASE = "cx kuyxnkfu úrj hcxujxqx hxlx jc qbx qj cx pdáx qj odókjlud ju hrjócx jc yjcdü árlskacxod mkuqt sdábx újckü sxlqkccd z ikík srxuqd cx skovjex pdsxóx jc nxñdúfu qjplwn qjc hxcjuérj qj hxgx qj cdn wuojcjn sdu xrpdlküxskfu qjc jgjlskpd qj cx uxskfu sdu jc úku qj ljsrhjlxl cx hcxüx qj cdn ukedn";
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        println("The pattern is: ");
        //String helperText = "el veloz murciélago hindú comía neliz cardillo y kiwi cuando la cigüeña tocaba el saxofón detrás del palenque de paja";
        String helperText = PATTERN;
        println(helperText);

        Map<Character, List<Integer>> patternPositions = new LinkedHashMap<>(helperText.length());

        int index = 0;
        for (Character currentChar : helperText.toLowerCase().toCharArray()) {
            List<Integer> positions = null;
            if (!patternPositions.containsKey(currentChar)) {
                positions = new ArrayList();
                patternPositions.put(currentChar, positions);
            } else {
                positions = patternPositions.get(currentChar);
            }

            positions.add(index++);
        }

//        for (Map.Entry entry : patternPositions.entrySet()) {
//            println(entry.getKey() + " " + entry.getValue().toString());
//        }
        
        List<String> cases = getCasesFromInputFile();
        List<String> decodedCases = new ArrayList<>();
        for (String currentCase : cases) {
            String result = processCase(PATTERN, currentCase, patternPositions);
            
            decodedCases.add(result);
            //decodedCases.add(result.replaceAll(PATTERN, ""));
        }
        
        writeOutput(decodedCases);
    }

    /**
     *
     * @param text
     */
    private static void println(Object text) {
        System.out.println(text.toString());
    }

    private static String processCase(String patternText, String caseToDecode, Map<Character, List<Integer>> patternPositions) {
        String resultado = "NO SE ENCONTRO SOLUCION ";
        char[] caseArray = caseToDecode.toCharArray();
              
        for (int i = 0; i <= caseArray.length - patternText.length(); i++) {

            //println("\nPossiblePattern: " + caseToDecode.substring(i, i + patternText.length()));
            Resultado result = processEntry(patternPositions, caseArray, i);

            if (result.isFoundPattern()) {
                println("SE ENCONTRO: " + result.isFoundPattern().toString());
                Map<Character, Character> dictionary = result.getDictionary();
                StringBuilder finalString = new StringBuilder(caseArray.length - patternText.length());

                for (int j = 0; j < caseArray.length; j++) {

                    if (j == result.getPatternBeginIndex()) {
                        //println(result.getPatternBeginIndex());
                        //println(j);
                        //println(patternText.length());
                        //println(j + patternText.length());

                        j = j + patternText.length() + 1;
                    }
                    Character currentChar = caseArray[j];
                    Character sustitute = dictionary.get(currentChar);
                    
                    //println(j + " "+ sustitute);
                    finalString.append(sustitute);
                }
                return finalString.toString();
            }
        }
        
        return resultado;
    }
    
    private static Resultado processEntry(Map<Character, List<Integer>> patternPositions, char[] encryptedString, int beginIndex) {
        Resultado resultado = new Resultado();
        Map<Integer, Boolean> visitedPositions = new HashMap<>();
        Map<Character, Character> dictionay = new HashMap<>();
        int i = 0;

        encryptedString:
        for (Map.Entry<Character, List<Integer>> entry : patternPositions.entrySet()) {
            Character currentChar = encryptedString[beginIndex + i++];
            
            while (dictionay.containsKey(currentChar)) {
                if (i + 1 <= encryptedString.length) {
                    currentChar = encryptedString[beginIndex + i++];
                } else {
                    break encryptedString;
                }
            }
            
            dictionay.put(currentChar, entry.getKey());
            
            //println(currentChar + " " + entry.getKey());

            for (Integer currentPos : entry.getValue()) {
                if (currentChar.equals(encryptedString[currentPos + beginIndex])) {
                    //println("YES");
                    visitedPositions.put(currentPos, Boolean.TRUE);
                } else {
                    //println("NO");
                    resultado.setFoundPattern(false);
                    break encryptedString;
                }
            }
        }

        if (resultado.isFoundPattern() == null) {
            resultado.setFoundPattern(true);
            resultado.setDictionary(dictionay);
            resultado.setPatternBeginIndex(beginIndex);
        }

        return resultado;
    }
    
    private static List<String> getCasesFromInputFile() {
        String result = "";
        List<String> cases = new ArrayList<>();
       
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = bufferedReader.readLine();
            }
            result = sb.toString();
            bufferedReader.close();
        } catch (IOException e) {
            println("No se ha podido leer el archivo de entrada, se usara un texto existente");
            result = TEST_CASE;
        }
        
        String[] tmpSplit = result.split("\n");
        String currentLine = "";
        for (int i = 1; i < tmpSplit.length; i++) {
            if (tmpSplit[i].length() > 0) {
                currentLine += tmpSplit[i];
                currentLine += " ";
            } else {
                if (currentLine.length() > 0) {
                    cases.add(currentLine);
                    currentLine = "";
                }
            }
            if (i == tmpSplit.length - 1 && currentLine.length() > 0) {
                cases.add(currentLine);
            }
        }
        
        return cases;
    }
    
    private static void writeOutput(List<String> decodedCases) {
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8");
            BufferedWriter out = new BufferedWriter(writer);

            for (String currentCase : decodedCases) {
                System.out.println(currentCase);
                out.write(currentCase);
                out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
