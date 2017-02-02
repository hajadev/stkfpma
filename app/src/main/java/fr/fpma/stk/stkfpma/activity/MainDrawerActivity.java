package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import fr.fpma.stk.stkfpma.R;

@EActivity(R.layout.activity_main_drawer)
public class MainDrawerActivity extends Activity {
    @StringArrayRes(R.array.left_menu)
    String[] drawerItemsList;
    @ViewById(R.id.my_drawer)
    ListView myDrawer;

    Activity currentActivity;

    @AfterViews
    void AfterViews(){
        currentActivity = this;
        myDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, drawerItemsList));
        myDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==drawerItemsList.length){ // On a cliqué sur Calendrier

                }
                Intent intent = new Intent(MainDrawerActivity.this, CalendarActivity.class);

                startActivity(intent);
                currentActivity.finish();
            }
        });
    }

}
