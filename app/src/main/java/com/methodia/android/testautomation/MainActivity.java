package com.methodia.android.testautomation;

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

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private ArrayList<Model> modelList;
    private ModelAdapter modelAdapter;
    private Spinner sortSelectionSpinner;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();

        modelList = new ArrayList<>();
        initData(modelList);

        modelAdapter = new ModelAdapter(this, modelList);
        listView.setAdapter(modelAdapter);

        ArrayAdapter<CharSequence> sortOptionsAdapter = ArrayAdapter.createFromResource(this, R.array.sortSelectionOptions, android.R.layout.simple_spinner_item);
        sortOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sortSelectionSpinner.setAdapter(sortOptionsAdapter);
        sortSelectionSpinner.setOnItemSelectedListener(new SortOrderSelectedListener());
    }

    private void initLayout() {
        listView = (ListView) findViewById(R.id.listView);
        sortSelectionSpinner = (Spinner) findViewById(R.id.sortSelectionSpinner);
    }

    private void initData(ArrayList<Model> list) {
        Model modelOne = new Model("Aaa", 8);
        list.add(modelOne);
        Model modelTwo = new Model("Aaa", 2);
        list.add(modelTwo);
        Model modelThree = new Model("Bbb", 1);
        list.add(modelThree);
        Model modelFour = new Model("Bbb", 5);
        list.add(modelFour);
        Model modelFive = new Model("Ccc", 6);
        list.add(modelFive);
        Model modelSix = new Model("Ccc", 3);
        list.add(modelSix);
    }

    private void printList(ArrayList<Model> modelList) {
        for (int i = 0; i < modelList.size(); i++) {
            Log.e("LIST", modelList.get(i).toString());
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
            if (sortOrder.equals(getString(R.string.nameAsc))) {
                Collections.sort(modelList, Model.NameAscendingComparator);
            } else if (sortOrder.equals(getString(R.string.nameDesc))) {
                Collections.sort(modelList, Model.NameDescendingComparator);
            } else if (sortOrder.equals(getString(R.string.numberAsc))) {
                Collections.sort(modelList, Model.NumberAscendingComparator);
            } else if (sortOrder.equals(getString(R.string.numberDesc))) {
                Collections.sort(modelList, Model.NumberDescendingComparator);
            }
            modelAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //do nothing
        }
    }
}