import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armazenamento {
	
	private Map<String, Integer> pontos = new HashMap<String, Integer>();
	
	private final String PATH = "pontos.txt";
	
	public Armazenamento() {
		carregaPontosDoArquivo();
	}

	private void carregaPontosDoArquivo() {
		try {
			List<String> lines = Files.readAllLines(getArquivo(), Charset.defaultCharset() );
			for(String linha: lines){
				pontos.put(linha.substring(0,linha.lastIndexOf(",")), Integer.valueOf(linha.substring(linha.lastIndexOf(",")+1,linha.length())));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void armazena(String usuario, String tipoPonto, int qtPontos) throws IOException {
		
		if ((pontos.get(usuario+","+tipoPonto)==null)||(pontos.get(usuario+","+tipoPonto)!=qtPontos)){
			pontos.put(usuario+","+tipoPonto, qtPontos);
			String dados = usuario+","+tipoPonto+","+qtPontos+System.getProperty( "line.separator");

			Files.write(getArquivo(), dados.getBytes(), StandardOpenOption.APPEND);
		}
	}

	private Path getArquivo() throws IOException {
		Path saida;
		try {
			saida = Files.createFile(Paths.get(PATH));
		} catch (FileAlreadyExistsException faee) {
			saida = Paths.get(PATH);
		}
		return saida;
	}
	
	public String getInformacaoDoArquivo(String usuario, String tipoPonto) throws IOException{
		List<String> lines = Files.readAllLines(getArquivo(), Charset.defaultCharset() );
		for(String linha: lines){
			if (linha.startsWith(usuario+","+tipoPonto+",")){
				return linha;
			}
		}
		return null;
	}
	
	public int getPontosDoUsuario(String usuario, String tipoPonto) {
		return pontos.get(usuario+","+tipoPonto);
	}

	public List<String> getUsuariosQueJaPontuaram() {
		List<String> lista = new ArrayList<String>();
		for (String pontuacao: pontos.keySet()){
			String usuario = pontuacao.substring(0,pontuacao.indexOf(","));
			if (!lista.contains(usuario))
				lista.add(usuario);
		}
		return lista;
	}

	public List<String> getTiposDePontoQueJaPontuaram() {
		List<String> lista = new ArrayList<String>();
		for (String pontuacao: pontos.keySet()){
			String tipoPonto = pontuacao.substring(pontuacao.indexOf(",")+1, pontuacao.length());
			if (!lista.contains(tipoPonto))
				lista.add(tipoPonto);
		}
		return lista;
	}

	public HashMap<String, Integer> getPontuacaoDeUsuario(String usuario) {
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		for (String pontuacao: pontos.keySet()){
			if (pontuacao.substring(0, pontuacao.indexOf(",")).equals(usuario)){
				lista.put(pontuacao.substring(pontuacao.indexOf(",")+1, pontuacao.length()), pontos.get(pontuacao));
			}
		}
		return lista;
	}
}
