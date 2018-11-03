package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import java.awt.*;

public class SearchEngineInputGUI extends JFrame {
    private FoundFilesTreeModel model;
    public SearchEngineInputGUI(String name, FoundFilesTreeModel model){
        super(name);
        setResizable(false);
        this.model = model;
    }
    public static void setupAndView(FoundFilesTreeModel model){
        SearchEngineInputGUI window = new SearchEngineInputGUI("Search Engine", model);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addComponentsToPane(window.getContentPane());
        window.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(d.width/2-window.getSize().width/2, d.height/2-window.getSize().height/2);
        window.setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {
        JPanel inputPanel = new JPanel();
        GroupLayout panelsLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(panelsLayout);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(panelsLayout);

        JLabel selectedDirLabel = new JLabel("Chosen dir:");
        JTextField selectedDirTextField = new JTextField(((FileMatchesNode)model.getRoot()).getFile().getAbsolutePath());
        JButton chooseDirButton = new JButton("Choose directory...");
        chooseDirButton.addActionListener(new ChooseRootDirComponent(model, selectedDirTextField));
        // choose dir button listetner
        JLabel extensionLabel = new JLabel("Extension:");
        JTextField extensionTextField = new JTextField();
        JLabel queryLabel = new JLabel("Search Query:");
        JTextField queryTextField = new JTextField();
        JButton searchButton = new JButton("Search");

        queryTextField.setText("a");
        extensionTextField.setText("java");

        panelsLayout.setAutoCreateGaps(true);
        panelsLayout.setAutoCreateContainerGaps(true);
        panelsLayout.setHorizontalGroup(
                panelsLayout.createSequentialGroup()
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(selectedDirLabel)
                        .addComponent(extensionLabel)
                        .addComponent(queryLabel)
                )
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(selectedDirTextField)
                        .addComponent(extensionTextField)
                        .addComponent(queryTextField)
                )
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(chooseDirButton)
                        .addComponent(searchButton)
                )
        );
        panelsLayout.setVerticalGroup(
                panelsLayout.createSequentialGroup()
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(selectedDirLabel)
                        .addComponent(selectedDirTextField)
                        .addComponent(chooseDirButton)
                )
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(extensionLabel)
                        .addComponent(extensionTextField)
                )
                .addGroup(
                        panelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(queryLabel)
                        .addComponent(queryTextField)
                        .addComponent(searchButton)
                )
        );

        searchButton.addActionListener(new SearchComponent(selectedDirTextField, extensionTextField, queryTextField));

        contentPane.add(inputPanel);
    }
}
