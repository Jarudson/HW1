package com.example.hw1.contacts;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;

import com.example.hw1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 1;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void removeItem(int position){
        // Get the id to locate the item in the items map
        String itemId = ITEMS.get(position).id;
        // remove the item from List
        ITEMS.remove(position);
        // remove the item from map
        ITEM_MAP.remove(itemId);
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Contact createDummyItem(int position) {
        return new Contact(String.valueOf(position), "Jarosław", "Gendaszyk", R.drawable.avatar1, 25091997, "123456789");
    }


//*****************************************************


    public static class Contact implements Parcelable {
        public String id;
        private String name;
        private String surname;
        private int picPath;
        private long birthday;
        private String number;

        public Contact(String id, String name, String surname, int picPath, long birthday, String number) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.picPath = picPath;
            this.birthday = birthday;
            this.number = number;
        }
        public Contact(String id, String name) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.picPath = picPath;
            this.birthday = birthday;
            this.number = number;
        }

        public String getName(){
            return name;
        }
        public String getSurname(){
            return surname;
        }
        public int getPicPath(){
            return picPath;
        }
        public long getBirthday(){
            return birthday;
        }
        public String getNumber(){
            return number;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
        public void setPicPath(int picPath){
            this.picPath = picPath;
        }
        public void setBirthday(long birthday){
            this.birthday = birthday;
        }
        public void setNumber(String number){
            this.number = number;
        }



        @Override
        public String toString() {
            return surname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
