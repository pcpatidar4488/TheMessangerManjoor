package com.example.manjooralam.themessanger.application;

import android.app.Application;
import android.content.Context;

import com.example.manjooralam.themessanger.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@ReportsCrashes(// will not be used
        mailTo = "manjooralam28@gmail.com, shahidanjum1607@gmail.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)

public class FlashApplication extends Application {

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       // MultiDex.install(this);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        // initalize Calligraphy
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("OpenSans-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
            mDatabaseReference.child("online").onDisconnect().setValue(ServerValue.TIMESTAMP);
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}

