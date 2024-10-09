
package clientside;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class ClientGUI {
    
    private JFrame frame;
    private JPanel buttonPnl, comboPnl;
    private JComboBox<String> vehicleCb;
    private JButton voteBtn, viewBtn, updateBtn;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public ClientGUI() {
        
        Color sageGreen = new Color(188, 184, 138);
        
        frame = new JFrame("Car of the Year");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(sageGreen);
        
        String[] vehicles = {"Car 1", "Car 2", "Car 3", "Car 4", "Car 5"};
        vehicleCb = new JComboBox<>(vehicles);
        comboPnl = new JPanel();
        comboPnl.add(new JLabel("Please select a vehicle: "));
        comboPnl.add(vehicleCb);
        frame.add(comboPnl, BorderLayout.NORTH);

        
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout()); 
        JLabel tableLabel = new JLabel("2024 CAR OF THE YEAR");
        tablePanel.add(tableLabel, BorderLayout.NORTH); 

        // table
        String[] columnName = {"Vehicle", "Votes"};
        tableModel = new DefaultTableModel(columnName, 0);
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        
        
        tablePanel.add(scrollPane, BorderLayout.CENTER); 

        
        frame.add(tablePanel, BorderLayout.CENTER); 

        
        buttonPnl = new JPanel();
        voteBtn = new JButton("Vote");
        updateBtn = new JButton("Update");
        viewBtn = new JButton("View");
        
        buttonPnl.add(voteBtn);
        buttonPnl.add(updateBtn);
        buttonPnl.add(viewBtn);
        frame.add(buttonPnl, BorderLayout.SOUTH);
        
        // Add vehicles names to table
        for (String vehicle : vehicles) {
            tableModel.addRow(new Object[]{vehicle, 0});
        }

        
        voteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVehicle = (String) vehicleCb.getSelectedItem();
                if (selectedVehicle != null) {
                    addVote(selectedVehicle);
                    JOptionPane.showMessageDialog(null, "Your vote has been casted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a vehicle.");
                }
            }
        });
        
        updateBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Votes has been updated!");
            }
            
        });
       
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                displayVote(); 
            }
        });

        frame.setVisible(true);
    }
    
    private void addVote(String vehicle) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(vehicle)) {
                int currentVotes = (int) tableModel.getValueAt(i, 1);
                tableModel.setValueAt(currentVotes + 1, i, 1);
                return;
            }
        }
    }
    
//    private void displayVote() {
//        StringBuilder voteInfo = new StringBuilder("Current Votes:\n");
//        for (int i = 0; i < tableModel.getRowCount(); i++) {
//            String vehicle = (String) tableModel.getValueAt(i, 0);
//            int votes = (int) tableModel.getValueAt(i, 1);
//            voteInfo.append(vehicle).append(": ").append(votes).append(" votes\n");
//        }
//        JOptionPane.showMessageDialog(null, voteInfo.toString());
//    }
}



//public class ClientGUI {
//    
//    private JFrame frame;
//    private JPanel buttonPnl, comboPnl;
//    private JComboBox vehicleCb;
//    private JButton voteBtn, viewBtn;
//    private JTextArea txtArea;
//    private JTable resultTable;
//    private DefaultTableModel tableModel;
//    
//    
//    public ClientGUI(){
//        
//        frame = new JFrame ("Car of the Year");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//        frame.setLayout(new BorderLayout());
//        
//        
//        
//        
//        //combobox
//        String [] vehicles = {"Car 1", "Car 2","Car 3", "Car 4", "Car 5"};
//        vehicleCb = new JComboBox<>(vehicles);
//        comboPnl = new JPanel();
//        comboPnl.add(new JLabel("Please select a vehicle"));
//        comboPnl.add(vehicleCb);
//        frame.add(comboPnl, BorderLayout.NORTH);
//        
//      
//  
////        txtArea = new JTextArea(10,30);
////        txtArea.setEditable(false);
////        frame.add(new JScrollPane(txtArea));
//
//        JLabel tableLabel = new JLabel("Vote for your favourite vehicle: ");
//        frame.add(tableLabel, BorderLayout.CENTER);
//        
//          String [] columnName = {"Vehicle", "Votes"};
//          tableModel = new DefaultTableModel(columnName, 0);
//          resultTable = new JTable(tableModel);
//          JScrollPane scrollPane = new JScrollPane(resultTable);
//          scrollPane.setPreferredSize(new Dimension(350,150));
//          frame.add(scrollPane, BorderLayout.CENTER);
//        
//          
//            buttonPnl = new JPanel();
//            voteBtn = new JButton("Vote");
//            viewBtn = new JButton("View");
//            
//            buttonPnl.add(voteBtn);
//            buttonPnl.add(viewBtn);
//
//            frame.add(buttonPnl, BorderLayout.SOUTH);
//          
//          
//          for(String vehicle : vehicles){
//              tableModel.addRow(new Object[]{vehicle, 0});
//          }
//        
//       voteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedVehicle = (String) vehicleCb.getSelectedItem();
//                if (selectedVehicle != null) {
//                    addVote(selectedVehicle);
//                    JOptionPane.showMessageDialog(null, "Your vote has been casted!");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Please select a vehicle.");
//                }
//            }
//        });
//       
//       viewBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                displayVote(); 
//            }
//        });
//
//      
//     frame.setVisible(true);
//    }
//    
//    
//    private void addVote(String vehicle) {
//        for (int i = 0; i < tableModel.getRowCount(); i++) {
//            if (tableModel.getValueAt(i, 0).equals(vehicle)) {
//                int currentVotes = (int) tableModel.getValueAt(i, 1);
//                tableModel.setValueAt(currentVotes + 1, i, 1);
//                return;
//            }
//        }
//    }
//    
//    private void displayVote() {
//        StringBuilder voteInfo = new StringBuilder("Current Votes:\n");
//        for (int i = 0; i < tableModel.getRowCount(); i++) {
//            String vehicle = (String) tableModel.getValueAt(i, 0);
//            int votes = (int) tableModel.getValueAt(i, 1);
//            voteInfo.append(vehicle).append(": ").append(votes).append(" votes\n");
//        }
//        JOptionPane.showMessageDialog(null, voteInfo.toString());
//    }
//    
   
//        private void addVote(String vehicle) {
//            for (int i = 0; i < tableModel.getRowCount(); i++) {
//                if (tableModel.getValueAt(i, 0).equals(vehicle)) { 
//                    int currentVotes = (int) tableModel.getValueAt(i, 1); 
//                    tableModel.setValueAt(currentVotes + 1, i, 1);
//                    return; 
//                }
//            }
////            
////            tableModel.addRow(new Object[]{vehicle, 1});
//        }
        
        
        
//      private void displayVote(){
////            
//            StringBuilder voteInfo = new StringBuilder("Current Votes: ");
//            for(int i =0; i<tableModel.getRowCount(); i++){
//                String vehicle = (String) tableModel.getValueAt(i, 0);
//                int vote = (int) tableModel.getValueAt(i, 1);
//                voteInfo.append(vehicle).append(": ").append(vote).append("votes");
//                
//            }
//            JOptionPane.showMessageDialog(null, voteInfo.toString());
//            
//        }
////        private void updatedVote(){
////            JOptionPane.showMessageDialog(null, "Votes has been updated!");
////        }
//
//     
//    


