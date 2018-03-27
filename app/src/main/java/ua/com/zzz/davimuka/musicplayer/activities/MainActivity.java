package ua.com.zzz.davimuka.musicplayer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.zzz.davimuka.musicplayer.R;
import ua.com.zzz.davimuka.musicplayer.Song;
import ua.com.zzz.davimuka.musicplayer.adapters.PageAdapter;
import ua.com.zzz.davimuka.musicplayer.fragments.FragmentLifecycle;

import static android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Song> songsList = new ArrayList<>();
    public static Song nowPlayingSong;

    private PageAdapter adapter;

    public static int currentTabId = 1;

    @BindView(R.id.ViewPager)
    ViewPager viewPager;
    @BindView(R.id.SlidingTabs)
    TabLayout tabLayout;
    @BindView(R.id.nowPlayingConstraintLayout)
    ConstraintLayout nowPlayingConstraintLayout;
    @BindView(R.id.nowPlayingImage)
    ImageView nowPlayingImageImageView;
    @BindView(R.id.nowPlayingArtistAndTitleTextView)
    TextView nowPlayingArtistAndTitleTextView;
    @BindView(R.id.nowPlayingPreviousImageView)
    ImageView nowPlayingPreviousImageView;
    @BindView(R.id.nowPlayingPlayPauseImageView)
    ImageView nowPlayingPlayPauseImageView;
    @BindView(R.id.nowPlayingNextImageView)
    ImageView nowPlayingNextImageView;
    @BindView(R.id.nowPlayingLikeImageView)
    ImageView nowPlayingLikeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        adapter = new PageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(pageChangeListener);

        tabLayout.setupWithViewPager(viewPager);

        songsList = addSongs();

        nowPlayingArtistAndTitleTextView.setSelected(true);

        setSongInfoToLayout();

        nowPlayingConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FullInfoActivity.class);
                startActivity(intent);
            }
        });

        nowPlayingPreviousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTabId == 1)
                    nowPlayingSong = nowPlayingSong.getPreviousSong();
                else if (currentTabId == 2)
                    nowPlayingSong = nowPlayingSong.getPreviousLikedSong();
                setSongInfoToLayout();
            }
        });

        nowPlayingPlayPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowPlayingPlayPauseImageView.getDrawable();
                if (nowPlayingPlayPauseImageView.getDrawable().getConstantState().equals(getDrawable(R.drawable.ic_play_arrow).getConstantState()))
                    nowPlayingPlayPauseImageView.setImageResource(R.drawable.ic_pause);
                else
                    nowPlayingPlayPauseImageView.setImageResource(R.drawable.ic_play_arrow);
            }
        });

        nowPlayingNextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTabId == 1)
                    nowPlayingSong = nowPlayingSong.getNextSong();
                else if (currentTabId == 2)
                    nowPlayingSong = nowPlayingSong.getNextLikedSong();
                setSongInfoToLayout();
            }
        });

        nowPlayingLikeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowPlayingSong.isLiked()) {
                    nowPlayingSong.setLiked(false);
                    nowPlayingLikeImageView.setImageResource(R.drawable.ic_action_heart);
                } else {
                    nowPlayingSong.setLiked(true);
                    nowPlayingLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSongInfoToLayout();
    }

    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        int currentPosition = 0;

        @Override
        public void onPageSelected(int newPosition) {

            FragmentLifecycle fragmentToHide = (FragmentLifecycle) adapter.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifecycle fragmentToShow = (FragmentLifecycle) adapter.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private ArrayList<Song> addSongs() {
        ArrayList<Song> songsList = new ArrayList<>();

        String[] songTitles = getResources().getStringArray(R.array.song_titles);
        String[] songArtists = getResources().getStringArray(R.array.song_artists);

        songsList.add(new Song(R.drawable.song_1_image, R.drawable.song_1_full_image, songTitles[0], songArtists[0], 174));
        songsList.add(new Song(R.drawable.song_2_image, R.drawable.song_2_full_image, songTitles[1], songArtists[1], 189));
        songsList.add(new Song(R.drawable.song_3_image, R.drawable.song_3_full_image, songTitles[2], songArtists[2], 212));
        songsList.add(new Song(R.drawable.song_4_image, R.drawable.song_4_full_image, songTitles[3], songArtists[3], 184));
        songsList.add(new Song(R.drawable.song_5_image, R.drawable.song_5_full_image, songTitles[4], songArtists[4], 201));
        songsList.add(new Song(R.drawable.song_6_image, R.drawable.song_6_full_image, songTitles[5], songArtists[5], 308));
        songsList.add(new Song(R.drawable.song_7_image, R.drawable.song_7_full_image, songTitles[6], songArtists[6], 166));
        songsList.add(new Song(R.drawable.song_8_image, R.drawable.song_8_full_image, songTitles[7], songArtists[7], 282));
        songsList.add(new Song(R.drawable.song_9_image, R.drawable.song_9_full_image, songTitles[8], songArtists[8], 253));
        songsList.add(new Song(R.drawable.song_10_image, R.drawable.song_10_full_image, songTitles[9], songArtists[9], 174));

        for (int i = 0; i < songsList.size(); i++) {
            if (i == 0) {
                songsList.get(i).setPreviousSong(songsList.get(songsList.size() - 1));
                songsList.get(i).setNextSong(songsList.get(i + 1));
            } else if (i == songsList.size() - 1) {
                songsList.get(i).setPreviousSong(songsList.get(i - 1));
                songsList.get(i).setNextSong(songsList.get(0));
            } else {
                songsList.get(i).setPreviousSong(songsList.get(i - 1));
                songsList.get(i).setNextSong(songsList.get(i + 1));
            }
        }

        nowPlayingSong = songsList.get(0);

        return songsList;
    }

    private void setSongInfoToLayout() {
        nowPlayingImageImageView.setImageResource(nowPlayingSong.getSmallImageID());
        StringBuilder nowPlayingTitleAndArtist = new StringBuilder().append(nowPlayingSong.getTitle()).append(" - ").append(nowPlayingSong.getArtist());
        nowPlayingArtistAndTitleTextView.setText(nowPlayingTitleAndArtist.toString());
        if (nowPlayingSong.isLiked())
            nowPlayingLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
        else
            nowPlayingLikeImageView.setImageResource(R.drawable.ic_action_heart);
    }

}
