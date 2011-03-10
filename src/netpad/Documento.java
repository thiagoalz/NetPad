package netpad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

import netpad.controle.ControleDocumento;
import netpad.usuario.Dono;
import netpad.usuario.Editor;
import netpad.usuario.Leitor;
import netpad.usuario.Usuario;

public class Documento {

	private ArrayList<Secao> listaSecoes;

	private String nome;	
	
	private ControleDocumento controlador;	
	
	private Usuario usuarioLocal = null;

	private Documento(String nome, Usuario u) {
		this.nome = nome;
		this.usuarioLocal = u;
		this.controlador=new ControleDocumento(this);		
	}

	private Documento(String nome, Usuario u, int qtdSecoes) {
		this(nome,u);
		criaSecoes(qtdSecoes);
	}

	public Documento(String nome, int qtdSecoes, Dono dono, String tituloSecao) {
		this(nome, dono, qtdSecoes);
		criaSecaoLocal(0, tituloSecao, dono);
	}

	public Documento(String nome, String usuarioNickname, String tituloSecao) throws TimeoutException {
		this(nome, new Leitor(usuarioNickname,tituloSecao));
		
		int[] respostaQuery=this.controlador.sendQuery(usuarioNickname);
		int totalSecoes=respostaQuery[0];
		int slotLivre=respostaQuery[1];
		
		criaSecoes(totalSecoes);

		if (slotLivre>=0) {
			this.usuarioLocal = new Editor(usuarioNickname);
			criaSecaoLocal(slotLivre, tituloSecao, (Editor) this.usuarioLocal);
		} else {
			JOptionPane.showMessageDialog(null, "Abrindo documento em modo somente leitura.");
		}
	}

	public SecaoLocal criaSecaoLocal(int numeroSecao, String titulo, Editor editor) {
		SecaoLocal sl = new SecaoLocal(numeroSecao, titulo, editor,controlador.getSupplier());
		listaSecoes.set(numeroSecao, sl);
		return sl;
	}

	private void criaSecoes(int qtdSecoes) {
		listaSecoes = new ArrayList<Secao>();
		for (int i = 0; i < qtdSecoes; i++) {
			this.criaSecao(i);
		}
	}

	public Usuario getDono() {
		return ((Secao) listaSecoes.get(0)).getEditor();
	}

	public String getNome() {
		return nome;
	}

	public int getQtdSecoes() {
		return listaSecoes.size();
	}

	public Secao getSecao(int index) {
		return listaSecoes.get(index);
	}
	
	public void fechar(){
		if (usuarioLocal instanceof Editor){
			this.getSecaoLocal().fecharSecao();
		}
		controlador.fechar();
	}

	public Usuario getUsuarioLocal() {
		return usuarioLocal;
	}
	
	public void setUsuarioLocal(Usuario us) {
		this.usuarioLocal=us;
	}

	public int getSlotLivre() {
		int slotLivre=-1;
		
		for (int i=0;i<listaSecoes.size();i++){
			Secao s=(Secao) listaSecoes.get(i);
			if(s.getEditor()==null){
				slotLivre=i;
				break;
			}
		}		
		
		return slotLivre;
	}

	public SecaoLocal getSecaoLocal() {
		SecaoLocal retorno = null;
		Iterator i = listaSecoes.iterator();
		while(i.hasNext()){
			Secao s = (Secao) i.next();
			if (s instanceof SecaoLocal){
				retorno = (SecaoLocal)s;
				break;
			}
		}
		return retorno;
	}

	public Secao criaSecao(int slot) {
		Secao retorno=new Secao(slot);
		try {
			listaSecoes.set(slot,retorno);
		} catch (IndexOutOfBoundsException ex){
			listaSecoes.add(slot, retorno);
		}
		return retorno;
	}
	
	public ControleDocumento getControle(){
		return this.controlador;
	}	
}
