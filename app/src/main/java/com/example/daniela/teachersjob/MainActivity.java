package com.example.daniela.teachersjob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private VagasFragment vagasFragment;
    private ConcursosFragment concursosFragment;
    private DiscoveryFragment discoveryFragment;
    private UsuariosFragment usuariosFragment;
    private ImageView imgUsuarioMenu;
    private TextView nomeUsuarioMenu;
    private TextView emailUsuarioMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (MySingleton.getInstance().getUsuario().getEmail().equalsIgnoreCase("ADMGabrielaBuenoBezerraADM1")) {
           // setContentView(R.layout.activity_main_adm);
          //  usuariosFragment = new UsuariosFragment();
        //}
        //else {
            setContentView(R.layout.activity_main);
            discoveryFragment = new DiscoveryFragment();
        //}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainFrame = findViewById(R.id.mainFrame);
        mainNav = findViewById(R.id.mainNav);
        vagasFragment = new VagasFragment();
        concursosFragment = new ConcursosFragment();

        mainNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragment(vagasFragment);
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_vagas:
                    setFragment(vagasFragment);
                    return true;
                case R.id.navigation_concursos:
                    setFragment(concursosFragment);
                    return true;
                case R.id.navigation_discovery:
                    setFragment(discoveryFragment);
                    return true;
            }
            return false;
        }
    };

    private  void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cadastro) {
        } else if (id == R.id.nav_vagas) {
            startActivity(new Intent(this, SuasVagasActivity.class));
        } else if (id == R.id.nav_concursos) {
            startActivity(new Intent(this, SeusConcursosActivity.class));
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(this, ConfiguracoesActivity.class));
        } else if (id == R.id.nav_infor) {
            startActivity(new Intent(this, InformacaoActivity.class));

        } else if (id == R.id.nav_sair) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Você realmente deseja sair de sua conta?");
            builder.setCancelable(true);
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        imgUsuarioMenu = findViewById(R.id.fotoUsuarioMenu);
        nomeUsuarioMenu = findViewById(R.id.nomeUsuarioMenu);
        emailUsuarioMenu = findViewById(R.id.emailUsuarioMenu);
        nomeUsuarioMenu.setText(MySingleton.getInstance().getUsuario().getNome());
        emailUsuarioMenu.setText(MySingleton.getInstance().getUsuario().getEmail());
        MySingleton.getInstance().setBitmapImageFromString(MySingleton.getInstance().getUsuario().getFoto(), imgUsuarioMenu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
