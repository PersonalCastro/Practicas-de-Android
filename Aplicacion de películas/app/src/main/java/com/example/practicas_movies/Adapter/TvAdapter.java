package com.example.practicas_movies.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practicas_movies.R;
import com.example.practicas_movies.models.MovieListed;
import com.example.practicas_movies.models.TVShowListed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.moviesViewHolder>{


    /*
    Atributos
    */
    public final Context context; //Almacena el contexto donde se ejecutará
    private ArrayList<TVShowListed> list; //Almacenará las películas a mostrar
    private TvAdapter.OnItemClickListener listener; //Listener para cuando se haga click

    //Defino un interface con el OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(TVShowListed tvShow);
    }

    /*
    Constructor
    */
    public TvAdapter(Context context) {
        this.list = new ArrayList<TVShowListed>();
        this.context = context;
        setListener();

    }

    /*
    Asocio al atributo listener el onItemClickListener correspondiente y sobreescribo el método onItemClick pasando como
    argumento una película
    */
    private void setListener () {
        this.listener = new TvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TVShowListed tvShow) {
                /*Intent newIntent = new Intent(context,DetailsMovie.class);
                newIntent.setFlags(newIntent.FLAG_ACTIVITY_NEW_TASK);

                newIntent.putExtra("nombrePeli", movie.getTitle());
                newIntent.putExtra("puntuacionPeli", String.valueOf(movie.getVote_average()) );
                newIntent.putExtra("idPeli", String.valueOf(movie.getId()));


                context.startActivity(newIntent);*/


                Toast.makeText(context,  tvShow.getName(), Toast.LENGTH_LONG).show();
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

        moviesViewHolder tvh = new TvAdapter.moviesViewHolder(itemView);
        return tvh;
    }

    /*
    Sobreescribe onViewHolder, haciendo que muestre la película que hay en el arraylist list en la posición que pasamos como
    parámetro
     */
    @Override
    public void onBindViewHolder(TvAdapter.moviesViewHolder holder, int position) {

        final TVShowListed tvShow = list.get(position);
        Log.i("Mi Id:", tvShow.getName() + "  " + tvShow.getId());
        holder.bindMovie(tvShow, this.listener);
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
        //TextView tvRating;
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
        public void bindMovie(final TVShowListed tvShow, final TvAdapter.OnItemClickListener listener) {

            if (tvShow.getName().length() > 32) {

                tvName.setText(tvShow.getName().substring(0, tvShow.getName().length() - 4) + "...");

            } else {
                tvName.setText(tvShow.getName());
            }
            //tvRating.setText("Puntuación: " + movie.getVote_average() + "/10");

            tvRating.setRating(tvShow.getVote_average() / 2);


            Picasso.with(itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + tvShow.getPoster_path()).error(R.mipmap.ic_launcher_round).into(tvPortada);

            //TODO: Conseguir mostrar imagen "error"
            /*if(tvPortada.getTag().equals((R.mipmap.ic_launcher_round))){
                //tvPortada.setScaleType(ImageView.ScaleType.CENTER);
            }else{
                //tvPortada.setScaleType(ImageView.ScaleType.FIT_XY);
            }*/


            /*Coloco el Listener a la vista)*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tvShow);
                }
            });

        }


    }


    /*
    Método que limpia el array list de contenidos, carga los nuevos contenidos que se le pasan por parámetro e invoca a
    notifyDataSetChanged para hacer que se refresque la vista del RecyclerView
     */
    public void swap(List<TVShowListed> newListTv) {
        list.clear();
        list.addAll(newListTv);
        notifyDataSetChanged();
    }


}