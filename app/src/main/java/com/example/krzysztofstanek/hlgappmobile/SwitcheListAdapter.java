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


public class SwitcheListAdapter extends ArrayAdapter<SwitcheModel>{

    private Context mContext;
    private List<SwitcheModel> list = new ArrayList<>();

    public SwitcheListAdapter(@NonNull Context context, @LayoutRes ArrayList<SwitcheModel> list) {
        super(context, 0 , list);
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.switchelistaelement ,parent,false);

        SwitcheModel current = list.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.switchelistaelement);
        name.setText(current.getName());
        name.setTag(current.getID());

        return listItem;
    }
}