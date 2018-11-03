package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import java.awt.*;

public class SearchEngineOutputGUI extends JFrame {
    private FoundFilesTreeModel model;
    private String stringToSearch;
    private String extension;

    private JPanel outputPanel;
    private JScrollPane directoryTreeScrollPane;
    private JTree directoryTree;
    private JTabbedPane fileTabbedPane;
    private TextFileViewPanel textFileViewPanel;

    private SearchEngineOutputGUI(FoundFilesTreeModel model, String stringToSearch, String extension){
        super("Search Results");
        this.model = model;
        this.stringToSearch = stringToSearch;
        this.extension = extension;
        this.setResizable(false);
    }
    public static void setupAndView(FoundFilesTreeModel model, String stringToSearch, String extension){
        SearchEngineOutputGUI window = new SearchEngineOutputGUI(model, stringToSearch, extension);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addComponentsToPane(window.getContentPane());
        window.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(d.width/2-window.getSize().width/2, d.height/2-window.getSize().height/2);
        window.setVisible(true);
    }
    private void addComponentsToPane(Container contentPane){
        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1,2,10,10));
        directoryTree = new JTree();
        directoryTree.setModel(model);

        model.addTreeModelListener(new TreeModelListener() {
            @Override
            public void treeNodesChanged(TreeModelEvent treeModelEvent) {}
            @Override
            public void treeNodesInserted(TreeModelEvent treeModelEvent) {
                directoryTree.expandPath(new TreePath(treeModelEvent.getPath()));
            }
            @Override
            public void treeNodesRemoved(TreeModelEvent treeModelEvent) {}
            @Override
            public void treeStructureChanged(TreeModelEvent treeModelEvent) {}
        });


        directoryTreeScrollPane = new JScrollPane(directoryTree);
        directoryTreeScrollPane.setPreferredSize(new Dimension(400,400));

        fileTabbedPane = new JTabbedPane();
        directoryTree.addTreeSelectionListener(new FileSelectionComponent(directoryTree, fileTabbedPane));

        outputPanel.add(directoryTreeScrollPane);
        outputPanel.add(fileTabbedPane);
        contentPane.add(outputPanel);
    }
}
