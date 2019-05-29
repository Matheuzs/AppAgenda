package br.com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.example.agenda.R;
import br.com.example.agenda.model.Contato;

public class ListaContatosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Contato> contatos = new ArrayList<>();

    public ListaContatosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        atribuiValoresDosCampos(position, viewCriada);
        return viewCriada;
    }

    private void atribuiValoresDosCampos(int position, View viewCriada) {
        TextView nome = viewCriada.findViewById(R.id.item_menu_nome);
        nome.setText(contatos.get(position).getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_menu_telefone);
        telefone.setText(contatos.get(position).getTelefone());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_view_contato, parent, false);
    }

    public void atualiza(List<Contato> contatos) {
        this.contatos.clear();
        this.contatos.addAll(contatos);
        notifyDataSetChanged();
    }

    public void remove(Contato contato) {
        contatos.remove(contato);
        notifyDataSetChanged();
    }
}
