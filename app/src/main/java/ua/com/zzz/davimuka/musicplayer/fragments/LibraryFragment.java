package ua.com.zzz.davimuka.musicplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.zzz.davimuka.musicplayer.R;
import ua.com.zzz.davimuka.musicplayer.activities.MainActivity;
import ua.com.zzz.davimuka.musicplayer.adapters.LibraryAdapter;

public class LibraryFragment extends Fragment implements FragmentLifecycle {

    @BindView(R.id.list)
    ListView listView;

    LibraryAdapter libraryAdapter;

    public LibraryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list, container, false);

        libraryAdapter = new LibraryAdapter(getActivity(), MainActivity.songsList);

        ButterKnife.bind(this, rootView);

        listView.setAdapter(libraryAdapter);

        return rootView;
    }

    @Override
    public void onPauseFragment() {
    }

    @Override
    public void onResumeFragment() {
        libraryAdapter = new LibraryAdapter(getActivity(), MainActivity.songsList);
        listView.setAdapter(libraryAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        libraryAdapter = new LibraryAdapter(getActivity(), MainActivity.songsList);
        listView.setAdapter(libraryAdapter);
    }
}
