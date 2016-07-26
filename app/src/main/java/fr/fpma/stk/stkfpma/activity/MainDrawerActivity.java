package fr.fpma.stk.stkfpma.activity;

import android.app.Activity;
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

    @AfterViews
    void AfterViews(){
        myDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, drawerItemsList));
    }

}
