package br.com.senai.stdevelopment;

import android.widget.EditText;

import br.com.senai.stdevelopment.modelo.Mensagem;

/**
 * Created by 22853582884 on 09/03/2018.
 */

public class MensagemHelper {
    public EditText mensagens;
    public Mensagem mensagem;

    public MensagemHelper(ContatoActivity activity) {

        mensagens = activity.findViewById(R.id.editSugestoes);

        mensagem = new Mensagem();

    }

    public Mensagem pegarMensagem(){
        mensagem.setMensagem(mensagens.getText().toString());
        return mensagem;
    }

    public void preencherMensagem(Mensagem mensagem){

        mensagens.setText(mensagem.getMensagem());

        this.mensagem = mensagem;
    }

}
