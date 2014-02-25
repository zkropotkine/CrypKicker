/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cordyceps.cryptkicker;

import java.util.Map;

/**
 *
 * @author zkropotkine
 */
public class Resultado {
    private Boolean foundPattern = null;
    private int patternBeginIndex = 0;
    private int sizePattern = 0;
    private Map<Character, Character> dictionary = null;
    
    /**
     * 
     */
    public Resultado () {
        // Do nothing
    }
    
    public Boolean isFoundPattern() {
        return foundPattern;
    }

    public void setFoundPattern(boolean foundPattern) {
        this.foundPattern = foundPattern;
    }

    public int getPatternBeginIndex() {
        return patternBeginIndex;
    }

    public void setPatternBeginIndex(int patternBeginIndex) {
        this.patternBeginIndex = patternBeginIndex;
    }

    public int getSizePattern() {
        return sizePattern;
    }

    public void setSizePattern(int sizePattern) {
        this.sizePattern = sizePattern;
    }

    public Map<Character, Character> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Character, Character> dictionary) {
        this.dictionary = dictionary;
    }
}
