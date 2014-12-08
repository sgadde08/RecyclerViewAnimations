package com.sgadde.recylerviewanimations;

/**
 * Created by sandeepgadde on 12/2/14.
 */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sandeepgadde on 12/2/14.
 */

public class AnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int FADE_IN = 0;
    public static final int SWING_IN_BOTTOM = 1;
    public static final int SWING_IN_RIGHT = 2;
    public static final int SCALE_IN = 3;

    private RecyclerView.Adapter adapter;
    private int animatingPosition = -1;
    private int numberOfColumns = 1;
    private int animationType = FADE_IN;

    public AnimationAdapter(RecyclerView.Adapter adapter) {
        super();
        this.adapter = adapter;
    }

    /**
     ** Setting number of columns for this adapter helps items animate one by one in a row
     ** if recyler view is using GridLayoutManager.
     **
     ** 1 by default
     */
    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public void setAnimationType(int animationType) {
        this.animationType = animationType;
    }

    @Override
    public int getItemViewType(int position) {
        return adapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return adapter.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        adapter.onBindViewHolder(viewHolder, position);
        if(position > animatingPosition && position >= numberOfColumns) {
            viewHolder.itemView.animate().cancel();
            viewHolder.itemView.setAlpha(0f);
            switch (animationType) {
                case FADE_IN: fadeIn(viewHolder.itemView, position);
                    break;
                case SWING_IN_BOTTOM: swingInBottom(viewHolder.itemView, position);
                    break;
                case SWING_IN_RIGHT: swingInRight(viewHolder.itemView, position);
                    break;
                case SCALE_IN: scaleIn(viewHolder.itemView, position);
                    break;
                default:
                    break;

            }
            animatingPosition = position;
        } else if (position == 0) animatingPosition = 0;
    }


    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }

    /**
     * Returns the delay in milliseconds after which animation for View with position mLastAnimatedPosition + 1 should start.
     */
    @SuppressLint("NewApi")
    private int calculateAnimationDelay(final int position) {
        if(position == 0) return position;
        int delay;
        int mAnimationDelayMillis = 200;
        delay = mAnimationDelayMillis * (position % numberOfColumns +1);
        return delay;
    }

    private void fadeIn(View animatableView, int animatablePosition) {
        Animator fadeAnimator = ObjectAnimator.ofFloat(animatableView, "alpha",
                0, 1);
        fadeAnimator.setStartDelay(calculateAnimationDelay(animatablePosition));
        fadeAnimator.setDuration(300);
        fadeAnimator.start();
    }

    private void swingInBottom(View animatableView, int animatablePosition) {
        Animator fadeAnimator = ObjectAnimator.ofFloat(animatableView, "alpha",
                0.5f, 1);
        Animator translateAnimator = ObjectAnimator.ofFloat(animatableView, "translationY",
                300, 0);
        Animator[] combinedAnimator = new Animator[2];
        combinedAnimator[0] = fadeAnimator;
        combinedAnimator[1] = translateAnimator;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(combinedAnimator);
        set.setStartDelay(calculateAnimationDelay(animatablePosition));
        set.setDuration(300);
        set.start();
    }

    private void swingInRight(View animatableView, int animatablePosition) {
        Animator fadeAnimator = ObjectAnimator.ofFloat(animatableView, "alpha",
                0.5f, 1);
        Animator translateAnimator = ObjectAnimator.ofFloat(animatableView, "translationX",
                100, 0);
        Animator[] combinedAnimator = new Animator[2];
        combinedAnimator[0] = fadeAnimator;
        combinedAnimator[1] = translateAnimator;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(combinedAnimator);
        set.setStartDelay(calculateAnimationDelay(animatablePosition));
        set.setDuration(100);
        set.start();
    }

    private void scaleIn(View animatableView, int animatablePosition) {
        animatableView.setPivotX(0f);
        animatableView.setPivotY(0f);

        Animator fadeAnimator = ObjectAnimator.ofFloat(animatableView, "alpha",
                0.5f, 1);
        Animator scaleAnimatorX = ObjectAnimator.ofFloat(animatableView, "scaleX",
                0.5f, 1);
        Animator scaleAnimatorY = ObjectAnimator.ofFloat(animatableView, "scaleY",
                0.5f, 1);

        Animator[] combinedAnimator = new Animator[3];
        combinedAnimator[0] = fadeAnimator;
        combinedAnimator[1] = scaleAnimatorX;
        combinedAnimator[2] = scaleAnimatorY;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(combinedAnimator);
        set.setStartDelay(calculateAnimationDelay(animatablePosition));
        set.setDuration(300);
        set.start();
    }
}

