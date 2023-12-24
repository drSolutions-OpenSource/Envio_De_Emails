package br.com.drsolutions.enviaremail.util;

import br.com.drsolutions.enviaremail.ui.JanelaMensagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Carregar a imagem que será utilizada como fundo da janela
 *
 * @author Diego Mendes Rodrigues
 */
public class CarregarImagem {
    /**
     * Carregar a imagem que será utilizada como fundo da janela
     *
     * @return Image sendo uma imagem
     */
    public static Image Carregar() {
        Image imagem = null;
        JanelaMensagem janelaMensagem = new JanelaMensagem();

        try {
            imagem = ImageIO.read(new File("imagens\\fundo.jpg"));
        } catch (IOException e) {
            janelaMensagem.mensagemErro(null,
                    "Erro: " + e.getMessage(),
                    "Erro ao carregar a imagem de fundo");
            e.printStackTrace();
        }

        return imagem;
    }
}
