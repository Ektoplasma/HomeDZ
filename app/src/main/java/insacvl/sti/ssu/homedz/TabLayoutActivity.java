package insacvl.sti.ssu.homedz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;
import insacvl.sti.ssu.homedz.pahowrapper.Listener;


public class TabLayoutActivity extends AppCompatActivity {

    /** This instance of <code>ConnectionDetails</code> **/
    private final TabLayoutActivity tabLayoutActivity = this;

    /**
     * {@link TabsPagerAdapter} that is used to get pages to display
     */
    TabsPagerAdapter tabsPagerAdapter;
    /**
     * The handle to the {@link Connection} which holds the data for the client
     * selected
     **/
    private String clientHandle = null;

    /**
     * The instance of {@link Connection} that the <code>clientHandle</code>
     * represents
     **/
    private Connection connection = null;

    /** The currently selected tab **/
    private int selected = 0;

    /**
     * {@link ViewPager} object allows pages to be flipped left and right
     */
    ViewPager pager;

    /**
     * The {@link TabLayoutActivity.ChangeListener} this object is using for the connection
     * updates
     **/
    private TabLayoutActivity.ChangeListener changeListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientHandle = getIntent().getStringExtra("handle");

        ActivityConstants.currentHandler = clientHandle;

        setContentView(R.layout.z_activity_tab_layout);

        final TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        pager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(pager);

        /*final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) { }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageSelected(int position) {

                // When the given tab is selected, switch to the corresponding page in
                // the ViewPager.
                pager.setCurrentItem(position);
                selected = position;
                // invalidate the options menu so it can be updated
                invalidateOptionsMenu();
                // history fragment is at position zero so get this then refresh its
                // view
                ((LogFragment) tabsPagerAdapter.getItem(0)).refresh();
            }
        };

        pager.addOnPageChangeListener(pageChangeListener);
        // do this in a runnable to make sure the viewPager's views are already instantiated before triggering the onPageSelected call
        pager.post(new Runnable()
        {
            @Override
            public void run()
            {
                pageChangeListener .onPageSelected(pager.getCurrentItem());
            }
        });*/

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TabLayoutActivity", "ICI SELECTION OK");
                // When the given tab is selected, switch to the corresponding page in
                // the ViewPager.
                pager.setCurrentItem(tab.getPosition());
                selected = tab.getPosition();
                // invalidate the options menu so it can be updated
                invalidateOptionsMenu();
                // history fragment is at position zero so get this then refresh its
                // view
                ((LogFragment) tabsPagerAdapter.getItem(0)).refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        pager.post(new Runnable() {
            @Override
            public void run() {
                new TabLayout.TabLayoutOnPageChangeListener(tabs).onPageSelected(pager.getCurrentItem());
            }
        });

        connection = Connections.getInstance(this).getConnection(clientHandle);
        changeListener = new TabLayoutActivity.ChangeListener();
        connection.registerChangeListener(changeListener);
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuID;
        Integer button = null;
        boolean connected = Connections.getInstance(this)
                .getConnection(clientHandle).isConnected();

        // Select the correct action bar menu to display based on the
        // connectionStatus and which tab is selected
        if (connected) {
            menuID = R.menu.activity_connection_details;
        }
        else {
            menuID = R.menu.activity_connection_details_disconnected;
        }
        // inflate the menu selected
        getMenuInflater().inflate(menuID, menu);
        Listener listener = new Listener(this, clientHandle);
        // add listeners
        if (button != null) {
            // add listeners
            menu.findItem(button).setOnMenuItemClickListener(listener);
            if (!Connections.getInstance(this).getConnection(clientHandle)
                    .isConnected()) {
                menu.findItem(button).setEnabled(false);
            }
        }
        // add the listener to the disconnect or connect menu option
        if (connected) {
            menu.findItem(R.id.disconnect).setOnMenuItemClickListener(listener);
        }
        else {
            menu.findItem(R.id.connectMenuOption).setOnMenuItemClickListener(
                    listener);
        }

        return true;
    }

    /**
     * <code>ChangeListener</code> updates the UI when the {@link Connection}
     * object it is associated with updates
     *
     */
    private class ChangeListener implements PropertyChangeListener {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            // connection object has change refresh the UI


            tabLayoutActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    tabLayoutActivity.invalidateOptionsMenu();
                    ((LogFragment) tabsPagerAdapter.getItem(0)).refresh();

                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        connection.removeChangeListener(null);
        super.onDestroy();
    }
}