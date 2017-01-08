package com.orionhealth.contactlist.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.orionhealth.contactlist.R;
import com.orionhealth.contactlist.models.Contact;

public class ContactDetailsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        Contact contact = getIntent().getParcelableExtra("contact");

        TextView username = (TextView) findViewById(R.id.username_value);
        TextView phone = (TextView) findViewById(R.id.phone_value);
        TextView address = (TextView) findViewById(R.id.address_value);
        TextView company = (TextView) findViewById(R.id.company_value);

        username.setText(contact.getUsername());
        phone.setText(contact.getPhone());
        address.setText(contact.getAddress().getSuite() + ", " + contact.getAddress().getStreet() + ", " +
                contact.getAddress().getCity() + ", " + contact.getAddress().getZipcode());
        company.setText(contact.getCompany().getName() + "\n" + contact.getCompany().getCatchPhrase() + "\n" +
                contact.getCompany().getBs());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
