package netpad.usuario;

public class Leitor extends Usuario {

	private String tituloSecao;
	
	public Leitor(String nickname,String tituloSecao) {
		super(nickname);
		this.tituloSecao=tituloSecao;
	}

	public String getTituloSecao() {
		return tituloSecao;
	}

	public void setTituloSecao(String tituloSecao) {
		this.tituloSecao = tituloSecao;
	}

}
