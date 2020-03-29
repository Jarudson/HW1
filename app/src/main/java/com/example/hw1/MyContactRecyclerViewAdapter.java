package com.example.hw1;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hw1.ContactList.OnListContactClickInteraction;
import com.example.hw1.contacts.ContactListContent.Contact;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnListContactClickInteraction}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListContactClickInteraction mListener;

    public MyContactRecyclerViewAdapter(List<Contact> items, OnListContactClickInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTextView.setText(mValues.get(position).toString());
        holder.mImView.setImageResource(mValues.get(position).getPicPath());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListContactClickInteraction(holder.mItem, position);
                }
            }
        });
    }
/*
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Drawable contactDrawable;
        Context context = holder.mView.getContext();
        contactDrawable = context.getResources().getDrawable(R.drawable.avatar1);
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).toString());
        holder.mImView.setImageDrawable(contactDrawable);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListContactClickInteraction(holder.mItem, position);
                }
            }
        });
    }
*/
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImView;
        public final TextView mTextView;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.surname);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText() + "'";
        }
    }
}
