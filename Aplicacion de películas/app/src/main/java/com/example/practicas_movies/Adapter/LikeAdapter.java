package com.example.practicas_movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practicas_movies.IntentMovie;
import com.example.practicas_movies.R;
import com.example.practicas_movies.models.MovieListed;
import com.example.practicas_movies.models.likeObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.moviesViewHolder>{


    /*
    Atributos
    */
    public final Context context; //Almacena el contexto donde se ejecutará
    private ArrayList<likeObject> list; //Almacenará las películas a mostrar
    private LikeAdapter.OnItemClickListener listener; //Listener para cuando se haga click
    private static int a;

    //Defino un interface con el OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(likeObject like);
    }

    /*
    Constructor
    */
    public LikeAdapter(Context context) {
        this.list = new ArrayList<likeObject>();
        this.context = context;
        setListener();
    }

    /*
    Asocio al atributo listener el onItemClickListener correspondiente y sobreescribo el método onItemClick pasando como
    argumento una película
    */
    private void setListener () {
        this.listener = new LikeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(likeObject like) {
                Intent newIntent = new Intent(context, IntentMovie.class);
                newIntent.setFlags(newIntent.FLAG_ACTIVITY_NEW_TASK);

                newIntent.putExtra("idPeli", String.valueOf(like.getId())+".0");
                newIntent.putExtra("imagenPeli", like.getPhoto());



                context.startActivity(newIntent);


                //Toast.makeText(context,  String.valueOf(like.getId()), Toast.LENGTH_LONG).show();
            }
        };
    }

    /*
    Sobreescribo onCreateViewHolder, donde  "inflo" la vista de cada item  y devuelve el viewholder que se crea pasándole la vista
    como parámetro
    */
    @Override
    public moviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        if(a%2 == 0){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_item_favourites_1, parent, false);

        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_item_favourites_2, parent, false);

        }

        a++;

        moviesViewHolder tvh = new moviesViewHolder(itemView);
        return tvh;
    }

    /*
    Sobreescribe onViewHolder, haciendo que muestre la película que hay en el arraylist list en la posición que pasamos como
    parámetro
     */
    @Override
    public void onBindViewHolder(moviesViewHolder holder, int position) {

        final likeObject like = list.get(position);
        holder.bindMovie(like, this.listener);
    }

    /*
    Sobreescribe getItemCount devolviendo el número de películas que tenemos en el arraylist list.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /*
    Defino el viewHolder anidado que hereda de Recycler.ViewHolder necesario para que funcione el adaptador
     */
    public class moviesViewHolder extends RecyclerView.ViewHolder {
        /*
        Atributos
         */
        TextView tvName;
        //TextView tvRating;ç
        RatingBar tvRating;
        ImageView tvPortada;

        /*

            Constructor, enlazo los atributos con los elementos del layout
         */
        public moviesViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            //tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            tvPortada = (ImageView) itemView.findViewById(R.id.imageView);
        }

        /*
        Defino un método que servirá para poner los datos de la película en los correspondientes textviews del layout e
        invocará al listener del adaptador cuando se haga click sobre la vista del viewHolder.
         */
        public void bindMovie(final likeObject like, final LikeAdapter.OnItemClickListener listener) {

            if(like.getName().length() > 32){

                tvName.setText(like.getName().substring(0,like.getName().length() - 4) + "...");

            }else{
                tvName.setText(like.getName());
            }
            //tvRating.setText("Puntuación: " + movie.getVote_average() + "/10");


            Picasso.with(itemView.getContext()).load( "https://image.tmdb.org/t/p/w500" +like.getPhoto()).error(R.mipmap.ic_launcher_round).into(tvPortada);



            /*Coloco el Listener a la vista)*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(like);
                }
            });

        }

    }






    /*
    Método que limpia el array list de contenidos, carga los nuevos contenidos que se le pasan por parámetro e invoca a
    notifyDataSetChanged para hacer que se refresque la vista del RecyclerView
     */
    public void swap(List<likeObject> newListMovies) {
        list.clear();
        list.addAll(newListMovies);
        notifyDataSetChanged();
    }





}
