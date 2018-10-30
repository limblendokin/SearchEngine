package com.limblend.searchengine;

import javax.swing.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class TextFileViewPanel extends JPanel {
    private JTextArea area;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private File file;
    private LinkedList<Integer> matches;
    private Iterator<Integer> iterator;
    public TextFileViewPanel(File file, LinkedList<Integer> matches){
        super();
        this.file = file;
        area = new JTextArea();
        area.setEditable(false);
        area.setColumns(50);
        this.add(area);
        this.matches = matches;
        iterator = matches.iterator();
        viewNextMatch();
    }
    public void viewNextMatch(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int skip = iterator.next();
            br.skip(skip);
            int curChar;
            char[] text = new char[800];
            for(int i = 0; i < 800 && (curChar=br.read())!=-1; i++){
                text[i] = (char)curChar;
            }
            area.setText(new String(text));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
