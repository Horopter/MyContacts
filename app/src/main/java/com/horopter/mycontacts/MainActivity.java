package com.horopter.mycontacts;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button contacts;
    private Button ringer;
    private boolean isPLAYING = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts = (Button)findViewById(R.id.contactme);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ContactList.class);
                startActivity(i);
            }
        });
        ringer = (Button)findViewById(R.id.ring);
        ringer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                if (isPLAYING) {
                    isPLAYING = false;
                    r.stop();
                } else {
                    isPLAYING = true;
                    r.play();
                }
            }
        });
    }
}
