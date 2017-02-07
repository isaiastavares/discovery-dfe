package br.com.discovery.dfe;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fincatto.dfe.classes.DFAmbiente;
import com.fincatto.dfe.utils.DFGeraCadeiaCertificados;
import com.fincatto.main.NFeConfigImpl;
import com.fincatto.nfe310.NFeConfig;

/**
 * Classe responsável por realizar o boot da aplicação
 *
 * @author Isaias Tavares
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	private static final NFeConfig config = new NFeConfigImpl();
    private static final String CACERT_PRODUCAO = "producao.cacerts";
    private static final String CAMINHO_CADEIA_SERTIFICADO = System.getProperty("java.home") + "/lib/security/" + CACERT_PRODUCAO;

	public static void main(String[] args) throws IOException, Exception {
		gerarCertificado();
		SpringApplication.run(Application.class, args);
	}

	private static void gerarCertificado() throws IOException, Exception {
		FileUtils.writeByteArrayToFile(
				new File(CAMINHO_CADEIA_SERTIFICADO),
				DFGeraCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.PRODUCAO,
				config.getCadeiaCertificadosSenha()));
	}
}
