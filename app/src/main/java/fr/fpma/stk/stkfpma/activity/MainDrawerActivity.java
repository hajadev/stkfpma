package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import fr.fpma.stk.stkfpma.R;
import fr.fpma.stk.stkfpma.fragment.CalendarFragment;
import fr.fpma.stk.stkfpma.fragment.WelcomeFragment;

@EActivity(R.layout.activity_main_drawer)
public class MainDrawerActivity extends Activity {
    @StringArrayRes(R.array.left_menu)
    String[] drawerItemsList;
    @ViewById(R.id.my_drawer)
    ListView myDrawer;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    Activity currentActivity;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @FragmentById(R.id.welcome_fragment_id)
    WelcomeFragment welcomeFragment;
    @FragmentById(R.id.calendar_fragment_id)
    CalendarFragment calendarFragment;

    Fragment currentFragment;

    @AfterViews
    void AfterViews(){
        fragmentManager = getFragmentManager();

        currentActivity = this;
        myDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, drawerItemsList));
        myDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    switchToWelcome();
                }
                else{
                    switchToCalendar();
                }
//                Intent intent = new Intent(MainDrawerActivity.this, CalendarActivity.class);
//                Intent intent = new Intent(MainDrawerActivity.this, CalendarViewActivity_.class);
//                startActivity(intent);
//                currentActivity.finish();
                drawerLayout.closeDrawer(myDrawer);
            }
        });

        initDisplay();

    }

    private void initDisplay(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.attach(welcomeFragment);
        fragmentTransaction.attach(calendarFragment);
        fragmentTransaction.hide(calendarFragment);
        fragmentTransaction.commit();
        currentFragment = welcomeFragment;
    }

    private void switchToCalendar(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(welcomeFragment);
        fragmentTransaction.show(calendarFragment);
        fragmentTransaction.commit();
        currentFragment = calendarFragment;
    }

    private void switchToWelcome(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(calendarFragment);
        fragmentTransaction.show(welcomeFragment);
        fragmentTransaction.commit();
        currentFragment = welcomeFragment;
    }

}
