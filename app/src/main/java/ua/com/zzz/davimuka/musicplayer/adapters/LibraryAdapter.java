package ua.com.zzz.davimuka.musicplayer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.zzz.davimuka.musicplayer.R;
import ua.com.zzz.davimuka.musicplayer.Song;
import ua.com.zzz.davimuka.musicplayer.activities.MainActivity;

public class LibraryAdapter extends ArrayAdapter {

    @BindView(R.id.songListItemConstraintLayout)
    ConstraintLayout songListItemConstraintLayout;
    @BindView(R.id.songListItemImageImageView)
    ImageView songListItemImageImageView;
    @BindView(R.id.songListItemDurationTextView)
    TextView songListItemDurationTextView;
    @BindView(R.id.songListItemTitleTextView)
    TextView songListItemTitleTextView;
    @BindView(R.id.songListItemArtistTextView)
    TextView songListItemArtistTextView;

    public LibraryAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
        ButterKnife.bind(this, listItemView);

        final Song currentSong = (Song) getItem(position);

        final ImageView songListItemLikeImageView = listItemView.findViewById(R.id.songListItemLikeImageView);
        final ConstraintLayout nowPlayingConstraintLayout = ((RelativeLayout) parent.getParent().getParent()).findViewById(R.id.nowPlayingConstraintLayout);

        assert currentSong != null;
        if (currentSong.isLiked())
            songListItemLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
        songListItemImageImageView.setImageResource(currentSong.getSmallImageID());
        songListItemDurationTextView.setText(formatTime(currentSong.getDuration()));
        songListItemTitleTextView.setText(currentSong.getTitle());
        songListItemArtistTextView.setText(currentSong.getArtist());

        songListItemLikeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSong.isLiked()) {
                    currentSong.setLiked(false);
                    notifyDataSetChanged();
                    songListItemLikeImageView.setImageResource(R.drawable.ic_action_heart_gray);
                    if (currentSong.equals(MainActivity.nowPlayingSong))
                        ((ImageView) nowPlayingConstraintLayout.findViewById(R.id.nowPlayingLikeImageView)).setImageResource(R.drawable.ic_action_heart);
                } else {
                    currentSong.setLiked(true);
                    songListItemLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
                    if (currentSong.equals(MainActivity.nowPlayingSong))
                        ((ImageView) nowPlayingConstraintLayout.findViewById(R.id.nowPlayingLikeImageView)).setImageResource(R.drawable.ic_action_heart_orange);
                }
            }
        });

        songListItemConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentTabId = 1;
                MainActivity.nowPlayingSong = currentSong;
                ImageView nowPlayingImage = nowPlayingConstraintLayout.findViewById(R.id.nowPlayingImage);
                TextView nowPlayingTitle = nowPlayingConstraintLayout.findViewById(R.id.nowPlayingArtistAndTitleTextView);
                ImageView nowPlayingLike = nowPlayingConstraintLayout.findViewById(R.id.nowPlayingLikeImageView);

                nowPlayingImage.setImageResource(currentSong.getSmallImageID());
                StringBuilder nowPlayingTitleAndArtist = new StringBuilder().append(currentSong.getTitle()).append(" - ").append(currentSong.getArtist());
                nowPlayingTitle.setText(nowPlayingTitleAndArtist.toString());
                if (currentSong.isLiked())
                    nowPlayingLike.setImageResource(R.drawable.ic_action_heart_orange);
                else
                    nowPlayingLike.setImageResource(R.drawable.ic_action_heart);
            }
        });

        return listItemView;
    }

    private String formatTime(int duration) {
        int hours = 0, minutes = 0, seconds;
        while (duration >= 3600) {
            hours++;
            duration -= 3600;
        }
        while (duration >= 60) {
            minutes++;
            duration -= 60;
        }
        seconds = duration;
        StringBuilder sb = new StringBuilder();
        if (hours != 0) {
            sb.append(hours).append(':');
        }
        if (minutes != 0) {
            if (hours != 0 && minutes < 10)
                sb.append('0').append(minutes).append(':');
            else
                sb.append(minutes).append(':');
        }
        if (minutes != 0 && seconds < 10)
            sb.append('0').append(seconds);
        else
            sb.append(seconds);
        return sb.toString();
    }
}
