import java.io.IOException;
import java.util.HashMap;

public class Placar {
	
	private Armazenamento armazenamento;
	
	public void setArmazenamento(Armazenamento armazenamento) {
		this.armazenamento = armazenamento;
	}
	
	public void registrar(String usuario, String tipoPonto, int qtPontos) throws IOException {
		armazenamento.armazena(usuario, tipoPonto, qtPontos);
	}

	public int getPontos(String usuario, String tipoPonto) {
		return armazenamento.getPontosDoUsuario(usuario, tipoPonto);
	}

	public HashMap<String, Integer> getPontuacaoDeUsuario(String usuario) {
		return armazenamento.getPontuacaoDeUsuario(usuario);
	}

}
