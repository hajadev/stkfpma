package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import fr.fpma.stk.stkfpma.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.hello)
    TextView textView;

    @AfterViews
    void afterViews(){
        textView.setText(R.string.welcome_page);
    }

}
