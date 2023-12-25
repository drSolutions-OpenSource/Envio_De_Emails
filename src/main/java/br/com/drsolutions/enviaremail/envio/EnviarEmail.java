package br.com.drsolutions.enviaremail.envio;

import br.com.drsolutions.enviaremail.configuracoes.Configuracoes;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Envio de e-mails com autenticação TTLS/SSL, utilizando a API Java Mail
 * As mensagens são enviadas no formato HTML com a codificação UTF-8, para aceitar os caracteres latino americanos
 *
 * @author Diego Mendes Rodrigues
 */
public class EnviarEmail {
    private Session secao;
    private boolean exibirMsgDeAcompanhamento = false;

    private String mensagemDeErro;

    public EnviarEmail() {
    }

    /**
     * Construtor definindo se as mensagens de acompanhamento devem ser exibidas no terminal
     *
     * @param exibirMensagensAcompanhamento boolean sendo true define que as mensagens serão exibidas
     */
    public EnviarEmail(boolean exibirMensagensAcompanhamento) {
        this.exibirMsgDeAcompanhamento = exibirMensagensAcompanhamento;
    }

    /**
     * Realizar a conexão e a autenticação com o servidor SMTP
     */
    public void conectarSMTP() {
        if (exibirMsgDeAcompanhamento) {
            System.out.println("# Autenticação");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", Configuracoes.SMTP);
        props.put("mail.smtp.port", Configuracoes.PORTA);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Configuracoes.USUARIO, Configuracoes.TOKEN);
            }
        };

        secao = Session.getInstance(props, auth);
    }

    /**
     * Enviar um e-mail no formato HTML com um anexo
     *
     * @param emailDestinatario String sendo o destinatário do e-mail
     * @param emailCC           String sendo quem receberá uma cópia do e-mail
     * @param emailCCO          String sendo quem receberá uma cópia oculta do e-mail
     * @param assunto           String sendo o assunto do e-mail
     * @param corpo             String sendo a mensagem no formato HTML
     * @param caminhoDoAnexo    String sendo o caminho do anexo no sistema operacional
     * @return boolean true caso o e-mail seja enviado, false caso contrário
     */
    public boolean enviarComAnexo(String emailDestinatario, String emailCC, String emailCCO, String assunto,
                                  String corpo, String caminhoDoAnexo) {
        try {
            if (exibirMsgDeAcompanhamento) {
                System.out.println("# Enviar e-mail com anexo");
            }

            /* Montar o cabeçalho da mensagem */
            MimeMessage mensagem = new MimeMessage(secao);
            mensagem.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mensagem.addHeader("format", "flowed");
            mensagem.addHeader("Content-Transfer-Encoding", "8bit");

            /* Definir o e-mail de origem */
            mensagem.setFrom(new InternetAddress(Configuracoes.EMAIL, Configuracoes.NOME));
            mensagem.setReplyTo(InternetAddress.parse(Configuracoes.EMAIL, false));

            /* Incluir o assunto na mensagem */
            mensagem.setSubject(assunto, "UTF-8");
            mensagem.setSentDate(new Date());

            /* Definir o destinatário */
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario, false));

            if (!emailCC.isEmpty())
                mensagem.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC, false));

            if (!emailCCO.isEmpty())
                mensagem.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailCCO, false));

            /* Mensagem com várias partes para enviar o anexo */
            Multipart multipart = new MimeMultipart();

            /* Corpo da mensagem em HTML */
            MimeBodyPart mensagemHTML = new MimeBodyPart();
            mensagemHTML.setContent(corpo, "text/html; charset=UTF-8");
            multipart.addBodyPart(mensagemHTML);

            /* Arquivo anexado */
            if (!caminhoDoAnexo.isEmpty()) {
                File file = new File(caminhoDoAnexo);

                /* Verificar se o arquivo do anexo existe e pode ser lido */
                if (!file.exists() || !file.isFile()) {
                    this.mensagemDeErro = "O arquivo selecionado para ser anexado não existe, ou não pode " +
                            "ser acessado.";
                    return false;
                }

                /* Verificar se o tamanho em MB do anexo é maior do que o permitido */
                if (((double) file.length() / (1024 * 1024)) > Configuracoes.TAMANHOMAXIMOANEXOMB) {
                    this.mensagemDeErro = "O arquivo selecionado para ser anexado possui o tamanho superior a " +
                            Configuracoes.TAMANHOMAXIMOANEXOMB + " MB, não podendo ser anexado.";
                    return false;
                }

                MimeBodyPart anexo = new MimeBodyPart();
                anexo.setDataHandler(new DataHandler(new FileDataSource(file)));
                anexo.setFileName(file.getName());
                multipart.addBodyPart(anexo);
            }

            /* Incluir o corpo e o anexo na mensagem */
            mensagem.setContent(multipart);

            /* Enviar a mensagem */
            Transport.send(mensagem);

            if (exibirMsgDeAcompanhamento) {
                System.out.println("# E-mail enviado");
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            if (exibirMsgDeAcompanhamento) {
                System.out.println("# FALHA ao enviar o e-mail: ");
            }
            e.printStackTrace();
            this.mensagemDeErro = e.getMessage();
            return false;
        }
        return true;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }
}
