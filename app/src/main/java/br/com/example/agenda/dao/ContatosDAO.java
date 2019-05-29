package br.com.example.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.example.agenda.model.Contato;

public class ContatosDAO {

    private final static List<Contato> contatos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Contato contato) {
        contato.setId(contadorDeIds);
        contatos.add(contato);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Contato contato) {
        Contato contatoEncontrado = buscaContatoPeloId(contato);
        if (contatoEncontrado != null) {
            int posicaoDoContato = contatos.indexOf(contatoEncontrado);
            contatos.set(posicaoDoContato, contato);
        }
    }

    @Nullable
    private Contato buscaContatoPeloId(Contato contato) {
        for (Contato a : contatos) {
            if(a.getId() == contato.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Contato> todosContatos() {
        return new ArrayList<>(contatos);
    }

    public void remove(Contato contato) {
        for (Contato a : contatos) {
            if (a.getId() == contato.getId()) {
                contato = a;
            }
        }
        if(contato != null) {
            contatos.remove(contato);
        }
    }
}
