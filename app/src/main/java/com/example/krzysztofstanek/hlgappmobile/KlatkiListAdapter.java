package com.example.krzysztofstanek.hlgappmobile;



import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class KlatkiListAdapter extends ArrayAdapter<KlatkiModel>{

    private Context mContext;
    private List<KlatkiModel> list = new ArrayList<>();

    public KlatkiListAdapter(@NonNull Context context, @LayoutRes ArrayList<KlatkiModel> list) {
        super(context, 0 , list);
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.klatkilistelement ,parent,false);

        KlatkiModel current = list.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.klatkilistaelement);
        name.setText(current.getName());
        name.setTag(current.getID());

        return listItem;
    }
}