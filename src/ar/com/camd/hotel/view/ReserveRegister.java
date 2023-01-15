package ar.com.camd.hotel.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import ar.com.camd.hotel.controller.ReserveController;
import ar.com.camd.hotel.model.PaymentMethod;
import ar.com.camd.hotel.model.Reserve;

@SuppressWarnings("serial")
public class ReserveRegister extends JFrame {

	private static final String MSG_WRONG_DATES = "¡La fecha de salida debe ser mas grande que la fecha de entrada!";
	private static final String IMG_ICON_RESERVAS_PNG = "../img/icon-reservas.png";
	private static final String IMG_A_H_40PX_PNG = "../img/aH-40px.png";
	private static final String IMG_RESERVAS_IMG_3_PNG = "../img/reservas-img-3.png";
	private static final String IMG_HA_100PX_PNG = "../img/Ha-100px.png";

	private ReserveController reserveController;

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblValorSimbolo;
	private JLabel labelAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveRegister frame = new ReserveRegister();
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
	public ReserveRegister() {
		super("Reserva");
		this.reserveController = new ReserveController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(ReserveRegister.class.getResource(IMG_A_H_40PX_PNG)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaE.getCalendarButton()
				.setIcon(new ImageIcon(ReserveRegister.class.getResource(IMG_ICON_RESERVAS_PNG)));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(SystemColor.window));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaE);

		// Gets the check-in date.
		txtFechaE.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date dateOut = txtFechaS.getDate();
					JDateChooser aDateChooser = (JDateChooser) evt.getSource();
					Date dateIn = aDateChooser.getDate();
					calculateReservationValue(dateIn, dateOut);
				}
			}
		});

		lblValorSimbolo = new JLabel("$");
		lblValorSimbolo.setVisible(false);
		lblValorSimbolo.setBounds(68, 332, 17, 25);
		lblValorSimbolo.setForeground(SystemColor.textHighlight);
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

		panel.add(lblValorSimbolo);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		txtFechaS = new JDateChooser();
		txtFechaS.getCalendarButton()
				.setIcon(new ImageIcon(ReserveRegister.class.getResource(IMG_ICON_RESERVAS_PNG)));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaS);

		// Gets the check-out date.
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					JDateChooser dateChooser = (JDateChooser) evt.getSource();
					Date dateOut = dateChooser.getDate();
					Date dateIn = txtFechaE.getDate();
					calculateReservationValue(dateIn, dateOut);
				}
			}
		});

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(82, 328, 275, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(BorderFactory.createEmptyBorder());
		txtValor.setColumns(10);
		panel.add(txtValor);

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		txtFormaPago = new JComboBox<>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));

		String[] options = new String[] {
				PaymentMethod.CASH.getDescription(), 
				PaymentMethod.DEBIT.getDescription(), 
				PaymentMethod.CREDIT.getDescription()
				};

		txtFormaPago.setModel(new DefaultComboBoxModel<String>(options));
		txtFormaPago.setSelectedIndex(0);
		panel.add(txtFormaPago);

		txtFormaPago.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> cb = (JComboBox<String>)event.getSource();
				String item = (String)cb.getSelectedItem();
				System.out.println(item);
			}
		});

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel1 = new JPanel();
		panel1.setBounds(428, 0, 482, 560);
		panel1.setBackground(new Color(12, 138, 199));
		panel.add(panel1);
		panel1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel1.add(logo);
		logo.setIcon(new ImageIcon(ReserveRegister.class.getResource(IMG_HA_100PX_PNG)));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReserveRegister.class.getResource(IMG_RESERVAS_IMG_3_PNG)));

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Date dateOut = txtFechaS.getDate();
				Date dateIn = txtFechaE.getDate();
				String sPaymentMethod = (String)txtFormaPago.getSelectedItem();
				String sReserveValue = txtValor.getText();
				
				// If all fields are completed go to guest view.
				if (dateIn != null && dateOut != null &&
					sPaymentMethod != null && sReserveValue != null) {

					LocalDate checkinDate = LocalDate.ofInstant(dateIn.toInstant(), ZoneId.systemDefault());
					LocalDate checkoutDate = LocalDate.ofInstant(dateOut.toInstant(), ZoneId.systemDefault());
					BigDecimal reserveValue = new BigDecimal(sReserveValue);
					PaymentMethod paymentMethod = PaymentMethod.findByDescription(sPaymentMethod);
					Reserve reserve = new Reserve(checkinDate, checkoutDate, reserveValue, paymentMethod);
					reserve = reserveController.save(reserve);
					System.out.println(reserve);
					
					dispose(); // Close the current view.
					GuestRegister registro = new GuestRegister(reserve); // Go to guest register view.
					registro.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Debes completar todos los campos.");
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(lblSiguiente);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	/**
	 * Show message wrong dates.
	 */
	private void showMsgWrongDates() {
		JOptionPane.showMessageDialog(this, MSG_WRONG_DATES, "Fecha incorrecta",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Calculates the reservation value.
	 * @param dateIn The check in date.
	 * @param dateOut The check out date.
	 */
	private void calculateReservationValue(Date dateIn, Date dateOut ) {
		if (dateOut != null && dateIn != null) {
			LocalDate checkinDate = LocalDate.ofInstant(dateIn.toInstant(), ZoneId.systemDefault());
			LocalDate checkoutDate = LocalDate.ofInstant(dateOut.toInstant(), ZoneId.systemDefault());
			System.out.printf("checkinDate<%s>, checkoutDate<%s>%n", checkinDate, checkoutDate);

			BigDecimal reserveValue = reserveController.calculateValue(checkinDate, checkoutDate);
			if (reserveValue == null) {
				showMsgWrongDates();
				txtFechaE.setDate(null);
				txtFechaS.setDate(null);
				return;
			}

			System.out.printf("reserveValue<%s>%n", reserveValue.toString());
			lblValorSimbolo.setVisible(true);
			txtValor.setText(reserveValue.toString());
		} else {
			System.out.println("Check-in date or check-out date is null.");
		}
	}
 }