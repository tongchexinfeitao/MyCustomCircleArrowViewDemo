package com.example.mycustomcirclearrowviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.mycustomcirclearrowviewdemo.R;

/**
 * Created by hasee on 2017/9/4.
 */

public class MyCustomCircleArrowView extends View {

    //从xml中获取的颜色
    private int circleBoundColor;
    private float circleBoundWidth;

    //当前画笔画圆的颜色
    private int CurrenCircleBoundColor;

    private Paint paint;

    public MyCustomCircleArrowView(Context context) {
        super(context);
        initVeiw(context);
    }


    public MyCustomCircleArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVeiw(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomCircleArrowView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {

            //就是我们自定义的属性的资源id
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyCustomCircleArrowView_circlr_bound_color:
                    circleBoundColor = typedArray.getColor(attr, Color.RED);
                    CurrenCircleBoundColor = circleBoundColor;
                    break;
                case R.styleable.MyCustomCircleArrowView_circlr_bound_width:
                    circleBoundWidth = typedArray.getDimension(attr, 3);
                    break;
            }
        }
    }

    private void initVeiw(Context context) {
        paint = new Paint();
    }

    public void setColor(int color) {
        if (CurrenCircleBoundColor != color) {
            CurrenCircleBoundColor = color;
        } else {
            CurrenCircleBoundColor = circleBoundColor;
        }
    }


    //圆心
    private float pivotX;
    private float pivotY;
    private float radius = 130;

    private float currentDegree = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(CurrenCircleBoundColor);
        paint.setStrokeWidth(circleBoundWidth);
        paint.setStyle(Paint.Style.STROKE);
        pivotX = getWidth() / 2;
        pivotY = getHeight() / 2;
        canvas.drawCircle(pivotX, pivotY, radius, paint);

        canvas.save();

        //旋转画布 , 如果旋转的的度数大的话,视觉上看着是旋转快的
        canvas.rotate(currentDegree, pivotX, pivotY);

        //提供了一些api可以用来画线(画路径)
        Path path = new Path();

        //从哪开始画 从A开始画
        path.moveTo(pivotX + radius, pivotY);

        //从A点画一个直线到D点
        path.lineTo(pivotX + radius - 20, pivotY - 20);

        //从D点画一个直线到B点
        path.lineTo(pivotX + radius, pivotY + 20);

        //从B点画一个直线到C点
        path.lineTo(pivotX + radius + 20, pivotY - 20);

        //闭合  --  从C点画一个直线到A点
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
        canvas.restore();

        //旋转的度数一个一个度数增加,  如果乘以一个速度的话,按一个速度速度增加
        currentDegree += 1 * currentSpeed;

        if (!isPause) {
            invalidate();
        }
    }

    private int currentSpeed = 1;
    private boolean isPause = false;

    public void speed() {
        ++currentSpeed;
        if (currentSpeed >= 10) {
            currentSpeed = 10;
            Toast.makeText(getContext(), "我比闪电还快", Toast.LENGTH_SHORT).show();
        }
    }

    public void slowDown() {
        --currentSpeed;
        if (currentSpeed <=1) {
            currentSpeed = 1;
        }
    }

    public void pauseOrStart() {

        //如果是开始状态的话去重新绘制
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {
            isPause = !isPause;
        }
    }
}
