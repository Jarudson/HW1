package com.example.hw1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hw1.contacts.ContactListContent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfo extends Fragment {

    public ContactInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }
    @SuppressLint("SetTextI18n")
    public void displayContact(ContactListContent.Contact contact){
        FragmentActivity activity = getActivity();

        TextView name = activity.findViewById(R.id.nameInfo);
        TextView surname = activity.findViewById(R.id.surnameInfo);
        TextView birthday = activity.findViewById(R.id.birthdayInfo);
        TextView phoneNumber = activity.findViewById(R.id.phoneNumberInfo);
        ImageView avatar = activity.findViewById(R.id.avatarInfo);
        if(contact == null){
            name.setText(null);
            surname.setText(null);
            birthday.setText(null);
            phoneNumber.setText(null);
            avatar.setImageDrawable(null);
            return;
        }
        else {
            name.setText(contact.getName());
            surname.setText(contact.getSurname());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date date = new Date(contact.getBirthday());
            String birthTxt = simpleDateFormat.format(date);
            birthday.setText("Birthday: " + birthTxt);
            phoneNumber.setText("Phone Number: " + contact.getNumber());
            Drawable avatarDrawable;
            avatarDrawable = getActivity().getResources().getDrawable(contact.getPicPath());
            avatar.setImageDrawable(avatarDrawable);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            ContactListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.contactExtra);
            if(receivedContact != null){
                displayContact(receivedContact);
            }
        }
    }
}
