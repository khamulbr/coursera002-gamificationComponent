import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

public class MockArmazenamento extends Armazenamento {
	
	private String usuario, usuarioEsperado, tipoPonto, tipoPontoEsperado;
	private int qtPontos, qtPontosEsperados;
	private HashMap<String, Integer> pontuacao, pontuacaoEsperada;
	
	public MockArmazenamento() {
		pontuacao = new HashMap<String, Integer>();
		pontuacaoEsperada = new HashMap<String, Integer>();
	}
	
	@Override
	public void armazena(String usuario, String tipoPonto, int qtPontos) throws IOException {
		this.usuario = usuario;
		this.tipoPonto = tipoPonto;
		this.qtPontos = qtPontos;
		pontuacao.put(usuario+","+tipoPonto, qtPontos);
	}

	@Override
	public int getPontosDoUsuario(String usuario, String tipoPonto) {
		return this.qtPontos;
	}

	public void setUsuarioEsperado(String usuarioEsperado) {
		this.usuarioEsperado = usuarioEsperado;		
	}

	public void setTipoPontoEsperado(String tipoPontoEsperado) {
		this.tipoPontoEsperado = tipoPontoEsperado;	
	}

	public void pontuacaoEsperada(int qtPontosEsperados) {
		this.qtPontosEsperados = qtPontosEsperados;
	}

	public void verificaPontuacaoDeUmUsuarioEUmTipo() {
		assertEquals(usuarioEsperado, usuario);	
		assertEquals(tipoPontoEsperado, tipoPonto);
		assertEquals(qtPontosEsperados, qtPontos);
	}

	public void setTiposPontosEsperados(HashMap<String, Integer> pontuacaoEsperada) {
		this.pontuacaoEsperada = pontuacaoEsperada;
	}

	public void verificaPontuacaoTotalDeUmUsuario() {
		assertEquals(pontuacaoEsperada, pontuacao);
	}

	@Override
	public HashMap<String, Integer> getPontuacaoDeUsuario(String usuario) {
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		for (String pontos: pontuacao.keySet()){
			if (pontos.substring(0, pontos.indexOf(",")).equals(usuario)){
				lista.put(pontos.substring(pontos.indexOf(",")+1, pontos.length()), pontuacao.get(pontos));
			}
		}
		return lista;
	}

	
}
