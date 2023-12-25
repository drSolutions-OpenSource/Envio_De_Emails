package br.com.drsolutions.enviaremail.configuracoes;

/**
 * Configurações para o envio de e-mail
 *
 * @author Diego Mendes Rodrigues
 */
public class Configuracoes {
    /* Nome do usuário, exemplo: Diego Rodrigues */
    public static final String NOME = "";

    /* E-mail utilizado, exemplo: diego@gmail.com */
    public static final String EMAIL = "";

    /* Usuário utilizado na autenticação, exemplo: diego@gmail.com */
    public static final String USUARIO = "";

    /* Token de App do Gmail utilizado na autenticação, exemplo: akaslkdjuashdjsd */
    /* Mais informações: https://developers.google.com/identity/protocols/oauth2?hl=pt-br */
    public static final String TOKEN = "";

    /* SMTP do Gmail, exemplo: smtp.gmail.com */
    public static final String SMTP = "smtp.gmail.com";

    /* Porta utilizada na conexão, exemplo: 587 */
    public static final String PORTA = "587";

    /* Maior tamanho de MB dos arquivos que podem ser anexados ao e-mail, exemplo: 20 */
    public static final int TAMANHOMAXIMOANEXOMB = 20;
}
