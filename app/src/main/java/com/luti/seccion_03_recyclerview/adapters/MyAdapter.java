package com.luti.seccion_03_recyclerview.adapters;

import android.app.Activity;
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
    private Activity activity;
    private int position;

//Añado el activity para poder utilizar el context menu
    public MyAdapter(ContextMenu mContext, List<Fruta> frutas, int layout,Activity activity , OnItemClickListener listener, OnItemClickListenerTot listenerTot){
        this.frutas = frutas;
        this.layout = layout;
        this.itemClickListener = listener;
        this.itemClickListenerTot = listenerTot;
        this.activity = activity;
        this.mContext = mContext;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        //context = parent.getContext();
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

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
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
            //añadimos el listener para el context menu
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final ContextMenu contextMenu, final Fruta fruta, final OnItemClickListener listener, final OnItemClickListenerTot listenerTot){
            //Procesamos los datos a renderizar
            this.textViewName.setText(fruta.getName());
            this.textViewDesc.setText(fruta.getDescription());
            //imageViewPoster.setImageResource(fruta.getImagen());
            Picasso.with(activity).load(fruta.getImagen()).fit().into(this.imageViewPoster);
            //Picasso.with(context).load(fruta.getImagen()).fit().into(imageViewPoster);
            Picasso.with(activity).load(fruta.getIcono()).fit().into(imageViewIcon);
            //Contador roto
            this.editTextCont.setText(fruta.getContador()+"");
            //Libreria Picasso para tratamiento de imagenes
            this.imageViewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fruta, getAdapterPosition());
                }
            });
            //imageViewPoster.setImageResource(movie.getPoster());
            //this.textViewName.setText(name);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                   PopupMenu popup = new PopupMenu(view.getContext(), view);
//                    popup.inflate(R.menu.menu_ctx_recycler);
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        public boolean onMenuItemClick(MenuItem item) {
//                            Log.d("pulsa","Menu");
//
//                            //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
//                    });
//                    popup.show();
//
//                    listenerTot.onItemClick(fruta, getAdapterPosition());
//                }
//            });
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
            Fruta fruitSelectted = frutas.get(this.getAdapterPosition());
            contextMenu.setHeaderTitle(fruitSelectted.getName());
            contextMenu.setHeaderIcon(fruitSelectted.getIcono());
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.menu_ctx_recycler, contextMenu);
            for(int i = 0; i < contextMenu.size();i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);

        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem){
            switch (menuItem.getItemId()) {
                case R.id.delete:
                    frutas.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset:
                    frutas.get(getAdapterPosition()).setContadorCero();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;

            }
        }



    }



    public  interface OnItemClickListener{
        void onItemClick(Fruta frutas, int position);
    }

    public  interface OnItemClickListenerTot{
        void onItemClick(Fruta frutas, int position);
    }








}

