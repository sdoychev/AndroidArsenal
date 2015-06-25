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
    }

    private void initLayout() {
        listView = (ListView) findViewById(R.id.listView);
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

    public void sortList(View v) {
        switch (v.getId()) {
            case R.id.sortNameAsc:
                Collections.sort(modelList, Model.NameAscendingComparator);
                break;
            case R.id.sortNameDesc:
                Collections.sort(modelList, Model.NameDescendingComparator);
                break;
            case R.id.sortNumberAsc:
                Collections.sort(modelList, Model.NumberAscendingComparator);
                break;
            case R.id.sortNumberDesc:
                Collections.sort(modelList, Model.NumberDescendingComparator);
                break;
        }
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
