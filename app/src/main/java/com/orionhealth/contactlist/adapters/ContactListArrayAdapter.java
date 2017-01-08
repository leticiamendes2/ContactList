package com.orionhealth.contactlist.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.orionhealth.contactlist.R;
import com.orionhealth.contactlist.models.Contact;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class ContactListArrayAdapter extends RealmBaseAdapter<Contact> implements ListAdapter {

    private List<Contact> contactList;
    private Activity act;

    public ContactListArrayAdapter(Activity act, OrderedRealmCollection<Contact> realmResults) {
        super(act, realmResults);
        this.act = act;
        this.contactList = realmResults;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public long getItemId(int i) {
        return contactList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = act.getLayoutInflater().inflate(R.layout.contact_list_item, viewGroup, false);
        Contact contact = contactList.get(i);

        TextView name = (TextView) view.findViewById(R.id.list_name);
        name.setText(contact.getName());

        TextView email = (TextView) view.findViewById(R.id.list_email);
        email.setText(contact.getEmail());

        return view;
    }
}
