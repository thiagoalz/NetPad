/**
 * 
 */
package netpad.controle;

import java.util.concurrent.TimeoutException;

import netpad.Documento;
import netpad.Secao;
import netpad.frames.DocumentFrame;
import netpad.orb.PushConsumer;
import netpad.orb.PushSupplier;
import netpad.usuario.Dono;
import netpad.usuario.Editor;
import netpad.usuario.Leitor;
import netpad.usuario.Usuario;

/**
 * @author John Holiver
 *
 */
public class ControleDocumento {

	private PushConsumer consumer;
	private PushSupplier supplier;
	
	private Documento doc;
	private DocumentFrame docFrame;
	
	private boolean esperandoAnswer=false;
	private int[] respostaAnswer;

	/**
	 * 
	 */
	public ControleDocumento(Documento doc) {
		this.doc=doc;
		
		supplier = new PushSupplier(doc.getNome());
		consumer = new PushConsumer(doc.getNome());
		while ((consumer.getConsumidor()==null)||(this.supplier.getProdutor()==null)){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		consumer.getConsumidor().setControleDocumento(this);
	}
	public PushConsumer getConsumer() {
		return consumer;
	}
	public void setConsumer(PushConsumer consumer) {
		this.consumer = consumer;
	}
	public Documento getDoc() {
		return doc;
	}
	public void setDoc(Documento doc) {
		this.doc = doc;
	}
	public PushSupplier getSupplier() {
		return supplier;
	}
	public void setSupplier(PushSupplier supplier) {
		this.supplier = supplier;
	}
	
	public void fechar(){
		Usuario usLocal=this.getUsuarioLocal();
		if(usLocal instanceof Editor){
			this.supplier.getProdutor().leave(doc.getUsuarioLocal().getNickname(), doc.getSecaoLocal().getNumero());
		}
		if(usLocal instanceof Dono){
			this.consumer.interrupt();
			this.supplier.interrupt();
		}
	}
	private Usuario getUsuarioLocal() {	
		return doc.getUsuarioLocal();
	}
	public void trataQuery(String nickNameRemoto) {
		Usuario usLocal=this.getUsuarioLocal();
		if(usLocal instanceof Dono){
			this.supplier.getProdutor().answer(nickNameRemoto, this.doc.getQtdSecoes(), doc.getSlotLivre());
		}		
	}
	public void trataAnswer(String nickNameRemoto, int totalSlots, int slotLivre) {
		if(nickNameRemoto.equals(this.getUsuarioLocal().getNickname())){
			this.respostaAnswer=new int[2];
			respostaAnswer[0]=totalSlots;
			respostaAnswer[1]=slotLivre;
			this.esperandoAnswer=false;
		} else {
			if(slotLivre>=0){
				doc.getSecao(slotLivre).setEditor(new Editor(nickNameRemoto));
			}
		}
	}
	public void trataUpdate(int slot, String nickNameRemoto, String titulo, String texto) {
		if(!nickNameRemoto.equals(this.getUsuarioLocal().getNickname())){
			Secao secao = doc.getSecao(slot);
			secao.atualizar(titulo, texto,nickNameRemoto);
			secao.setEditor(new Editor(nickNameRemoto));
		}
	}
	public void trataLeave(String nickNameRemoto, int slot) {
		Secao sec=doc.getSecao(slot);
		sec.clear();
		
		Usuario usLocal=this.getUsuarioLocal();
		if(usLocal instanceof Leitor){
			Leitor leitor=(Leitor)usLocal;
			try {
				int[] respostaQuery=this.sendQuery(usLocal.getNickname());
				int slotLivre=respostaQuery[1];				
				if (slotLivre>=0) {
					Editor novoUsuario=new Editor(usLocal.getNickname());
					this.doc.setUsuarioLocal(novoUsuario);
					
					this.doc.criaSecaoLocal(slotLivre, leitor.getTituloSecao(), novoUsuario);
					docFrame.dispose();
					DocumentFrame df = new DocumentFrame(doc);
					this.setDocFrame(df);
					df.setVisible(true);
					df.requestFocus();
				} 
			} catch (TimeoutException e) {
			}
		}
	}
	
	public int[] sendQuery(String usuarioNickname) throws TimeoutException {
		int tentativas = 0;
		int maxTentativas = 2;
		this.esperandoAnswer=true;
		this.supplier.getProdutor().query(usuarioNickname);
		while((esperandoAnswer)&&(tentativas<maxTentativas)){
			try {
				Thread.sleep(2000);
				tentativas++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(tentativas>=maxTentativas){
			//this.supplier.fecharCanal();
			this.consumer.interrupt();
			this.supplier.interrupt();
			throw new TimeoutException();
		}

		return this.respostaAnswer;
	}
	public DocumentFrame getDocFrame() {
		return docFrame;
	}
	public void setDocFrame(DocumentFrame docFrame) {
		this.docFrame = docFrame;
	}

}
