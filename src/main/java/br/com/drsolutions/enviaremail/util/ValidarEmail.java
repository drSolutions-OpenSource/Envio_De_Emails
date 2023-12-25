package br.com.drsolutions.enviaremail.util;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Validar se o endereço de e-mail informado é válido
 *
 * @author Diego Mendes Rodrigues
 */
public class ValidarEmail {
    /**
     * Validar se o endereço de e-mail informado é válido
     *
     * @param email String sendo o e-mail que será validado
     * @return boolean sendo true caso o e-mail seja válido, false caso contrário
     */
    public boolean validar(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
