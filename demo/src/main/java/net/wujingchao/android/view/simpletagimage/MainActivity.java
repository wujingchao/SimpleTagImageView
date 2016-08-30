package net.wujingchao.android.view.simpletagimage;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.LinkedHashMap;
import java.util.Map;


public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";

    private SimpleTagImageView mSimpleTagImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleTagImageView = (SimpleTagImageView) findViewById(R.id.stiv);
        setUpTextSizeSeekBar();
        setUpCornerDistanceSeekBar();
        setUpTextColorSpinner();
        setUpTextStyleSpinner();
        setUpTextTag();
        setUpTagBackgroundColor();
        setUpTagWidth();
        setUpTagOrientation();
        setUpTagRoundRadius();
        setUpScaleType();
    }

    private void setUpTextSizeSeekBar() {
        SeekBar mTextSizeSeekBar = (SeekBar) findViewById(R.id.sb_text_size);
        mTextSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "progress:" + progress);
                mSimpleTagImageView.setTagTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setUpCornerDistanceSeekBar() {
        SeekBar mCornerDistanceSeekBar = (SeekBar) findViewById(R.id.sb_corner_distance);
        mCornerDistanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSimpleTagImageView.setCornerDistance(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setUpTextColorSpinner() {
        final Map<String, Integer> colors = new LinkedHashMap<>();
        colors.put("WHITE", Color.WHITE);
        colors.put("RED", Color.RED);
        colors.put("BLUE", Color.BLUE);
        colors.put("CYAN", Color.CYAN);
        colors.put("YELLOW", Color.YELLOW);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_text_color);
        final Object[] keys = colors.keySet().toArray();
        final SpinnerAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = (String) keys[position];
                mSimpleTagImageView.setTagTextColor(colors.get(key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpTextStyleSpinner() {
        final Map<String, Byte> styles = new LinkedHashMap<>();
        styles.put("NORMAL", SimpleTagImageView.TEXT_STYLE_NORMAL);
        styles.put("BOLD", SimpleTagImageView.TEXT_STYLE_BOLD);
        styles.put("ITALIC", SimpleTagImageView.TEXT_STYLE_ITALIC);
        styles.put("BOLD_ITALIC", SimpleTagImageView.TEXT_STYLE_BOLD_ITALIC);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_tag_text_style);
        final Object[] keys = styles.keySet().toArray();
        final SpinnerAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = (String) keys[position];
                mSimpleTagImageView.setTagTextStyle(styles.get(key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpTextTag() {
        EditText mEt = (EditText) findViewById(R.id.tv);
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSimpleTagImageView.setTagText(s.toString());
            }
        });
    }

    private void setUpTagBackgroundColor() {
        final Map<String, Integer> colors = new LinkedHashMap<>();
        colors.put("Green", 0xaf27CDC0);
        colors.put("RED", Color.RED);
        colors.put("BLUE", Color.BLUE);
        colors.put("CYAN", Color.CYAN);
        colors.put("YELLOW", Color.YELLOW);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_background_color);
        final Object[] keys = colors.keySet().toArray();
        final SpinnerAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = (String) keys[position];
                mSimpleTagImageView.setTagBackgroundColor(colors.get(key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpTagWidth() {
        SeekBar mTagWidthSeekBar = (SeekBar) findViewById(R.id.sb_tag_width);
        mTagWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSimpleTagImageView.setTagWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setUpTagOrientation() {
        final Map<String, Byte> orientations = new LinkedHashMap<>();
        orientations.put("LEFT_TOP", SimpleTagImageView.LEFT_TOP);
        orientations.put("RIGHT_TOP", SimpleTagImageView.RIGHT_TOP);
        orientations.put("LEFT_BOTTOM", SimpleTagImageView.LEFT_BOTTOM);
        orientations.put("RIGHT_BOTTOM", SimpleTagImageView.RIGHT_BOTTOM);
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_tag_orientation);
        final Object[] keys = orientations.keySet().toArray();
        final SpinnerAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = (String) keys[position];
                mSimpleTagImageView.setTagOrientation(orientations.get(key));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putString("tag_text", mSimpleTagImageView.getTagText());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mSimpleTagImageView.setTagText(savedInstanceState.getString("tag_text"));
        }
    }

    private void setUpTagRoundRadius() {
        SeekBar mTextSizeSeekBar = (SeekBar) findViewById(R.id.sb_tag_round_radius);
        mTextSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSimpleTagImageView.setTagRoundRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setUpScaleType() {
        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_tag_scale_type);
//        final ImageView.ScaleType []  types = ImageView.ScaleType.values();
        final ImageView.ScaleType[] types = {ImageView.ScaleType.FIT_XY};
        final SpinnerAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, types[position].toString());
                mSimpleTagImageView.setScaleType(types[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
