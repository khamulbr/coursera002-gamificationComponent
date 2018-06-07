import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ArmazenamentoTest {

	@Test
	public void testArmazenarUmUsuario() throws IOException {
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.armazena("guerra", "estrela", 10);
		assertEquals("guerra,estrela,10",armazenamento.getInformacaoDoArquivo("guerra","estrela"));
	}
	
	@Test
	public void testArmazenarDoisUsuarios() throws IOException {
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.armazena("guerra", "estrela", 10);
		armazenamento.armazena("guerra", "moeda", 25);
		assertEquals("guerra,moeda,25",armazenamento.getInformacaoDoArquivo("guerra","moeda"));
	}
	
	@Test
	public void testRecuperarPontosDeUsuario() throws IOException{
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.armazena("guerra", "estrela", 10);
		assertEquals(10, armazenamento.getPontosDoUsuario("guerra","estrela"));
	}
	
	@Test
	public void testRecuperarUsuariosQueJaPontuaram() throws IOException{
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.armazena("guerra", "estrela", 10);
		armazenamento.armazena("guerra", "moeda", 25);
		armazenamento.armazena("joao", "moeda", 15);
		
		List<String> usuariosEsperados = new ArrayList<String>();
		usuariosEsperados.add("guerra");
		usuariosEsperados.add("joao");
		
		Assert.assertEquals( usuariosEsperados, armazenamento.getUsuariosQueJaPontuaram());
	}
	
	@Test
	public void testRecuperarTiposDePontoQueJaPontuaram() throws IOException{
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.armazena("guerra", "estrela", 10);
		armazenamento.armazena("guerra", "moeda", 25);
		armazenamento.armazena("joao", "moeda", 15);
		
		List<String> pontosEsperados = new ArrayList<String>();
		pontosEsperados.add("estrela");
		pontosEsperados.add("moeda");
		
		Assert.assertEquals( pontosEsperados, armazenamento.getTiposDePontoQueJaPontuaram());
	}
}
