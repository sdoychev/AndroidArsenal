package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.methodia.android.testautomation.Model.RowModel;
import com.methodia.android.testautomation.ModelAdapter;
import com.methodia.android.testautomation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity {

    private ArrayList<RowModel> rowModelList;
    private ModelAdapter modelAdapter;
    private Spinner sortSelectionSpinner;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();

        rowModelList = new ArrayList<>();
        initData(rowModelList);

        modelAdapter = new ModelAdapter(this, rowModelList);
        listView.setAdapter(modelAdapter);

        ArrayAdapter<CharSequence> sortOptionsAdapter = ArrayAdapter.createFromResource(this, R.array.sortSelectionOptions, android.R.layout.simple_spinner_dropdown_item);
        sortOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSelectionSpinner.setAdapter(sortOptionsAdapter);
        sortSelectionSpinner.setOnItemSelectedListener(new SortOrderSelectedListener());
    }

    private void initLayout() {
        listView = (ListView) findViewById(R.id.listView);
        sortSelectionSpinner = (Spinner) findViewById(R.id.sortSelectionSpinner);
    }

    private void initData(ArrayList<RowModel> list) {
        RowModel rowModelOne = new RowModel("Aaa", 8);
        list.add(rowModelOne);
        RowModel rowModelTwo = new RowModel("Aaa", 2);
        list.add(rowModelTwo);
        RowModel rowModelThree = new RowModel("Bbb", 1);
        list.add(rowModelThree);
        RowModel rowModelFour = new RowModel("Bbb", 5);
        list.add(rowModelFour);
        RowModel rowModelFive = new RowModel("Ccc", 6);
        list.add(rowModelFive);
        RowModel rowModelSix = new RowModel("Ccc", 3);
        list.add(rowModelSix);
    }

    private void printList(ArrayList<RowModel> rowModelList) {
        for (int i = 0; i < rowModelList.size(); i++) {
            Log.e("LIST", rowModelList.get(i).toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SortOrderSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String sortOrder = parent.getItemAtPosition(position).toString();
            Comparator<RowModel> comparator = RowModel.NameAscendingComparator; //by default we sort by name ascending
            if (sortOrder.equals(getString(R.string.nameAsc))) {
                comparator = RowModel.NameAscendingComparator;
            } else if (sortOrder.equals(getString(R.string.nameDesc))) {
                comparator = RowModel.NameDescendingComparator;
            } else if (sortOrder.equals(getString(R.string.numberAsc))) {
                comparator = RowModel.NumberAscendingComparator;
            } else if (sortOrder.equals(getString(R.string.numberDesc))) {
                comparator = RowModel.NumberDescendingComparator;
            }
            Collections.sort(rowModelList, comparator);
            modelAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //do nothing
        }
    }
}