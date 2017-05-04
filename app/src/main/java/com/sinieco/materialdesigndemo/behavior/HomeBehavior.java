package com.sinieco.materialdesigndemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by BaiMeng on 2017/5/4.
 */
public class HomeBehavior extends CoordinatorLayout.Behavior<Button> {
    private int windowWidth ;
    private int oldX ;
    private boolean isFirst = true;
    public HomeBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        if(isFirst){
            oldX = dependency.getLeft() ;
            isFirst = false ;
        }

        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();
        int childLelt = child.getLeft() - dependency.getLeft() + oldX ;
        oldX = left ;
        return super.layoutDependsOn(parent, child, dependency);
    }
}
