package com.dongduk.hangeul.hangeul_test1.decorators;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class WeekendDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public WeekendDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return (weekDay == Calendar.SUNDAY) || (weekDay == Calendar.SATURDAY);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.parseColor("#9b9b9b")));
    }
}
