package com.methodia.android.testautomation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.methodia.android.testautomation.Model.RowModel;

import java.util.ArrayList;

/**
 * Created by Doychev on 24.6.2015 г..
 */
public class ModelAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RowModel> rowModels;

    public ModelAdapter(Context context, ArrayList<RowModel> rowModelList) {
        this.context = context;
        this.rowModels = rowModelList;
    }

    @Override
    public int getCount() {
        return rowModels.size();
    }

    @Override
    public Object getItem(int position) {
        return rowModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout listViewRow;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listViewRow = (LinearLayout) inflater.inflate(R.layout.list_view_row, null);
        } else {
            listViewRow = (LinearLayout) convertView;
        }

        TextView textName = (TextView) listViewRow.findViewById(R.id.modelName);
        TextView textNumber = (TextView) listViewRow.findViewById(R.id.modelNumber);
        textName.setText(rowModels.get(position).getName());
        textNumber.setText("" + rowModels.get(position).getNumber());

        return listViewRow;
    }
}