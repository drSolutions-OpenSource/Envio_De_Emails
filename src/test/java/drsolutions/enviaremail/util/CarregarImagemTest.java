package drsolutions.enviaremail.util;

import br.com.drsolutions.enviaremail.util.CarregarImagem;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;

/**
 * Testes na classe CarregarImagem
 *
 * @author Diego Mendes Rodrigues
 */
public class CarregarImagemTest {
    @Test
    public void Carregar() {
        Image imagem = CarregarImagem.Carregar();
        assertFalse(imagem.getSource().toString().isEmpty());
    }
}
