package br.com.senai.stdevelopment.modelo;

import java.io.Serializable;

/**
 * Created by 22853582884 on 09/03/2018.
 */

public class Mensagem implements Serializable{
    private Long id;
    private String mensagem;

    @Override
    public String toString() {
        return getId() + "\n" + "\t\t\t" + getMensagem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
