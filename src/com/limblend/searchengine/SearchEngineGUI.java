package com.limblend.searchengine;

import javax.swing.*;
import java.awt.*;

public class SearchEngineGUI extends JFrame {
    private FoundFilesTreeModel model;
    public SearchEngineGUI(String name, FoundFilesTreeModel model){
        super(name);
        setResizable(false);
        this.model = model;
    }
    public static void setupAndView(FoundFilesTreeModel model){
        SearchEngineGUI window = new SearchEngineGUI("Search Engine", model);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addComponentsToPane(window.getContentPane());
        window.pack();
        window.setVisible(true);
    }

    private void addComponentsToPane(Container contentPane) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));

        JPanel inputPanel = new JPanel();
        GridLayout inputPanelLayout = new GridLayout(1,3,10,10);
        inputPanel.setLayout(inputPanelLayout);

        JPanel outputPanel = new JPanel();
        GridLayout outputLayout = new GridLayout(1,2,10,10);
        outputPanel.setLayout(outputLayout);

        JPanel textFieldsInputPanel = new JPanel();
        GridLayout textFieldsInputPanelLayout = new GridLayout(2,3,10,10);
        textFieldsInputPanel.setLayout(textFieldsInputPanelLayout);

        JLabel selectedDirLabel = new JLabel("No dir selected");
        JButton chooseDirButton = new JButton("Choose directory...");
        chooseDirButton.addActionListener(new ChooseRootDirComponent(model, selectedDirLabel));
        // choose dir button listetner
        JLabel extensionLabel = new JLabel("Extension:");
        JTextField extensionTextField = new JTextField();
        JLabel queryLabel = new JLabel("Search Query:");
        JTextField queryTextField = new JTextField();
        JButton searchButton = new JButton("Search");

        queryTextField.setText("private");
        extensionTextField.setText("java");

        textFieldsInputPanel.add(chooseDirButton);
        textFieldsInputPanel.add(selectedDirLabel);
        textFieldsInputPanel.add(extensionLabel);
        textFieldsInputPanel.add(extensionTextField);
        textFieldsInputPanel.add(queryLabel);
        textFieldsInputPanel.add(queryTextField);

        inputPanel.add(chooseDirButton);
        inputPanel.add(textFieldsInputPanel);
        inputPanel.add(searchButton);

        JTree directoryTree = new JTree();
        directoryTree.setModel(model);

        searchButton.addActionListener(new SearchComponent(model, extensionTextField, queryTextField, directoryTree));

        JScrollPane directoryTreeScrollPane = new JScrollPane(directoryTree);
        directoryTreeScrollPane.setPreferredSize(new Dimension(400,400));

        JTabbedPane fileTabbedPane = new JTabbedPane();
        directoryTree.addTreeSelectionListener(new FileSelectionComponent(directoryTree, fileTabbedPane));

        outputPanel.add(directoryTreeScrollPane);
        outputPanel.add(fileTabbedPane);

        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);

        contentPane.add(mainPanel);
    }
}
