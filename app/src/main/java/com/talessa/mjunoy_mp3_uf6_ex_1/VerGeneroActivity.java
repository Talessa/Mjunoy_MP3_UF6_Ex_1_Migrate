package com.talessa.mjunoy_mp3_uf6_ex_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.talessa.mjunoy_mp3_uf6_ex_1.model.Persona;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class VerGeneroActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Persona>> {
    Realm realm;
    RealmAdapter adapter;
    ListView listView;
    RealmResults<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_genero);

        Intent intent= getIntent();

        String opcion = intent.getStringExtra("opcion");
        realm=Realm.getDefaultInstance();
        listView=findViewById(R.id.listaview);
        mostrar(opcion);
    }

    private void mostrar(String genero) {
        load();
        personas= personas.where()
                .equalTo("genero",genero)
                .findAll();
        adapter =  new RealmAdapter(this,personas,R.layout.item_persona);
        listView.setAdapter(adapter);

    }

    private void load() {
        personas = realm.where(Persona.class).findAll();
        personas.addChangeListener(this);

        adapter = new RealmAdapter(this,personas,R.layout.item_persona);

    }

    @Override
    public void onChange(RealmResults<Persona> personas) {
        adapter.notifyDataSetChanged();
    }
}
