package com.caocao.bestmarket.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caocao.bestmarket.R;

import java.util.ArrayList;

/**
 * Created by vutuananh on 4/13/2018.
 */


public class NoteAdapter extends ArrayAdapter<Note> {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    /**
     * Create a new {@link NoteAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param notes is the list of {@link Note}s to be displayed.
     */
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_notification, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Note currentNote = getItem(position);

        // Find the TextView in the list_notification.xmltion.xml layout with the ID miwok_text_view.
        TextView contentTextView = (TextView) listItemView.findViewById(R.id.content_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        contentTextView.setText(currentNote.getContent());

        // Find the TextView in the list_notification.xmltion.xml layout with the ID default_text_view.
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        timeTextView.setText(currentNote.getTime());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.noti_profile_image);
        if(currentNote.hasImage()){
            imageView.setImageResource(currentNote.getImage());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}