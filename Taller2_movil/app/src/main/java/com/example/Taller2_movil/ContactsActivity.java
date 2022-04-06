package com.example.Taller2_movil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsActivity extends AppCompatActivity {
    String[] mProjection;
    Cursor mCursor;
    ContactsAdapter mContactsAdapter;
    ListView listContacts;

    private int READ_CONTACTS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        pedirPermiso();

        inflarObjetos();

        initView();
    }

    private void pedirPermiso(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){
                Toast.makeText(this, "Mostrar contactos", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACTS_PERMISSION_CODE);
        }
    }

    private void inflarObjetos(){
        listContacts = (ListView) findViewById(R.id.listContacts);
        mProjection = new String[]{
                ContactsContract.Profile._ID,
                ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
        };
        mContactsAdapter = new ContactsAdapter(this, null, 0);
        listContacts.setAdapter(mContactsAdapter);
    }

    public void initView(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            mCursor=getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI,
                    mProjection, null, null, null);
            mContactsAdapter.changeCursor(mCursor);
        }
    }
}