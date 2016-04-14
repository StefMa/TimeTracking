package guru.stefma.timetracking;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class TimeTrackView extends CardView {

    public TimeTrackView(Context context) {
        this(context, null);
    }

    public TimeTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setCardElevation(getResources().getDimension(R.dimen.cardview_default_elevation));
        setRadius(getResources().getDimension(R.dimen.cardview_default_radius));

        inflate(context, R.layout.view_time_track, this);
    }

}
