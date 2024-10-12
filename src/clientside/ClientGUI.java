
package clientside;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
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
    private JPanel buttonPnl, comboPnl, combinedPnl;
    private JComboBox<String> vehicleCb;
    private JButton voteBtn, viewBtn, updateBtn, addCarBtn;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private boolean haveVoted = false;

    public ClientGUI() {
        
        Color sageGreen = new Color(188, 184, 138);
        
        frame = new JFrame("Car of the Year");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(sageGreen);
        
        String[] vehicles = {"Suzuki Fronx", "Toyota Urban Cruiser", "BMW X1", "Mercedes-Benz GLC", "Volkswagen Amarok"};
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
        
        //adding a new car
        JPanel addCarPnl = new JPanel();
        addCarPnl.setLayout(new FlowLayout (FlowLayout.CENTER));// centers everything in pnl
        JLabel addCarLbl = new JLabel("Would you like to add a car?");
        addCarBtn = new JButton("Add Car");
        
        addCarPnl.add(addCarLbl);
        addCarPnl.add(addCarBtn);
        
        //to add the pnl below the table 
        frame.add(addCarPnl, BorderLayout.SOUTH);

        
        buttonPnl = new JPanel();
        voteBtn = new JButton("Vote");
        updateBtn = new JButton("Update");
        viewBtn = new JButton("View");
        
        buttonPnl.add(voteBtn);
        buttonPnl.add(updateBtn);
        buttonPnl.add(viewBtn);
        
        //to combine the btn and addcar pnl on one pnl
        combinedPnl = new JPanel();
        combinedPnl.setLayout(new BorderLayout());
        combinedPnl.add(addCarPnl, BorderLayout.NORTH);
        combinedPnl.add(buttonPnl, BorderLayout.SOUTH);
        
        
        frame.add(combinedPnl, BorderLayout.SOUTH);
        
        // Add vehicles names to table
        for (String vehicle : vehicles) {
            tableModel.addRow(new Object[]{vehicle, 0});
        }

        
        voteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(haveVoted){
                    JOptionPane.showMessageDialog(null, "You may only vote once, sorry!");
                    return; //exits if user already voted
                }
                
                
                String selectedVehicle = (String) vehicleCb.getSelectedItem();
                if (selectedVehicle != null) {
                    JOptionPane.showMessageDialog(null, "Are you sure?");
                    addVote(selectedVehicle);
                    haveVoted = true; //sets to true after voting
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
                displayVote(); 
            }
        });
        
        addCarBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 if(haveVoted){
                    JOptionPane.showMessageDialog(null, "You may only vote once, sorry!");
                    return; //exits if user already voted
                }
                
                String newCar = JOptionPane.showInputDialog("Please enter the car you would like to add!");
                if(newCar != null)
                    if(!newCar.equals("")){
                        vehicleCb.addItem(newCar);
                        haveVoted = true;
                        tableModel.addRow(new Object[]{newCar, 1});
                    }
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
    
    private void displayVote() {
        StringBuilder voteInfo = new StringBuilder("Current Votes:\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String vehicle = (String) tableModel.getValueAt(i, 0);
            int votes = (int) tableModel.getValueAt(i, 1);
            voteInfo.append(vehicle).append(": ").append(votes).append(" votes\n");
        }
        JOptionPane.showMessageDialog(null, voteInfo.toString());
    }
}

