package com.example.hw1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hw1.contacts.ContactListContent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddContact extends AppCompatActivity {
    long date;
    ContactListContent contactListContent;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        final EditText txt3 = findViewById(R.id.editBirthday);
        txt3.setInputType(InputType.TYPE_NULL);
        txt3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddContact.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                txt3.setText(dayOfMonth + "/" + (month +1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    public int randomizePicture(){
        ArrayList<Integer> pictures = new ArrayList<>();
        pictures.add(R.drawable.avatar_1);
        pictures.add(R.drawable.avatar_2);
        pictures.add(R.drawable.avatar_3);
        pictures.add(R.drawable.avatar_4);
        pictures.add(R.drawable.avatar_5);
        pictures.add(R.drawable.avatar_6);
        pictures.add(R.drawable.avatar_7);
        pictures.add(R.drawable.avatar_8);
        pictures.add(R.drawable.avatar_9);
        pictures.add(R.drawable.avatar_10);
        pictures.add(R.drawable.avatar_11);
        pictures.add(R.drawable.avatar_12);
        pictures.add(R.drawable.avatar_13);
        pictures.add(R.drawable.avatar_14);
        pictures.add(R.drawable.avatar_15);
        pictures.add(R.drawable.avatar_16);
        int max = pictures.size() -1;
        int min = 0;
        return pictures.get((int) ((Math.random() * max - min + 1) +min));
    }

    boolean checkNumber(String x){
        int counter = 0;
        if(x.length() == 9){
        for(int i = 0; i < x.length(); i++){
                if((x.charAt(i) >= 48) && (x.charAt(i) <= 57)){
                    counter++;
                }
            }
            if(counter == x.length()) return true;
            else return false;
        }
        else return false;

    }

    public void addClick(View view) {
        EditText txt1 = findViewById(R.id.editName);
        EditText txt2 = findViewById(R.id.editSurname);
        EditText txt3 = findViewById(R.id.editBirthday);
        EditText txt4 = findViewById(R.id.editPhoneNumber);

        String name = txt1.getText().toString();
        String surname = txt2.getText().toString();
        String birthday = txt3.getText().toString();
        String phoneNumber = txt4.getText().toString();

        if(name.isEmpty() || surname.isEmpty() || birthday.isEmpty() || phoneNumber.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please input all data", Toast.LENGTH_SHORT).show();
        }
        else {
            if(checkNumber(phoneNumber)){
                Toast.makeText(getApplicationContext(), "New contact added", Toast.LENGTH_SHORT).show();
                ContactListContent.addItem(new ContactListContent.Contact("Contact" + ContactListContent.ITEMS.size() +1, name, surname, randomizePicture(), date, phoneNumber));
                ((ContactList) getSupportFragmentManager().findFragmentById(R.id.contactList)).notifyDataChange();
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Wrong phone number format", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
