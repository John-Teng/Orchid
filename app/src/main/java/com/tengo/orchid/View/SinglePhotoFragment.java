package com.tengo.orchid.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tengo.orchid.Presenter.SinglePhotoPresenter;
import com.tengo.orchid.R;
import com.tengo.orchid.View.Adapters.SinglePhotoPagerAdapter;

/**
 * Created by johnteng on 2018-03-10.
 */

public class SinglePhotoFragment extends android.support.v4.app.Fragment
        implements SinglePhotoPagerAdapter.SinglePhotoDelegate {

    public interface SinglePhotoPresenterDelegate {
        Bitmap getImage(int position);

        int getCount();
    }

    private static final int NUM_LOADED_IMAGES = 5;
    private static final long INFO_VIEW_ANIMATION_SPEED = 200;
    private static final float SWIPE_MIN_DISTANCE = 25;
    private static final float SWIPE_MAX_OFF_PATH = 200;
    private static final float SWIPE_THRESHOLD_VELOCITY = 200;

    private boolean mShowingInfoView = true;
    private ImageView mShowInfoView;
    private View mInfoView;
    private ViewPager mViewPager;
    private SinglePhotoPagerAdapter mAdapter;
    private SinglePhotoPresenterDelegate mPresenterDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // SHOULDNT BE NEEDED
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_photo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view == null) {
            return;
        }
        mPresenterDelegate = new SinglePhotoPresenter(getContext());
        mInfoView = view.findViewById(R.id.photo_info_view);
        mShowInfoView = (ImageView) view.findViewById(R.id.photo_show_info);
        mShowInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleInfoView();
            }
        });
        setupSwipeListeners();
        mViewPager = (ViewPager) view.findViewById(R.id.single_photo_viewpager);
        mViewPager.setOffscreenPageLimit(NUM_LOADED_IMAGES);
        mAdapter = new SinglePhotoPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);

        Bundle args = getArguments();
        if (args != null) {
            int photoId = args.getInt(getString(R.string.param_photo_id));
            mViewPager.setCurrentItem(photoId);
        }
    }

    private void setupSwipeListeners() {
        final GestureDetector infoViewSwipeDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                // top to bottom swipe
                if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    toggleInfoView();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                return false;
            }
        });
        mInfoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return infoViewSwipeDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public Bitmap getImage(int position) {
        return mPresenterDelegate.getImage(position);
    }

    @Override
    public int getCount() {
        return mPresenterDelegate.getCount();
    }

    private void toggleInfoView() {
        if (mInfoView.isEnabled()) {
            toggleInfoViewVisibility();
        }
    }

    private void toggleInfoViewVisibility() {
        mInfoView.setVisibility(View.VISIBLE);
        mInfoView.setEnabled(false);
        mInfoView.animate()
                .setDuration(INFO_VIEW_ANIMATION_SPEED)
                .translationY(mShowingInfoView ? mInfoView.getHeight() : 0)
                .alpha(mShowingInfoView ? 0.0f : 1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mInfoView.setVisibility(mShowingInfoView ? View.GONE : View.VISIBLE);
                        mShowingInfoView = !mShowingInfoView;
                        mInfoView.setEnabled(true);
                    }
                });
        mShowInfoView.animate()
                .rotationX(mShowingInfoView ? 180 : 0);
    }
}
