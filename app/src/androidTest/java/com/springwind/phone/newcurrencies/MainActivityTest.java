package com.springwind.phone.newcurrencies;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    public void setUp() throws Exception {
        super.setUp();

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
