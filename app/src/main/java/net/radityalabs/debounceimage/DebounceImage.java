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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by radityagumay on 6/8/17.
 */

public class DebounceImage extends FrameLayout {

    private static final String TAG = DebounceImage.class.getSimpleName();

    private int count, current, commonSize, backCommonSize;
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

        addResizeView();
        addBounceAnimation();

        if (mBackgroundImage != -1) {
            backgroundImageView.setImageResource(mBackgroundImage);
        }
        if (mForegroundImage != -1) {
            circleImageView.setImageResource(mForegroundImage);
        }
    }

    private Animation translateY() {
        Animation animation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.0f
        );
        animation.setDuration(10000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setInterpolator(new LinearInterpolator());
        return animation;
    }

    private void addBounceAnimation() {
        setAnimation(translateY());
    }

    private void addResizeView() {
        int center = Math.abs(count / 2);
        int backSize, foreSize;
        if (current == center) {
            backSize = backCommonSize;
            foreSize = commonSize;
        } else {
            if (center > current) {
                int divider = center - current;
                foreSize = commonSize / divider;
                backSize = backCommonSize / divider;
            } else {
                int divider = current - center;
                foreSize = commonSize / divider;
                backSize = backCommonSize / divider;
            }

            if (foreSize == commonSize) {
                foreSize -= 30;
                backSize -= 30;
            }
        }
        backgroundImageView.setLayoutParams(new LayoutParams(backSize, backSize, Gravity.CENTER));
        circleImageView.setLayoutParams(new LayoutParams(foreSize, foreSize, Gravity.CENTER));
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL));
    }

    private float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.debounce_image, this, true);

        backgroundImageView = (ImageView) findViewById(R.id.iv_background);
        circleImageView = (CircleImageView) findViewById(R.id.iv_foreground);

        commonSize = (int) getDimension(R.dimen.debounce_image_size);
        backCommonSize = (int) getDimension(R.dimen.background_debounce_image_size);
    }

    private int getDpValueInt(int size) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (size * scale + 0.5f);
    }

    private float getDpValueFloat(int size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
    }
}
