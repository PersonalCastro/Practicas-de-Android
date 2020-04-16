package com.example.practicas_movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicas_movies.Adapter.ActorsAdapter;
import com.example.practicas_movies.Adapter.RecomendadoAdapter;
import com.example.practicas_movies.Sqlite.SqliteConnector;
import com.example.practicas_movies.models.CreditsFeed;
import com.example.practicas_movies.models.Genre;
import com.example.practicas_movies.models.MovieDetail;
import com.example.practicas_movies.models.MovieFeed;
import com.example.practicas_movies.models.MovieListed;
import com.example.practicas_movies.models.ProductionCompany;
import com.example.practicas_movies.models.Result;
import com.example.practicas_movies.models.TVShowFeed;
import com.example.practicas_movies.models.Trailer;
import com.example.practicas_movies.retrofit.MovieService;
import com.example.practicas_movies.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntentMovie extends AppCompatActivity {

    ImageView portada, goback, like, likeLiked;
    TextView titulo, descripcion, estudio, genero, lanzamiento;
    RatingBar ratingBar;
    Button whatchNow;
    RecyclerView actores;
    ActorsAdapter actoresAdapter;
    public SqliteConnector conectorF;


    public Context context; //Almacena el contexto donde se ejecutará
    public String id_peli;
    public String imagen_peli;
    public String youtubeTrailerKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas_view_layout);

        context = this;

        portada = (ImageView) findViewById(R.id.imageViewPortada);
        goback = (ImageView) findViewById(R.id.imagegoBack);
        like = (ImageView) findViewById(R.id.imageFavourite);
        likeLiked = (ImageView) findViewById(R.id.imageFavourite2);



        titulo = (TextView) findViewById(R.id.textTitle);
        descripcion = (TextView) findViewById(R.id.textViewDescription);
        estudio = (TextView) findViewById(R.id.textViewStudio);
        genero = (TextView) findViewById(R.id.textViewGenre);
        lanzamiento = (TextView) findViewById(R.id.textViewRelease);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        whatchNow = (Button) findViewById(R.id.buttonWatchNow);

        actores = (RecyclerView) findViewById(R.id.actores);

        conectorF = new SqliteConnector(getApplicationContext(),"movie",null, 1);


        /*   RECYCLERVIEW Search   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        actores = (RecyclerView) findViewById(R.id.actores);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        actores.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        actores.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        actoresAdapter = new ActorsAdapter(this.getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        actores.setAdapter(actoresAdapter);
        //Pone la animación por defecto de recyclerView
        actores.setItemAnimator(new DefaultItemAnimator());
        /*   -- Search --   */





        Intent intent = getIntent();

        Picasso.with(this).load( "https://image.tmdb.org/t/p/w500" +intent.getStringExtra("imagenPeli")).error(R.mipmap.ic_launcher_round).into(portada);

        imagen_peli = intent.getStringExtra("imagenPeli");

        loadAllInfo(intent.getStringExtra("idPeli"));
        id_peli = intent.getStringExtra("idPeli");

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeLiked.setVisibility(View.VISIBLE);
                like.setVisibility(View.GONE);

                conectorF.insertDB(id_peli,String.valueOf(titulo.getText()),imagen_peli);

            }
        });

        likeLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                like.setVisibility(View.VISIBLE);
                likeLiked.setVisibility(View.GONE);

                conectorF.eliminateDB(id_peli);

            }
        });


        if(conectorF.readLikeList_byId(id_peli)){
            like.setVisibility(View.GONE);
            likeLiked.setVisibility(View.VISIBLE);
        }



    }

    public void loadAllInfo(String id_string){

        loadMovieInfo(id_string);
        loadMovieInfo_Trailer(id_string);
        loadActors(id_string);

    }

    public void getVideo(Result trailer){

        youtubeTrailerKey = trailer.getKey();

        whatchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeTrailerKey));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=" + youtubeTrailerKey));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
    }

    public void createToast(){

        whatchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,  "trailer not available", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void loadMovieInfo(String id){
        MovieService info = RetrofitInstance.getRetrofitInstance().create(MovieService.class);

        Call<MovieDetail> call = info.getSearchById(id, RetrofitInstance.getApiKey(), "en");

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                switch (response.code()) {
                    case 200:
                        //pb1.setVisibility(View.GONE);
                        MovieDetail data = response.body();

                        titulo.setText(data.getTitle());
                        ratingBar.setRating(data.getVote_average() / 2);
                        descripcion.setText(data.getOverview());
                        lanzamiento.setText(data.getRelease_date());



                        int solo2 = 0;
                        boolean masde2 = false;
                        for (ProductionCompany aux_company:data.getProduction_companies()) {
                            solo2++;
                            if(solo2 <= 2){
                                estudio.append(aux_company.getName() + ", ");
                            }else{
                                masde2 = true;
                            }
                        }
                        estudio.setText(estudio.getText().subSequence(0, estudio.getText().length() -2));
                        if(masde2){
                            estudio.append(", ...");
                        }

                        int solo3 = 0;
                        boolean masde3 = false;
                        for (Genre aux_genre:data.getGenres()) {
                            solo3++;
                            if(solo3 <= 3){
                                genero.append(aux_genre.getName() + ", ");
                            }else{
                                masde3 = true;
                            }
                        }
                        genero.setText(genero.getText().subSequence(0, genero.getText().length() -2));
                        if(masde3){
                            genero.append(", ...");
                        }


                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
            }
        });
    }

    public void loadMovieInfo_Trailer(String id){
        MovieService info = RetrofitInstance.getRetrofitInstance().create(MovieService.class);

        Call<Trailer> call = info.getSearchTrailerById(id, RetrofitInstance.getApiKey(), "en");

        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                switch (response.code()) {
                    case 200:
                        //pb1.setVisibility(View.GONE);
                        Trailer data = response.body();


                        if(data.getResults().isEmpty()){
                            createToast();

                        }else{

                            boolean youtubeTry = false;
                            Result aux_trailer = new Result();
                            for (Result trailers: data.getResults()) {

                                if(trailers.getSite().equals("YouTube") && !youtubeTry){
                                    youtubeTry = true;
                                    aux_trailer = trailers;
                                }
                            }

                            if(youtubeTry){
                                getVideo(aux_trailer);

                            }else{
                                createToast();
                            }
                        }

                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
            }
        });
    }

    public void loadActors (String id) {
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);

        Call<CreditsFeed> call = getMovie.getCredits(id, RetrofitInstance.getApiKey(), "en");

        call.enqueue(new Callback<CreditsFeed>() {
            @Override
            public void onResponse(Call<CreditsFeed> call, Response<CreditsFeed> response) {

                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        //pb3.setVisibility(View.GONE);
                        CreditsFeed data = response.body();

                        actoresAdapter.swap(data.getCast());
                        actoresAdapter.notifyDataSetChanged();

                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<CreditsFeed> call, Throwable t) {
            }
        });
    }

}
