package com.springwind.phone.newcurrencies;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Muzhou on 12/7/2017.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2{
    private MainActivity mMainActivity;
    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;
    private Spinner mForSpinner,mHomSpinner;
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInteger() throws Throwable {
        proxyCurrencyConverterTask("12");
    }

    public void testFloat() throws Throwable {
        proxyCurrencyConverterTask("12..3");
    }

    public void proxyCurrencyConverterTask(final String str) throws Throwable {
        final CountDownLatch latch = new CountDownLatch(1);
        mMainActivity.setCurrencyTaskCallback(new MainActivity.CurrencyTaskCallback() {
            @Override
            public void executionDone() {
                latch.countDown();
                assertEquals(convertToDouble(mTextView.getText().toString().substring(0,5)),convertToDouble(str));
            }
        });
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEditText.setText(str);
                mForSpinner.setSelection(0);
                mHomSpinner.setSelection(0);
                mButton.performClick();

            }
        });
        latch.await(30, TimeUnit.SECONDS);
    }
    public double convertToDouble(String str) throws NumberFormatException {
        double dReturn = 0;
        try{
            dReturn = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw e;
        }
        return dReturn;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        ArrayList<String> bogusCurrencies = new ArrayList<String>();
        bogusCurrencies.add("USD|United States Dollar");
        bogusCurrencies.add("EUR|Euro");
        Intent intent = new Intent();
        intent.putExtra(Main2Activity.KEY_ARRAYLIST,bogusCurrencies);
        setActivityIntent(intent);

        mMainActivity = (MainActivity) getActivity();
        mButton = (Button) mMainActivity.findViewById(R.id.button);
        mTextView = (TextView) mMainActivity.findViewById(R.id.txt_converted);
        mEditText = (EditText) mMainActivity.findViewById(R.id.editText);
        mForSpinner = (Spinner) mMainActivity.findViewById(R.id.spn_for);
        mHomSpinner = (Spinner) mMainActivity.findViewById(R.id.spn_hom);
    }

    public MainActivityTest(Class activityClass) {
        super(activityClass);
    }
}
