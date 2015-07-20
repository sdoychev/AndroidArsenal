package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.methodia.android.testautomation.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class TimberActivity extends Activity {

    static final ButterKnife.Action<View> INVISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.INVISIBLE);
        }
    };
    static final ButterKnife.Setter<View, Integer> SET_VISIBILITY = new ButterKnife.Setter<View, Integer>() {
        @Override
        public void set(View view, Integer value, int index) {
            view.setVisibility(value);
        }
    };
    // INJECT VIEWS ===
    @Bind(R.id.introLabel)
    TextView introLabel;
    @Bind(R.id.buttonOne)
    Button buttonOne;
    @Bind(R.id.buttonTwo)
    Button buttonTwo;
    // IT IS POSSIBLE TO CREATE LISTS OF VIEWS ======
    @Bind({R.id.buttonOne, R.id.buttonTwo})
    List<Button> buttonsList;

    // HANDLE BUTTON CLICKS ===
    @OnClick(R.id.buttonOne)
    void clickButtonOne() {
        Timber.i("Button ONE clicked!");
    }

    @OnClick(R.id.buttonTwo)
    public void clickButtonTwo(Button button) {
        button.setText("Clicked!");
    }

    // HANDLE MULTIPLE BUTTON CLICKS ======
    @OnClick({R.id.buttonOne, R.id.buttonTwo})
    public void clickMultiple(Button button) {
        if (button.getText().equals("Clicked!")) {
            Timber.w("Correct button clicked!");
        } else {
            Timber.e("Wrong button clicked!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);

        //BIND INJECTIONS TO VIEW
        ButterKnife.bind(this);

        //PLAND TIMBER TREE - DEBUG TREE WON'T SHOW MESSAGE IN RELEASE APK
        Timber.plant(new Timber.DebugTree());

        // TESTS WITH TIMBER LIB.
        Timber.i("Test INFO logging.");
        Timber.d("Test DEBUG logging.");
        Timber.e("Test ERROR logging.");
        String stringParam = "String";
        int intParam = 123;
        Timber.i("Test logging with string param - %s.", stringParam);
        Timber.d("Test logging with int param - %d.", intParam);
        Timber.e("Test logging with string and int params - %s, %d.", stringParam, intParam);

        // TESTS WITH BUTTER KNIFE LIB.
        buttonOne.setText("New text for ButtonOne");
        ButterKnife.apply(buttonsList, INVISIBLE);
        ButterKnife.apply(buttonsList, SET_VISIBILITY, View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timber_pid_cat, menu);
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
