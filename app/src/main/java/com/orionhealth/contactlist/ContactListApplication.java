package com.orionhealth.contactlist;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ContactListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
