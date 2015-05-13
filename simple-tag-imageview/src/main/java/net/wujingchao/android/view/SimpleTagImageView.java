package net.wujingchao.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


/**
 * @author wujingchao  2015-02-20 email:wujingchao@aliyun.com
 */
public class SimpleTagImageView extends ImageView {

    public static final String TAG = "SimpleTagImageView";

    public  static final byte LEFT_TOP = 0x00;

    public  static final byte RIGHT_TOP = 0x01;

    public  static final byte LEFT_BOTTOM = 0x02;

    public  static final byte RIGHT_BOTTOM = 0x03;

    private static final float THE_SQUARE_ROOT_OF_2 = (float) Math.sqrt(2);

    private static final int DEFAULT_TAG_WIDTH = 20;

    private static final int DEFAULT_CORNER_DISTANCE = 20;

    private static final int DEFAULT_TAG_BACKGROUND_COLOR = 0x9F27CDC0;

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

    private Paint mBitmapPaint;

    private RectF mRoundRect;

    private boolean mTagEnable;

    private int mRoundRadius;

    public SimpleTagImageView(Context context) {
        this(context, null);
    }

    public SimpleTagImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTagImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDensity = context.getResources().getDisplayMetrics().density;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SimpleTagImageView,defStyleAttr,0);
        mTagOrientation = a.getInteger(R.styleable.SimpleTagImageView_simple_tag_orientation,0);
        mTagWidth = a.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_tag_width, dip2px(DEFAULT_TAG_WIDTH));
        mCornerDistance = a.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_corner_distance,dip2px(DEFAULT_CORNER_DISTANCE));
        mTagBackgroundColor = a.getColor(R.styleable.SimpleTagImageView_simple_tag_background_color,DEFAULT_TAG_BACKGROUND_COLOR);
        mTagText = a.getString(R.styleable.SimpleTagImageView_simple_tag_text);
        mTagTextSize = a.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_tag_textSize, dip2px(DEFAULT_TAG_TEXT_SIZE));
        mTagTextColor = a.getColor(R.styleable.SimpleTagImageView_simple_tag_textColor, DEFAULT_TAG_TEXT_COLOR);
        mTagEnable = a.getBoolean(R.styleable.SimpleTagImageView_simple_tag_enable,true);
        mRoundRadius = a.getDimensionPixelSize(R.styleable.SimpleTagImageView_simple_tag_round_radius,0);
        a.recycle();
        if(TextUtils.isEmpty(mTagText))mTagText = "";
        mPaint = new Paint();
        mPath = new Path();
        mTextPaint = new Paint();
        mTagTextBound = new Rect();
        startPoint = new MyPoint();
        endPoint = new MyPoint();
        mRoundRect = new RectF();
    }

    /**
     *
     * @param textSize unit:dip
     */
    public void setTagTextSize(int textSize) {
        this.mTagTextSize = dip2px(textSize);
        invalidate();
    }

    public int getTagTextSize(){
        return mTagTextSize;
    }

    /**
     *
     * @param cornerDistance unit:dip
     */
    public void setCornerDistance(int cornerDistance) {
        if(this.mCornerDistance == cornerDistance)return;
        this.mCornerDistance = dip2px(cornerDistance);
        invalidate();
    }

    /**
     *
     * @return unit:dip
     */
    public int getCornerDistance() {
        return px2dip(this.mCornerDistance);
    }

    public int getTagTextColor() {
        return this.mTagTextColor;
    }

    public void setTagTextColor(int tagTextColor) {
        if(this.mTagTextColor == tagTextColor)return;
        this.mTagTextColor = tagTextColor;
        invalidate();
    }

    public String getTagText() {
        return this.mTagText;
    }

    public void setTagText(String tagText){
        if(tagText.equals(this.mTagText))return;
        this.mTagText = tagText;
        invalidate();
    }

    public void setTagBackgroundColor(int tagBackgroundColor) {
        if(this.mTagBackgroundColor == tagBackgroundColor)return;
        this.mTagBackgroundColor = tagBackgroundColor;
        invalidate();
    }

    public int getTagBackgroundColor() {
        return this.mTagBackgroundColor;
    }

    /**
     * @return unit:dip
     */
    public int getTagWidth() {
        return px2dip(this.mTagWidth);
    }

    /**
     *
     * @param tagWidth unit:dip
     */
    public void setTagWidth(int tagWidth) {
        this.mTagWidth = dip2px(tagWidth);
        invalidate();
    }

    /**
     * @return  0 : left_top
     *          1 : right_top
     *          2 : left_bottom
     *          3 : right_bottom
     */
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
        if(tagOrientation == this.mTagOrientation)return;
        this.mTagOrientation = tagOrientation;
        invalidate();
    }

    public void setTagEnable(boolean tagEnable) {
        if(this.mTagEnable == tagEnable) return ;
        this.mTagEnable = tagEnable;
        invalidate();
    }

    public boolean getTagEnable() {
        return this.mTagEnable;
    }

    public  int getTagRoundRadius() {
        return this.mRoundRadius;
    }

    public void setTagRoundRadius(int roundRadius) {
        if(this.mRoundRadius == roundRadius) return;
        this.mRoundRadius = roundRadius;
        invalidate();
    }

    @Override
    protected void onDraw(@SuppressWarnings("NullableProblems") Canvas mCanvas) {
        if(mRoundRadius == 0) {
            super.onDraw(mCanvas);
        }else {
            Drawable d = getDrawable();
            if(d == null) return;
            if(d.getIntrinsicWidth() == 0 || d.getIntrinsicHeight() == 0) return;
            setupBitmapPaint();
            mRoundRect.set(getPaddingLeft(),getPaddingTop(),getMeasuredWidth() - getPaddingRight(),getMeasuredHeight() - getPaddingBottom());
            mCanvas.drawRoundRect(mRoundRect, mRoundRadius, mRoundRadius, mBitmapPaint);
        }

        if(mTagWidth > 0 && mTagEnable) {
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
//          斜边长度
            float hypotenuse = THE_SQUARE_ROOT_OF_2 * rDistance;
            mCanvas.drawTextOnPath(mTagText, mPath, hypotenuse / 2 - mTagTextBound.width() / 2,
                    mTagTextBound.height() / 2, mTextPaint);
        }
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

    private void setupBitmapPaint() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap mBitmap = drawableToBitmap(drawable);
        BitmapShader mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        if(getScaleType() != ScaleType.FIT_XY){
            Log.w(TAG,String.format("Now scale type just support fitXY,other type invalid"));
        }
        //now scale type just support fitXY
        //todo support all scale type
        Matrix mMatrix = new Matrix();
        mMatrix.setScale(getWidth() * 1.0f / mBitmap.getWidth(), getHeight() * 1.0f / mBitmap.getHeight());
        mBitmapShader.setLocalMatrix(mMatrix);
        if(mBitmapPaint == null) {
            mBitmapPaint = new Paint();
            mBitmapPaint.setDither(false);
            mBitmapPaint.setAntiAlias(true);
            mBitmapPaint.setShader(mBitmapShader);
        }
    }

    private int dip2px(int dip) {
        return (int)(mDensity * dip + 0.5f);
    }

    private int px2dip(float px) {
        return (int)(px/mDensity + 0.5f);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    static class MyPoint {
        float x;
        float y;
    }
}
