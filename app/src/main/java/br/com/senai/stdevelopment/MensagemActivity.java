package br.com.senai.stdevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senai.stdevelopment.modelo.Mensagem;
import br.com.senai.stdevelopment.modelo.MensagemDAO;

public class MensagemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaMensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listaMensagens = findViewById(R.id.listMensagens);
        listaMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mensagem mensagem = (Mensagem) listaMensagens.getItemAtPosition(i);
                Intent intent = new Intent(MensagemActivity.this, ContatoActivity.class);
                intent.putExtra("mensagens", mensagem);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Você já está nessa tela!", Toast.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        registerForContextMenu(listaMensagens);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mensagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Opção desabilitada!", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login4) {
            Intent intent = new Intent(MensagemActivity.this, TelaPrincipalActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_sobreNos4) {
            Intent intent = new Intent(MensagemActivity.this, SobreNosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_historia4) {
            Intent intent = new Intent(MensagemActivity.this, HistoriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contato4) {
            Intent intent = new Intent(MensagemActivity.this, ContatoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_mensagem4) {
            Toast.makeText(getApplicationContext(), "Você já está nessa tela!", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem remover = menu.add("Remover");
        MenuItem editar = menu.add("Editar");

        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Mensagem mensagem = (Mensagem) listaMensagens.getItemAtPosition(info.position);

                MensagemDAO dao = new MensagemDAO(MensagemActivity.this);
                dao.remover(mensagem);
                dao.close();
                carregarLista();
                Toast.makeText(getApplicationContext(), "Mensagem removida com sucesso de Sua Vida", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(), "Clique em cime de sua mensagem para edita-la!", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    private void carregarLista(){
        MensagemDAO dao = new MensagemDAO(this);

        List<Mensagem> mensagens = dao.buscaMensagens();
        ArrayAdapter<Mensagem> adaptador = new ArrayAdapter<Mensagem>(this, android.R.layout.simple_list_item_1, mensagens);

        listaMensagens.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
