package netpad;
import java.util.Timer;
import java.util.TimerTask;

import netpad.orb.PushSupplier;
import netpad.usuario.Editor;

public class SecaoLocal extends Secao {
	private Timer timer;

	public SecaoLocal(int numero, String titulo, Editor editor,PushSupplier produtor) {
		super(numero);
		setTitulo(titulo);
		this.editor = editor;
		this.setEditable(true);
		this.titulo.setEditable(true);
		int delay = 2000; // 120s
		int period = 5000; // 20s
		timer = new Timer("SecaoLocalTimer", true);
		timer.scheduleAtFixedRate(new TaskEscreveSecao(produtor), delay, period);
	}

	public void fecharSecao() {
		timer.cancel();
	}

	private class TaskEscreveSecao extends TimerTask {
		private PushSupplier produtor;
		
		public TaskEscreveSecao(PushSupplier produtor) {
			this.produtor=produtor;
		}

		@Override
		public void run() {
			this.produtor.getProdutor().update(getNumero(), getEditorNickname(), getTitulo(),
					getText());
		}

	}
}
