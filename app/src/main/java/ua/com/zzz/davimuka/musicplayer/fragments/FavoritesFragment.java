package ua.com.zzz.davimuka.musicplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ua.com.zzz.davimuka.musicplayer.R;
import ua.com.zzz.davimuka.musicplayer.Song;
import ua.com.zzz.davimuka.musicplayer.activities.MainActivity;
import ua.com.zzz.davimuka.musicplayer.adapters.FavoritesAdapter;

public class FavoritesFragment extends Fragment implements FragmentLifecycle {

    public static ArrayList<Song> favoriteSongsList = new ArrayList<>();

    FavoritesAdapter favoritesAdapter;
    ListView listView;

    public FavoritesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list, container, false);

        listView = rootView.findViewById(R.id.list);

        return rootView;
    }

    @Override
    public void onPauseFragment() {
    }

    @Override
    public void onResumeFragment() {
        favoriteSongsList.clear();

        for (Song song : MainActivity.songsList) {
            if (song.isLiked())
                favoriteSongsList.add(song);
        }
        if (favoriteSongsList.size() != 1) {
            for (int i = 0; i < favoriteSongsList.size(); i++) {
                if (i == 0) {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(favoriteSongsList.size() - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(i + 1));
                } else if (i == favoriteSongsList.size() - 1) {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(i - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(0));
                } else {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(i - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(i + 1));
                }
            }
        }

        favoritesAdapter = new FavoritesAdapter(getActivity(), favoriteSongsList);

        listView.setAdapter(favoritesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        for (Song song : MainActivity.songsList) {
            if (song.isLiked())
                favoriteSongsList.add(song);
        }
        if (favoriteSongsList.size() != 1) {
            for (int i = 0; i < favoriteSongsList.size(); i++) {
                if (i == 0) {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(favoriteSongsList.size() - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(i + 1));
                } else if (i == favoriteSongsList.size() - 1) {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(i - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(0));
                } else {
                    favoriteSongsList.get(i).setPreviousLikedSong(favoriteSongsList.get(i - 1));
                    favoriteSongsList.get(i).setNextLikedSong(favoriteSongsList.get(i + 1));
                }
            }
        }
        favoritesAdapter = new FavoritesAdapter(getActivity(), favoriteSongsList);
        listView.setAdapter(favoritesAdapter);
    }
}
