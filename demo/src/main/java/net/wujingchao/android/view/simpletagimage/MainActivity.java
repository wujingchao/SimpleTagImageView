package net.wujingchao.android.view.simpletagimage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import net.wujingchao.android.view.SimpleTagImageView;


public class MainActivity extends Activity {

    private SimpleTagImageView mSimpleTagImageView;

    private EditText mEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleTagImageView = (SimpleTagImageView) findViewById(R.id.stiv);
        mEt = (EditText) findViewById(R.id.tv);
    }

    public void textSizePlus(View view) {
        mSimpleTagImageView.setTagTextSize(mSimpleTagImageView.getTagTextSize() + 1);
    }

    public void textSizeReduce(View view) {
        mSimpleTagImageView.setTagTextSize(mSimpleTagImageView.getTagTextSize() - 1);
    }

    public void cornerDistancePlus(View view) {
        mSimpleTagImageView.setCornerDistance(mSimpleTagImageView.getCornerDistance() + 1);
    }

    public void cornerDistanceReduce(View view) {
        mSimpleTagImageView.setCornerDistance(mSimpleTagImageView.getCornerDistance() - 1);
    }

    private int colors [] = {Color.RED,Color.BLUE,Color.CYAN,Color.YELLOW,Color.WHITE};

    private int index = 0;

    public void changeTextColor(View view) {
        index ++;
        if(index > colors.length - 1)index = 0;
        mSimpleTagImageView.setTagTextColor(colors[index]);
    }

    public void changeTagText(View view) {
        String tagText = mEt.getText().toString();
        if(TextUtils.isEmpty(tagText))return;
        mSimpleTagImageView.setTagText(tagText);
    }

    public void changeTagBackgroundColor(View view) {
        index ++;
        if(index > colors.length - 1)index = 0;
        mSimpleTagImageView.setTagBackgroundColor(colors[index]);
    }

    public void tagWidthPlus(View view) {
        mSimpleTagImageView.setTagWidth(mSimpleTagImageView.getTagWidth() + 1);
    }
    public void tagWidthReduce(View view) {
        mSimpleTagImageView.setTagWidth(mSimpleTagImageView.getTagWidth() - 1);
    }

    int [] orientations = {
            SimpleTagImageView.LEFT_TOP,
            SimpleTagImageView.RIGHT_TOP,
            SimpleTagImageView.LEFT_BOTTOM,
            SimpleTagImageView.RIGHT_BOTTOM
    };

    private int j = 0;

    public void changeTagOrientation(View view) {
        j++;
        if(j >  orientations.length -1 ) j = 0;
        mSimpleTagImageView.setmTagOrientation(orientations[j]);
    }
}
