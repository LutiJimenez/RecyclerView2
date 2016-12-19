package com.luti.seccion_03_recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luti.seccion_03_recyclerview.R;
import com.luti.seccion_03_recyclerview.activities.MainActivity;
import com.luti.seccion_03_recyclerview.models.Fruta;
import com.luti.seccion_03_recyclerview.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luti on 4/12/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Fruta> frutas;
    private int layout;
    private OnItemClickListener itemClickListener;
    private  OnItemClickListenerTot itemClickListenerTot;
    private Context context;
    private ContextMenu mContext;
    private int position;


    public MyAdapter(ContextMenu mContext, List<Fruta> frutas, int layout, OnItemClickListener listener, OnItemClickListenerTot listenerTot){
        this.frutas = frutas;
        this.layout = layout;
        this.itemClickListener = listener;
        this.itemClickListenerTot = listenerTot;
        this.mContext = mContext;


    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        holder.bind(mContext, frutas.get(position), itemClickListener, itemClickListenerTot);
        //holder.itemView.setLongClickable(true);

    }

    @Override
    public int getItemCount() {
        return frutas.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewDesc;
        public ImageView imageViewPoster;
        public ImageView imageViewIcon;
        public TextView editTextCont;


        //Constructor
        public ViewHolder(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.icono);
            editTextCont = (TextView) itemView.findViewById(R.id.editTextCont);
            //this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);

        }






        public void bind(final ContextMenu contextMenu, final Fruta fruta, final OnItemClickListener listener, final OnItemClickListenerTot listenerTot){
            //Procesamos los datos a renderizar
            textViewName.setText(fruta.getName());
            textViewDesc.setText(fruta.getDescription());
            //imageViewPoster.setImageResource(fruta.getImagen());
            Picasso.with(context).load(fruta.getImagen()).into(imageViewPoster);
            //Picasso.with(context).load(fruta.getImagen()).fit().into(imageViewPoster);
            Picasso.with(context).load(fruta.getIcono()).fit().into(imageViewIcon);
            //Contador roto
            editTextCont.setText(String.valueOf(fruta.getContador()));
            //Libreria Picasso para tratamiento de imagenes
            imageViewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fruta, getAdapterPosition());
                }
            });
            //imageViewPoster.setImageResource(movie.getPoster());
            //this.textViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   PopupMenu popup = new PopupMenu(view.getContext(), view);
                    popup.inflate(R.menu.menu_ctx_recycler);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            Log.d("pulsa","Menu");

                            //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    popup.show();

                    listenerTot.onItemClick(fruta, getAdapterPosition());
                }
            });


        }



    }



    public  interface OnItemClickListener{
        void onItemClick(Fruta frutas, int position);
    }

    public  interface OnItemClickListenerTot{
        void onItemClick(Fruta frutas, int position);
    }

    private void deleteFruta(int position){
        frutas.remove(position);
        //mAdapter.notifyItemRemoved(position);
    }







}

