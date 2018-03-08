package com.sarthak.retrofit.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GithubRepoAdapter extends ArrayAdapter<GithubRepo> {

    private Context mContext;
    private List<GithubRepo> reposList;

    public GithubRepoAdapter(@NonNull Context context, List<GithubRepo> values) {
        super(context, R.layout.card_repos_list, values);

        mContext = context;
        reposList = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.card_repos_list, parent, false);
        }

        TextView textView = row.findViewById(R.id.list_item_pagination_text);

        GithubRepo item = reposList.get(position);
        String message = item.getName();
        textView.setText(message);

        return row;
    }
}
