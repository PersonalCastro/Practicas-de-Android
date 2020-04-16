package com.example.practicas_movies.ui.peliculas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicas_movies.Adapter.RecomendadoAdapter;
import com.example.practicas_movies.R;
import com.example.practicas_movies.models.MovieFeed;
import com.example.practicas_movies.retrofit.MovieService;
import com.example.practicas_movies.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculasFragment extends Fragment {

    private PeliculasViewModel peliculasViewModel;

    RecomendadoAdapter recomendadoAdapter, ratedAdapter, searchAdapter, upcomingAdapter/*, popularAdapter*/;
    RecyclerView recyclerViewSearch, recyclerViewRecomendados, recyclerRated, recyclerUpcoming/*, recyclerPopular*/;

    EditText searchText;

    ConstraintLayout searchConstraint;


    ProgressBar pb1, pb2, pb3, pb4/*, pb5*/;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        peliculasViewModel =
                ViewModelProviders.of(this).get(PeliculasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_peliculas, container, false);

        searchText = (EditText) root.findViewById(R.id.editText);

        searchConstraint = (ConstraintLayout) root.findViewById(R.id.searchConstraintLayout);

        pb1 = (ProgressBar) root.findViewById(R.id.progressBar);
        pb2 = (ProgressBar) root.findViewById(R.id.progressBar3);
        pb3 = (ProgressBar) root.findViewById(R.id.progressBar4);
        pb4 = (ProgressBar) root.findViewById(R.id.progressBar5);
        //pb5 = (ProgressBar) root.findViewById(R.id.progressBar6);

        pb1.setVisibility(View.VISIBLE);
        pb2.setVisibility(View.VISIBLE);
        pb4.setVisibility(View.VISIBLE);
        //pb5.setVisibility(View.VISIBLE);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){
                    searchConstraint.setVisibility(View.GONE);
                    pb3.setVisibility(View.GONE);
                }else{
                    searchConstraint.setVisibility(View.VISIBLE);
                    pb3.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.GONE);

                    loadSearch(String.valueOf(searchText.getText()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        /*   RECYCLERVIEW Search   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerViewSearch = (RecyclerView) root.findViewById(R.id.recyclerView1);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerViewSearch.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        searchAdapter = new RecomendadoAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerViewSearch.setAdapter(searchAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        loadSearchRated();
        /*   -- Search --   */



        /*   RECYCLERVIEW Trending   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerRated = (RecyclerView) root.findViewById(R.id.recyclerView2);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerRated.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerRated.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        recomendadoAdapter = new RecomendadoAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerRated.setAdapter(recomendadoAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerRated.setItemAnimator(new DefaultItemAnimator());
        loadSearchTrending();

        /*   -- Trending --   */


        /*   RECYCLERVIEW Upcoming   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerUpcoming = (RecyclerView) root.findViewById(R.id.recyclerView4);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerUpcoming.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerUpcoming.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        upcomingAdapter = new RecomendadoAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerUpcoming.setAdapter(upcomingAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerUpcoming.setItemAnimator(new DefaultItemAnimator());
        loadSearchUpcoming();

        /*   -- Upcoming --   */


        /*   RECYCLERVIEW Popular   */
       /* //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerPopular = (RecyclerView) root.findViewById(R.id.recyclerView4);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerPopular.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerPopular.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        popularAdapter = new RecomendadoAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerPopular.setAdapter(popularAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerPopular.setItemAnimator(new DefaultItemAnimator());
        loadSearchPopular();*/

        /*   -- Popular --   */


        /*   RECYCLERVIEW TOP RATED   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerViewRecomendados = (RecyclerView) root.findViewById(R.id.recyclerView3);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerViewRecomendados.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerViewRecomendados.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        ratedAdapter = new RecomendadoAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerViewRecomendados.setAdapter(ratedAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerViewRecomendados.setItemAnimator(new DefaultItemAnimator());
        loadSearchRated();
        /*   -- TOP RATED --   */



        return root;
    }

    public void loadSearchRated () {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<MovieFeed> call = getMovie.getTopRated(RetrofitInstance.getApiKey(), "en");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb2.setVisibility(View.GONE);
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        MovieFeed data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        ratedAdapter.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        ratedAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
            }
        });
    }

    public void loadSearchTrending () {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<MovieFeed> call = getMovie.getNowPaying(RetrofitInstance.getApiKey(), "en");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb1.setVisibility(View.GONE);
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        MovieFeed data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        recomendadoAdapter.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        recomendadoAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }



            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
            }
        });
    }


    /*public void loadSearchPopular () {
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<MovieFeed> call = getMovie.getPopular(RetrofitInstance.getApiKey(), "en", "es");
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                switch (response.code()) {
                    case 200:
                        pb5.setVisibility(View.GONE);
                        MovieFeed data = response.body();
                        popularAdapter.swap(data.getResults());
                        popularAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }



            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
            }
        });
    }*/


    public void loadSearchUpcoming () {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<MovieFeed> call = getMovie.getUpcoming(RetrofitInstance.getApiKey(), "en", "es");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb4.setVisibility(View.GONE);
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        MovieFeed data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        upcomingAdapter.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        upcomingAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }



            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
            }
        });
    }

    public void loadSearch (String searchString) {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<MovieFeed> call = getMovie.getSearch(RetrofitInstance.getApiKey(), "en", searchString);
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                Log.i("String", String.valueOf(response));

                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb3.setVisibility(View.GONE);
                        recyclerViewSearch.setVisibility(View.VISIBLE);
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        MovieFeed data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        searchAdapter.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        searchAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
            }
        });
    }



}