package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import fr.fpma.stk.stkfpma.R;


@EActivity(R.layout.activity_view_calendar)
public class CalendarViewActivity extends Activity{
    @ViewById(R.id.calendarView)
    CalendarView calendarView;
    @ViewById(R.id.date_display)
    TextView dateDisplay;

    @AfterViews
    void afterViews(){
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);

                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });
    }

}
