package ar.com.camd.hotel.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.camd.hotel.controller.FindController;
import ar.com.camd.hotel.model.Guest;
import ar.com.camd.hotel.model.Nationality;
import ar.com.camd.hotel.model.PaymentMethod;
import ar.com.camd.hotel.model.Reserve;

@SuppressWarnings("serial")
public class FindGuestReservation extends JFrame {

	private final String TITLE_INVALID_PAYMENT_METHOD = 
			"Método de pago no válido";

	private final String MSG_INVALID_PAYMENT_METHOD = 
			String.format("Los valores posibles para le método de pago son: %s, %s o %s.",
					PaymentMethod.CASH.getDescription(), 
					PaymentMethod.DEBIT.getDescription(), 
					PaymentMethod.CREDIT.getDescription());

	private final String MSG_SELECCIONE_UN_ITEM = 
			"Por favor, seleccione un item";

	private FindController findController;

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindGuestReservation frame = new FindGuestReservation();
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
	public FindGuestReservation() {
		this.findController = new FindController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(FindGuestReservation.class.getResource("../img/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(FindGuestReservation.class.getResource("../img/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(FindGuestReservation.class.getResource("../img/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FindGuestReservation.class.getResource("../img/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
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
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

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
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Clean the tables.
				modelo.getDataVector().clear();
				modeloH.getDataVector().clear();

				String searchTerm = txtBuscar.getText();
				System.out.printf("searchTerm<%s>", searchTerm);
				if (isDigit(searchTerm)) {
					Guest guest = findController.getByReserveId(Integer.valueOf(searchTerm));
					fillTable(guest);
				} else {
					List<Guest> guests = findController.getByLastName(searchTerm);
					guests.forEach(guest -> fillTable(guest));
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		btnEditar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbHuespedes.getSelectedRow() != -1) {
					updateGuest();
				} else if (tbReservas.getSelectedRow() != -1) {
					updateReserve();
				} else {
					showMsgSelectItem();
				}
			}
		});

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		btnEliminar.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbHuespedes.getSelectedRow() != -1) {
					removeReserve(tbHuespedes.getSelectedRow());
				} else if (tbReservas.getSelectedRow() != -1) {
					removeReserve();
				} else {
					showMsgSelectItem();
				}
			}
		});
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
	 * Check if the String is a digit.
	 * 
	 * @param txt The text to evaluate.
	 * @return true if it is a digit.
	 */
	private static boolean isDigit(String txt) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(txt);
		return m.matches();
	}

	/**
	 * Removes a selected guest.
	 */
	private void removeGuest() {
		Optional.ofNullable(modelo.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = (Integer) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0);
					Integer guestRemoved = this.findController.removeGuest(id);
					modeloH.removeRow(tbHuespedes.getSelectedRow());

					JOptionPane.showMessageDialog(this, guestRemoved + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM));
	}

	/**
	 * Removes a selected reserve.
	 */
	private void removeReserve() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = (Integer) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
					Integer reservationsRemoved = this.findController.removeReserve(id);
					
					int indexRow = tbReservas.getSelectedRow();
					modelo.removeRow(indexRow);
					modeloH.removeRow(indexRow);

					JOptionPane.showMessageDialog(this, reservationsRemoved + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM));
	}
	
	/**
	 * Removes a selected reserve.
	 * @param rowIndex The index row.
	 */
	private void removeReserve(Integer rowIndex) {
		Optional.ofNullable(modelo.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = (Integer) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6);
					Integer reservationsRemoved = this.findController.removeReserve(id);
					
					int indexRow = tbHuespedes.getSelectedRow();
					modelo.removeRow(indexRow);
					modeloH.removeRow(indexRow);

					JOptionPane.showMessageDialog(this, reservationsRemoved + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM));
	}
	
	/**
	 * Shows message Select an item, please.
	 */
	private void showMsgSelectItem() {
		JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM);
	}

	/**
	 * Updates the guest.
	 */
	protected void updateGuest() {
		Optional.ofNullable(modelo.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {

			Integer id = (Integer) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0);
			String name = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
			String lastName = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
			LocalDate birthDate = LocalDate.parse(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
			Nationality nationality = Nationality.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString());
			String phoneNumber = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();

			Guest guest = new Guest(id, name, lastName, birthDate, nationality, phoneNumber);
			Integer guestUpdated = this.findController.update(guest);

			String msg = String.format("¡%d item actualizado con éxito!", guestUpdated);
			JOptionPane.showMessageDialog(this, msg);

		}, () -> JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM));
	}
	
	/**
	 * Updates the reserve.
	 */
	protected void updateReserve() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {

			Integer id = (Integer) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
			LocalDate checkinDate = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
			LocalDate checkoutDate = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
			BigDecimal value = new BigDecimal(modelo.getValueAt(tbReservas.getSelectedRow(),3).toString()); 
			
			PaymentMethod paymentMethod = PaymentMethod.findByDescription((String)modelo.getValueAt(tbReservas.getSelectedRow(),4));
			if (paymentMethod == null) {
				JOptionPane.showMessageDialog(this, MSG_INVALID_PAYMENT_METHOD, 
						TITLE_INVALID_PAYMENT_METHOD, JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			Reserve reserve = new Reserve(id, checkinDate, checkoutDate, value, paymentMethod);
			Integer guestUpdated = this.findController.update(reserve);

			String msg = String.format("¡%d item actualizado con éxito!", guestUpdated);
			JOptionPane.showMessageDialog(this, msg);

		}, () -> JOptionPane.showMessageDialog(this, MSG_SELECCIONE_UN_ITEM));
	}

	/**
	 * Fills the table with guest and reserve information.
	 * @param guest The guest.
	 */
	private void fillTable(Guest guest) {
		modeloH.addRow(new Object[] { guest.getId(), guest.getName(), guest.getLastname(),
				guest.getBirthdate(), guest.getNationality().getDescription(), guest.getPhoneNumber(),
				guest.getReserve().getId()});

		modelo.addRow(new Object[] { guest.getReserve().getId(), guest.getReserve().getCheckinDate(),
				guest.getReserve().getCheckoutDate(), guest.getReserve().getValue(),
				guest.getReserve().getPaymentMethod().getDescription()});
	}
}