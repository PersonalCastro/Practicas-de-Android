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
import com.example.practicas_movies.models.CreditsListed;
import com.example.practicas_movies.models.MovieListed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.moviesViewHolder>{


    /*
    Atributos
    */
    public final Context context; //Almacena el contexto donde se ejecutará
    private ArrayList<CreditsListed> list; //Almacenará las películas a mostrar
    private ActorsAdapter.OnItemClickListener listener; //Listener para cuando se haga click

    //Defino un interface con el OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(CreditsListed credits);
    }

    /*
    Constructor
    */
    public ActorsAdapter(Context context) {
        this.list = new ArrayList<CreditsListed>();
        this.context = context;
        setListener();

    }

    /*
    Asocio al atributo listener el onItemClickListener correspondiente y sobreescribo el método onItemClick pasando como
    argumento una película
    */
    private void setListener () {
        this.listener = new ActorsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CreditsListed credits) {
                /*Intent newIntent = new Intent(context, IntentMovie.class);
                newIntent.setFlags(newIntent.FLAG_ACTIVITY_NEW_TASK);

                newIntent.putExtra("idPeli", String.valueOf(movie.getId()));
                newIntent.putExtra("imagenPeli", movie.getPoster_path());



                context.startActivity(newIntent);*/


                Toast.makeText(context,  credits.getName(), Toast.LENGTH_LONG).show();
            }
        };
    }

    /*
    Sobreescribo onCreateViewHolder, donde  "inflo" la vista de cada item  y devuelve el viewholder que se crea pasándole la vista
    como parámetro
    */
    @Override
    public moviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_actors_item, parent, false);

        moviesViewHolder tvh = new moviesViewHolder(itemView);
        return tvh;
    }

    /*
    Sobreescribe onViewHolder, haciendo que muestre la película que hay en el arraylist list en la posición que pasamos como
    parámetro
     */
    @Override
    public void onBindViewHolder(moviesViewHolder holder, int position) {

        final CreditsListed movie = list.get(position);
        Log.i("Mi Id:", movie.getName() + "  " + movie.getId());
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
        TextView actorName;
        ImageView actorPhoto;

        /*

            Constructor, enlazo los atributos con los elementos del layout
         */
        public moviesViewHolder(View itemView) {
            super(itemView);
            actorName = (TextView) itemView.findViewById(R.id.textViewName);
            actorPhoto = (ImageView) itemView.findViewById(R.id.imageActor);
        }

        /*
        Defino un método que servirá para poner los datos de la película en los correspondientes textviews del layout e
        invocará al listener del adaptador cuando se haga click sobre la vista del viewHolder.
         */
        public void bindMovie(final CreditsListed movie, final ActorsAdapter.OnItemClickListener listener) {

            actorName.setText(movie.getName());
            Picasso.with(itemView.getContext()).load( "https://image.tmdb.org/t/p/w500" + movie.getProfile_path()).error(R.mipmap.ic_launcher_round).into(actorPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });

        }

    }


    public void swap(List<CreditsListed> newListMovies) {
        list.clear();
        list.addAll(newListMovies);
        notifyDataSetChanged();
    }





}
