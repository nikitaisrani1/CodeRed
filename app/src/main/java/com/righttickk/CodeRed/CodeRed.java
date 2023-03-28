package com.righttickk.CodeRed;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class CodeRed extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
