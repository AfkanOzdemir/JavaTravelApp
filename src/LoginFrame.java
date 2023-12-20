import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public String userName, password;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	
	// TASARIM KISMI //
	
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 225, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gezio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(74, 90, 69, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A3SOFT");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(84, 133, 64, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(235, 0, 199, 261);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGiri = new JLabel("Giriş");
		lblGiri.setBounds(73, 42, 54, 29);
		lblGiri.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel_1.add(lblGiri);
		
		textField = new JTextField();
		textField.setBounds(59, 102, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(59, 148, 86, 20);
		panel_1.add(passwordField);
		
		JButton btnNewButton = new JButton("Giriş Yap !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				/*BUTONA TIKLANIRSA*/
				userName = textField.getText();
				password = new String(passwordField.getPassword()); // Fix deprecated method call
				
				try {
		            // CnDb sınıfından bir nesne oluştur ve bağlantıyı aç
		            CnDb db = new CnDb();
		            db.connection();
		            Connection conn = db.getConnection();

		            // SQL sorgusunu oluştur
		            String sql = "SELECT * FROM adminler WHERE username = ? AND pass = ?";
		            PreparedStatement statement = conn.prepareStatement(sql);
		            statement.setString(1, userName);
		            statement.setString(2, password);

		            // Sorguyu çalıştır ve sonuçları al
		            ResultSet results = statement.executeQuery();

		            // Eğer sonuçlar varsa, kullanıcı adı ve şifre doğru
		            if (results.next()) {
		                // Kullanıcı adı ve şifre doğru
		            	dispose();
		            	MainFrame mf = new MainFrame();
		            	mf.show();
		            } else {
		                // Kullanıcı adı veya şifre yanlış
		                JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre yanlış!","Message",JOptionPane.ERROR_MESSAGE);
		            }

		            // Kaynakları temizle
		            results.close();
		            statement.close();
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(56, 208, 89, 23);
		panel_1.add(btnNewButton);
	}
}
