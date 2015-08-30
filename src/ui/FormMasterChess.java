package ui;

/**
 * Created by cgallo on 22/08/15.
 */

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.ControladorJugarPartida;
import entidades.*;

public class FormMasterChess extends JFrame{
    private JButton buttonBuscarJugadorBlancas;
    private JPanel rootPanel;
    private JButton buttonJugar;
    private JButton buttonBuscarJugadorNegras;
    private JTextField textJugadorNegras;
    private JTextField textJugadorBlancas;
    private JLabel lblJugadorBlancas;
    private JLabel lblJugadorNegras;
    private JLabel lblInfo;
    private JTextField textOrigen;
    private JTextField textPieza;
    private JTextField textDestino;
    private JButton buttonMover;
    private JPanel jPanelAgregarJugador;
    private JTextField textNombre;
    private JTextField textApellido;
    private JButton buttonAddJugador;
    private JPanel jPanelJugar;
    private JTextField textDNI;
    private JLabel lblColor;
    private JPanel jPanelTablero;

    ControladorJugarPartida controladorJugarPartida = new ControladorJugarPartida();

    public FormMasterChess(){
        super("MasterChess");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        buttonBuscarJugadorBlancas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    int dni = Integer.parseInt(textJugadorBlancas.getText());
                    Jugador jugador = controladorJugarPartida.buscarJugadorBlanco(dni);
                    if (jugador != null) {
                        lblJugadorBlancas.setText(jugador.getNombre() + jugador.getApellido());
                    } else {
                        int dialogResult = JOptionPane.showConfirmDialog(FormMasterChess.this,
                                "Jugador no encontrado, ¿desea darlo de alta?",
                                "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            lblColor.setText("blancas");
                            textDNI.setText(String.valueOf(dni));
                            jPanelAgregarJugador.setVisible(true);
                            jPanelJugar.setVisible(false);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(FormMasterChess.this, "DNI incorrecto.");
                }
            }
        });

        buttonBuscarJugadorNegras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    int dni = Integer.parseInt(textJugadorNegras.getText());
                    Jugador jugador = controladorJugarPartida.buscarJugadorNegro(dni);
                    if (jugador != null) {
                        lblJugadorNegras.setText(jugador.getNombre() + jugador.getApellido());
                    } else {
                        int dialogResult = JOptionPane.showConfirmDialog(FormMasterChess.this,
                                "Jugador no encontrado, ¿desea darlo de alta?",
                                "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            lblColor.setText("negras");
                            textDNI.setText(String.valueOf(dni));
                            jPanelAgregarJugador.setVisible(true);
                            jPanelJugar.setVisible(false);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(FormMasterChess.this, "DNI incorrecto.");
                }
            }
        });

        buttonAddJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Jugador nuevoJugador = new Jugador();
                    nuevoJugador.setDni(Integer.parseInt(textDNI.getText()));
                    nuevoJugador.setNombre(textNombre.getText());
                    nuevoJugador.setApellido(textApellido.getText());
                    controladorJugarPartida.addJugador(nuevoJugador);

                    lblInfo.setText("Se guardó el jugador satisfactoriamente");
                    if (lblColor.getText() == "blancas") {
                        textJugadorBlancas.setText(textDNI.getText());
                        buttonBuscarJugadorBlancas.doClick();
                    } else {
                        textJugadorNegras.setText(textDNI.getText());
                        buttonBuscarJugadorNegras.doClick();
                    }
                    jPanelJugar.setVisible(true);
                    jPanelAgregarJugador.setVisible(false);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(FormMasterChess.this, "DNI incorrecto.");
                } finally {
                    textDNI.setText("");
                    textNombre.setText("");
                    textApellido.setText("");
                }
                }
            }
            );

        buttonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            controladorJugarPartida.iniciarPartida();
            try {
                controladorJugarPartida.iniciarPartida();
                lblInfo.setText("Turno: BLANCAS");
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(FormMasterChess.this, e.getMessage());
            }
            }
        }
        );

        buttonMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                char[] desdeArray = textOrigen.getText().toCharArray();
                char[] hastaArray = textDestino.getText().toCharArray();
                char desdeX = desdeArray[0];
                int desdeY = Character.getNumericValue(desdeArray[1]);
                char hastaX = hastaArray[0];
                int hastaY = Character.getNumericValue(hastaArray[1]);
                try {
                    Pieza pieza = controladorJugarPartida.moverPieza(desdeX, desdeY, hastaX, hastaY);
                    textPieza.setText(String.valueOf(pieza.getNombre()));
                    lblInfo.setText("Turno: " + controladorJugarPartida.getTurno());
                } catch (NullPointerException e){
                    JOptionPane.showMessageDialog(FormMasterChess.this, e.getMessage());
                } catch (Exception e){
                    JOptionPane.showMessageDialog(FormMasterChess.this, e.getMessage());
                }
                /*
                try {
                    controladorJugarPartida.iniciarPartida();
                    lblInfo.setText("Turno: BLANCAS");
                } catch (NullPointerException e){
                    JOptionPane.showMessageDialog(FormMasterChess.this, e.getMessage());
                }
                */
            }
        }
        );

        setVisible(true);
        }
    }
