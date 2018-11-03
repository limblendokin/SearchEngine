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

    public ArrayList<Integer> search(File file, String pattern){
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath());
            int curIndex = -1;
            ArrayList<Integer> matches = new ArrayList<>();
            if (pattern.length() == 0) {
                return matches;
            }
            int[] badCharactersTable = createBadCharactersJumpTable(pattern);
            int[] suffixesTable = createSuffixesJumpTable(pattern);
            StringBuffer buffer = new StringBuffer();
            int c = -1;
            while (buffer.length() < pattern.length() && (c = br.read()) != -1) {
                buffer.append((char) c);
                curIndex++;
            }
            boolean endOfFileReached = c==-1;
            int i;
            int readCount;
            int max;
            while (!endOfFileReached) {
                i = pattern.length() - 1;
                while (i >= 0 && buffer.charAt(i) == pattern.charAt(i)) {
                    i--;
                }
                if (i < 0) {
                    i++;
                    matches.add(curIndex - pattern.length() + 1);
                }
                max = Math.max(suffixesTable[i], badCharactersTable[buffer.charAt(i)]);
                readCount = i + max - buffer.length() + 1;
                buffer.delete(0, readCount);
                while (buffer.length() < pattern.length() && (c = br.read()) != -1) {
                    buffer.append((char) c);
                    curIndex++;
                }
                if (c == -1)
                    endOfFileReached = true;
            }
            br.close();
            return matches;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    private int[] createBadCharactersJumpTable(String pattern){
        int[] badCharacters = new int[Character.MAX_VALUE];
        Arrays.fill(badCharacters, pattern.length());
        for(int i = 0; i < pattern.length()-1; i++){
            badCharacters[ pattern.charAt(i) ] = pattern.length() - 1 - i;
        }
        return badCharacters;
    }
    private int[] createSuffixesJumpTable(String pattern){
        int[] suffixes = new int[pattern.length()];
        int lastPrefix = pattern.length()-1;
        for(int i = pattern.length() - 1; i >= 0; i--){
            if(isPrefix(pattern, i+1)){
                lastPrefix = i+1;
            }
            suffixes[i] = lastPrefix - i + pattern.length() - 1;
        }
        for(int i = 0; i < pattern.length()-1; i++){
            int slen = suffixLength(pattern, i);
            if(pattern.charAt(i-slen)!=pattern.charAt(pattern.length() - 1 - slen))
                suffixes[pattern.length()-1-slen] = pattern.length() - 1 - i + slen;
        }
        return suffixes;
    }
    private int suffixLength(String pattern, int pos){
        int len = 0;
        while(pattern.charAt(pos-len) == pattern.charAt(pattern.length()-1-len) && len<pos){
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
