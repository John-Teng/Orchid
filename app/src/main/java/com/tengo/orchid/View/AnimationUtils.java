package com.tengo.orchid.View;

import android.view.View;

/**
 * Created by johnteng on 2018-03-10.
 */

public class AnimationUtils {

    private static final int FADE_DURATION = 500;

    public static void fadeInToVisible(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setAlpha(0.0f);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1.0f).setDuration(FADE_DURATION);
    }

    public static void fadeOutToGone(View view) {
        if (view.getVisibility() == View.GONE) {
            return;
        }
        view.setAlpha(1.0f);
        view.animate().alpha(0.0f).setDuration(FADE_DURATION);
        view.setVisibility(View.GONE);
    }
}

