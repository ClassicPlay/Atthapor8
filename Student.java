import java.awt.EventQueue;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
public class Student extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtGrade;
	private String calculateGrade(int score) {
	    if (score >= 80) return "A";
	    else if (score >= 70) return "B";
	    else if (score >= 60) return "C";
	    else if (score >= 50) return "D";
	    else return "F";}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1198, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudent = new JLabel("Student Grade");
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudent.setBounds(550, 10, 114, 22);
		contentPane.add(lblStudent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(534, 42, 629, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "SurName", "Grade"
			}
		));
		model = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(86, 58, 45, 13);
		contentPane.add(lblID);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(255, 58, 45, 13);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("SurName");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSurname.setBounds(43, 106, 73, 13);
		contentPane.add(lblSurname);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGrade.setBounds(255, 106, 45, 13);
		contentPane.add(lblGrade);
		
		txtID = new JTextField();
		txtID.setBounds(114, 57, 96, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(310, 57, 149, 19);
		contentPane.add(txtName);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(114, 105, 131, 19);
		contentPane.add(txtSurname);
		
		txtGrade = new JTextField();
		txtGrade.setColumns(10);
		txtGrade.setBounds(310, 105, 96, 19);
		contentPane.add(txtGrade);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
		        String name = txtName.getText().trim();
		        String surname = txtSurname.getText().trim();
		        String scoreText  = txtGrade.getText().trim();
				 try {
			            int score = Integer.parseInt(scoreText);
			            if (score < 0 || score > 100) {
			                JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
			                return;
			            }

			            String grade = calculateGrade(score);
			            String row[] = { id, name, surname, grade };
			            model.addRow(row);

			            txtID.setText("");
			            txtName.setText("");
			            txtSurname.setText("");
			            txtGrade.setText("");
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
			        }
			}
			
		});
		btnInsert.setBackground(new Color(0, 255, 0));
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsert.setBounds(47, 279, 85, 29);
		contentPane.add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int selectedRow = table.getSelectedRow();
			String id = txtID.getText().trim();
	        String name = txtName.getText().trim();
	        String surname = txtSurname.getText().trim();
	        String grade = txtGrade.getText().trim();
	        
	        model.setValueAt(id, selectedRow, 0);
	        model.setValueAt(name, selectedRow, 1);
	        model.setValueAt(surname, selectedRow, 2);
	        model.setValueAt(grade, selectedRow, 3);
	        
	        txtID.setText("");
	        txtName.setText("");
	        txtSurname.setText("");
	        txtGrade.setText("");
			}
		});
		btnUpdate.setBackground(new Color(0, 255, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(189, 279, 85, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null,  JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        int confirm = JOptionPane.showConfirmDialog(null, JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            model.removeRow(selectedRow); 
		        }
		    }
		});
		btnDelete.setBackground(new Color(201, 1, 6));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(321, 279, 85, 29);
		contentPane.add(btnDelete);
		
		JButton btnSavefile = new JButton("SaveFlie");
		btnSavefile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("เลือกที่เก็บไฟล์");
			        int userSelection = fileChooser.showSaveDialog(null);
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToSave = fileChooser.getSelectedFile();
			            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave + ".txt"))) {
			                for (int i = 0; i < model.getColumnCount(); i++) {
			                    writer.write(model.getColumnName(i) + "\t");
			                }
			                writer.newLine();

			                for (int i = 0; i < model.getRowCount(); i++) {
			                    for (int j = 0; j < model.getColumnCount(); j++) {
			                        writer.write(model.getValueAt(i, j).toString() + "\t");
			                    }
			                    writer.newLine();
			                }
			                JOptionPane.showMessageDialog(null,JOptionPane.INFORMATION_MESSAGE);
			            } catch (IOException ex) {
			                JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
			            }
			        }
			}
		});
		btnSavefile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSavefile.setBackground(Color.GREEN);
		btnSavefile.setBounds(48, 319, 85, 29);
		contentPane.add(btnSavefile);
		
		JButton btnLoadfile = new JButton("LoadFlie");
		btnLoadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("เลือกไฟล์ที่ต้องการโหลด");

		        int userSelection = fileChooser.showOpenDialog(null);

		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToLoad = fileChooser.getSelectedFile();

		            try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad))) {
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.setRowCount(0); 

		                String line;
		                boolean isFirstLine = true;

		                while ((line = reader.readLine()) != null) {
		                    if (isFirstLine) { 
		                        isFirstLine = false; 
		                        continue; 
		                    }

		                    String[] rowData = line.split("\t"); 
		                    model.addRow(rowData);
		                }

		                JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
		            } catch (IOException ex) {
		                JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
		            }
		        }
			}
		});
		btnLoadfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadfile.setBackground(Color.GREEN);
		btnLoadfile.setBounds(189, 318, 85, 29);
		contentPane.add(btnLoadfile);
		
		JButton btnShowchart = new JButton("ShowChart");
		btnShowchart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGradeChart();
			}
		});
		btnShowchart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowchart.setBackground(Color.BLUE);
		btnShowchart.setForeground(new Color(0, 0, 0));
		btnShowchart.setBounds(320, 319, 120, 29);
		contentPane.add(btnShowchart);
	}
	private void showGradeChart() {
	    int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String grade = model.getValueAt(i, 3).toString();
	        switch (grade) {
	            case "A": gradeA++; break;
	            case "B": gradeB++; break;
	            case "C": gradeC++; break;
	            case "D": gradeD++; break;
	            case "F": gradeF++; break;
	        }
	    }
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(gradeA,"", "A");
	    dataset.addValue(gradeB,"", "B");
	    dataset.addValue(gradeC,"", "C");
	    dataset.addValue(gradeD,"", "D");
	    dataset.addValue(gradeF,"", "F");

	    JFreeChart barChart = ChartFactory.createBarChart(
	        "Numgrade",
	        "grade",
	        "Student",
	        dataset
	    );

	      ChartPanel chartPanel = new ChartPanel(barChart);
	      JFrame chartFrame = new JFrame("Student Grade Chart");
	      chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      chartFrame.setSize(600, 400);
	      chartFrame.getContentPane().add(chartPanel);
	      chartFrame.setVisible(true);
	}
}
