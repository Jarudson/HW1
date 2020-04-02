package com.example.hw1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hw1.contacts.ContactListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements
        ContactList.OnListContactClickInteraction,
        DeleteDialog.OnDeleteDialogInteractionListener,
        CallDialog.OnCallDialogInteractionListener
{
    private int currentItemPosition = -1;
    private int checkCurrentItemPosition = -1;
    public static final String contactExtra = "contactExtra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddContact.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void startSecondActivity(ContactListContent.Contact contact, int position){
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(contactExtra, contact);
        startActivity(intent);
    }

    private void showCallDialog(ContactListContent.Contact contact){
        CallDialog.newInstance(contact).show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    private void displayContactInFragment(ContactListContent.Contact contact){
        ContactInfo contactInfo = ((ContactInfo) getSupportFragmentManager().findFragmentById(R.id.detailInfo));
        if(contactInfo != null){
            contactInfo.displayContact(contact);
        }
    }


    @Override
    public void onListContactClickInteraction(ContactListContent.Contact contact, int position) {
        //Toast.makeText(getApplicationContext(), "single click", Toast.LENGTH_SHORT).show();
        checkCurrentItemPosition = position;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayContactInFragment(contact);
        }
        else {
            startSecondActivity(contact, position);
        }
    }

    @Override
    public void onListContactLongClickInteraction(ContactListContent.Contact contact) {
        showCallDialog(contact);
    }

    @Override
    public void onButtonTrashClick (int position){
        showDeleteDialog();
        currentItemPosition = position;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                ((ContactList) getSupportFragmentManager().findFragmentById(R.id.contactList)).notifyDataChange();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Add contact error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDeleteDialogPositivieClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < ContactListContent.ITEMS.size()){
            ContactListContent.removeItem(currentItemPosition);
            ((ContactList) getSupportFragmentManager().findFragmentById(R.id.contactList)).notifyDataChange();
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && currentItemPosition == checkCurrentItemPosition){
                displayContactInFragment(null);
            }
        }
    }

    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Delete not confirmed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallPositivieClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Calling...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Call not confirmed", Toast.LENGTH_SHORT).show();
    }
}
