package net.wujingchao.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import wujingchao.net.mylibrary.R;

/**
 * @author wujingchao  2015-02-20 email:wujingchao@aliyun.com
 */
public class SimpleTagImageView extends ImageView {

    public  static final byte LEFT_TOP = 0x00;

    public  static final byte RIGHT_TOP = 0x01;

    public  static final byte LEFT_BOTTOM = 0x02;

    public  static final byte RIGHT_BOTTOM = 0x03;

    private final static float THE_SQUARE_ROOT_OF_2 = (float) Math.sqrt(2);

    private final static int DEFAULT_TAG_WIDTH = 20;

    private final static int DEFAULT_CORNER_DISTANCE = 20;

    private final static int DEFAULT_TAG_BACKGROUND_COLOR = 0x9F27CDC0;

    private static final int DEFAULT_TAG_TEXT_SIZE = 15;

    private static final int DEFAULT_TAG_TEXT_COLOR = 0xFFFFFFFF;

    private float mCornerDistance;

    private float mTagWidth;

    private int mTagBackgroundColor;

    private Path mPath;

    private Paint mPaint;

    private String mTagText;

    private int mTagTextSize;

    private Paint mTextPaint;

    private Rect mTagTextBound;

    private int mTagTextColor;

    private float mDensity;

    private int mTagOrientation;

    private MyPoint startPoint;

    private MyPoint endPoint;

    public SimpleTagImageView(Context context) {
        this(context, null);
    }

    public SimpleTagImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SimpleTagImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDensity = context.getResources().getDisplayMetrics().density;
        TypedArray typeArray = context.obtainStyledAttributes(attrs,R.styleable.SimpleTagImageView,defStyleAttr,0);
        mTagOrientation = typeArray.getInteger(R.styleable.SimpleTagImageView_simple_tag_orientation,0);
        mTagWidth = typeArray.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_tag_width, dip2px(DEFAULT_TAG_WIDTH));
        mCornerDistance = typeArray.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_corner_distance,dip2px(DEFAULT_CORNER_DISTANCE));
        mTagBackgroundColor = typeArray.getColor(R.styleable.SimpleTagImageView_simple_tag_background_color,DEFAULT_TAG_BACKGROUND_COLOR);
        mTagText = typeArray.getString(R.styleable.SimpleTagImageView_simple_tag_text);
        mTagTextSize = typeArray.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_tag_textSize, dip2px(DEFAULT_TAG_TEXT_SIZE));
        mTagTextColor = typeArray.getColor(R.styleable.SimpleTagImageView_simple_tag_textColor, DEFAULT_TAG_TEXT_COLOR);
        typeArray.recycle();
        if(TextUtils.isEmpty(mTagText))mTagText = "";
        mPaint = new Paint();
        mPath = new Path();
        mTextPaint = new Paint();
        mTagTextBound = new Rect();
        startPoint = new MyPoint();
        endPoint = new MyPoint();
    }

    @Override
    protected void onDraw(@SuppressWarnings("NullableProblems") Canvas mCanvas) {
        super.onDraw(mCanvas);
        float rDistance = mCornerDistance + mTagWidth/2;
        chooseTagOrientation(rDistance);
        mTextPaint.setTextSize(mTagTextSize);
        mTextPaint.getTextBounds(mTagText,0,mTagText.length(),mTagTextBound);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTagBackgroundColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(mTagWidth);
        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(endPoint.x, endPoint.y);
        mCanvas.drawPath(mPath, mPaint);
        mTextPaint.setColor(mTagTextColor);
        mTextPaint.setTextSize(mTagTextSize);
        mTextPaint.setAntiAlias(true);
//        斜边长度
        float hypotenuse = THE_SQUARE_ROOT_OF_2 * rDistance;
        mCanvas.drawTextOnPath(mTagText, mPath, hypotenuse / 2 - mTagTextBound.width() / 2,
                mTagTextBound.height() / 2, mTextPaint);
    }

    private void chooseTagOrientation(float rDistance) {
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        switch (mTagOrientation) {
            case 0:
                startPoint.x = 0;
                startPoint.y = rDistance;
                endPoint.x = rDistance;
                endPoint.y = 0;
                break;
            case 1:
                startPoint.x = mWidth - rDistance;
                startPoint.y = 0;
                endPoint.x = mWidth;
                endPoint.y = rDistance;
                break;
            case 2:
                startPoint.x = 0;
                startPoint.y = mHeight - rDistance;
                endPoint.x = rDistance;
                endPoint.y = mHeight;
                break;
            case 3:
                startPoint.x = mWidth - rDistance;
                startPoint.y = mHeight;
                endPoint.x = mWidth;
                endPoint.y = mHeight - rDistance;
                break;
        }
    }

    /**
     *
     * @param textSize unit:dip
     */
    public void setTagTextSize(int textSize) {
        this.mTagTextSize = dip2px(textSize);
        invalidate();
    }

    @SuppressWarnings("unused")
    public int getTagTextSize(){
        return mTagTextSize;
    }

    /**
     *
     * @param cornerDistance unit:dip
     */
    public void setCornerDistance(int cornerDistance) {
        this.mCornerDistance = dip2px(cornerDistance);
        invalidate();
    }

    /**
     *
     * @return unit:dip
     */
    @SuppressWarnings("unused")
    public int getCornerDistance() {
        return (int)(mCornerDistance/mDensity + 0.5f);
    }

    @SuppressWarnings("unused")
    public int getTagTextColor() {
        return this.mTagTextColor;
    }

    public void setTagTextColor(int tagTextColor) {
        this.mTagTextColor = tagTextColor;
        invalidate();
    }

    @SuppressWarnings("unused")
    public String getTagText() {
        return this.mTagText;
    }

    public void setTagText(String mTagText){
        this.mTagText = mTagText;
        invalidate();
    }

    public void setTagBackgroundColor(int tagBackgroundColor) {
        this.mTagBackgroundColor = tagBackgroundColor;
        invalidate();
    }

    @SuppressWarnings("unused")
    public int getTagBackgroundColor() {
        return this.mTagBackgroundColor;
    }

    /**
     * @return unit:dip
     */
    @SuppressWarnings("unused")
    public int getTagWidth() {
        return (int)(this.mTagWidth / mDensity + 0.5f);
    }

    /**
     *
     * @param tagWidth unit:dip
     */
    public void setTagWidth(int tagWidth) {
        this.mTagWidth = (int)(tagWidth * mDensity + 0.5f);
        invalidate();
    }

    /**
     * @return  0 : left_top
     *          1 : right_top
     *          2 : left_bottom
     *          3 : right_bottom
     */
    @SuppressWarnings("unused")
    public int getTagOrientation() {
        return mTagOrientation;
    }

    /**
     *
     * @param tagOrientation {@link #LEFT_TOP} or
     *                       {@link #LEFT_BOTTOM} or
     *                       {@link #RIGHT_TOP} or
     *                       {@link #RIGHT_BOTTOM}
     */
    public void setTagOrientation(int tagOrientation) {
        this.mTagOrientation = tagOrientation;
        invalidate();
    }

    private int dip2px(int dip) {
        return (int)(mDensity * dip + 0.5f);
    }

    @SuppressWarnings("unused")
    private int px2dip(float px) {
        return (int)(px/mDensity + 0.5f);
    }

    static class MyPoint {
        float x;
        float y;
    }
}
