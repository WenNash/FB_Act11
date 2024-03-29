package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    TextView tv_key;
    EditText ed_Nama, ed_telpon;
    Button btnEdit;
    DatabaseReference databaseReference;
    String kode, nama, telpon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        tv_key = findViewById(R.id.tv_key);
        ed_Nama = findViewById(R.id.editNama);
        ed_telpon = findViewById(R.id.editTelpon);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("kunci1");
        nama = bundle.getString("kunci2");
        telpon = bundle.getString("kunci3");

        tv_key.setText("Key: "+kode);
        ed_Nama.setText(nama);
        ed_telpon.setText(telpon);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeman(new Teman(ed_Nama.getText().toString(),
                        ed_telpon.getText().toString()));
            }
        });
    }
    public void editTeman(Teman teman){
        databaseReference.child("Teman")
                .child(kode)
                .setValue(teman)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TemanEdit.this, "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}