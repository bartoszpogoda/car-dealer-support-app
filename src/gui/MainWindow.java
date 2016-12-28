package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import gui.controllers.CarManagementController;
import gui.interfaces.CarManagementGUI;
import gui.interfaces.MainWindowGUI;
import java.awt.Font;

public class MainWindow extends JFrame implements CarManagementGUI, MainWindowGUI {

	private JTabbedPane tabbedPane;

	private JPanel carListPanel;
	private JPanel reservationsPanel;
	private JPanel historyPanel;

	private JPanel buttonsPanel;

	private JButton btnSellCar;
	private JButton btnReserveCar;
	private JButton btnAddCar;

	private JTable carTable;
	private JScrollPane carTableScrollPane;

	private JTable historyTable;
	private JScrollPane historyTableScrollPane;

	private JTable reservationTable;
	private JScrollPane reservationTableScrollPane;

	private JScrollPane switchableScrollPane;

	private PanelFilters panelFilters;

	private PanelAddCar panelAddCar;

	public MainWindow() {

		this.setTitle("Komis Samochodowy - Autor: Bartosz Pogoda");

		initGUI();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setSize(new Dimension(800, 560));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void initGUI() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(tabbedPane);

		carListPanel = new JPanel();
		initCarListPanel();
		tabbedPane.addTab("Lista samochodów", carListPanel);

		reservationsPanel = new JPanel();
		initReservationsPanel();
		tabbedPane.addTab("Rezerwacje", reservationsPanel);

		historyPanel = new JPanel();
		initHistoryPanel();
		tabbedPane.addTab("Historia", historyPanel);

		btnAddCar = new JButton("Dodaj samochód");
		btnAddCar.setActionCommand("btnAddCar");
		btnAddCar.setAlignmentX(RIGHT_ALIGNMENT);

		// buttonsPanel.add(Box.createHorizontalStrut(400));
		buttonsPanel.add(btnAddCar);

	}

	private void initCarListPanel() {

		carListPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		// table
		carTable = new JTable();
		carTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		carTableScrollPane = new JScrollPane(carTable);
		carTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		carTable.setFillsViewportHeight(true);
		carTable.setRowHeight(20);
		// carTable.setAutoCreateRowSorter(true);

		carTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (carTable.getSelectedRow() == -1) {
					btnReserveCar.setEnabled(false);
					btnSellCar.setEnabled(false);
				} else {
					btnReserveCar.setEnabled(true);
					btnSellCar.setEnabled(true);
				}

			}

		});

		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		carListPanel.add(carTableScrollPane, c);

		// filters panel
		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.HORIZONTAL;
		switchableScrollPane = new JScrollPane();
		d.gridy = 0;
		d.gridx = 1;
		d.weightx = 1;
		carListPanel.add(switchableScrollPane, d);

		buttonsPanel = new JPanel();
		
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.gridx = 0;
		e.gridy = 1;
		e.gridwidth = 3;
		carListPanel.add(buttonsPanel, e);

		btnSellCar = new JButton("Sprzedaj");
		btnSellCar.setActionCommand("sellCar");
		btnReserveCar = new JButton("Zarezerwuj");
		btnReserveCar.setActionCommand("reserveCar");
		btnReserveCar.setEnabled(false);
		btnSellCar.setEnabled(false);

		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
		buttonsPanel.add(btnSellCar);
		buttonsPanel.add(btnReserveCar);

		Border blackline = BorderFactory.createLineBorder(Color.GRAY);
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(blackline, "Akcje"));

	}

	private void initHistoryPanel() {
		// table
		historyTable = new JTable();
		historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyTableScrollPane = new JScrollPane(historyTable);
		historyTable.setFillsViewportHeight(true);
		historyTableScrollPane.setPreferredSize(new Dimension(700, 400));

		historyPanel.add(historyTableScrollPane);
	}

	private void initReservationsPanel() {
		// table
		reservationTable = new JTable();
		reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservationTableScrollPane = new JScrollPane(reservationTable);
		reservationTable.setFillsViewportHeight(true);
		reservationTableScrollPane.setPreferredSize(new Dimension(700, 400));

		reservationsPanel.add(reservationTableScrollPane);
	}

	public void setPanelFilters(PanelFilters panelFilters) {
		this.panelFilters = panelFilters;
	}

	public void setPanelAddCar(PanelAddCar panelAddCar) {
		this.panelAddCar = panelAddCar;
	}

	public void switchToFilterView() {
		switchableScrollPane.setViewportView(panelFilters);

		Border blackline = BorderFactory.createLineBorder(Color.GRAY);
		switchableScrollPane.setBorder(BorderFactory.createTitledBorder(blackline, "Filtry"));
	}

	public void switchToAddCarView() {
		switchableScrollPane.setViewportView(panelAddCar);

		Border blackline = BorderFactory.createLineBorder(Color.GRAY);
		switchableScrollPane.setBorder(BorderFactory.createTitledBorder(blackline, "Dodawanie samochodu"));
	}

	public void setCarTableModel(AbstractTableModel model) {
		this.carTable.setModel(model);
	}

	public void setHistoryTableModel(AbstractTableModel model) {
		this.historyTable.setModel(model);
	}

	public void setReservationTableModel(AbstractTableModel model) {
		this.reservationTable.setModel(model);
	}

	@Override
	public int getSelectedCarID() {
		return (int) carTable.getModel().getValueAt(carTable.getSelectedRow(), 0);
	}

	@Override
	public void setCarManagementController(CarManagementController controller) {
		this.btnReserveCar.addActionListener(controller);
		this.btnSellCar.addActionListener(controller);
		this.btnAddCar.addActionListener(controller);

	}

	@Override
	public void repaint() {

		if (carTable.getModel().getRowCount() <= 0) {
			btnReserveCar.setEnabled(false);
			btnSellCar.setEnabled(false);
		} else {
			btnReserveCar.setEnabled(true);
			btnSellCar.setEnabled(true);
		}

		carTable.revalidate();

		super.repaint();

	}

	@Override
	public void reportError(String message) {
		JOptionPane.showMessageDialog(this, message, "B³¹d", JOptionPane.ERROR_MESSAGE);

	}

	@Override
	public void update() {
		this.repaint();

	}

	@Override
	public JDialog createModalDialog() {
		return new JDialog(this, true);
	}
}
