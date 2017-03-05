package com.github.githubapplication.user_details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.githubapplication.R;
import com.github.githubapplication.entities.Repository;

import java.util.ArrayList;

/**
 * Created by marcus on 04/03/17.
 */

public class RepositoryListAdapter extends ArrayAdapter<Repository> {

    public RepositoryListAdapter(Context context, ArrayList<Repository> repositories) {
        super(context, 0, repositories);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Repository repository = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_repository, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(repository != null ? repository.getName() : null);

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(repository != null ? repository.getDescription() : null);

        return convertView;
    }

}