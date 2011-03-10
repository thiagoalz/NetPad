package netpad.frames.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netpad.Documento;
import netpad.frames.DocumentFrame;
import netpad.frames.MainFrame;
import netpad.usuario.Dono;

public class NovoDocumentoDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButtonOk = null;

	private JButton jButtonCancel = null;

	private JTextField jTextFieldNomeDocumento = null;

	private JLabel jLabelNomeDocumento = null;

	private JComboBox jComboBoxQtdSecoes = null;

	private JLabel jLabelQtdSecoes = null;

	private JLabel jLabelTituloSecao = null;

	private JTextField jTextFieldTituloSecao = null;

	/**
	 * @param owner
	 */
	public NovoDocumentoDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(288, 180);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(10, 10, 5, 10);
			gridBagConstraints2.gridx = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.insets = new Insets(10, 10, 5, 0);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			jLabelTituloSecao = new JLabel();
			jLabelTituloSecao.setText("Título da Seção:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.gridy = 2;
			jLabelQtdSecoes = new JLabel();
			jLabelQtdSecoes.setText("Quantidade de seções:");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.gridy = 2;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridx = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.insets = new Insets(10, 10, 5, 0);
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridy = 0;
			jLabelNomeDocumento = new JLabel();
			jLabelNomeDocumento.setText("Nome do documento:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(10, 10, 5, 10);
			gridBagConstraints4.gridwidth = 1;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.gridx = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.insets = new Insets(5, 5, 10, 10);
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridy = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 0, 10, 5);
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 3;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJButtonOk(), gridBagConstraints);
			jContentPane.add(getJButtonCancel(), gridBagConstraints3);
			jContentPane.add(getJTextFieldNomeDocumento(), gridBagConstraints4);
			jContentPane.add(jLabelNomeDocumento, gridBagConstraints5);
			jContentPane.add(getJComboBoxQtdSecoes(), gridBagConstraints6);
			jContentPane.add(jLabelQtdSecoes, gridBagConstraints7);
			jContentPane.add(jLabelTituloSecao, gridBagConstraints1);
			jContentPane.add(getJTextFieldTituloSecao(), gridBagConstraints2);
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
			jButtonOk.setText("Novo");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); 
					
					MainFrame parent = (MainFrame)NovoDocumentoDialog.this.getOwner();
					
					String nomeDoc = jTextFieldNomeDocumento.getText();
					String tituloSecao = jTextFieldTituloSecao.getText();
					String usuarioNickname = parent.getUsuario();
					int qtdSecoes = (Integer) jComboBoxQtdSecoes.getSelectedItem();
					
					/*PushSupplier p = new PushSupplier();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					p.getProdutor().query("Oi");
					*/
					DocumentFrame df = new DocumentFrame(new Documento(nomeDoc, qtdSecoes, new Dono(usuarioNickname), tituloSecao));
					df.setVisible(true);
					
					
					NovoDocumentoDialog.this.dispose();
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
					NovoDocumentoDialog.this.dispose();
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
	 * This method initializes jComboBoxQtdSecoes
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxQtdSecoes() {
		if (jComboBoxQtdSecoes == null) {
			jComboBoxQtdSecoes = new JComboBox();
			int qtdMaximaSecoes = 10;
			for(int i=1; i<qtdMaximaSecoes;i++){
				jComboBoxQtdSecoes.addItem(i);
			}
		}
		return jComboBoxQtdSecoes;
	}

	/**
	 * This method initializes jTextFieldTituloSecao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldTituloSecao() {
		if (jTextFieldTituloSecao == null) {
			jTextFieldTituloSecao = new JTextField();
		}
		return jTextFieldTituloSecao;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
