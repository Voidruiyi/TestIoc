package com.ruiyi.testioc.view;

import android.os.Bundle;
import android.widget.TextView;

import com.ruiyi.testioc.R;
import com.ruiyi.testioc.inject.LayoutInject;
import com.ruiyi.testioc.inject.ViewInject;

@LayoutInject(R.layout.activity_main)
public class MainActivity extends InJectActivity {

    @ViewInject(R.id.tv)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextView.setText("1350");
    }
}
