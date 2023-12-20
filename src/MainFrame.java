import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private String ListeleQuery = "SELECT * FROM turlar";
	private String PersonelQuery = "SELECT * FROM personeller";

	private boolean isButtonActive = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JTable table_1 = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	public void populateTable(CnDb connection, String query, JPanel turlarPanel, JTable table_1, String[] colNames) {
		try {
			// Fetch data from database
			connection.stmt = connection.con.createStatement();
			connection.rst = connection.stmt.executeQuery(query);

			// Define table model
			DefaultTableModel model = new DefaultTableModel();
			ResultSetMetaData metaData = connection.rst.getMetaData();

			int cols = metaData.getColumnCount();
			String[] colname = new String[cols];
			for (int i = 0; i < cols; i++) {
				colname[i] = metaData.getColumnName(i + 1);
				model.setColumnIdentifiers(colname);
			}

			// Add rows to the table model
			while (connection.rst.next()) {
				Object[] rowData = new Object[cols];
				for (int i = 0; i < cols; i++) {
					rowData[i] = connection.rst.getString(colNames[i]);
				}
				model.addRow(rowData);
			}

			scrollPane.setViewportView(table_1);

			// Set the table model to table_1
			table_1.setModel(model);

		} catch (Exception e) {
			System.out.println("HATA VERILER CEKILEMEDI.");
		} finally {
			try {
				if (connection.con != null) {
					connection.con.close();
					System.out.println("Fonksiyondaki baglantı kapandı");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 150, 481);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setEnabled(false);
		panel_1.setBounds(160, 0, 524, 481);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel turlarPanel = new JPanel();
		turlarPanel.setBounds(10, 11, 504, 459);
		panel_1.add(turlarPanel);
		turlarPanel.setLayout(null);

		
		textField = new JTextField();
		textField.setBounds(309, 426, 86, 20);
		turlarPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Silinecek Turun Id'si");
		lblNewLabel.setBounds(143, 429, 159, 14);
		turlarPanel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 40, 86, 20);
		turlarPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(106, 40, 86, 20);
		turlarPanel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(202, 40, 86, 20);
		turlarPanel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(298, 40, 86, 20);
		turlarPanel.add(textField_4);

		JLabel lblNewLabel_1 = new JLabel("Tur id");
		lblNewLabel_1.setBounds(10, 15, 86, 14);
		turlarPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Taşıt Id");
		lblNewLabel_1_1.setBounds(106, 15, 86, 14);
		turlarPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Hareket Zamanı");
		lblNewLabel_1_1_1.setBounds(202, 15, 86, 14);
		turlarPanel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Varış Noktası");
		lblNewLabel_1_1_1_1.setBounds(298, 15, 86, 14);
		turlarPanel.add(lblNewLabel_1_1_1_1);
		
		
		
		scrollPane.setBounds(10, 127, 484, 288);
		turlarPanel.add(scrollPane);

		JButton btnNewButton_3_1_1 = new JButton("Ekle");
		JButton btnNewButton_1 = new JButton("Turlar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        lblNewLabel_1.setText("Tur id");
		        lblNewLabel_1_1.setText("Taşıt id");
		        lblNewLabel_1_1_1.setText("Hareket Zamanı");
		        lblNewLabel_1_1_1_1.setText("Varış Noktası");
				for (ActionListener al : btnNewButton_3_1_1.getActionListeners()) {
				    btnNewButton_3_1_1.removeActionListener(al);
				}
				 btnNewButton_3_1_1.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	// EKLE BUTONUNA TIKLANDIGI ZAMAN
							String turid = textField_1.getText();
							String tasitid = textField_2.getText();
							String harakettime = textField_3.getText();
							String destination = textField_4.getText();

							// Database connection
							CnDb connection = new CnDb();
							connection.connection();

							// Create a SQL query
							String query = "INSERT INTO turlar (turid, tasitid, harakettime, destination) VALUES (?, ?, ?, ?)";
							// Use a PreparedStatement to execute your query
							String[] allCols = { "turid", "tasitid", "harakettime", "destination" };
							try {
								PreparedStatement pstmt = connection.con.prepareStatement(query);
								pstmt.setString(1, turid); // set the data in the query
								pstmt.setString(2, tasitid);
								pstmt.setString(3, harakettime);
								pstmt.setString(4, destination);
								pstmt.executeUpdate(); // execute the query
							} catch (SQLException e2) {
								e2.printStackTrace();
							} finally {
								populateTable(connection, ListeleQuery, turlarPanel, table_1, allCols);
							}
			            	
			            }
			        });
				
				
				try {
					CnDb connection = new CnDb();
					connection.connection();
					String[] allCols = { "turid", "tasitid", "harakettime", "destination" };
					populateTable(connection, ListeleQuery, turlarPanel, table_1, allCols);
				} catch (Exception e1) {
					System.out.println("HATA VERILER CEKILEMEDI.");
					// TODO: handle exception
				}
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Personeller");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        lblNewLabel_1.setText("Personel id");
		        lblNewLabel_1_1.setText("Adı");
		        lblNewLabel_1_1_1.setText("Soyadı");
		        lblNewLabel_1_1_1_1.setText("Maaşı");
				for (ActionListener al : btnNewButton_3_1_1.getActionListeners()) {
				    btnNewButton_3_1_1.removeActionListener(al);
				}
		        btnNewButton_3_1_1.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	System.out.println("Personeller ekle butona tıkaldbı");

						// EKLE BUTONUNA TIKLANDIGI ZAMAN
						String personelid = textField_1.getText();
						String personelname = textField_2.getText();
						String personelsurname = textField_3.getText();
						String personelsalary = textField_4.getText();

						// Database connection
						CnDb connection = new CnDb();
						connection.connection();

						// Create a SQL query
						String query = "INSERT INTO personeller (personelid, personelname, personelsurname, personelsalary) VALUES (?, ?, ?, ?)";
						// Use a PreparedStatement to execute your query
						String[] allCols = { "personelid", "personelname", "personelsurname", "personelsalary",
								"personelbirthdate", "personeljoindate" };
						try {
							System.out.println("Personeller ekle TRY'A GIRDI");
							PreparedStatement pstmt = connection.con.prepareStatement(query);
							pstmt.setString(1, personelid); // set the data in the query
							pstmt.setString(2, personelname);
							pstmt.setString(3, personelsurname);
							pstmt.setString(4, personelsalary);
							System.out.println("SALARY BITIRDI");
							// pstmt.setString(5, "55");
							// pstmt.setString(6, "55");
							pstmt.executeUpdate(); // execute the query
							System.out.println("TRY BITIRDI");

						} catch (SQLException e2) {
							System.out.println("CATCH'E GIRDI");

							e2.printStackTrace();
						} finally {
							populateTable(connection, PersonelQuery, turlarPanel, table_1, allCols);
							System.out.println("FINALLY GIRDI");
						}
		            }
		        });
				try {
					CnDb connection = new CnDb();
					connection.connection();
					String[] allCols = { "personelid", "personelname", "personelsurname", "personelsalary",
							"personelbirthdate", "personeljoindate" };
					populateTable(connection, PersonelQuery, turlarPanel, table_1, allCols);
				} catch (Exception e1) {
					System.out.println("HATA VERILER CEKILEMEDI.");
					// TODO: handle exception
				}
			}
		});
		panel.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Müşteriler");
		panel.add(btnNewButton);

		JButton btnBiletler_1 = new JButton("Biletler");
		panel.add(btnBiletler_1);

		JButton btnBiletler_2 = new JButton("Taşıtlar");
		panel.add(btnBiletler_2);

		JButton btnBiletler = new JButton("Çıkış");
		panel.add(btnBiletler);

		JButton btnNewButton_3_1 = new JButton("Sil");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String turid = textField.getText();

				// Database connection
				CnDb connection = new CnDb();
				connection.connection();
				// Create a SQL query
				String query = "delete from turlar where turid = ?";
				String[] allCols = { "turid", "tasitid", "harakettime", "destination" };
				try {
					PreparedStatement pstmt = connection.con.prepareStatement(query);
					pstmt.setString(1, turid); // set the data in the query
					pstmt.executeUpdate(); // execute the query
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					System.out.println("Finally'e geldi");
					// Create a JTable
					// populateTable(connection, ListeleQuery, turlarPanel, table_1 ,allCols);
				}
			}
		});
		btnNewButton_3_1.setBounds(405, 425, 89, 23);
		turlarPanel.add(btnNewButton_3_1);
		
			btnNewButton_3_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// EKLE BUTONUNA TIKLANDIGI ZAMAN
					String turid = textField_1.getText();
					String tasitid = textField_2.getText();
					String harakettime = textField_3.getText();
					String destination = textField_4.getText();

					// Database connection
					CnDb connection = new CnDb();
					connection.connection();

					// Create a SQL query
					String query = "INSERT INTO turlar (turid, tasitid, harakettime, destination) VALUES (?, ?, ?, ?)";
					// Use a PreparedStatement to execute your query
					String[] allCols = { "turid", "tasitid", "harakettime", "destination" };
					try {
						PreparedStatement pstmt = connection.con.prepareStatement(query);
						pstmt.setString(1, turid); // set the data in the query
						pstmt.setString(2, tasitid);
						pstmt.setString(3, harakettime);
						pstmt.setString(4, destination);
						pstmt.executeUpdate(); // execute the query
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						populateTable(connection, ListeleQuery, turlarPanel, table_1, allCols);
					}
				}
			});
		btnNewButton_3_1_1.setBounds(394, 39, 100, 23);
		turlarPanel.add(btnNewButton_3_1_1);



		try {
			CnDb connection = new CnDb();
			connection.connection();
			String[] allCols = { "turid", "tasitid", "harakettime", "destination" };
			populateTable(connection, ListeleQuery, turlarPanel, table_1, allCols);
			
			textField_5 = new JTextField();
			textField_5.setVisible(false);
			textField_5.setColumns(10);
			textField_5.setBounds(10, 96, 86, 20);
			turlarPanel.add(textField_5);
			
			JLabel lblNewLabel_1_2 = new JLabel("Tur id");
			lblNewLabel_1_2.setVisible(false);
			lblNewLabel_1_2.setBounds(10, 71, 86, 14);
			turlarPanel.add(lblNewLabel_1_2);
			
			textField_6 = new JTextField();
			textField_6.setVisible(false);
			textField_6.setColumns(10);
			textField_6.setBounds(106, 96, 86, 20);
			turlarPanel.add(textField_6);
			
			JLabel lblNewLabel_1_1_2 = new JLabel("Taşıt Id");
			lblNewLabel_1_1_2.setVisible(false);
			lblNewLabel_1_1_2.setBounds(106, 71, 86, 14);
			turlarPanel.add(lblNewLabel_1_1_2);
			
			textField_7 = new JTextField();
			textField_7.setVisible(false);
			textField_7.setColumns(10);
			textField_7.setBounds(202, 96, 86, 20);
			turlarPanel.add(textField_7);
			
			JLabel lblNewLabel_1_1_1_2 = new JLabel("Hareket Zamanı");
			lblNewLabel_1_1_1_2.setVisible(false);
			lblNewLabel_1_1_1_2.setBounds(202, 71, 86, 14);
			turlarPanel.add(lblNewLabel_1_1_1_2);
			
			textField_8 = new JTextField();
			textField_8.setVisible(false);
			textField_8.setColumns(10);
			textField_8.setBounds(298, 96, 86, 20);
			turlarPanel.add(textField_8);
			
			JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Varış Noktası");
			lblNewLabel_1_1_1_1_1.setVisible(false);
			lblNewLabel_1_1_1_1_1.setBounds(298, 71, 86, 14);
			turlarPanel.add(lblNewLabel_1_1_1_1_1);
		} catch (Exception e) {
			System.out.println("HATA VERILER CEKILEMEDI.");
		}
	}
}
