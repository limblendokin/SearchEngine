package com.limblend.searchengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TextFileViewPanel extends JPanel {
    private JTextArea area;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JLabel counter;
    private JButton nextButton;
    private JButton prevButton;
    private JButton selectAllButton;
    private File file;
    private ArrayList<Integer> matches;
    private int curPosition;
    public TextFileViewPanel(File file, ArrayList<Integer> matches){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        buttonPanel = new JPanel();

        this.file = file;
        area = new JTextArea();
        area.setEditable(false);

        scrollPane = new JScrollPane(area);
        this.add(scrollPane);

        counter = new JLabel();
        buttonPanel.add(counter);

        prevButton = new JButton("Previous Match");
        prevButton.addActionListener(actionEvent -> viewPrevMatch());
        buttonPanel.add(prevButton);

        nextButton = new JButton("Next Match");
        nextButton.addActionListener(actionEvent -> viewNextMatch());
        buttonPanel.add(nextButton);

        this.add(buttonPanel);

        this.matches = matches;
        curPosition=0;
        viewTextOnCurrentPosition();

    }
    private void viewNextMatch(){
        if(curPosition < matches.size()-1) {
            curPosition++;
            viewTextOnCurrentPosition();
        }
        else{
            viewErrorDialog("You've reached last match!");
        }
    }
    private void viewPrevMatch(){
        if(curPosition > 0) {
            curPosition--;
            viewTextOnCurrentPosition();
        }
        else{
            viewErrorDialog("You've reached first match!");
        }
    }
    private void viewErrorDialog(String errorMessage){
        JDialog dialog = new JDialog();
        dialog.setSize(200,100);
        dialog.add(new JLabel(errorMessage));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    private void viewTextOnCurrentPosition(){
        try {
            int skip = matches.get(curPosition);
            counter.setText(curPosition+1+"/"+matches.size());
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.skip(skip);
            int curChar;
            char[] text = new char[800];
            for (int i = 0; i < 800 && (curChar = br.read()) != -1; i++) {
                text[i] = (char) curChar;
            }
            area.setText(new String(text));
            area.setCaretPosition(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
