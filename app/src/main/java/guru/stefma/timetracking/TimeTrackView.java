package guru.stefma.timetracking;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;

public class TimeTrackView extends CardView {

    private View mTimeTrackRemove;

    public TimeTrackView(Context context) {
        this(context, null);
    }

    public TimeTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setCardElevation(getResources().getDimension(R.dimen.cardview_default_elevation));
        setRadius(getResources().getDimension(R.dimen.cardview_default_radius));

        inflate(context, R.layout.view_time_track, this);

        mTimeTrackRemove = findViewById(R.id.time_track_remove);
    }

    public void setOnRemoveClickListener(View.OnClickListener clickListener) {
        mTimeTrackRemove.setOnClickListener(clickListener);
    }

}
