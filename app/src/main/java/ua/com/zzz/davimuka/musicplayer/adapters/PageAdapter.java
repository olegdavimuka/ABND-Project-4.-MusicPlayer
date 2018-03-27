package ua.com.zzz.davimuka.musicplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ua.com.zzz.davimuka.musicplayer.fragments.FavoritesFragment;
import ua.com.zzz.davimuka.musicplayer.fragments.LibraryFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    private String tabTitles[] = new String[]{
            "Library", "Favorites",};

    public PageAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        fragments.add(new LibraryFragment());
        fragments.add(new FavoritesFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
