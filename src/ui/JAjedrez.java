package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.*;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.TransferHandler;

import appExceptions.appException;
import entidades.Jugador;
import entidades.Partida;
import entidades.Pieza;
import entidades.Posicion;
import negocio.ControladorJugarPartida;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JAjedrez extends JComponent {

	private JFrame frame;
	
	private Casilla lblH1;
	private Casilla lblG1;
	private Casilla lblF1;
	private Casilla lblD1;
	private Casilla lblE1;
	private Casilla lblC1;
	private Casilla lblA1;
	private Casilla lblB1;
	private Casilla lblA8;
	private Casilla lblB2;
	private Casilla lblC2;
	private Casilla lblD2;
	private Casilla lblE2;
	private Casilla lblF2;
	private Casilla lblG2;
	private Casilla lblH2;
	private Casilla lblB3;
	private Casilla lblC3;
	private Casilla lblD3;
	private Casilla lblE3;
	private Casilla lblF3;
	private Casilla lblG3;
	private Casilla lblH3;
	private Casilla lblA2;
	private Casilla lblB4;
	private Casilla lblC4;
	private Casilla lblD4;
	private Casilla lblE4;
	private Casilla lblF4;
	private Casilla lblG4;
	private Casilla lblH4;
	private Casilla lblA3;
	private Casilla lblB8;
	private Casilla lblC5;
	private Casilla lblD5;
	private Casilla lblE5;
	private Casilla lblF5;
	private Casilla lblG5;
	private Casilla lblH5;
	private Casilla lblA4;
	private Casilla lblC8;
	private Casilla lblD8;
	private Casilla lblE8;
	private Casilla lblF6;
	private Casilla lblG6;
	private Casilla lblH6;
	private Casilla lblA5;
	private Casilla lblB5;
	private Casilla lblF8;
	private Casilla lblG7;
	private Casilla lblH7;
	private Casilla lblA6;
	private Casilla lblB6;
	private Casilla lblC6;
	private Casilla lblD6;
	private Casilla lblG8;
	private Casilla lblH8;
	private Casilla lblA7;
	private Casilla lblB7;
	private Casilla lblC7;
	private Casilla lblD7;
	private Casilla lblE7;
	private Casilla lblF7;
	private Casilla lblE6;
	
	/* FEDE */
	public static HashMap<String, Casilla> tablero = new HashMap<String, Casilla>();
	public static ControladorJugarPartida controladorJugarPartida = new ControladorJugarPartida();
	private JPanel panelTablero;
	private JTextField textJugadorBlancas;
	private JLabel lblJugadorBlancas;
	private JLabel lblJugadorNegro;
	private JTextField textJugadorNegras;
	private JButton buttonBuscarJugadorNegras;
	private JLabel lblJugadorNegras;
	private JLabel lblTituloColor;
	private JLabel lblColor;
	private JTextField textDNI;
	private JLabel lblTituloDNI;
	private JLabel lblTituloNombre;
	private JTextField textNombre;
	private JTextField textApellido;
	private JLabel lblTituloApellido;
	private JButton buttonAddJugador;
	private JButton buttonBuscarJugadorBlancas = new JButton("BUSCAR");
	private JButton buttonJugar;
	private JLabel lblUnoVsDos;
	private JPanel panelIzq;
	private JPanel panelIzqPartida;
	private JPanel panelIzqCargaJugadores;
	private JLabel labelJB;
	private JLabel labelJN;
	/*----*/
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAjedrez window = new JAjedrez();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JAjedrez() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		panelIzq = new JPanel();
		frame.getContentPane().add(panelIzq);
		panelIzq.setLayout(new CardLayout(0, 0));
		
		panelIzqCargaJugadores = new JPanel();
		panelIzq.add(panelIzqCargaJugadores, "panelCarga");
		panelIzqCargaJugadores.setLayout(null);
		
		textJugadorBlancas = new JTextField();
		textJugadorBlancas.setBounds(10, 37, 323, 20);
		panelIzqCargaJugadores.add(textJugadorBlancas);
		
		textJugadorBlancas.setColumns(10);
		
		buttonBuscarJugadorBlancas = new JButton("BUSCAR");
		buttonBuscarJugadorBlancas.setBounds(343, 37, 89, 23);
		panelIzqCargaJugadores.add(buttonBuscarJugadorBlancas);
		
		JLabel lblJugadorBlanco = new JLabel("Jugador Blanco:");
		lblJugadorBlanco.setBounds(10, 11, 200, 20);
		panelIzqCargaJugadores.add(lblJugadorBlanco);
		lblJugadorBlanco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblJugadorBlancas = new JLabel("");
		lblJugadorBlancas.setBounds(10, 62, 323, 14);
		panelIzqCargaJugadores.add(lblJugadorBlancas);
		
		lblJugadorNegro = new JLabel("Jugador Negro:");
		lblJugadorNegro.setBounds(10, 87, 200, 20);
		panelIzqCargaJugadores.add(lblJugadorNegro);
		lblJugadorNegro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textJugadorNegras = new JTextField();
		textJugadorNegras.setBounds(10, 113, 323, 20);
		panelIzqCargaJugadores.add(textJugadorNegras);
		textJugadorNegras.setColumns(10);
		
		buttonBuscarJugadorNegras = new JButton("BUSCAR");
		buttonBuscarJugadorNegras.setBounds(343, 113, 89, 23);
		panelIzqCargaJugadores.add(buttonBuscarJugadorNegras);
		
		lblJugadorNegras = new JLabel("");
		lblJugadorNegras.setBounds(10, 138, 323, 14);
		panelIzqCargaJugadores.add(lblJugadorNegras);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 300, 422, 2);
		panelIzqCargaJugadores.add(separator);
		
		lblTituloColor = new JLabel("Color:");
		lblTituloColor.setBounds(10, 313, 57, 20);
		panelIzqCargaJugadores.add(lblTituloColor);
		lblTituloColor.setVisible(false);
		lblTituloColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblColor = new JLabel("");
		lblColor.setBounds(70, 313, 263, 20);
		panelIzqCargaJugadores.add(lblColor);
		lblColor.setVisible(false);
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textDNI = new JTextField();
		textDNI.setBounds(70, 344, 263, 20);
		panelIzqCargaJugadores.add(textDNI);
		textDNI.setEditable(false);
		textDNI.setVisible(false);
		textDNI.setColumns(10);
		
		lblTituloDNI = new JLabel("DNI:");
		lblTituloDNI.setBounds(10, 344, 57, 20);
		panelIzqCargaJugadores.add(lblTituloDNI);
		lblTituloDNI.setVisible(false);
		lblTituloDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblTituloNombre = new JLabel("Nombre:");
		lblTituloNombre.setBounds(10, 375, 57, 20);
		panelIzqCargaJugadores.add(lblTituloNombre);
		lblTituloNombre.setVisible(false);
		lblTituloNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textNombre = new JTextField();
		textNombre.setBounds(70, 375, 263, 20);
		panelIzqCargaJugadores.add(textNombre);
		textNombre.setVisible(false);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(70, 406, 263, 20);
		panelIzqCargaJugadores.add(textApellido);
		textApellido.setVisible(false);
		textApellido.setColumns(10);
		
		lblTituloApellido = new JLabel("Apellido:");
		lblTituloApellido.setBounds(10, 406, 57, 20);
		panelIzqCargaJugadores.add(lblTituloApellido);
		lblTituloApellido.setVisible(false);
		lblTituloApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		buttonAddJugador = new JButton("AGREGAR");
		buttonAddJugador.setBounds(343, 437, 89, 23);
		panelIzqCargaJugadores.add(buttonAddJugador);
		
		lblUnoVsDos = new JLabel("VS");
		lblUnoVsDos.setBounds(10, 212, 422, 12);
		panelIzqCargaJugadores.add(lblUnoVsDos);
		lblUnoVsDos.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnoVsDos.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 178, 422, 2);
		panelIzqCargaJugadores.add(separator_1);
		
		buttonJugar = new JButton("JUGAR");
		buttonJugar.setBounds(343, 266, 89, 23);
		panelIzqCargaJugadores.add(buttonJugar);
		buttonJugar.setEnabled(false);
		
		labelJB = new JLabel("");
		labelJB.setHorizontalAlignment(SwingConstants.CENTER);
		labelJB.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelJB.setBounds(10, 191, 422, 20);
		panelIzqCargaJugadores.add(labelJB);
		
		labelJN = new JLabel("");
		labelJN.setHorizontalAlignment(SwingConstants.CENTER);
		labelJN.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelJN.setBounds(10, 235, 422, 20);
		panelIzqCargaJugadores.add(labelJN);
		
		panelIzqPartida = new JPanel();
		panelIzq.add(panelIzqPartida, "panelPartida");
		panelIzqPartida.setLayout(null);
		
		JButton buttonNuevaPartida = new JButton("Nueva Partida");
		
		buttonNuevaPartida.setBounds(10, 418, 427, 42);
		panelIzqPartida.add(buttonNuevaPartida);
		buttonAddJugador.setVisible(false);
		panelTablero = new JPanel();
		frame.getContentPane().add(panelTablero);
		panelTablero.setLayout(new GridLayout(8, 8, 0, 0));
		
		lblA8 = new Casilla("","A8");
		panelTablero.add(lblA8);
		lblA8.setBackground(Color.WHITE);
		lblA8.setHorizontalAlignment(SwingConstants.CENTER);
		lblA8.setOpaque(true);
		tablero.put("A8",lblA8);
		
		lblB8 = new Casilla("","B8");
		panelTablero.add(lblB8);
		lblB8.setHorizontalAlignment(SwingConstants.CENTER);
		lblB8.setOpaque(true);
		lblB8.setForeground(Color.BLACK);
		lblB8.setBackground(Color.LIGHT_GRAY);
		tablero.put("B8",lblB8);
		
		lblC8 = new Casilla("","C8");
		panelTablero.add(lblC8);
		lblC8.setBackground(Color.WHITE);
		lblC8.setHorizontalAlignment(SwingConstants.CENTER);
		lblC8.setOpaque(true);
		tablero.put("C8",lblC8);
		
		lblD8 = new Casilla("","D8");
		panelTablero.add(lblD8);
		lblD8.setHorizontalAlignment(SwingConstants.CENTER);
		lblD8.setOpaque(true);
		lblD8.setForeground(Color.BLACK);
		lblD8.setBackground(Color.LIGHT_GRAY);
		tablero.put("D8",lblD8);
		
		lblE8 = new Casilla("","E8");
		panelTablero.add(lblE8);
		lblE8.setBackground(Color.WHITE);
		lblE8.setHorizontalAlignment(SwingConstants.CENTER);
		lblE8.setOpaque(true);
		tablero.put("E8",lblE8);
		
		lblF8 = new Casilla("","F8");
		panelTablero.add(lblF8);
		lblF8.setHorizontalAlignment(SwingConstants.CENTER);
		lblF8.setOpaque(true);
		lblF8.setForeground(Color.BLACK);
		lblF8.setBackground(Color.LIGHT_GRAY);
		tablero.put("F8",lblF8);
		
		lblG8 = new Casilla("","G8");
		panelTablero.add(lblG8);
		lblG8.setBackground(Color.WHITE);
		lblG8.setHorizontalAlignment(SwingConstants.CENTER);
		lblG8.setOpaque(true);
		tablero.put("G8",lblG8);
		
		lblH8 = new Casilla("","H8");
		panelTablero.add(lblH8);
		lblH8.setHorizontalAlignment(SwingConstants.CENTER);
		lblH8.setOpaque(true);
		lblH8.setForeground(Color.BLACK);
		lblH8.setBackground(Color.LIGHT_GRAY);
		tablero.put("H8",lblH8);
		
		lblA7 = new Casilla("","A7");
		panelTablero.add(lblA7);
		lblA7.setHorizontalAlignment(SwingConstants.CENTER);
		lblA7.setOpaque(true);
		lblA7.setForeground(Color.BLACK);
		lblA7.setBackground(Color.LIGHT_GRAY);
		tablero.put("A7",lblA7);
		
		lblB7 = new Casilla("","B7");
		panelTablero.add(lblB7);
		lblB7.setBackground(Color.WHITE);
		lblB7.setHorizontalAlignment(SwingConstants.CENTER);
		lblB7.setOpaque(true);
		tablero.put("B7",lblB7);
		
		lblC7 = new Casilla("","C7");
		panelTablero.add(lblC7);
		lblC7.setHorizontalAlignment(SwingConstants.CENTER);
		lblC7.setOpaque(true);
		lblC7.setForeground(Color.BLACK);
		lblC7.setBackground(Color.LIGHT_GRAY);
		tablero.put("C7",lblC7);
		
		lblD7 = new Casilla("","D7");
		panelTablero.add(lblD7);
		lblD7.setBackground(Color.WHITE);
		lblD7.setHorizontalAlignment(SwingConstants.CENTER);
		lblD7.setOpaque(true);
		tablero.put("D7",lblD7);
		
		lblE7 = new Casilla("","E7");
		panelTablero.add(lblE7);
		lblE7.setHorizontalAlignment(SwingConstants.CENTER);
		lblE7.setOpaque(true);
		lblE7.setForeground(Color.BLACK);
		lblE7.setBackground(Color.LIGHT_GRAY);
		tablero.put("E7",lblE7);
		
		lblF7 = new Casilla("","F7");
		panelTablero.add(lblF7);
		lblF7.setBackground(Color.WHITE);
		lblF7.setHorizontalAlignment(SwingConstants.CENTER);
		lblF7.setOpaque(true);
		tablero.put("F7",lblF7);
		
		lblG7 = new Casilla("","G7");
		panelTablero.add(lblG7);
		lblG7.setHorizontalAlignment(SwingConstants.CENTER);
		lblG7.setOpaque(true);
		lblG7.setForeground(Color.BLACK);
		lblG7.setBackground(Color.LIGHT_GRAY);
		tablero.put("G7",lblG7);
		
		lblH7 = new Casilla("","H7");
		panelTablero.add(lblH7);
		lblH7.setBackground(Color.WHITE);
		lblH7.setHorizontalAlignment(SwingConstants.CENTER);
		lblH7.setOpaque(true);
		tablero.put("H7",lblH7);
		
		lblA6 = new Casilla("","A6");
		panelTablero.add(lblA6);
		lblA6.setBackground(Color.WHITE);
		lblA6.setHorizontalAlignment(SwingConstants.CENTER);
		lblA6.setOpaque(true);
		tablero.put("A6",lblA6);
		
		lblB6 = new Casilla("","B6");
		panelTablero.add(lblB6);
		lblB6.setHorizontalAlignment(SwingConstants.CENTER);
		lblB6.setOpaque(true);
		lblB6.setForeground(Color.BLACK);
		lblB6.setBackground(Color.LIGHT_GRAY);
		tablero.put("B6",lblB6);
		
		lblC6 = new Casilla("","C6");
		panelTablero.add(lblC6);
		lblC6.setBackground(Color.WHITE);
		lblC6.setHorizontalAlignment(SwingConstants.CENTER);
		lblC6.setOpaque(true);
		tablero.put("C6",lblC6);
		
		lblD6 = new Casilla("","D6");
		panelTablero.add(lblD6);
		lblD6.setHorizontalAlignment(SwingConstants.CENTER);
		lblD6.setOpaque(true);
		lblD6.setForeground(Color.BLACK);
		lblD6.setBackground(Color.LIGHT_GRAY);
		tablero.put("D6",lblD6);
		
		lblE6 = new Casilla("","E6");
		panelTablero.add(lblE6);
		lblE6.setBackground(Color.WHITE);
		lblE6.setHorizontalAlignment(SwingConstants.CENTER);
		lblE6.setOpaque(true);
		tablero.put("E6",lblE6);
		
		lblF6 = new Casilla("","F6");
		panelTablero.add(lblF6);
		lblF6.setHorizontalAlignment(SwingConstants.CENTER);
		lblF6.setOpaque(true);
		lblF6.setForeground(Color.BLACK);
		lblF6.setBackground(Color.LIGHT_GRAY);
		tablero.put("F6",lblF6);
		
		lblG6 = new Casilla("","G6");
		panelTablero.add(lblG6);
		lblG6.setBackground(Color.WHITE);
		lblG6.setHorizontalAlignment(SwingConstants.CENTER);
		lblG6.setOpaque(true);
		tablero.put("G6",lblG6);
		
		lblH6 = new Casilla("","H6");
		panelTablero.add(lblH6);
		lblH6.setHorizontalAlignment(SwingConstants.CENTER);
		lblH6.setOpaque(true);
		lblH6.setForeground(Color.BLACK);
		lblH6.setBackground(Color.LIGHT_GRAY);
		tablero.put("H6",lblH6);
		
		lblA5 = new Casilla("","A5");
		panelTablero.add(lblA5);
		lblA5.setHorizontalAlignment(SwingConstants.CENTER);
		lblA5.setOpaque(true);
		lblA5.setForeground(Color.BLACK);
		lblA5.setBackground(Color.LIGHT_GRAY);
		tablero.put("A5",lblA5);
		
		lblB5 = new Casilla("","B5");
		panelTablero.add(lblB5);
		lblB5.setBackground(Color.WHITE);
		lblB5.setHorizontalAlignment(SwingConstants.CENTER);
		lblB5.setOpaque(true);
		tablero.put("B5",lblB5);
		
		lblC5 = new Casilla("","C5");
		panelTablero.add(lblC5);
		lblC5.setHorizontalAlignment(SwingConstants.CENTER);
		lblC5.setOpaque(true);
		lblC5.setForeground(Color.BLACK);
		lblC5.setBackground(Color.LIGHT_GRAY);
		tablero.put("C5",lblC5);
		
		lblD5 = new Casilla("","D5");
		panelTablero.add(lblD5);
		lblD5.setBackground(Color.WHITE);
		lblD5.setHorizontalAlignment(SwingConstants.CENTER);
		lblD5.setOpaque(true);
		tablero.put("D5",lblD5);
		
		lblE5 = new Casilla("","E5");
		panelTablero.add(lblE5);
		lblE5.setHorizontalAlignment(SwingConstants.CENTER);
		lblE5.setOpaque(true);
		lblE5.setForeground(Color.BLACK);
		lblE5.setBackground(Color.LIGHT_GRAY);
		tablero.put("E5",lblE5);
		
		lblF5 = new Casilla("","F5");
		panelTablero.add(lblF5);
		lblF5.setBackground(Color.WHITE);
		lblF5.setHorizontalAlignment(SwingConstants.CENTER);
		lblF5.setOpaque(true);
		tablero.put("F5",lblF5);
		
		lblG5 = new Casilla("","G5");
		panelTablero.add(lblG5);
		lblG5.setHorizontalAlignment(SwingConstants.CENTER);
		lblG5.setOpaque(true);
		lblG5.setForeground(Color.BLACK);
		lblG5.setBackground(Color.LIGHT_GRAY);
		tablero.put("G5",lblG5);
		
		lblH5 = new Casilla("","H5");
		panelTablero.add(lblH5);
		lblH5.setBackground(Color.WHITE);
		lblH5.setHorizontalAlignment(SwingConstants.CENTER);
		lblH5.setOpaque(true);
		tablero.put("H5",lblH5);
		
		lblA4 = new Casilla("","A4");
		panelTablero.add(lblA4);
		lblA4.setBackground(Color.WHITE);
		lblA4.setHorizontalAlignment(SwingConstants.CENTER);
		lblA4.setOpaque(true);
		tablero.put("A4",lblA4);
		
		lblB4 = new Casilla("","B4");
		panelTablero.add(lblB4);
		lblB4.setHorizontalAlignment(SwingConstants.CENTER);
		lblB4.setOpaque(true);
		lblB4.setForeground(Color.BLACK);
		lblB4.setBackground(Color.LIGHT_GRAY);
		tablero.put("B4",lblB4);
		
		lblC4 = new Casilla("","C4");
		panelTablero.add(lblC4);
		lblC4.setBackground(Color.WHITE);
		lblC4.setHorizontalAlignment(SwingConstants.CENTER);
		lblC4.setOpaque(true);
		tablero.put("C4",lblC4);
		
		lblD4 = new Casilla("","D4");
		panelTablero.add(lblD4);
		lblD4.setHorizontalAlignment(SwingConstants.CENTER);
		lblD4.setOpaque(true);
		lblD4.setForeground(Color.BLACK);
		lblD4.setBackground(Color.LIGHT_GRAY);
		tablero.put("D4",lblD4);
		
		lblE4 = new Casilla("","E4");
		panelTablero.add(lblE4);
		lblE4.setBackground(Color.WHITE);
		lblE4.setHorizontalAlignment(SwingConstants.CENTER);
		lblE4.setOpaque(true);
		tablero.put("E4",lblE4);
		
		lblF4 = new Casilla("","F4");
		panelTablero.add(lblF4);
		lblF4.setHorizontalAlignment(SwingConstants.CENTER);
		lblF4.setOpaque(true);
		lblF4.setForeground(Color.BLACK);
		lblF4.setBackground(Color.LIGHT_GRAY);
		tablero.put("F4",lblF4);
		
		lblG4 = new Casilla("","G4");
		panelTablero.add(lblG4);
		lblG4.setBackground(Color.WHITE);
		lblG4.setHorizontalAlignment(SwingConstants.CENTER);
		lblG4.setOpaque(true);
		tablero.put("G4",lblG4);
		
		lblH4 = new Casilla("","H4");
		panelTablero.add(lblH4);
		lblH4.setHorizontalAlignment(SwingConstants.CENTER);
		lblH4.setOpaque(true);
		lblH4.setForeground(Color.BLACK);
		lblH4.setBackground(Color.LIGHT_GRAY);
		tablero.put("H4",lblH4);
		
		lblA3 = new Casilla("","A3");
		panelTablero.add(lblA3);
		lblA3.setHorizontalAlignment(SwingConstants.CENTER);
		lblA3.setOpaque(true);
		lblA3.setForeground(Color.BLACK);
		lblA3.setBackground(Color.LIGHT_GRAY);
		tablero.put("A3",lblA3);
		
		lblB3 = new Casilla("","B3");
		panelTablero.add(lblB3);
		lblB3.setBackground(Color.WHITE);
		lblB3.setHorizontalAlignment(SwingConstants.CENTER);
		lblB3.setOpaque(true);
		tablero.put("B3",lblB3);
		
		lblC3 = new Casilla("","C3");
		panelTablero.add(lblC3);
		lblC3.setHorizontalAlignment(SwingConstants.CENTER);
		lblC3.setOpaque(true);
		lblC3.setForeground(Color.BLACK);
		lblC3.setBackground(Color.LIGHT_GRAY);
		tablero.put("C3",lblC3);
		
		lblD3 = new Casilla("","D3");
		panelTablero.add(lblD3);
		lblD3.setBackground(Color.WHITE);
		lblD3.setHorizontalAlignment(SwingConstants.CENTER);
		lblD3.setOpaque(true);
		tablero.put("D3",lblD3);
		
		lblE3 = new Casilla("","E3");
		panelTablero.add(lblE3);
		lblE3.setHorizontalAlignment(SwingConstants.CENTER);
		lblE3.setOpaque(true);
		lblE3.setForeground(Color.BLACK);
		lblE3.setBackground(Color.LIGHT_GRAY);
		tablero.put("E3",lblE3);
		
		lblF3 = new Casilla("","F3");
		panelTablero.add(lblF3);
		lblF3.setBackground(Color.WHITE);
		lblF3.setHorizontalAlignment(SwingConstants.CENTER);
		lblF3.setOpaque(true);
		tablero.put("F3",lblF3);
		
		lblG3 = new Casilla("","G3");
		panelTablero.add(lblG3);
		lblG3.setHorizontalAlignment(SwingConstants.CENTER);
		lblG3.setOpaque(true);
		lblG3.setForeground(Color.BLACK);
		lblG3.setBackground(Color.LIGHT_GRAY);
		tablero.put("G3",lblG3);
		
		lblH3 = new Casilla("","H3");
		panelTablero.add(lblH3);
		lblH3.setBackground(Color.WHITE);
		lblH3.setHorizontalAlignment(SwingConstants.CENTER);
		lblH3.setOpaque(true);
		tablero.put("H3",lblH3);
		
		lblA2 = new Casilla("","A2");
		panelTablero.add(lblA2);
		lblA2.setForeground(Color.BLACK);
		lblA2.setOpaque(true);
		lblA2.setBackground(Color.WHITE);
		lblA2.setHorizontalAlignment(SwingConstants.CENTER);
		tablero.put("A2",lblA2);
		
		lblB2 = new Casilla("","B2");
		panelTablero.add(lblB2);
		lblB2.setHorizontalAlignment(SwingConstants.CENTER);
		lblB2.setOpaque(true);
		lblB2.setForeground(Color.BLACK);
		lblB2.setBackground(Color.LIGHT_GRAY);
		tablero.put("B2",lblB2);
		
		lblC2 = new Casilla("","C2");
		panelTablero.add(lblC2);
		lblC2.setBackground(Color.WHITE);
		lblC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblC2.setOpaque(true);
		tablero.put("C2",lblC2);
		
		lblD2 = new Casilla("","D2");
		panelTablero.add(lblD2);
		lblD2.setHorizontalAlignment(SwingConstants.CENTER);
		lblD2.setOpaque(true);
		lblD2.setForeground(Color.BLACK);
		lblD2.setBackground(Color.LIGHT_GRAY);
		tablero.put("D2",lblD2);
		
		lblE2 = new Casilla("","E2");
		panelTablero.add(lblE2);
		lblE2.setBackground(Color.WHITE);
		lblE2.setHorizontalAlignment(SwingConstants.CENTER);
		lblE2.setOpaque(true);
		tablero.put("E2",lblE2);
		
		lblF2 = new Casilla("","F2");
		panelTablero.add(lblF2);
		lblF2.setHorizontalAlignment(SwingConstants.CENTER);
		lblF2.setOpaque(true);
		lblF2.setForeground(Color.BLACK);
		lblF2.setBackground(Color.LIGHT_GRAY);
		tablero.put("F2",lblF2);
		
		lblG2 = new Casilla("","G2");
		panelTablero.add(lblG2);
		lblG2.setBackground(Color.WHITE);
		lblG2.setHorizontalAlignment(SwingConstants.CENTER);
		lblG2.setOpaque(true);
		tablero.put("G2",lblG2);
		
		lblH2 = new Casilla("","H2");
		panelTablero.add(lblH2);
		lblH2.setHorizontalAlignment(SwingConstants.CENTER);
		lblH2.setOpaque(true);
		lblH2.setForeground(Color.BLACK);
		lblH2.setBackground(Color.LIGHT_GRAY);
		tablero.put("H2",lblH2);
		
		lblA1 = new Casilla("","A1");
		panelTablero.add(lblA1);
		lblA1.setHorizontalAlignment(SwingConstants.CENTER);
		lblA1.setOpaque(true);
		lblA1.setForeground(Color.BLACK);
		lblA1.setBackground(Color.LIGHT_GRAY);
		
		tablero.put("A1",lblA1);
		
		lblB1 = new Casilla("","B1");
		panelTablero.add(lblB1);
		lblB1.setBackground(Color.WHITE);
		lblB1.setHorizontalAlignment(SwingConstants.CENTER);
		lblB1.setOpaque(true);
		tablero.put("B1",lblB1);
		
		lblC1 = new Casilla("","C1");
		panelTablero.add(lblC1);
		lblC1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC1.setOpaque(true);
		lblC1.setForeground(Color.BLACK);
		lblC1.setBackground(Color.LIGHT_GRAY);
		tablero.put("C1",lblC1);
		
		lblD1 = new Casilla("","D1");
		panelTablero.add(lblD1);
		lblD1.setBackground(Color.WHITE);
		lblD1.setHorizontalAlignment(SwingConstants.CENTER);
		lblD1.setOpaque(true);
		tablero.put("D1",lblD1);
		
		lblE1 = new Casilla("","E1");
		panelTablero.add(lblE1);
		lblE1.setHorizontalAlignment(SwingConstants.CENTER);
		lblE1.setOpaque(true);
		lblE1.setForeground(Color.BLACK);
		lblE1.setBackground(Color.LIGHT_GRAY);
		tablero.put("E1",lblE1);
		
		lblF1 = new Casilla("","F1");
		panelTablero.add(lblF1);
		lblF1.setBackground(Color.WHITE);
		lblF1.setHorizontalAlignment(SwingConstants.CENTER);
		lblF1.setOpaque(true);
		tablero.put("F1",lblF1);
		
		lblG1 = new Casilla("","G1");
		panelTablero.add(lblG1);
		lblG1.setHorizontalAlignment(SwingConstants.CENTER);
		lblG1.setOpaque(true);
		lblG1.setForeground(Color.BLACK);
		lblG1.setBackground(Color.LIGHT_GRAY);
		tablero.put("G1",lblG1);
		
		lblH1 = new Casilla("","H1");
		panelTablero.add(lblH1);
		lblH1.setBackground(Color.WHITE);
		lblH1.setHorizontalAlignment(SwingConstants.CENTER);
		lblH1.setOpaque(true);
		tablero.put("H1",lblH1);

		textJugadorBlancas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
					ocultarModuloAgregar();
					lblJugadorBlancas.setText("");
					textJugadorBlancas.setText("");
			}
		});
		textJugadorNegras.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ocultarModuloAgregar();
				lblJugadorNegras.setText("");
				textJugadorNegras.setText("");
			}
		});
		buttonNuevaPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ocultarModuloAgregar();
				textJugadorBlancas.setText("");
				lblJugadorBlancas.setText("");
				textJugadorNegras.setText("");
				lblJugadorNegras.setText("");
				buttonJugar.setEnabled(false);

				controladorJugarPartida = new ControladorJugarPartida();
				labelJB.setText("");
				labelJN.setText("");
							
				
				CardLayout cl = (CardLayout)(panelIzq.getLayout());
			    cl.show(panelIzq, "panelCarga");
			    
				String[] casillas = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3", "B4", "D4", "F4", "H4", "A5", "C5", "E5", "G5", "B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8", 
									 "B1", "D1", "F1", "H1", "A2", "C2", "E2", "G2", "B3", "D3", "F3", "H3", "A4", "C4", "E4", "G4", "B5", "D5", "F5", "H5", "A6", "C6", "E6", "G6", "B7", "D7", "F7", "H7", "A8", "C8", "E8", "G8"};
				for(int i = 1; i < 65; i++) {
					JAjedrez.tablero.get(casillas[i-1]).setPieza("");
					JAjedrez.tablero.get(casillas[i-1]).setIcon(null);
				}
							    
			    
			    //armarTablero(p);
				
				
			}
		});
		buttonJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(buttonJugar.isEnabled()){clickBotonJugar();}
			}
		});
		// VALIDAR EN CAPA DE NEGOCIO QUE NO SE INGRESEN DATOS VACÍOS
		buttonAddJugador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			   clickBotonAgregarJugador();
			}
		});
		buttonBuscarJugadorNegras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickBotonBuscarNegras();
			}
		});
		buttonBuscarJugadorBlancas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickBotonBuscarBlancas();
			}
		});

			

		
		
		
		

      
		
	}
	
	private void clickBotonBuscarBlancas() {
		try {
			ocultarModuloAgregar();
            int dni = Integer.parseInt(textJugadorBlancas.getText());
            Jugador jugador = controladorJugarPartida.buscarJugadorBlanco(dni);
            if (jugador != null) {
                lblJugadorBlancas.setText(jugador.getNombre() +" "+ jugador.getApellido());
                labelJB.setText(controladorJugarPartida.getJugadorBlancoActual().getNombre()+" "+controladorJugarPartida.getJugadorBlancoActual().getNombre());
                //lblUnoVsDos.setText(controladorJugarPartida.getJugadorBlancoActual().getNombre()+" ...VS... "+controladorJugarPartida.getJugadorNegroActual().getNombre());
                if(controladorJugarPartida.getJugadorNegroActual()==null){
                	textJugadorNegras.requestFocus();
                	deshabilitarBotonJugar();
                } else {
                	habilitarBotonJugar();
                	buttonJugar.requestFocus();
                }
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(JAjedrez.this,
                        "Jugador no encontrado. ¿Desea darlo de alta?",
                        "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {
                	textDNI.setText(String.valueOf(dni));
                	lblColor.setText("blancas");
                    mostrarModuloAgregar();
                } else {
                	//lblUnoVsDos.setText(controladorJugarPartida.getJugadorBlancoActual().getNombre()+" ...VS... "+controladorJugarPartida.getJugadorNegroActual().getNombre());
                	deshabilitarBotonJugar();
                	textJugadorBlancas.requestFocus();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(JAjedrez.this, "DNI incorrecto.");
            textJugadorBlancas.requestFocus();
            deshabilitarBotonJugar();
        }
	};
	
	private void clickBotonBuscarNegras() {
		try {
			ocultarModuloAgregar();
            int dni = Integer.parseInt(textJugadorNegras.getText());
            Jugador jugador = controladorJugarPartida.buscarJugadorNegro(dni);
            if (jugador != null) {
                lblJugadorNegras.setText(jugador.getNombre() +" "+ jugador.getApellido());
                labelJN.setText(controladorJugarPartida.getJugadorNegroActual().getNombre()+" "+controladorJugarPartida.getJugadorNegroActual().getApellido());
                //lblUnoVsDos.setText(controladorJugarPartida.getJugadorBlancoActual().getNombre()+" ...VS... "+controladorJugarPartida.getJugadorNegroActual().getNombre());
                if(controladorJugarPartida.getJugadorBlancoActual().getNombre()==null){
                	textJugadorBlancas.requestFocus();
                	deshabilitarBotonJugar();
                } else {
                	habilitarBotonJugar();
                	buttonJugar.requestFocus();
                }
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(JAjedrez.this,
                        "Jugador no encontrado. ¿Desea darlo de alta?",
                        "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {
                	textDNI.setText(String.valueOf(dni));
                	lblColor.setText("negras");
                    mostrarModuloAgregar();
                } else {
                	//lblUnoVsDos.setText(controladorJugarPartida.getJugadorBlancoActual().getNombre()+" ...VS... "+controladorJugarPartida.getJugadorNegroActual().getNombre());
                	deshabilitarBotonJugar();
                	textJugadorNegras.requestFocus();
                	}
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(JAjedrez.this, "DNI incorrecto.");
            textJugadorNegras.requestFocus();
            deshabilitarBotonJugar();
        }
	};
	
	private void clickBotonAgregarJugador() {
		try {
			if(textDNI.getText().isEmpty() || textNombre.getText().isEmpty() || textApellido.getText().isEmpty()) {
			   JOptionPane.showMessageDialog(JAjedrez.this, "Faltan Datos");
			   if(textNombre.getText().isEmpty()) {
				   textNombre.requestFocus();
			   	} else{
			   		textApellido.requestFocus();
			   	}
			} else {
			   Jugador nuevoJugador = new Jugador();
			   nuevoJugador.setDni(Integer.parseInt(textDNI.getText()));
			   nuevoJugador.setNombre(textNombre.getText());
			   nuevoJugador.setApellido(textApellido.getText());
			   controladorJugarPartida.addJugador(nuevoJugador);
			   JOptionPane.showMessageDialog(JAjedrez.this, "Jugador guardado exitosamente");
			   if (lblColor.getText() == "blancas") {
				   textJugadorBlancas.setText(textDNI.getText());
				   ocultarModuloAgregar();
				   clickBotonBuscarBlancas();
			   } else {
				   textJugadorNegras.setText(textDNI.getText());
				   ocultarModuloAgregar();
				   clickBotonBuscarNegras();
			   }
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(JAjedrez.this, "DNI incorrecto.");
		}
	}

	private void clickBotonJugar() {
		try {
			ocultarModuloAgregar();
			Partida p = controladorJugarPartida.hayPartidaPendiente();
			if(p!=null) {
				int dialogResult = JOptionPane.showConfirmDialog(JAjedrez.this,
					"Hay una partida pendiente. ¿Desea continuarla?",
					"Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(dialogResult == JOptionPane.NO_OPTION) {
					p = controladorJugarPartida.iniciarPartida();
				}
			} else {
				p = controladorJugarPartida.iniciarPartida();
			}
			//Cambio la "carta"
			CardLayout cl = (CardLayout)(panelIzq.getLayout());
		    cl.show(panelIzq, "panelPartida");
			
		    armarTablero(p);
        } catch(appException e) {
        	JOptionPane.showMessageDialog(JAjedrez.this, e.getMessage());
        }
        //lblInfo.setText("Turno: " + controladorJugarPartida.getTurno());
	}
    
	private void armarTablero(Partida p) {

		HashMap<Posicion, Pieza> tab = p.getTablero();
        for (Pieza pieza: tab.values()) {
        	String coord = (String.valueOf(pieza.getPosicion().getX())+String.valueOf(pieza.getPosicion().getY())).toUpperCase();
        	String nombrePieza = pieza.getNombre()+pieza.getColor().charAt(0);

        	tablero.get(coord).setPieza(nombrePieza);
        	tablero.get(coord).setIcon(new ImageIcon(JAjedrez.class.getResource("/images/"+nombrePieza.toUpperCase()+".png")));
        }
		
	}
	

        
	
	
	
	
	private void habilitarBotonJugar(){
		if(controladorJugarPartida.getJugadorBlancoActual()!=null && controladorJugarPartida.getJugadorNegroActual()!=null) {
			buttonJugar.setEnabled(true);
		}
	}
	private void deshabilitarBotonJugar(){
		if(controladorJugarPartida.getJugadorBlancoActual()==null || controladorJugarPartida.getJugadorNegroActual()==null) {
			buttonJugar.setEnabled(false);
		}
	}
	
	private void mostrarModuloAgregar(){
		lblTituloColor.setVisible(true);
        lblColor.setVisible(true);
        lblTituloDNI.setVisible(true);
        textDNI.setVisible(true);
        lblTituloNombre.setVisible(true);
        textNombre.setVisible(true);
        lblTituloApellido.setVisible(true);
        textApellido.setVisible(true);
        buttonAddJugador.setVisible(true);
                
        textJugadorBlancas.setText("");
    	lblJugadorBlancas.setText("");
    	textJugadorNegras.setText("");
    	lblJugadorNegras.setText("");
    	textNombre.requestFocus();
	}
	
	private void ocultarModuloAgregar(){
		lblTituloColor.setVisible(false);
        lblColor.setVisible(false);
        lblTituloDNI.setVisible(false);
        textDNI.setVisible(false);
        lblTituloNombre.setVisible(false);
        textNombre.setVisible(false);
        lblTituloApellido.setVisible(false);
        textApellido.setVisible(false);
        buttonAddJugador.setVisible(false);
        
        textDNI.setText("");
        textNombre.setText("");
        textApellido.setText("");
       
	}
}

