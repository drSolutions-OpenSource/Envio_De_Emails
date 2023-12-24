package br.com.drsolutions.enviaremail.ui;

import br.com.drsolutions.enviaremail.envio.EnviarEmail;
import br.com.drsolutions.enviaremail.util.CarregarImagem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

/**
 * Tela do Sistema para o Envio de E-mails
 *
 * @author Diego Mendes Rodrigues
 */
public class TelaPrincipal extends JDialog {
    /* Painel com os componentes da tela */
    final Image imagemFundoTela = CarregarImagem.Carregar();
    JPanel painel = new JPanel(new GridBagLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagemFundoTela, 0, 0, null);
        }
    };

    /* Componentes da tela */
    JLabel tituloTelaEnviar = new JLabel("Envio de E-mails");
    JLabel paraTitulo = new JLabel("Para");
    JLabel paraCcTitulo = new JLabel("Cópia");
    JLabel paraCcoTitulo = new JLabel("Cópia Oculta");
    JLabel assuntoTitulo = new JLabel("Assunto");
    JLabel anexoTitulo = new JLabel("Anexo");
    JLabel mensagemTitulo = new JLabel("Mensagem");
    JLabel linhaEmBranco = new JLabel(" ");
    JLabel linhaEmBranco2 = new JLabel(" ");
    JLabel linhaEmBranco3 = new JLabel(" ");
    JLabel linhaEmBranco4 = new JLabel(" ");
    JLabel linhaEmBranco5 = new JLabel(" ");
    JLabel linhaEmBranco6 = new JLabel(" ");
    JTextField para = new JTextField();
    JTextField paraCc = new JTextField();
    JTextField paraCco = new JTextField();
    JTextField assunto = new JTextField();
    JTextField anexoArquivo = new JTextField();
    JTextArea mensagem = new JTextArea();
    JButton btnEnviar = new JButton("Enviar o e-mail");
    JButton btnAnexar = new JButton("Arquivo");
    JFileChooser selecionarAnexo = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JanelaMensagem janelaMensagem = new JanelaMensagem();

    public TelaPrincipal() {
        Locale localeBrasil = new Locale("pt", "BR");
        Locale.setDefault(localeBrasil);

        /* Propriedades básicas da tela */
        setTitle(tituloTelaEnviar.getText());
        setSize(new Dimension(600, 680));
        setLocationRelativeTo(null);
        setResizable(false);
        painel.setBorder(new EmptyBorder(0, 20, 10, 20));

        /* Controlador para o posicionamento dos componentes na Tela */
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;

        /* Componentes da Tela */
        paraTitulo.setPreferredSize(new Dimension(100, 25));
        painel.add(paraTitulo, gridBagConstraints);

        gridBagConstraints.gridx++;
        para.setPreferredSize(new Dimension(445, 25));
        para.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.add(para, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx--;
        linhaEmBranco.setPreferredSize(new Dimension(545, 15));
        painel.add(linhaEmBranco, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy++;
        paraCcTitulo.setPreferredSize(new Dimension(100, 25));
        painel.add(paraCcTitulo, gridBagConstraints);

        gridBagConstraints.gridx++;
        paraCc.setPreferredSize(new Dimension(445, 25));
        paraCc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.add(paraCc, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx--;
        linhaEmBranco2.setPreferredSize(new Dimension(445, 15));
        painel.add(linhaEmBranco2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy++;
        paraCcoTitulo.setPreferredSize(new Dimension(100, 25));
        painel.add(paraCcoTitulo, gridBagConstraints);

        gridBagConstraints.gridx++;
        paraCco.setPreferredSize(new Dimension(445, 25));
        paraCco.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.add(paraCco, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx--;
        linhaEmBranco3.setPreferredSize(new Dimension(545, 15));
        painel.add(linhaEmBranco3, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy++;
        assuntoTitulo.setPreferredSize(new Dimension(100, 25));
        painel.add(assuntoTitulo, gridBagConstraints);

        gridBagConstraints.gridx++;
        assunto.setPreferredSize(new Dimension(445, 25));
        assunto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.add(assunto, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx--;
        linhaEmBranco4.setPreferredSize(new Dimension(545, 15));
        painel.add(linhaEmBranco4, gridBagConstraints);

        gridBagConstraints.gridy++;
        anexoTitulo.setPreferredSize(new Dimension(545, 25));
        painel.add(anexoTitulo, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        btnAnexar.setPreferredSize(new Dimension(90, 25));
        painel.add(btnAnexar, gridBagConstraints);

        /* Configurações da tela onde o usuário seleciona o arquivo que será anexado */
        selecionarAnexo.setApproveButtonText("Selecionar arquivo");
        selecionarAnexo.setDialogTitle("Arquivo Anexado ao E-mail");

        /*
         * Ação do botão 'Anexar um arquivo'
         * Quando o usuário seleciona um arquivo, o campo 'anexoArquivo' é preenchido com esse arquivo
         */
        btnAnexar.addActionListener(e -> {
            int resposta = selecionarAnexo.showOpenDialog(null);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                anexoArquivo.setText(selecionarAnexo.getSelectedFile().getAbsolutePath());
            } else {
                anexoArquivo.setText("");
            }
        });

        gridBagConstraints.gridx++;
        anexoArquivo.setPreferredSize(new Dimension(443, 25));
        anexoArquivo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        anexoArquivo.setEditable(false);
        painel.add(anexoArquivo, gridBagConstraints);

        gridBagConstraints.gridx--;
        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.gridy++;
        linhaEmBranco5.setPreferredSize(new Dimension(545, 15));
        painel.add(linhaEmBranco5, gridBagConstraints);

        gridBagConstraints.gridy++;
        mensagemTitulo.setPreferredSize(new Dimension(545, 25));
        painel.add(mensagemTitulo, gridBagConstraints);

        gridBagConstraints.gridy++;
        mensagem.setPreferredSize(new Dimension(545, 280));
        mensagem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        painel.add(mensagem, gridBagConstraints);

        gridBagConstraints.gridy++;
        linhaEmBranco6.setPreferredSize(new Dimension(545, 25));
        painel.add(linhaEmBranco6, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx--;
        gridBagConstraints.gridy++;
        btnEnviar.setPreferredSize(new Dimension(180, 35));
        btnEnviar.isDefaultButton();
        btnEnviar.setMnemonic('E');
        Icon sendIcon = new ImageIcon("imagens\\iconeEnviarEmail.png");
        btnEnviar.setIcon(sendIcon);
        painel.add(btnEnviar, gridBagConstraints);

        /*
         * Ação do botão 'Enviar e-mail'
         * - Valida se existe um destinatário, se o assunto está preenchido e se a mensagem está preenchida
         * - Enviar o e-mail
         * - Avisa ao usuário que o e-mail foi enviado
         * - Limpa os campos da tela
         */
        btnEnviar.addActionListener(e -> {
            if (validarCampos()) {
                bloquearCampos(true);
                EnviarEmail enviarEmail = new EnviarEmail();
                enviarEmail.conectarSMTP();
                if (enviarEmail.enviarComAnexo(para.getText(), paraCc.getText(), paraCco.getText(), assunto.getText(),
                        mensagem.getText().replaceAll("\\n", "<br/>"),
                        anexoArquivo.getText())) {
                    limparCampos();
                    janelaMensagem.caixaDeConfirmacao(null, "O e-mail foi enviado!");
                } else {
                    janelaMensagem.mensagemErro(null,
                            "Erro: " + enviarEmail.getMensagemDeErro(),
                            "Erro ao enviar o e-mail");
                }
                bloquearCampos(false);
                para.grabFocus();
            }
        });

        /* Adicionar o painel na tela */
        add(painel, BorderLayout.WEST);

        /* Fechar o sistema quando clicar o X */
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        /* Exibir a tela */
        setVisible(true);
        para.grabFocus();
    }

    /**
     * Validar se os campos Para, Assunto e Mensagem foram preenchidos
     *
     * @return true caso os campos tenham sido preenchidos, e false caso contrário
     */
    private boolean validarCampos() {
        if (para.getText().isEmpty()) {
            janelaMensagem.mensagemAviso(this, "O campo 'Para' precisa ser preenchido",
                    "Campo obrigatório");
            return false;
        } else if (assunto.getText().isEmpty()) {
            janelaMensagem.mensagemAviso(this, "O campo 'Assunto' precisa ser preenchido",
                    "Campo obrigatório");
            return false;
        } else if (mensagem.getText().isEmpty()) {
            janelaMensagem.mensagemAviso(this, "O campo 'Mensagem' precisa ser preenchido",
                    "Campo obrigatório");
            return false;
        }
        return true;
    }

    /**
     * Bloquear ou desbloquear os elementos da tela
     *
     * @param bloquear true irá bloquear os elementos da tela, falses irá desbloquear
     */
    private void bloquearCampos(boolean bloquear) {
        para.setEnabled(!bloquear);
        paraCc.setEnabled(!bloquear);
        paraCco.setEnabled(!bloquear);
        assunto.setEnabled(!bloquear);
        anexoArquivo.setEnabled(!bloquear);
        mensagem.setEnabled(!bloquear);
        btnAnexar.setEnabled(!bloquear);
    }

    /**
     * Limpar todos os campos da tela após o envio do e-mail
     */
    private void limparCampos() {
        para.setText("");
        paraCc.setText("");
        paraCco.setText("");
        assunto.setText("");
        anexoArquivo.setText("");
        mensagem.setText("");
    }
}
