package com.limblend.searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BoyerMoorePatternFinder implements FilePatternFinder {
    private File file;

    public BoyerMoorePatternFinder() {}

    public LinkedList<Integer> search(File file, String pattern) throws IOException {

        BufferedReader br = Files.newBufferedReader(file.toPath());

        LinkedList<Integer> matches = new LinkedList<>();
        if(pattern.length() == 0){
            return matches;
        }
        int[] badCharactersTable = createBadCharactersJumpTable(pattern);
        int[] suffixesTable = createSuffixesJumpTable(pattern);

        StringBuffer text = new StringBuffer(pattern.length());
        int c;
        for(int i = 0; i<pattern.length() && (c=br.read())!=-1; i++){
            text.append((char)c);
        }
        int i;
        int max;
        while (text.length()>=pattern.length()){

            for(i=pattern.length()-1; i<text.length() && pattern.charAt(i)==text.charAt(i); i--){
                if(i==0){
                    matches.add(i);
                    break;
                }
            }
            max = Math.max(suffixesTable[pattern.length()-1-i], badCharactersTable[pattern.charAt(i)]);
            text.delete(0, max);
            i = 0;
            while(i<max && (c=br.read())!=-1){
                text.append((char)c);
                i++;
            }
        }
        return matches;
    }
    private int[] createBadCharactersJumpTable(String pattern){
        int[] badCharacters = new int[Character.MAX_VALUE];
        Arrays.fill(badCharacters, pattern.length());
        for(int i = 0; i < pattern.length(); i++){
            badCharacters[ pattern.charAt(i) ] = pattern.length() - 1 - i;
        }
        return badCharacters;
    }
    private int[] createSuffixesJumpTable(String pattern){
        int[] suffixes = new int[pattern.length()];
        int lastPrefix = pattern.length();
        for(int i = pattern.length(); i > 0; i--){
            if(isPrefix(pattern, i)){
                lastPrefix = i;
            }
            suffixes[pattern.length() - i] = lastPrefix - i + pattern.length();
        }
        for(int i = 0; i < pattern.length()-1; i++){
            int slen = suffixLength(pattern, i);
            suffixes[slen] = pattern.length() - 1 - i + slen;
        }
        return suffixes;
    }
    private int suffixLength(String pattern, int pos){
        int len = 0;
        for(int i = pos, j = pattern.length() - 1; i >= 0 && pattern.charAt(i) == pattern.charAt(j); --i, --j){
            len++;
        }
        return len;
    }
    private boolean isPrefix(String pattern, int pos){
        for(int i = pos, j = 0; i < pattern.length(); ++i, ++j){
            if(pattern.charAt(i)!=pattern.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
