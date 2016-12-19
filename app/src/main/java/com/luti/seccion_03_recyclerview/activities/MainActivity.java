package com.luti.seccion_03_recyclerview.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.luti.seccion_03_recyclerview.adapters.ContextMenuRecyclerView;
import com.luti.seccion_03_recyclerview.adapters.MyAdapter;
import com.luti.seccion_03_recyclerview.R;
import com.luti.seccion_03_recyclerview.models.Fruta;
import com.luti.seccion_03_recyclerview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruta> frutas;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayaoutManager;
    private ContextMenu mContext;


    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frutas = this.getAllFrutas();
        mRecyclerView  = (RecyclerView) findViewById(R.id.recyclerView);

        // aqui vamos a cambiar la forma de ver el recycler view como List
        mLayaoutManager = new LinearLayoutManager(this);
        //este para verlo como un Grid!!!
        //mLayaoutManager = new GridLayoutManager(this, 2);
        //Este para verlo como un grid pero de diferente tamaño como por ejemplo fotos!!!, con esto no se podria utilizar el setHasFixedSize de mas abajo
        //mLayaoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(mContext, frutas, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruta frutas, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                //deleteFruta(position);
                añadirFruta(position);

            }

        },
                new MyAdapter.OnItemClickListenerTot() {
                    @Override
                    public void onItemClick(Fruta frutas, int position) {
   /*                    PopupMenu popup = new PopupMenu(mRecyclerView.getContext(), mRecyclerView);
                        popup.inflate(R.menu.menu_ctx_recycler);
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                Log.d("pulsa","Menu");
                                //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                        popup.setGravity(20);
                        popup.show();*/
                        //registerForContextMenu(mRecyclerView);
                    }
                });


        //Si sabemos que el layout el recycler no  va a cambiar el tamaño no va a aumentar es mejor poner esto
        mRecyclerView.setHasFixedSize(true);
        //implementa una animacion por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayaoutManager);
        mRecyclerView.setAdapter(mAdapter);
        // Registrar el context menu para ambos


    }

    //Para añadir los options en el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                this.addFruta(frutas.size());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    //Context Menu para cada item
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Obtener info en el context menu del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.CtxRecOpc1:
                this.deleteFruta(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    private List<Fruta> getAllFrutas(){
        return  new ArrayList<Fruta>(){{
            add(new Fruta("Pera","Fruta de agua", R.drawable.pera,R.drawable.ic_pera,1 ));
            add(new Fruta("Naranja","Fruta de Valencia", R.drawable.naranja,R.drawable.ic_orange,1));
            add(new Fruta("Kiwi","Fruta con pelo", R.drawable.kiwi,R.drawable.ic_kiwi,1));
            add(new Fruta("Platano","Fruta de Canarias", R.drawable.platano,R.drawable.ic_banana,1));

        }};
    }

   private void addFruta(int position){
        frutas.add(position, new Fruta("Nueva Fruta" + (++counter), "Fruta añadida" ,R.drawable.newmovie, 1, R.drawable.ic_orange ) );
        mAdapter.notifyItemInserted(position);
        //añadir un codigo
        mLayaoutManager.scrollToPosition(position);

    }

    private void deleteFruta(int position){
        frutas.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void añadirFruta(int position){
        counter = frutas.get(position).getContador() + 1;
        frutas.get(position).setContador(counter);
        mAdapter.notifyItemChanged(position, frutas);
        counter = 0;
    }
}
