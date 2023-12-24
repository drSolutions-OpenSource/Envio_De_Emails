package br.com.drsolutions.enviaremail.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Exibir janelas com caixas de diálogo e mensagens no sistema
 *
 * @author Diego Mendes Rodrigues
 */
public class JanelaMensagem {
    /**
     * Exibir uma janela de mensagem de erro
     *
     * @param parentComponent componente do qual essa janela será derivada
     * @param mensagem        sendo a mensagem
     * @param titulo          sendo o título da janela
     */
    public void mensagemErro(Component parentComponent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parentComponent, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Eximir uma janela com uma mensagem de aviso
     *
     * @param parentComponent componente do qual essa janela será derivada
     * @param mensagem        sendo a mensagem
     * @param titulo          sendo o título da janela
     */
    public void mensagemAviso(Component parentComponent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parentComponent, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Exibir uma janela com uma questão
     *
     * @param parentComponent componente do qual essa janela será derivada
     * @param mensagem        sendo a mensagem
     * @param titulo          sendo o título da janela
     */
    public void mensagemQuestao(Component parentComponent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parentComponent, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Exibir uma janela com uma mensagem
     *
     * @param parentComponent componente do qual essa janela será derivada
     * @param mensagem        sendo a mensagem
     * @param titulo          sendo o título da janela
     */
    public void mensagem(Component parentComponent, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(parentComponent, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Exibir uma janela com uma confirmação
     *
     * @param parentComponent componente do qual essa janela será derivada
     * @param mensagem        sendo a mensagem
     */
    public void caixaDeConfirmacao(Component parentComponent, String mensagem) {
        JOptionPane.showMessageDialog(parentComponent, mensagem);
    }
}
