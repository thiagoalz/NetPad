package netpad.frames.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netpad.Documento;
import netpad.frames.DocumentFrame;
import netpad.frames.MainFrame;

public class AbrirDocumentoDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="0,0"

	private JButton jButtonOk = null;

	private JButton jButtonCancel = null;

	private JTextField jTextFieldNomeDocumento = null;

	private JLabel jLabelNomeDocumento = null;

	private JLabel jLabelTituloSecao = null;

	private JTextField jTextFieldTituloSecao = null;

	/**
	 * @param owner
	 */
	public AbrirDocumentoDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(278, 135);
		this.setTitle("Abrir documento");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 1;
			gridBagConstraints22.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints22.anchor = GridBagConstraints.WEST;
			gridBagConstraints22.gridy = 2;
			jLabelTituloSecao = new JLabel();
			jLabelTituloSecao.setText("Título da Seção:");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.insets = new Insets(10, 10, 5, 0);
			gridBagConstraints21.gridwidth = 2;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.gridy = 1;
			jLabelNomeDocumento = new JLabel();
			jLabelNomeDocumento.setText("Nome do documento:");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(10, 10, 5, 10);
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.insets = new Insets(5, 5, 10, 10);
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.gridy = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.insets = new Insets(5, 10, 10, 5);
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 3;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.setSize(new Dimension(270, 106));
			jContentPane.add(getJButtonOk(), gridBagConstraints);
			jContentPane.add(getJButtonCancel(), gridBagConstraints1);
			jContentPane.add(getJTextFieldNomeDocumento(), gridBagConstraints2);
			jContentPane.add(jLabelNomeDocumento, gridBagConstraints21);
			jContentPane.add(jLabelTituloSecao, gridBagConstraints22);
			jContentPane.add(getJTextFieldTituloSecao(), gridBagConstraints3);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setText("Abrir");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); 
					
					MainFrame parent = (MainFrame)AbrirDocumentoDialog.this.getOwner();
					
					String nomeDoc = jTextFieldNomeDocumento.getText();
					String tituloSecao = jTextFieldTituloSecao.getText();
					String usuarioNickname = parent.getUsuario();
					DocumentFrame df;
					try {
						df = new DocumentFrame(new Documento(nomeDoc, usuarioNickname, tituloSecao));
						df.setVisible(true);
						
						AbrirDocumentoDialog.this.dispose();
					} catch (TimeoutException e1) {
						//df = null;
						JOptionPane.showMessageDialog(AbrirDocumentoDialog.this, "Não foi possível abrir o documento especificado.\n Verifique a existência do mesmo ou tente mais tarde.");
					}
				}
			});
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jButtonCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			jButtonCancel = new JButton();
			jButtonCancel.setText("Cancelar");
			jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					AbrirDocumentoDialog.this.dispose();
				}
			});
		}
		return jButtonCancel;
	}

	/**
	 * This method initializes jTextFieldNomeDocumento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNomeDocumento() {
		if (jTextFieldNomeDocumento == null) {
			jTextFieldNomeDocumento = new JTextField();
			jTextFieldNomeDocumento.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldNomeDocumento;
	}

	/**
	 * This method initializes jTextFieldTituloSecao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTituloSecao() {
		if (jTextFieldTituloSecao == null) {
			jTextFieldTituloSecao = new JTextField();
			jTextFieldTituloSecao.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldTituloSecao;
	}

}  //  @jve:decl-index=0:visual-constraint="-3,11"
