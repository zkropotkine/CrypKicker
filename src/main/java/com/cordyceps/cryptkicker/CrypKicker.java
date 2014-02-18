package com.cordyceps.cryptkicker;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.nio.charset.StandardCharsets.*;

/**
 * 
 * @author zkropotkine
 */
public class CrypKicker 
{   
    private static String PATTERN = "El veloz murcierlago hindu comia feliz cardillo y kiwi cuando la cigüeña tocaba el saxofon detras del palenque de paja";
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        
        //System.getProperty("file.encoding","UTF-8");
        Scanner scanner = new Scanner(System.in);
       
        
        println("The pattern is: ");
        //String helperText = scanner.nextLine();
        
        
        String helperText = PATTERN;
        println(PATTERN);
        byte[] theByteArray = PATTERN.getBytes("ISO-8859-1"); 
        
         byte ptext[] = PATTERN.getBytes(UTF_8); 
String value = new String(ptext, UTF_8);    

    String unicodeMessage =
    "ñoño";
    
    System.out.println("Ñ");

    PrintStream out = new PrintStream(System.out, true, "ISO-8859-1");
    out.println(unicodeMessage);
                
        Map<Byte, List<Integer>> charsPositions = new LinkedHashMap<Byte, List<Integer>>(helperText.length());
        
        int index = 0;
        for (Byte currentChar : helperText.toLowerCase().getBytes()) {
            List<Integer> positions = null;
            if (! charsPositions.containsKey(currentChar)) {
               positions = new ArrayList();
               charsPositions.put(currentChar, positions);
            } else {
               positions = charsPositions.get(currentChar);
            }
            
            positions.add(index++);
        }
        
        for (Map.Entry entry : charsPositions.entrySet()) {
           println(entry.getKey() +  " " + entry.getValue().toString());
        }
        
        
        
        println("Number of cases to be analyzed: ");
        int numberCases = scanner.nextInt();
        scanner.nextLine();
        
        println("Introduce the cases: ");
        String casesOneString = scanner.nextLine();
        
        println(casesOneString);
        
        String[] cases = casesOneString.split("\n");

        
        for (String currentString : cases) {
            println(currentString);
        }
        
        println("END");
    }
    
    /**
     * 
     * @param text 
     */
    private static void println(String text) {
        System.out.println(text);
    }
}
