package com.example.practicas_movies.ui.favoritos;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicas_movies.Adapter.LikeAdapter;
import com.example.practicas_movies.Adapter.RecomendadoAdapter;
import com.example.practicas_movies.R;
import com.example.practicas_movies.Sqlite.SqliteConnector;
import com.example.practicas_movies.models.likeObject;

import java.util.ArrayList;

public class FavoritasFragment extends Fragment {

    private FavoritasViewModel favoritasViewModel;
    private RecyclerView likeRecycle;
    private LikeAdapter adaptadorLike;
    private SqliteConnector conector;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritasViewModel =
                ViewModelProviders.of(this).get(FavoritasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favoritos, container, false);

        conector = new SqliteConnector(root.getContext(),"movie",null, 1);

        likeRecycle = (RecyclerView) root.findViewById(R.id.recycleFavourites);
        likeRecycle.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        likeRecycle.setHasFixedSize(true);
        adaptadorLike = new LikeAdapter(root.getContext().getApplicationContext());
        likeRecycle.setAdapter(adaptadorLike);
        likeRecycle.setItemAnimator(new DefaultItemAnimator());
        loadFavourites();


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavourites();

    }

    public void loadFavourites () {


        ArrayList<likeObject> aux_likes = conector.readLikeList();



        adaptadorLike.swap(aux_likes);
        adaptadorLike.notifyDataSetChanged();

    }
}