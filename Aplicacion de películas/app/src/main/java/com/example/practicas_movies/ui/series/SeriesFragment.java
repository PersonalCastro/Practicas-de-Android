package com.example.practicas_movies.ui.series;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicas_movies.Adapter.RecomendadoAdapter;
import com.example.practicas_movies.Adapter.TvAdapter;
import com.example.practicas_movies.R;
import com.example.practicas_movies.models.MovieFeed;
import com.example.practicas_movies.models.TVShowFeed;
import com.example.practicas_movies.retrofit.MovieService;
import com.example.practicas_movies.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesFragment extends Fragment {

    private SeriesViewModel seriesViewModel;

    TvAdapter recomendadoAdapter, ratedAdapter, searchAdapter/*, upcomingAdapter*/;
    RecyclerView recyclerViewSearch, recyclerViewRecomendados, recyclerRated/*, recyclerUpcoming*/;

    EditText searchText;

    ConstraintLayout searchConstraint;

    ProgressBar pb1/*, pb2*/, pb3, pb4/*, pb5*/;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seriesViewModel =
                ViewModelProviders.of(this).get(SeriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_series, container, false);

        searchText = (EditText) root.findViewById(R.id.editText);

        searchConstraint = (ConstraintLayout) root.findViewById(R.id.searchConstraintLayout);

        pb3 = (ProgressBar) root.findViewById(R.id.progressBar2);
        //pb2 = (ProgressBar) root.findViewById(R.id.progressBar5);
        pb1 = (ProgressBar) root.findViewById(R.id.progressBar6);
        pb4 = (ProgressBar) root.findViewById(R.id.progressBar7);
        //pb5 = (ProgressBar) root.findViewById(R.id.progressBar6);

        pb1.setVisibility(View.VISIBLE);
        //pb2.setVisibility(View.VISIBLE);
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



        /*   RECYCLERVIEW SEARCH   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerViewSearch = (RecyclerView) root.findViewById(R.id.recyclerView1);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerViewSearch.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        searchAdapter = new TvAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerViewSearch.setAdapter(searchAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        loadSearchRated();
        /*   -- SEARCH --   */



        /*   RECYCLERVIEW RECOMENDADOS   */
        //Enlaza el RecyclerView definido en el layout con el objeto recyclerView
        recyclerRated = (RecyclerView) root.findViewById(R.id.recyclerView2);
        //Establece que recyclerView tendrá un layout lineal, en concreto vertical
        recyclerRated.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        //Indica que cada uno de los items va a tener un tamaño fijo
        recyclerRated.setHasFixedSize(true);
        //Establece la  decoración por defecto de cada uno de lo items: una línea de división
        //recyclerViewRecomendados.addItemDecoration(new DividerItemDecoration(recyclerViewRecomendados.getContext(), DividerItemDecoration.VERTICAL));
        //Instancia un objeto de la clase MovieAdapter
        recomendadoAdapter = new TvAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerRated.setAdapter(recomendadoAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerRated.setItemAnimator(new DefaultItemAnimator());
        loadSearchRecomendados();

        /*   -- RECOMENDADOS --   */



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
        ratedAdapter = new TvAdapter(root.getContext().getApplicationContext());
        //Establece el adaptador asociado a recyclerView
        recyclerViewRecomendados.setAdapter(ratedAdapter);
        //Pone la animación por defecto de recyclerView
        recyclerViewRecomendados.setItemAnimator(new DefaultItemAnimator());
        loadSearchRated();
        /*   -- TOP RATED --   */



        return root;
    }

    public void loadSearchRecomendados () {
        /* Crea la instanncia de retrofit */
        MovieService getTopRatedTv = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<TVShowFeed> call = getTopRatedTv.getTopRatedTv(RetrofitInstance.getApiKey(), "en");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<TVShowFeed>() {
            @Override
            public void onResponse(Call<TVShowFeed> call, Response<TVShowFeed> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb1.setVisibility(View.GONE);
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        TVShowFeed data = response.body();
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
            public void onFailure(Call<TVShowFeed> call, Throwable t) {
            }
        });
    }

    public void loadSearchRated () {
        /* Crea la instanncia de retrofit */
        MovieService getTv = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<TVShowFeed> call = getTv.getNowPayingTv(RetrofitInstance.getApiKey(), "en");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<TVShowFeed>() {
            @Override
            public void onResponse(Call<TVShowFeed> call, Response<TVShowFeed> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb4.setVisibility(View.GONE);

                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        TVShowFeed data = response.body();
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
            public void onFailure(Call<TVShowFeed> call, Throwable t) {
            }
        });
    }

    public void loadSearch (String searchString) {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<TVShowFeed> call = getMovie.getSearchTv(RetrofitInstance.getApiKey(), "en", searchString);
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<TVShowFeed>() {
            @Override
            public void onResponse(Call<TVShowFeed> call, Response<TVShowFeed> response) {
                Log.i("String", String.valueOf(response));

                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        pb3.setVisibility(View.GONE);
                        recyclerViewSearch.setVisibility(View.VISIBLE);

                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        TVShowFeed data = response.body();
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
            public void onFailure(Call<TVShowFeed> call, Throwable t) {
            }
        });
    }
}