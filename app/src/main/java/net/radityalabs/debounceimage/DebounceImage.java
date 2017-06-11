package net.radityalabs.debounceimage;

/**
 * Created by radityagumay on 6/11/17.
 */

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by radityagumay on 6/8/17.
 */

public class DebounceImage extends FrameLayout {

    private static final String TAG = DebounceImage.class.getSimpleName();

    private int count, current, commonSize;
    private int mForegroundImage = -1, mBackgroundImage = -1;

    private CircleImageView circleImageView;
    private ImageView backgroundImageView;

    public DebounceImage(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public DebounceImage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DebounceImage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setBackgroundImage(@IdRes int resId) {
        mBackgroundImage = resId;
    }

    public void setForegroundImage(@IdRes int resId) {
        mForegroundImage = resId;
    }

    public void refreshView(int count, int current) {
        this.count = count;
        this.current = current;

        resizeCurrentView();

        if (mBackgroundImage != -1) {
            backgroundImageView.setImageResource(mBackgroundImage);
        }
        if (mForegroundImage != -1) {
            circleImageView.setImageResource(mForegroundImage);
        }
    }

    private void resizeCurrentView() {
        int center = Math.abs(count / 2);
        int size;
        if (current == center) {
            size = commonSize;
        } else {
            size = commonSize - (commonSize / (center - current));
        }
        Log.d(TAG, "" + size + " position:" + current);
        setLayoutParams(new LayoutParams(size, size));
    }

    private float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.debounce_image, this, true);

        backgroundImageView = (ImageView) findViewById(R.id.iv_bgframe);
        circleImageView = (CircleImageView) findViewById(R.id.iv_photo);

        commonSize = (int) getDimension(R.dimen.debounce_image_size);
    }

    private int getDpValueInt(int size) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (size * scale + 0.5f);
    }

    private float getDpValueFloat(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
    }
}
