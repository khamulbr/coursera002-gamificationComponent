import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

public class PlacarTest {
	
	@Test
	public void testRegistrarPontoParaUsuario() throws IOException {
		Placar placar = new Placar();
		MockArmazenamento mock = new MockArmazenamento();
		placar.setArmazenamento(mock);
		mock.setUsuarioEsperado("guerra");
		mock.setTipoPontoEsperado("estrela");
		mock.pontuacaoEsperada(20);
		placar.registrar("guerra", "estrela", 20);
		assertEquals(20,placar.getPontos("guerra","estrela"));
		mock.verificaPontuacaoDeUmUsuarioEUmTipo();
	}
	
	@Test
	public void testBuscarTodosOsPontosDeUmUsuario() throws IOException{
		Placar placar = new Placar();
		MockArmazenamento mock = new MockArmazenamento();
		placar.setArmazenamento(mock);
		HashMap<String, Integer> pontuacaoEsperada = new HashMap<String, Integer>();
		pontuacaoEsperada.put("estrela", 20);
		pontuacaoEsperada.put("moeda", 10);
		
		mock.setTiposPontosEsperados(pontuacaoEsperada);
		
		placar.registrar("guerra", "estrela", 20);
		placar.registrar("guerra", "moeda", 10);
		placar.registrar("joao", "estrela", 25);
		
		assertEquals(pontuacaoEsperada, placar.getPontuacaoDeUsuario("guerra"));
	}
	
	

}
