package guru.stefma.timetracking.settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

import guru.stefma.timetracking.R;

public class WorkingHoursView extends FrameLayout {

    private NumberPicker mHoursPicker;

    private NumberPicker mMinutePicker;

    public WorkingHoursView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_working_hours, this);
        mHoursPicker = (NumberPicker) findViewById(R.id.working_hours_hours);
        mMinutePicker = (NumberPicker) findViewById(R.id.working_hours_minutes);

        int dividerColor = context.getResources().getColor(R.color.colorAccent);
        setDividerColor(mHoursPicker, dividerColor);
        setDividerColor(mMinutePicker, dividerColor);
    }

    public void setHoursRange(int start, int end) {
        mHoursPicker.setMinValue(start);
        mHoursPicker.setMaxValue(end);
    }

    public void setMinuteRange(int start, int end) {
        mMinutePicker.setMinValue(start);
        mMinutePicker.setMaxValue(end);
    }

    public void setHours(int hours) {
        mHoursPicker.setValue(hours);
    }

    public void setMinutes(int minutes) {
        mMinutePicker.setValue(minutes);
    }

    public int getHours() {
        return mHoursPicker.getValue();
    }

    public int getMinutes() {
        return mMinutePicker.getValue();
    }

    private void setDividerColor(NumberPicker picker, int color) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field field : pickerFields) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    field.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
