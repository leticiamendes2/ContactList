package com.orionhealth.contactlist.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orionhealth.contactlist.interfaces.AsyncResponse;
import com.orionhealth.contactlist.adapters.ContactListArrayAdapter;
import com.orionhealth.contactlist.network.GetService;
import com.orionhealth.contactlist.R;
import com.orionhealth.contactlist.dialogs.ConnectionErrorDialogFragment;
import com.orionhealth.contactlist.models.Contact;
import com.orionhealth.contactlist.utils.Util;

import org.json.JSONArray;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private static final String ORIENTATION = "ORIENTATION";
    private Realm realm;
    private ListView contactsListView;
    private boolean orientationChanged = false;
    private boolean activityCreated = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityCreated = true;
        realm = Realm.getDefaultInstance();
        contactsListView = (ListView) this.findViewById(R.id.contact_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(activityCreated && orientationChanged)
        {
            orientationChanged = false;
            fillListView();
        }
        else if(activityCreated)
        {
            if (!new Util().hasNetworkConnection(this)) {
                new ConnectionErrorDialogFragment().show(this.getFragmentManager(), "connection_error_message");
            } else {
                GetService getService = new GetService();
                getService.delegate = this;
                getService.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }

        activityCreated = false;
    }

    private void fillListView() {
        RealmResults<Contact> contacts = realm.where(Contact.class).findAll();
        ContactListArrayAdapter adapter = new ContactListArrayAdapter(this, contacts);
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(MainActivity.this, ContactDetailsActivity.class);
                mIntent.putExtra("contact", (Contact)adapterView.getItemAtPosition(i));
                startActivity(mIntent);
            }
        });

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        contactsListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_a_z:
                this.sort(0);
                return true;
            case R.id.sort_z_a:
                this.sort(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void processFinish(final JSONArray result) {
        realm.beginTransaction();
        realm.where(Contact.class).findAll().deleteAllFromRealm();
        realm.createAllFromJson(Contact.class, result);
        realm.commitTransaction();
        fillListView();
    }

    private  void sort(int direction)
    {
        RealmResults<Contact> result;
        if(direction == 0)
        {
            result = realm.where(Contact.class).findAllSorted("name", Sort.ASCENDING);
        }
        else
        {
            result = realm.where(Contact.class).findAllSorted("name", Sort.DESCENDING);
        }

        ContactListArrayAdapter adapter = new ContactListArrayAdapter(this, result);
        contactsListView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(ORIENTATION, true);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        orientationChanged = savedInstanceState.getBoolean(ORIENTATION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}