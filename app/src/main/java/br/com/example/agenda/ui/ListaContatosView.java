package br.com.example.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.example.agenda.dao.ContatosDAO;
import br.com.example.agenda.model.Contato;
import br.com.example.agenda.ui.adapter.ListaContatosAdapter;

public class ListaContatosView {

    private final ListaContatosAdapter adapter;
    private final ContatosDAO dao;
    private final Context context;

    public ListaContatosView(Context context) {
        this.context = context;
        this.adapter = new ListaContatosAdapter(context);
        this.dao = new ContatosDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo Contato =(")
                .setMessage("Você tem certeza que quer remover o contato?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Contato contatoEscolhido = adapter.getItem(menuInfo.position);
                        remove(contatoEscolhido);
                    }
                })
                .setNegativeButton("NÃO", null)
                .show();
    }

    public void atualizaContatos() {
        adapter.atualiza(dao.todosContatos());
    }

    private void remove(Contato contato) {
        dao.remove(contato);
        adapter.remove(contato);
    }

    public void configuraAdapter(ListView listaDeContatos) {

        listaDeContatos.setAdapter(adapter);
    }
}
