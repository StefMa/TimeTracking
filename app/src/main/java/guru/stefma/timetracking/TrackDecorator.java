package guru.stefma.timetracking;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import java.util.List;

public class TrackDecorator implements LineBackgroundSpan {

    private static final int TOP_MARGIN = 8;

    private static final int BOTTOM_MARGIN = 13;

    private static final int RECT_HEIGHT = 6;

    private static final int HOURS_PER_DAY = 24;

    private static final String[] mColorList = {"#FF4081", "#40C4FF", "#FF6E40", "#FF9800", "#2196F3"};

    private final int mCount;

    private final List<Float> mWorkingHours;

    public TrackDecorator(int count, List<Float> workingHours) {
        mCount = count;
        mWorkingHours = workingHours;
    }

    @Override
    public void drawBackground(Canvas canvas, Paint paint,
                               int left, int right, int top, int baseline, int bottom,
                               CharSequence charSequence,
                               int start, int end, int lineNum) {

        int oldColor = paint.getColor();
        for (int i = 0; i < mCount; i++) {
            float dayHour = left + right;
            Float workingHour = mWorkingHours.get(i);
            float workCount = dayHour * workingHour / HOURS_PER_DAY;
            paint.setColor(Color.parseColor(mColorList[i]));
            int rectTop = bottom + TOP_MARGIN + (RECT_HEIGHT * i);
            int rectBottom = bottom + BOTTOM_MARGIN + (RECT_HEIGHT * i);

            canvas.drawRect(left, rectTop, workCount, rectBottom, paint);
        }
        paint.setColor(oldColor);

    }

}
