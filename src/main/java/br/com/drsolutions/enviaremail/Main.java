package br.com.drsolutions.enviaremail;

import br.com.drsolutions.enviaremail.configuracoes.Configuracoes;
import br.com.drsolutions.enviaremail.ui.JanelaMensagem;
import br.com.drsolutions.enviaremail.ui.TelaPrincipal;

import static br.com.drsolutions.enviaremail.configuracoes.Configuracoes.*;

/**
 * Envio de e-mails utilizando a API Java Mail com os servidores do Gmail
 * Realize as configurações na classe 'Configuracoes'
 * .
 * Exemplo de envio com anexos:
 * http://blog.thedevconf.com/2011/04/javamail-enviando-mensagem-html-com.html
 *
 * @author Diego Mendes Rodrigues
 * @version 1.1
 * @since 12/25/2023
 */
public class Main {
    public static void main(String[] args) {
        if (validarConfiguracoes()) {
            TelaPrincipal telaEnviarEmail = new TelaPrincipal();
        }
    }

    /**
     * Validar as configurações do sistema
     *
     * @return true caso as configurações estejam realizadas, false caso contrário
     */
    private static boolean validarConfiguracoes() {
        if (NOME.isEmpty() || EMAIL.isEmpty() || USUARIO.isEmpty() || TOKEN.isEmpty() ||
                SMTP.isEmpty() || PORTA.isEmpty()) {
            JanelaMensagem janelaMensagem = new JanelaMensagem();
            janelaMensagem.mensagemErro(null, "Ajuste as configuraçÕes do sistema, como " +
                            "o nome do usuário, e-mail, token, \nservidor SMTP e a porta de conexão, " +
                            "na classe 'Configuracoes', antes de \n" +
                            "utilizar este Sistema para o Envio de E-mails.",
                    "Erro nas configurações");
            return false;
        }
        return true;
    }
}
