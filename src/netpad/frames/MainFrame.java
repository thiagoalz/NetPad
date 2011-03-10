package netpad.frames;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import netpad.frames.dialogs.AbrirDocumentoDialog;
import netpad.frames.dialogs.NovoDocumentoDialog;

public class MainFrame extends JFrame {
	private String usuario; // @jve:decl-index=0:

	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenuArquivo = null;

	private JMenuItem jMenuArquivoItemNovo = null;

	private JMenuItem jMenuArquivoItemAbrir = null;

	private JMenu jMenuUsuario = null;

	private JMenuItem jMenuUsuarioItemNovo = null;

	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
		//this.riseOpenORB();
	}

	/**
	 * Método para levantar os serviços do OpenORB de mapa de nomes e fábrica de
	 * canais de eventos
	 * 
	 */
	private void riseOpenORB() {
		Runtime r = Runtime.getRuntime();
		try {
			Process p;
			BufferedReader bfr;
			String msg;

			System.out.println("-- Comando ----------------------");
			System.out
					.println("java -classpath %CLASSPATH% org.openorb.util.MapNamingContext -ORBPort=2001");
			p = r
					.exec("java -classpath %CLASSPATH% org.openorb.util.MapNamingContext -ORBPort=2001");

			System.out.println("-- Input -------------------------");
			bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((msg = bfr.readLine()) != null) {
				System.out.println(msg);
			}

			System.out.println("-- Erro -------------------------");
			bfr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((msg = bfr.readLine()) != null) {
				System.out.println(msg);
			}
			System.out.println("---------------------------------");

			System.out.println("-- Comando ----------------------");
			System.out
					.println("java -classpath %CLASSPATH% org.openorb.event.Server");
			p = r.exec("java -classpath %CLASSPATH% org.openorb.event.Server");

			System.out.println("-- Input -------------------------");
			bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((msg = bfr.readLine()) != null) {
				System.out.println(msg);
			}

			System.out.println("-- Erro -------------------------");
			bfr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((msg = bfr.readLine()) != null) {
				System.out.println(msg);
			}
			System.out.println("---------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(285, 55);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getJJMenuBar());
		this.setUsuario(null);
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuArquivo());
			jJMenuBar.add(getJMenuUsuario());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenuArquivo
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenuArquivo() {
		if (jMenuArquivo == null) {
			jMenuArquivo = new JMenu();
			jMenuArquivo.setText("Arquivo");
			jMenuArquivo.setEnabled(false);
			jMenuArquivo.add(getJMenuArquivoItemNovo());
			jMenuArquivo.add(getJMenuArquivoItemAbrir());
		}
		return jMenuArquivo;
	}

	/**
	 * This method initializes jMenuArquivoItemNovo
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuArquivoItemNovo() {
		if (jMenuArquivoItemNovo == null) {
			jMenuArquivoItemNovo = new JMenuItem();
			jMenuArquivoItemNovo.setText("Novo");
			jMenuArquivoItemNovo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()");
							JDialog dialog = new NovoDocumentoDialog(
									MainFrame.this);
							dialog.pack();
							dialog.setVisible(true);
						}
					});
		}
		return jMenuArquivoItemNovo;
	}

	/**
	 * This method initializes jMenuArquivoItemAbrir
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuArquivoItemAbrir() {
		if (jMenuArquivoItemAbrir == null) {
			jMenuArquivoItemAbrir = new JMenuItem();
			jMenuArquivoItemAbrir.setText("Abrir...");
			jMenuArquivoItemAbrir
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()");
							JDialog dialog = new AbrirDocumentoDialog(
									MainFrame.this);
							dialog.pack();
							dialog.setVisible(true);
						}
					});
		}
		return jMenuArquivoItemAbrir;
	}

	/**
	 * This method initializes jMenuUsuario
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenuUsuario() {
		if (jMenuUsuario == null) {
			jMenuUsuario = new JMenu();
			jMenuUsuario.setText("Usuário");
			jMenuUsuario.add(getJMenuUsuarioItemNovo());
		}
		return jMenuUsuario;
	}

	/**
	 * This method initializes jMenuUsuarioItemNovo
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuUsuarioItemNovo() {
		if (jMenuUsuarioItemNovo == null) {
			jMenuUsuarioItemNovo = new JMenuItem();
			jMenuUsuarioItemNovo.setText("Novo");
			jMenuUsuarioItemNovo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()");
							String usuario = (String) JOptionPane
									.showInputDialog(
											MainFrame.this,
											"Digite o nickname do novo usuário:",
											"Novo usuário",
											JOptionPane.INFORMATION_MESSAGE);
							if (usuario != null) {
								if (usuario.equals("")) {
									usuario = null;
								}
							}
							setUsuario(usuario);
							//PushConsumer pc = new PushConsumer();
							/*
							 * PushSupplier ps = new PushSupplier(); try {
							 * Thread.sleep(5000); } catch (InterruptedException
							 * e1) { // TODO Auto-generated catch block
							 * e1.printStackTrace(); }
							 * ps.getProdutor().query(usuario);
							 * ps.getProdutor().query("LALA");
							 * ps.getProdutor().update(10, "Alfa", "Oi",
							 * "Texto"); ps.getProdutor().answer("Beta", 5, 2);
							 * ps.getProdutor().leave("Delta", 2);
							 * System.out.println("----------------");
							 * System.out.println("Sleep de 2"); try {
							 * Thread.sleep(2000); } catch (InterruptedException
							 * e1) { // TODO Auto-generated catch block
							 * e1.printStackTrace(); }
							 * System.out.println("Inicio do pc"); PushConsumer
							 * pc = new PushConsumer();
							 * System.out.println("Sleep de 2"); try {
							 * Thread.sleep(2000); } catch (InterruptedException
							 * e1) { // TODO Auto-generated catch block
							 * e1.printStackTrace(); }
							 * ps.getProdutor().query("Funfa?");
							 */
						}
					});
		}
		return jMenuUsuarioItemNovo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
		String tituloOriginal = "NetPad/FRB";
		if (usuario != null) {
			String novoTitulo = tituloOriginal + " - " + usuario;
			setTitle(novoTitulo);
			jMenuArquivo.setEnabled(true);
		} else {
			this.setTitle(tituloOriginal);
			jMenuArquivo.setEnabled(false);
		}
	}

} // @jve:decl-index=0:visual-constraint="10,10"
