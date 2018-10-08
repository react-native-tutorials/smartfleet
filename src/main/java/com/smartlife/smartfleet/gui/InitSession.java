/**
 * ENMA Ltd. Proprietary License
 */
package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

/**
 * 
 * @author Marius-Iulian Grigoras
 *
 * @version 1.0.0
 *
 * @year 2018
 */
public class InitSession extends JFrame {

	protected static final Log logger = LogFactory.getLog(InitSession.class);
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 7859574367982160630L;

	public static final String OK_CMD = "OK";
	public static final String CANCEL_CMD = "CANCEL";
	private JPanel mainPane;
	private JTextField txtUser;
	private JPasswordField pwdPass;
	private JButton btnOk;
	private JButton btnSalir;
	private JPanel pnlCenter;
	private JPanel pnlSouth;

	AbstractRefreshableApplicationContext context;
	SmartFacade facade;
	InitSessionActionListener listener;
	int noTries = 0;
	Usuario usuario;

	/**
	 * Create the frame.
	 */
	public void initUI() {
		facade = (SmartFacade) getContext().getBean("smartFacade");
		listener = new InitSessionActionListener(this);
		setResizable(false);
		setTitle("Init Session");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(377,164);
		center();
		setIconImage(new ImageIcon(InitSession.class.getResource(Constants.ICON_PATH)).getImage());
		setContentPane(getMainPane());
		setState(Frame.NORMAL);
		setVisible(Boolean.TRUE);
	}

	public void center() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}

	public JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			mainPane.setLayout(new BorderLayout(0, 0));
			mainPane.add(getPnlCenter(), BorderLayout.CENTER);
			mainPane.add(getPnlSouth(), BorderLayout.SOUTH);
		}
		return mainPane;
	}

	public JPanel getPnlCenter() {
		if (pnlCenter == null) {
			pnlCenter = new JPanel();
			pnlCenter.setBackground(Color.BLACK);
			pnlCenter.setLayout(null);

			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(41, 30, 60, 20);
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setForeground(Color.WHITE);
			pnlCenter.add(lblUsuario);
			pnlCenter.add(getTxtUser());

			JLabel lblClave = new JLabel("Clave:");
			lblClave.setBounds(41, 61, 60, 20);
			lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClave.setForeground(Color.WHITE);
			pnlCenter.add(lblClave);

			pnlCenter.add(getPwdPass());
		}
		return pnlCenter;
	}

	public JTextField getTxtUser() {
		if (txtUser == null) {
			txtUser = new JTextField();
			final String value = System.getProperty("user.name");
			com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
			logger.info(NTSystem.getName());
			logger.info(NTSystem.getUserSID());
			txtUser.setText(value);
			txtUser.setBounds(109, 30, 247, 26);
			txtUser.setColumns(20);
		}
		return txtUser;
	}

	public JPasswordField getPwdPass() {
		if (pwdPass == null) {
			pwdPass = new JPasswordField();
			pwdPass.setBounds(109, 61, 247, 26);
			pwdPass.setColumns(20);
			pwdPass.setText("123456789");
			pwdPass.setRequestFocusEnabled(Boolean.TRUE);
			pwdPass.addActionListener(e->enterApp());
		}
		return pwdPass;
	}

	public JPanel getPnlSouth() {
		if (pnlSouth == null) {
			pnlSouth = new JPanel();
			pnlSouth.setBackground(Color.BLACK);
			FlowLayout flpnlSouth = (FlowLayout) pnlSouth.getLayout();
			flpnlSouth.setVgap(2);
			flpnlSouth.setHgap(2);
			flpnlSouth.setAlignment(FlowLayout.RIGHT);
			pnlSouth.add(getBtnOk());
			pnlSouth.add(getBtnSalir());
		}
		return pnlSouth;
	}

	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.setActionCommand(OK_CMD);
			btnOk.addActionListener(listener);
		}
		return btnOk;
	}

	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setActionCommand(CANCEL_CMD);
			btnSalir.addActionListener(listener);
		}
		return btnSalir;
	}

	public void exitApp() {
		int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the application?", Constants.MSG_TITLE,
				JOptionPane.YES_NO_OPTION);
		if (JOptionPane.YES_OPTION == value) {
			System.exit(0);
		}
	}

	public void enterApp() {
		final String userName = getTxtUser().getText().trim();
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a valid user name", Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		final String passValue = String.valueOf(getPwdPass().getPassword());
		logger.info(passValue);
		if (passValue.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a valid password", Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		final Usuario user = getFacade().findUserByName(userName);
		final String userPass = user.getUsuPass();
		logger.info("User Pass " + userPass);
		if (userPass.equalsIgnoreCase(passValue)) {
			this.setUsuario(user);
			logger.info("New MainMenu Created");
			SmartMainFrame customDialog = new SmartMainFrame(this);
			customDialog.setVisible(Boolean.TRUE);
			dispose();
		} else {
			noTries++;
			if (noTries == 3) {
				JOptionPane.showMessageDialog(this, "Number of attempts exceeded. Bye!", Constants.MSG_TITLE,
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			JOptionPane.showMessageDialog(this, "Wrong password. Try again!", Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}

	public AbstractRefreshableApplicationContext getContext() {
		return context;
	}

	public void setContext(AbstractRefreshableApplicationContext context) {
		this.context = context;
	}

	public SmartFacade getFacade() {
		return facade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
