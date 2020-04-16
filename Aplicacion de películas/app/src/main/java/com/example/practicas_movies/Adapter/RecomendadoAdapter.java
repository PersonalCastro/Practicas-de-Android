package com.example.practicas_movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecomendadoAdapter extends RecyclerView.Adapter<RecomendadoAdapter.moviesViewHolder>{


    /*
    Atributos
    */
    public final Context context; //Almacena el contexto donde se ejecutará
    private ArrayList<MovieListed> list; //Almacenará las películas a mostrar
    private RecomendadoAdapter.OnItemClickListener listener; //Listener para cuando se haga click

    //Defino un interface con el OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(MovieListed movie);
    }

    /*
    Constructor
    */
    public RecomendadoAdapter(Context context) {
        this.list = new ArrayList<MovieListed>();
        this.context = context;
        setListener();

    }

    /*
    Asocio al atributo listener el onItemClickListener correspondiente y sobreescribo el método onItemClick pasando como
    argumento una película
    */
    private void setListener () {
        this.listener = new RecomendadoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieListed movie) {
                Intent newIntent = new Intent(context, IntentMovie.class);
                newIntent.setFlags(newIntent.FLAG_ACTIVITY_NEW_TASK);

                newIntent.putExtra("idPeli", String.valueOf(movie.getId()));
                newIntent.putExtra("imagenPeli", movie.getPoster_path());



                context.startActivity(newIntent);


                //Toast.makeText(context,  movie.getTitle(), Toast.LENGTH_LONG).show();
            }
        };
    }

    /*
    Sobreescribo onCreateViewHolder, donde  "inflo" la vista de cada item  y devuelve el viewholder que se crea pasándole la vista
    como parámetro
    */
    @Override
    public moviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_item, parent, false);

        moviesViewHolder tvh = new moviesViewHolder(itemView);
        return tvh;
    }

    /*
    Sobreescribe onViewHolder, haciendo que muestre la película que hay en el arraylist list en la posición que pasamos como
    parámetro
     */
    @Override
    public void onBindViewHolder(moviesViewHolder holder, int position) {

        final MovieListed movie = list.get(position);
        Log.i("Mi Id:", movie.getTitle() + "  " + movie.getId());
        holder.bindMovie(movie, this.listener);
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
            tvRating = (RatingBar) itemView.findViewById(R.id.ratingBar2);
            tvPortada = (ImageView) itemView.findViewById(R.id.imageView);
        }

        /*
        Defino un método que servirá para poner los datos de la película en los correspondientes textviews del layout e
        invocará al listener del adaptador cuando se haga click sobre la vista del viewHolder.
         */
        public void bindMovie(final MovieListed movie, final RecomendadoAdapter.OnItemClickListener listener) {

            if(movie.getTitle().length() > 32){

                tvName.setText(movie.getTitle().substring(0,movie.getTitle().length() - 4) + "...");

            }else{
                tvName.setText(movie.getTitle());
            }
            //tvRating.setText("Puntuación: " + movie.getVote_average() + "/10");

            tvRating.setRating(movie.getVote_average() / 2);


            Picasso.with(itemView.getContext()).load( "https://image.tmdb.org/t/p/w500" + movie.getPoster_path()).error(R.mipmap.ic_launcher_round).into(tvPortada);

            //TODO: Conseguir mostrar imagen "error"


            /*if(tvPortada.getTag().equals((R.mipmap.ic_launcher_round))){
                //tvPortada.setScaleType(ImageView.ScaleType.CENTER);
            }else{
                //tvPortada.setScaleType(ImageView.ScaleType.FIT_XY);
            }*/


            /*Coloco el Listener a la vista)*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });

        }

    }






    /*
    Método que limpia el array list de contenidos, carga los nuevos contenidos que se le pasan por parámetro e invoca a
    notifyDataSetChanged para hacer que se refresque la vista del RecyclerView
     */
    public void swap(List<MovieListed> newListMovies) {
        list.clear();
        list.addAll(newListMovies);
        notifyDataSetChanged();
    }





}
