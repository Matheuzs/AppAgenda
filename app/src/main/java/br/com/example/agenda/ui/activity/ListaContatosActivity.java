package br.com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.example.agenda.R;
import br.com.example.agenda.model.Contato;
import br.com.example.agenda.ui.ListaContatosView;

import static br.com.example.agenda.ui.activity.ConstantesActivities.CHAVE_CONTATO;

public class ListaContatosActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Lista de Contatos";

    private ListaContatosView listaContatosView = new ListaContatosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        setTitle(TITLE_APPBAR);
        configuraFabNovoContato();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaContatosView.atualizaContatos();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == item.getItemId()) {
            listaContatosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_contatos_menu, menu);
    }

    private void configuraFabNovoContato() {
        FloatingActionButton botaoNovoContato = findViewById(R.id.activity_lista_contatos_fab_novo_contato);
        botaoNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereContato();
            }
        });
    }

    private void abreFormularioModoInsereContato() {
        startActivity(new Intent(this, FormularioContatoActivity.class));
    }


    private void configuraLista() {
        ListView listaDeContatos = findViewById(R.id.activity_lista_de_contatos_listview);
        listaContatosView.configuraAdapter(listaDeContatos);
        configuraListenerClickPorItem(listaDeContatos);
        registerForContextMenu(listaDeContatos);
    }


    private void configuraListenerClickPorItem(ListView listaDeContatos) {
        listaDeContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contato contatoEscolhido = (Contato) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaContato(contatoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaContato(Contato contato) {
        Intent vaiParaFormularioActivity = new Intent(ListaContatosActivity.this, FormularioContatoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_CONTATO, contato);
        startActivity(vaiParaFormularioActivity);
    }


}
