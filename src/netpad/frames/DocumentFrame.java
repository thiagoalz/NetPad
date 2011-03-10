package netpad.frames;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import netpad.Documento;
import netpad.Secao;

public class DocumentFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Documento documento = null;

	private JScrollPane jScrollPane = null;

	private JPanel jContentPanel = null;
	
	private ArrayList<SlotPanel> listaSlot;
	

	/**
	 * This is the default constructor
	 */
	public DocumentFrame(Documento d) {
		super();
		this.documento = d;
		this.documento.getControle().setDocFrame(this);
		addSlotPanels();
		initialize();
	}

	private void addSlotPanels() {
		listaSlot=new ArrayList<SlotPanel>();
		for(int i=0;i<documento.getQtdSecoes();i++){
			listaSlot.add(new SlotPanel(documento.getSecao(i)));
		}
	}

	private void placeSlotPanels() {
		for(int i=0;i<listaSlot.size();i++){
			SlotPanel sp = listaSlot.get(i);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor=GridBagConstraints.CENTER;
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.insets = new Insets(0,5,5,5);
			this.getJContentPanel().add(sp, gbc);	
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		int documentFrameSizeY = 0;
		if (documento.getQtdSecoes() > 2) {
			documentFrameSizeY = 768;
		} else if (documento.getQtdSecoes() == 2){
			documentFrameSizeY = 650;
		} else if (documento.getQtdSecoes() < 2){
			documentFrameSizeY = 340;
		}
		this.setSize(835, documentFrameSizeY);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.setContentPane(getJScrollPane());
		this.setTitle("Documento - "+documento.getNome()+" // Usuário: "+documento.getUsuarioLocal().getNickname());
		placeSlotPanels();
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()");
				DocumentFrame.this.documento.fechar();
			}			
		});
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJContentPanel());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jContentPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPanel() {
		if (jContentPanel == null) {
			jContentPanel = new JPanel();
			jContentPanel.setLayout(new GridBagLayout());
		}
		return jContentPanel;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void atualizaDocFrame(Secao sec) {
		SlotPanel newSp = new SlotPanel(sec);
		listaSlot.set(sec.getNumero(), newSp);		
		this.getJContentPanel().removeAll();
		jContentPanel = null;
		jScrollPane = null;
		this.setContentPane(getJScrollPane());
		placeSlotPanels();
		this.repaint();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
