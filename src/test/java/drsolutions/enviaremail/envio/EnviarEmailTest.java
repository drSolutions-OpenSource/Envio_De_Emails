package drsolutions.enviaremail.envio;

import br.com.drsolutions.enviaremail.configuracoes.Configuracoes;
import br.com.drsolutions.enviaremail.envio.EnviarEmail;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Testes da classe EnviarEmail
 *
 * @author Diego Mendes Rodrigues
 */
public class EnviarEmailTest {
    @Test
    public void enviarComAnexoTest() {
        Random r = new Random();
        String destinatario = Configuracoes.EMAIL;
        String destinatarioCC = "";
        String destinatarioCCO = "";
        String assunto = "Reunião - " + r.nextInt(100 - 10) + 10;
        String mensagem = "Prezado Oscar Niemeyer,<br/><br/>" +
                "Podemos conversar sobre os novos prédios que o senhor está desenvolvendo?<br/>" +
                "Amanhã seria uma boa opção para nossa conversa.<br/><br/>" +
                "Atenciosamente,<br/>" +
                "Isis Nable Valverde";
        String anexo = "imagens\\fundo.jpg";
        EnviarEmail enviarEmail = new EnviarEmail(true);
        enviarEmail.conectarSMTP();
        assertTrue(enviarEmail.enviarComAnexo(destinatario, destinatarioCC, destinatarioCCO,
                assunto, mensagem, anexo));
    }
}
