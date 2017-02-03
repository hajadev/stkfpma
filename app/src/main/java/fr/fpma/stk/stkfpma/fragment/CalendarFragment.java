package fr.fpma.stk.stkfpma.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import fr.fpma.stk.stkfpma.R;

@EFragment(R.layout.fragment_calendar)
public class CalendarFragment extends Fragment{
    @ViewById(R.id.calendarView)
    CalendarView calendarView;
    @ViewById(R.id.date_display)
    TextView dateDisplay;

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @AfterViews
    void afterViews(){
        dateDisplay.setText("Date: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);

                Toast.makeText(context, "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });
    }
}
