package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import fr.fpma.stk.stkfpma.R;
import fr.fpma.stk.stkfpma.fragment.CalendarFragment;
import fr.fpma.stk.stkfpma.fragment.WelcomeFragment;

@EActivity(R.layout.activity_main_drawer)
public class MainDrawerActivity extends YouTubeFailureRecoveryActivity {

    @StringArrayRes(R.array.left_menu)
    String[] drawerItemsList;
    @ViewById(R.id.my_drawer)
    ListView myDrawer;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Activity currentActivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;

    @FragmentById(R.id.welcome_fragment_id)
    WelcomeFragment welcomeFragment;
    @FragmentById(R.id.calendar_fragment_id)
    CalendarFragment calendarFragment;
    @FragmentById(R.id.youtube_player_fragment)
    YouTubePlayerFragment youTubePlayerFragment;

    private YouTubePlayer youTubePlayer;

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
                else if(i==3){
                    switchToYoutube();
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
        youTubePlayerFragment.initialize(DEVELOPER_KEY, this);
        this.getYouTubePlayerProvider();
        fragmentTransaction.attach(welcomeFragment);
        fragmentTransaction.attach(calendarFragment);
        fragmentTransaction.attach(youTubePlayerFragment);
        fragmentTransaction.hide(calendarFragment);
        fragmentTransaction.hide(youTubePlayerFragment);
        fragmentTransaction.commit();
        currentFragment = welcomeFragment;
    }

    private void switchToCalendar(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(welcomeFragment);
        fragmentTransaction.hide(youTubePlayerFragment);
        fragmentTransaction.show(calendarFragment);
        fragmentTransaction.commit();
        currentFragment = calendarFragment;
    }

    private void switchToWelcome(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(calendarFragment);
        fragmentTransaction.hide(youTubePlayerFragment);
        fragmentTransaction.show(welcomeFragment);
        fragmentTransaction.commit();
        currentFragment = welcomeFragment;
    }

    private void switchToYoutube(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(calendarFragment);
        fragmentTransaction.hide(welcomeFragment);
        fragmentTransaction.show(youTubePlayerFragment);
        fragmentTransaction.commit();
        currentFragment = youTubePlayerFragment;
    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("nYRkD8bRkjA");
        }
        youTubePlayer = player;
//        youTubePlayer.loadVideo("nYRkD8bRkjA");
        youTubePlayer.loadPlaylist("UUg2kFkj6H9Ed37NwZShts-w");
        Log.i("haja", "initialisation success!!!!!");
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerFragment;
    }

}
