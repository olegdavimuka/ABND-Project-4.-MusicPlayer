package ua.com.zzz.davimuka.musicplayer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.zzz.davimuka.musicplayer.R;

public class FullInfoActivity extends AppCompatActivity {

    @BindView(R.id.fullInfoTitleTextView)
    TextView fullInfoTitleTextView;
    @BindView(R.id.fullInfoArtistTextView)
    TextView fullInfoArtistTextView;
    @BindView(R.id.fullInfoImageImageView)
    ImageView fullInfoImageImageView;
    @BindView(R.id.fullInfoPreviousImageView)
    ImageView fullInfoPreviousImageView;
    @BindView(R.id.fullInfoPlayPauseImageView)
    ImageView fullInfoPlayPauseImageView;
    @BindView(R.id.fullInfoNextImageView)
    ImageView fullInfoNextImageView;
    @BindView(R.id.fullInfoLikeImageView)
    ImageView fullInfoLikeImageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_info_activity);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSongInfoToLayout();

        fullInfoPreviousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.currentTabId == 1)
                    MainActivity.nowPlayingSong = MainActivity.nowPlayingSong.getPreviousSong();
                else if (MainActivity.currentTabId == 2)
                    MainActivity.nowPlayingSong = MainActivity.nowPlayingSong.getPreviousLikedSong();
                setSongInfoToLayout();
            }
        });

        fullInfoPlayPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullInfoPlayPauseImageView.getDrawable().getConstantState().equals(getDrawable(R.drawable.ic_play_arrow).getConstantState()))
                    fullInfoPlayPauseImageView.setImageResource(R.drawable.ic_pause);
                else
                    fullInfoPlayPauseImageView.setImageResource(R.drawable.ic_play_arrow);
            }
        });

        fullInfoNextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.currentTabId == 1)
                    MainActivity.nowPlayingSong = MainActivity.nowPlayingSong.getNextSong();
                else if (MainActivity.currentTabId == 2)
                    MainActivity.nowPlayingSong = MainActivity.nowPlayingSong.getNextLikedSong();
                setSongInfoToLayout();
            }
        });

        fullInfoLikeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.nowPlayingSong.isLiked()) {
                    MainActivity.nowPlayingSong.setLiked(false);
                    fullInfoLikeImageView.setImageResource(R.drawable.ic_action_heart);
                } else {
                    MainActivity.nowPlayingSong.setLiked(true);
                    fullInfoLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
                }
            }
        });

    }

    private void setSongInfoToLayout() {
        fullInfoTitleTextView.setText(MainActivity.nowPlayingSong.getTitle());
        fullInfoArtistTextView.setText(MainActivity.nowPlayingSong.getArtist());
        fullInfoImageImageView.setImageResource(MainActivity.nowPlayingSong.getFullImageID());
        if (MainActivity.nowPlayingSong.isLiked())
            fullInfoLikeImageView.setImageResource(R.drawable.ic_action_heart_orange);
        else
            fullInfoLikeImageView.setImageResource(R.drawable.ic_action_heart);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
