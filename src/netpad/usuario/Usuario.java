package netpad.usuario;

public abstract class Usuario {
	protected String nickname;

	public Usuario(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
