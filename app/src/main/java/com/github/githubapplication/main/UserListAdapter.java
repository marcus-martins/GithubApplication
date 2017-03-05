package com.github.githubapplication.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.githubapplication.R;
import com.github.githubapplication.entities.User;
import com.github.githubapplication.main.contracts.MainView;

import java.util.ArrayList;

/**
 * Created by marcus on 04/03/17.
 */

public class UserListAdapter extends ArrayAdapter<User> {

    private MainView mainView;

    public UserListAdapter(Context context, ArrayList<User> users, MainView mainView) {
        super(context, 0, users);
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        TextView loginName = (TextView) convertView.findViewById(R.id.loginName);
        ImageView userImage = (ImageView) convertView.findViewById(R.id.userImage);

        loginName.setText(user != null ? user.getLogin() : null);

        Glide.with(getContext())
                .load(user != null ? user.getPhoto() : null)
                .centerCrop()
                .crossFade()
                .into(userImage);


        RelativeLayout itemUser = (RelativeLayout) convertView.findViewById(R.id.relItemUser);
        itemUser.setOnClickListener(v ->
                mainView.openUser(user != null ? user.getLogin() : null,
                        user != null ? user.getPhoto() : null));

        return convertView;
    }

}
