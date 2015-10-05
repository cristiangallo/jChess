package ui;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;




import appExceptions.appException;
import appExceptions.appGameOver;


//import com.sun.javafx.geom.Rectangle;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.awt.MouseInfo;

public class Casilla extends JLabel{
	
    private String pieza;
    private String coordenada;
    private HashMap<String, Icon> tableroBack = new HashMap<String, Icon>();
    
   /* @FEDE. CONSTRUCTOR */
    public Casilla (String p, String coord) {
    	pieza = p;
    	coordenada = coord;
    	
    	this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				insideCasilla(e);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Casilla c = (Casilla) e.getSource();
				
				if(c.getPieza() != "") {
					System.out.println("ORIGEN: "+c.getCoordenada());
					habilitarTransferHandler();
					// Esto es un derroche de imperfección. hago un hashmap de los iconos que hay en las casillas ANTES de soltar la pieza 
					for (Map.Entry<String, Casilla> entry : JAjedrez.tablero.entrySet()) {
						 String key = entry.getKey();
						 Icon icon = entry.getValue().getIcon();
						 tableroBack.put(key,icon);
					}
					
					
				    c.setTransferHandler(new TransferHandler("icon") {
						// ESTE QUEDA ASÍ PARA QUE ME TOME LA ACCIÓN "MOVE" QUE POR AGLÚN MOTIVO LA SACARON
				    	@Override
						public int getSourceActions(JComponent c) {
							return COPY | MOVE;
						}
				    	
						// DEFINO QUE HAGO UNA VEZ FINALIZADO EL DND
				    	@Override
						protected void exportDone(JComponent source, Transferable data, int action) {
				    		Casilla c = ((Casilla) source);
				    		String casillaDestino = null;
				    		
				    		// Coordenadas del puntero al momento de soltar la pieza
							Point p = MouseInfo.getPointerInfo().getLocation();
							
							// Averiguo dónde fue a parar la pieza
							String[] casillas = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3", "B4", "D4", "F4", "H4", "A5", "C5", "E5", "G5", "B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8", 
									 			 "B1", "D1", "F1", "H1", "A2", "C2", "E2", "G2", "B3", "D3", "F3", "H3", "A4", "C4", "E4", "G4", "B5", "D5", "F5", "H5", "A6", "C6", "E6", "G6", "B7", "D7", "F7", "H7", "A8", "C8", "E8", "G8"};
							
							for(int i = 1; i < 65; i++) {
								if(JAjedrez.tablero.get(casillas[i-1]).getPosicionCasilla().contains(p.x,  p.y)) {
									casillaDestino = JAjedrez.tablero.get(casillas[i-1]).getCoordenada();
									break;
								}
							}
							
							if(action == MOVE) {
								
								//  Si el origen y destino es el mismo (Si el puntero está dentro de la Casilla de origen)
								if(c.getCoordenada().equals(casillaDestino)) {
									System.out.println("LA PIEZA SE SOLTÓ EN LA MISMA CASILLA");
								}else {
									System.out.println("LA PIEZA SE SOLTÓ EN LA CASILLA: " + casillaDestino);
									deshabilitarTransferHandler();
									// si el movimiento es válido
									
									//moverPieza (desdeX:char, desdeY:int, hastaX:char, hastaY:int):Pieza
									try{
										char desdeX = c.getCoordenada().toLowerCase().charAt(0);
										int desdeY = Integer.parseInt(c.getCoordenada().substring(1, 2));
										char hastaX = casillaDestino.toLowerCase().charAt(0);
										int hastaY = Integer.parseInt(casillaDestino.substring(1, 2)); 
												
												
										 System.out.println(desdeX);
										 System.out.println(desdeY);
										 System.out.println(hastaX);
										 System.out.println(hastaY);
										
										JAjedrez.controladorJugarPartida.moverPieza(desdeX, desdeY, hastaX, hastaY);
										c.setIcon(null);	
										JAjedrez.tablero.get(casillaDestino).setPieza(c.getPieza());
							    		c.setPieza("");
							    		c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										
									} catch (appException e){
										JOptionPane.showMessageDialog(Casilla.this, e);
							            System.out.println(e);
							            c.setIcon(tableroBack.get(c.getCoordenada()));
							    		JAjedrez.tablero.get(casillaDestino).setIcon(tableroBack.get(casillaDestino));
							        } catch (appGameOver e) {
							        	JOptionPane.showMessageDialog(Casilla.this, e);
									}
									
									
									
									
									
							    	
								}
								// AL PONER "LOLOLO" (O CUALQUIER SARASA) ANULA EL TRANSFER
								//((Casilla) source).setTransferHandler(new TransferHandler("lololo"));
								//pintarCasillas();
							}else if(action == NONE) {
								System.out.println("NONE");
							}
						}
					});
				    c.getTransferHandler().exportAsDrag(c, e, TransferHandler.MOVE);
				}
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				pintarCasillas();
			}
		});
    }
    
    
    
    
    
    private void insideCasilla(MouseEvent e) {
		Casilla tmp = (Casilla) e.getSource();
    	if(tmp.getPieza() != "") {
			tmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
    
    public String getPieza() {return pieza;}
	public void setPieza(String pieza) {this.pieza = pieza;} 
	public String getCoordenada() {return coordenada;}
	public void setCoordenada(String coord) {this.coordenada = coord;} 
	
	/* le pregunto a la capa de negocios los movimientos permitidos para la pieza en esta casilla*/
	public ArrayList<String> getMovimientos() {
		ArrayList<String> arrayMovimientosPermitidos = new ArrayList<String>(Arrays.asList("D6","E6","F6"));
		/* VACÍO ArrayList<String> arrayMovimientosPermitidos = new ArrayList<String>();*/
		return arrayMovimientosPermitidos;
	}
	
	private void pintarCasillas() {
		String[] casillasN = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3", "B4", "D4", "F4", "H4", "A5", "C5", "E5", "G5", "B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8"};
		String[] casillasB = {"B1", "D1", "F1", "H1", "A2", "C2", "E2", "G2", "B3", "D3", "F3", "H3", "A4", "C4", "E4", "G4", "B5", "D5", "F5", "H5", "A6", "C6", "E6", "G6", "B7", "D7", "F7", "H7", "A8", "C8", "E8", "G8"};
		for(int i = 1; i < 33; i++) {
			JAjedrez.tablero.get(casillasN[i-1]).setBackground(Color.LIGHT_GRAY);
			JAjedrez.tablero.get(casillasB[i-1]).setBackground(Color.WHITE);
		}
	}
	
	private void habilitarTransferHandler() {
		String[] casillas = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3", "B4", "D4", "F4", "H4", "A5", "C5", "E5", "G5", "B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8", 
							 "B1", "D1", "F1", "H1", "A2", "C2", "E2", "G2", "B3", "D3", "F3", "H3", "A4", "C4", "E4", "G4", "B5", "D5", "F5", "H5", "A6", "C6", "E6", "G6", "B7", "D7", "F7", "H7", "A8", "C8", "E8", "G8"};
		for(int i = 1; i < 65; i++) {
			JAjedrez.tablero.get(casillas[i-1]).setTransferHandler(new TransferHandler("icon"));
		}
	}
	
	private void deshabilitarTransferHandler() {
		String[] casillas = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3", "B4", "D4", "F4", "H4", "A5", "C5", "E5", "G5", "B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8", 
							 "B1", "D1", "F1", "H1", "A2", "C2", "E2", "G2", "B3", "D3", "F3", "H3", "A4", "C4", "E4", "G4", "B5", "D5", "F5", "H5", "A6", "C6", "E6", "G6", "B7", "D7", "F7", "H7", "A8", "C8", "E8", "G8"};
		for(int i = 1; i < 65; i++) {
			// AL PONER "LOLOLO" (O CUALQUIER SARASA) ANULA EL TRANSFER
			JAjedrez.tablero.get(casillas[i-1]).setTransferHandler(new TransferHandler("DESHABILITAR"));
		}
	}
	
		
	private Rectangle getPosicionCasilla() {
		// Coordenadas de la Casilla (construyo el rectángulo)
		// Rectangle(int x, int y, int width, int height)
		Rectangle r = new Rectangle(this.getLocationOnScreen().x,
									this.getLocationOnScreen().y,
									this.getBounds().width,
									this.getBounds().height);
		return r;
	}
	
	
	
	
}

