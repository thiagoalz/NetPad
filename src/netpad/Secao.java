package netpad;

import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JTextField;

import netpad.usuario.Editor;

public class Secao extends TextArea {
	protected JTextField titulo = new JTextField();
	protected JLabel editorNickname = new JLabel();

	protected Editor editor;

	protected int numero;

	public Secao(int numero) {
		super();
		this.setEditable(false);
		this.titulo.setEditable(false);
		this.numero = numero;
	}

	public String getEditorNickname() {
		try {
			return editor.getNickname();
		} catch (NullPointerException e) {
			return "Vazio";
		}
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
		editorNickname.setText(this.getEditorNickname());
	}

	public String getTitulo() {
		return titulo.getText();
	}

	public void setTitulo(String titulo) {
		this.titulo.setText(titulo);
	}

	public int getNumero() {
		return numero;
	}

	public JTextField getTextFieldTitulo() {
		return titulo;
	}
	
	public JLabel getLabelEditorNickname() {
		editorNickname.setText(this.getEditorNickname());
		return editorNickname;
	}
	
	public void atualizar(String titulo,String texto,String nick){
		this.setTitulo(titulo);
		this.setText(texto);
		this.setEditor(new Editor(nick));
	}

	public void clear() {
		this.setText("");
		this.titulo.setText("");
		this.editor =null;
		this.editorNickname.setText(this.getEditorNickname());
		
	}

}
