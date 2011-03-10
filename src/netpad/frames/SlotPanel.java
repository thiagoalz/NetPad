package netpad.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import netpad.Secao;

public class SlotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String titleSlot;

	private JLabel jLabelTitulo = null;

	private JTextField jTextFieldTitulo = null;

	private JLabel jLabelUser = null;

	private JLabel jLabelUserNickname = null;

	private JScrollPane jScrollPane = null;

	private Secao secao = null;

	/**
	 * This is the default constructor
	 */
	public SlotPanel(Secao secao) {
		super();
		this.secao = secao;
		this.titleSlot = " Slot "+secao.getNumero()+" ";
		initialize();
		/* Tentativa de pegar o Focus
		 * if (secaoTextArea.getClass() == SecaoLocal.class){
		 * secaoTextArea.requestFocusInWindow();
		}*/
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.BOTH;
		gridBagConstraints21.gridy = 1;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.weighty = 1.0;
		gridBagConstraints21.gridwidth = 4;
		gridBagConstraints21.insets = new Insets(20, 10, 10, 10);
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 3;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.insets = new Insets(0, 5, 0, 10);
		gridBagConstraints3.gridy = 0;
		jLabelUserNickname = secao.getLabelEditorNickname();
		jLabelUserNickname.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 2;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 0;
		jLabelUser = new JLabel();
		jLabelUser.setText("Usuário:");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.anchor = GridBagConstraints.WEST;
		gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.insets = new Insets(0, 10, 0, 5);
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.gridy = 0;
		jLabelTitulo = new JLabel();
		jLabelTitulo.setText("Título:");
		this.setSize(800, 300);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), titleSlot, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial Narrow", Font.BOLD, 12), Color.orange));
		this.setPreferredSize(new Dimension(800, 300));
		this.add(jLabelTitulo, gridBagConstraints);
		this.add(getJTextFieldTitulo(), gridBagConstraints1);
		this.add(jLabelUser, gridBagConstraints2);
		this.add(jLabelUserNickname, gridBagConstraints3);
		this.add(getJScrollPane(), gridBagConstraints21);
	}

	/**
	 * This method initializes jTextFieldTitulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTitulo() {
		if (jTextFieldTitulo == null) {
			jTextFieldTitulo = secao.getTextFieldTitulo();
			jTextFieldTitulo.setPreferredSize(new Dimension(200, 20));
		}
		return jTextFieldTitulo;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(secao);
		}
		return jScrollPane;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
