package com.methodia.android.testautomation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private ArrayList<Model> modelList;
    private ModelAdapter modelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelList = new ArrayList<>();
        initData(modelList);
        modelAdapter = new ModelAdapter(this, modelList);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(modelAdapter);
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

    //TODO Refactor - find a way to create a function for all four below.

    public void sortNameAsc(View v) {
        Log.e("LIST", "Sorting by name, ascending ...");
        Collections.sort(modelList, Model.NameAscendingComparator);
        printList(modelList);
        modelAdapter.notifyDataSetChanged();
    }

    public void sortNameDesc(View v) {
        Log.e("LIST", "Sorting by name, descending ...");
        Collections.sort(modelList, Model.NameDescendingComparator);
        printList(modelList);
        modelAdapter.notifyDataSetChanged();
    }

    public void sortNumberAsc(View v) {
        Log.e("LIST", "Sorting by number, ascending ...");
        Collections.sort(modelList, Model.NumberAscendingComparator);
        printList(modelList);
        modelAdapter.notifyDataSetChanged();
    }

    public void sortNumberDesc(View v) {
        Log.e("LIST", "Sorting by number, descending ...");
        Collections.sort(modelList, Model.NumberDescendingComparator);
        printList(modelList);
        modelAdapter.notifyDataSetChanged();
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
}
