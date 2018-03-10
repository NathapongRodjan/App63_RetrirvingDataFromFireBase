package com.example.nathapong.app63_retrirvingdatafromfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtKey = (EditText)findViewById(R.id.edtKey);
        final EditText edtValue = (EditText)findViewById(R.id.edtValue);
        Button btnSendDataToServer = (Button)findViewById(R.id.btnSendDataToServer);
        final TextView txtValue = (TextView)findViewById(R.id.txtValue);
        Button btnGetDataFromServer = (Button)findViewById(R.id.btnGetDataFromServer);

        final TextView txtChildValue = (TextView)findViewById(R.id.txtChildValue);
        final TextView txtComputerName = (TextView)findViewById(R.id.txtComputerName);
        final TextView txtMobileName = (TextView)findViewById(R.id.txtMobileName);
        final TextView txtMyValue = (TextView)findViewById(R.id.txtMyValue);

        Button btnGetChildValue = (Button)findViewById(R.id.btnGetChildValue);
        Button btnGetComputerName = (Button)findViewById(R.id.btnGetComputerName);
        Button btnGetMobileName = (Button)findViewById(R.id.btnGetMobileName);
        Button btnMyValue = (Button)findViewById(R.id.btnMyValue);



        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        btnSendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child(edtKey.getText().toString())
                        .setValue(edtValue.getText().toString());

                String uniqueID = databaseReference.push().getKey();

                //databaseReference.child(uniqueID).setValue("")

            }
        });

        btnGetDataFromServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference("004")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int intValue = dataSnapshot.getValue(Integer.class);
                                Log.i("TAG", intValue + "");

                                txtValue.setText(intValue + "");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

            }
        });


        btnGetChildValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference("005").child("Child")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                txtChildValue.setText(value);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });

        btnGetComputerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase.getReference("Computer Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        txtComputerName.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnGetMobileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference("Mobile Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        txtMobileName.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        btnMyValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference("Something/Something Else/Thing/Key")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String value = dataSnapshot.getValue(String.class);
                                txtMyValue.setText(value);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }
}
