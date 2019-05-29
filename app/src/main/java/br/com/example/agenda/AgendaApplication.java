package br.com.example.agenda;

import android.app.Application;

import br.com.example.agenda.dao.ContatosDAO;
import br.com.example.agenda.model.Contato;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaContatosParaTeste();
    }

    private void criaContatosParaTeste() {
        ContatosDAO dao = new ContatosDAO();
        Contato contato1 = new Contato("Matheus Augusto", "(11)960183901", "matheus@hotmail.com");
        dao.salva(contato1);
        Contato contato2 = new Contato("Aline Suzan", "(11)947835275", "aline@hotmail.com");
        dao.salva(contato2);
    }
}
