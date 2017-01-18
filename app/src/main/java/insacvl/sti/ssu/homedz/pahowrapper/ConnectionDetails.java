package insacvl.sti.ssu.homedz.pahowrapper;

/*******************************************************************************
 * Copyright (c) 1999, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import insacvl.sti.ssu.homedz.R;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * The connection details activity operates the fragments that make up the
 * connection details screen.
 * <p>
 * The fragments which this FragmentActivity uses are
 * <ul>
 * <li>{@link HistoryFragment}
 * </ul>
 *
 */
public class ConnectionDetails extends AppCompatActivity implements
        ActionBar.TabListener {

    /**
     * {@link SectionsPagerAdapter} that is used to get pages to display
     */
    SectionsPagerAdapter sectionsPagerAdapter;
    /**
     * {@link ViewPager} object allows pages to be flipped left and right
     */
    ViewPager viewPager;

    /** The currently selected tab **/
    private int selected = 0;

    /**
     * The handle to the {@link Connection} which holds the data for the client
     * selected
     **/
    private String clientHandle = null;

    /** This instance of <code>ConnectionDetails</code> **/
    private final ConnectionDetails connectionDetails = this;

    /**
     * The instance of {@link Connection} that the <code>clientHandle</code>
     * represents
     **/
    private Connection connection = null;

    /**
     * The {@link ChangeListener} this object is using for the connection
     * updates
     **/
    private ChangeListener changeListener = null;

    /**
     * @see android.support.v7.app.AppCompatActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientHandle = getIntent().getStringExtra("handle");

        setContentView(R.layout.activity_connection_details);
        // Create the adapter that will return a fragment for each of the pages
        sectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // Set up the action bar for tab navigation
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // add the sectionsPagerAdapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                    @Override
                    public void onPageSelected(int position) {
                        // select the tab that represents the current page
                        actionBar.setSelectedNavigationItem(position);

                    }
                });

        // Create the tabs for the screen
        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(sectionsPagerAdapter.getPageTitle(i));
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }

        connection = Connections.getInstance(this).getConnection(clientHandle);
        changeListener = new ChangeListener();
        connection.registerChangeListener(changeListener);
    }


    @Override
    protected void onDestroy() {
        connection.removeChangeListener(null);
        super.onDestroy();
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

            switch (selected) {
                case 0 : // history view
                    menuID = R.menu.activity_connection_details;
                    break;
                /*case 1 : // subscribe view
                    menuID = R.menu.activity_subscribe;
                    button = R.id.subscribe;
                    break;*/
                /*case 1 : // publish view
                    menuID = R.menu.activity_publish;
                    button = R.id.publish;
                    break;*/
                default :
                    menuID = R.menu.activity_connection_details;
                    break;
            }
        }
        else {
            switch (selected) {
                case 0 : // history view
                    menuID = R.menu.activity_connection_details_disconnected;
                    break;
                /*case 1 : // subscribe view
                    menuID = R.menu.activity_subscribe_disconnected;
                    button = R.id.subscribe;
                    break;*/
                /*case 1 : // publish view
                    menuID = R.menu.activity_publish_disconnected;
                    button = R.id.publish;
                    break;*/
                default :
                    menuID = R.menu.activity_connection_details_disconnected;
                    break;
            }
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        viewPager.setCurrentItem(tab.getPosition());
        selected = tab.getPosition();
        // invalidate the options menu so it can be updated
        invalidateOptionsMenu();
        // history fragment is at position zero so get this then refresh its
        // view
        ((HistoryFragment) sectionsPagerAdapter.getItem(0)).refresh();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    /**
     * Provides the Activity with the pages to display for each tab
     *
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        // Stores the instances of the pages
        private ArrayList<Fragment> fragments = null;

        /**
         * Only Constructor, requires a the activity's fragment managers
         *
         * @param fragmentManager
         */
        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragments = new ArrayList<Fragment>();
            // create the history view, passes the client handle as an argument
            // through a bundle
            Fragment fragment = new HistoryFragment();
            Bundle args = new Bundle();
            args.putString("handle", getIntent().getStringExtra("handle"));
            fragment.setArguments(args);
            // add all the fragments for the display to the fragments list
            fragments.add(fragment);

        }

        /**
         * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
         */
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        /**
         * @see android.support.v4.view.PagerAdapter#getCount()
         */
        @Override
        public int getCount() {
            return fragments.size();
        }

        /**
         *
         * @see FragmentPagerAdapter#getPageTitle(int)
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0 :
                    return getString(R.string.history).toUpperCase();
                /*case 1 :
                    return getString(R.string.subscribe).toUpperCase();*/
                /*case 1 :
                    return getString(R.string.publish).toUpperCase();*/
            }
            // return null if there is no title matching the position
            return null;
        }

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

            connectionDetails.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    connectionDetails.invalidateOptionsMenu();
                    ((HistoryFragment) connectionDetails.sectionsPagerAdapter
                            .getItem(0)).refresh();

                }
            });

        }
    }

}
