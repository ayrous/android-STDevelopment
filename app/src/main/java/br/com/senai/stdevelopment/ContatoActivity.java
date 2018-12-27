package br.com.senai.stdevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senai.stdevelopment.modelo.Mensagem;
import br.com.senai.stdevelopment.modelo.MensagemDAO;

public class ContatoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button enviar;
    private EditText sugestoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enviar = findViewById(R.id.btnEnviar);
        sugestoes = findViewById(R.id.editSugestoes);

        final MensagemHelper help = new MensagemHelper(ContatoActivity.this);
        Intent intent = getIntent();
        final Mensagem mensagem = (Mensagem) intent.getSerializableExtra("mensagens");

        if (mensagem != null) {
            help.preencherMensagem(mensagem);
        }

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sugestoes.getText().length() != 0){
                    Intent intent = new Intent(ContatoActivity.this, MensagemActivity.class);
                    startActivity(intent);
                    Mensagem mensagem1 = help.pegarMensagem();
                    MensagemDAO dao = new MensagemDAO(ContatoActivity.this);

                    if (mensagem1.getId() != null) {
                        dao.editar(mensagem1);
                        Toast.makeText(getApplicationContext(), "Sua mensagem foi editada com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        dao.salvar(mensagem1);
                        Toast.makeText(getApplicationContext(), "Sua mensagem foi enviada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                    dao.close();

                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Insira uma mensagem!", Toast.LENGTH_LONG).show();
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContatoActivity.this, MensagemActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.contato, menu);
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

        if (id == R.id.nav_login3) {
            Intent intent = new Intent(ContatoActivity.this, TelaPrincipalActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_sobreNos3) {
            Intent intent = new Intent(ContatoActivity.this, SobreNosActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_historia3) {
            Intent intent = new Intent(ContatoActivity.this, HistoriaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contato3) {
            Toast.makeText(getApplicationContext(), "Você já está nessa tela!", Toast.LENGTH_LONG).show();


        } else if (id == R.id.nav_mensagem3) {
            Intent intent = new Intent(ContatoActivity.this, MensagemActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
