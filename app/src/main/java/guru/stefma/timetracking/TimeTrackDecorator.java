package guru.stefma.timetracking;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class TimeTrackDecorator implements DayViewDecorator {

    private final CalendarDay mDayToDecorate;

    public TimeTrackDecorator(CalendarDay dayToDecorate) {
        mDayToDecorate = dayToDecorate;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(mDayToDecorate);
    }

    @Override
    public void decorate(DayViewFacade view) {
        LineBackgroundSpan span = new LineBackgroundSpan() {

            @Override
            public void drawBackground(
                    Canvas canvas, Paint paint,
                    int left, int right, int top, int baseline, int bottom,
                    CharSequence charSequence,
                    int start, int end, int lineNum
            ) {
                int oldColor = paint.getColor();
                // Example: First work 6:00 - 13:00
                // Example: Second work: 16:00 - 19:30
                float dayHour = left + right;

                // The first line display 7 Hours of Work
                float firstWorkEndHour = dayHour * 7 / 24;
                paint.setColor(Color.YELLOW);
                canvas.drawRect(left, bottom + 8, firstWorkEndHour, bottom + 13, paint);

                // The second line display 3.5 Hours of Work
                double secondWorkEndHour = dayHour * 3.5 / 24;
                paint.setColor(Color.RED);
                canvas.drawRect(left, bottom + 14, (float) secondWorkEndHour, bottom + 19, paint);
                paint.setColor(oldColor);
            }
        };
        view.addSpan(span);
    }

}
