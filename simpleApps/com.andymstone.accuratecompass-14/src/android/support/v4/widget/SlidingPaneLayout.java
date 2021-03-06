package android.support.v4.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ak;
import android.support.v4.view.z;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.andymstone.compasslib.v;
import com.andymstone.core.o;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    static final w a;
    private int b;
    private int c;
    private Drawable d;
    private final int e;
    private boolean f;
    private View g;
    private float h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private float m;
    private float n;
    private u o;
    private final aa p;
    private boolean q;
    private boolean r;
    private final Rect s;
    private final ArrayList t;

    class SavedState extends BaseSavedState {
        public static final Creator CREATOR;
        boolean a;

        static {
            CREATOR = new v();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt() != 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 17) {
            a = new z();
        } else if (i >= 16) {
            a = new y();
        } else {
            a = new x();
        }
    }

    private void a(float f) {
        int i = 0;
        t tVar = (t) this.g.getLayoutParams();
        int i2 = (!tVar.c || tVar.leftMargin > 0) ? false : 1;
        int childCount = getChildCount();
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt != this.g) {
                int i3 = (int) ((1.0f - this.i) * ((float) this.l));
                this.i = f;
                childAt.offsetLeftAndRight(i3 - ((int) ((1.0f - f) * ((float) this.l))));
                if (i2 != 0) {
                    a(childAt, 1.0f - this.i, this.c);
                }
            }
            i++;
        }
    }

    private void a(View view, float f, int i) {
        t tVar = (t) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24) | (16777215 & i);
            if (tVar.d == null) {
                tVar.d = new Paint();
            }
            tVar.d.setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_OVER));
            if (ak.c(view) != 2) {
                ak.a(view, o.MyAlertDialog_myAlertDialogButtonDrawable, tVar.d);
            }
            d(view);
        } else if (ak.c(view) != 0) {
            if (tVar.d != null) {
                tVar.d.setColorFilter(null);
            }
            Runnable sVar = new s(this, view);
            this.t.add(sVar);
            ak.a(this, sVar);
        }
    }

    private boolean a(View view, int i) {
        if (!this.r && !a(0.0f, i)) {
            return false;
        }
        this.q = false;
        return true;
    }

    private boolean b(View view, int i) {
        if (!this.r && !a(1.0f, i)) {
            return false;
        }
        this.q = true;
        return true;
    }

    private static boolean c(View view) {
        if (ak.f(view)) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable background = view.getBackground();
        if (background == null) {
            return false;
        }
        return background.getOpacity() == -1;
    }

    private void d(View view) {
        a.a(this, view);
    }

    void a() {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
            i++;
        }
    }

    void a(View view) {
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        int i;
        int i2;
        int i3;
        int i4;
        if (view == null || !c(view)) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            i4 = view.getLeft();
            i3 = view.getRight();
            i2 = view.getTop();
            i = view.getBottom();
        }
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt != view) {
                int i6;
                i6 = (Math.max(paddingLeft, childAt.getLeft()) < i || Math.max(paddingTop, childAt.getTop()) < i || Math.min(width, childAt.getRight()) > i || Math.min(height, childAt.getBottom()) > i) ? 0 : o.MyAlertDialog_MAD_buttonBarStyle;
                childAt.setVisibility(i6);
                i5++;
            } else {
                return;
            }
        }
    }

    boolean a(float f, int i) {
        if (!this.f) {
            return false;
        }
        t tVar = (t) this.g.getLayoutParams();
        if (!this.p.a(this.g, (int) (((float) (tVar.leftMargin + getPaddingLeft())) + ((float) this.j) * f), this.g.getTop())) {
            return false;
        }
        a();
        ak.b(this);
        return true;
    }

    public boolean b() {
        return b(this.g, 0);
    }

    boolean b(View view) {
        if (view == null) {
            return false;
        }
        boolean z;
        t tVar = (t) view.getLayoutParams();
        z = (this.f && tVar.c && this.h > 0.0f) ? 1 : false;
        return z;
    }

    public boolean c() {
        return a(this.g, 0);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof t && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        if (!this.p.a(true)) {
            return;
        }
        if (this.f) {
            ak.b(this);
        } else {
            this.p.f();
        }
    }

    public boolean d() {
        return !this.f || this.h == 1.0f;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && this.d != null) {
            int intrinsicWidth = this.d.getIntrinsicWidth();
            int left = childAt.getLeft();
            this.d.setBounds(left - intrinsicWidth, childAt.getTop(), left, childAt.getBottom());
            this.d.draw(canvas);
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        t tVar = (t) view.getLayoutParams();
        int save = canvas.save(o.MyAlertDialog_myAlertDialogButtonDrawable);
        if (!(!this.f || tVar.b || this.g == null)) {
            canvas.getClipBounds(this.s);
            this.s.right = Math.min(this.s.right, this.g.getLeft());
            canvas.clipRect(this.s);
        }
        if (VERSION.SDK_INT >= 11) {
            drawChild = super.drawChild(canvas, view, j);
        } else if (!tVar.c || this.h <= 0.0f) {
            if (view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(false);
            }
            drawChild = super.drawChild(canvas, view, j);
        } else {
            if (!view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(true);
            }
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, (float) view.getLeft(), (float) view.getTop(), tVar.d);
                drawChild = false;
            } else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + view + " returned null drawing cache");
                drawChild = super.drawChild(canvas, view, j);
            }
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    public boolean e() {
        return this.f;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new t();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new t(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new t((MarginLayoutParams) layoutParams) : new t(layoutParams);
    }

    public int getCoveredFadeColor() {
        return this.c;
    }

    public int getParallaxDistance() {
        return this.l;
    }

    public int getSliderFadeColor() {
        return this.b;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.r = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r = true;
        int size = this.t.size();
        int i = 0;
        while (i < size) {
            ((s) this.t.get(i)).run();
            i++;
        }
        this.t.clear();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int a = z.a(motionEvent);
        if (!this.f && a == 0 && getChildCount() > 1) {
            View childAt = getChildAt(1);
            if (childAt != null) {
                this.q = !this.p.b(childAt, (int) motionEvent.getX(), (int) motionEvent.getY());
            }
        }
        if (!this.f || (this.k && a != 0)) {
            this.p.e();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (a == 3 || a == 1) {
            this.p.e();
            return false;
        } else {
            float x;
            float y;
            boolean z;
            switch (a) {
                case v.DropShadowView_cornerRadius:
                    this.k = false;
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    this.m = x;
                    this.n = y;
                    if (this.p.b(this.g, (int) x, (int) y) && b(this.g)) {
                        z = true;
                    }
                    z = false;
                    break;
                case o.MyAlertDialog_myAlertDialogButtonDrawable:
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    x = Math.abs(x - this.m);
                    y = Math.abs(y - this.n);
                    if (x > ((float) this.p.d()) && y > x) {
                        this.p.e();
                        this.k = true;
                        return false;
                    }
                    z = false;
                    break;
                default:
                    z = false;
                    break;
            }
            return this.p.a(motionEvent) || i != 0;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.r) {
            float f;
            f = (this.f && this.q) ? 1.0f : 0.0f;
            this.h = f;
        }
        int i6 = 0;
        int i7 = paddingLeft;
        while (i6 < childCount) {
            int i8;
            int i9;
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() == 8) {
                i8 = i7;
            } else {
                t tVar = (t) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (tVar.b) {
                    int min = Math.min(paddingLeft, i5 - paddingRight - this.e) - i7 - tVar.leftMargin + tVar.rightMargin;
                    this.j = min;
                    tVar.c = ((tVar.leftMargin + i7) + min) + (measuredWidth / 2) > i5 - paddingRight;
                    i8 = tVar.leftMargin + ((int) (((float) min) * this.h)) + i7;
                    i9 = 0;
                } else if (!this.f || this.l == 0) {
                    i9 = 0;
                    i8 = paddingLeft;
                } else {
                    i9 = (int) ((1.0f - this.h) * ((float) this.l));
                    i8 = paddingLeft;
                }
                i9 = i8 - i9;
                childAt.layout(i9, paddingTop, i9 + measuredWidth, childAt.getMeasuredHeight() + paddingTop);
                paddingLeft += childAt.getWidth();
            }
            i6++;
            i7 = i8;
        }
        if (this.r) {
            if (this.f) {
                if (this.l != 0) {
                    a(this.h);
                }
                if (((t) this.g.getLayoutParams()).c) {
                    a(this.g, this.h, this.b);
                }
            } else {
                i9 = 0;
                while (i9 < childCount) {
                    a(getChildAt(i9), 0.0f, this.b);
                    i9++;
                }
            }
            a(this.g);
        }
        this.r = false;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (mode == Integer.MIN_VALUE) {
                i3 = mode2;
                i4 = size;
                size = size2;
            } else {
                if (mode == 0) {
                    i3 = mode2;
                    i4 = 300;
                    size = size2;
                }
                i3 = mode2;
                i4 = size;
                size = size2;
            }
        } else if (mode2 == 0 && isInEditMode()) {
            if (mode2 == 0) {
                i3 = Integer.MIN_VALUE;
                i4 = size;
                size = 300;
            }
            i3 = mode2;
            i4 = size;
            size = size2;
        } else {
            throw new IllegalStateException("Height must not be UNSPECIFIED");
        }
        switch (i3) {
            case Integer.MIN_VALUE:
                size2 = 0;
                mode2 = size - getPaddingTop() - getPaddingBottom();
                break;
            case 1073741824:
                size2 = size - getPaddingTop() - getPaddingBottom();
                mode2 = size2;
                break;
            default:
                size2 = 0;
                mode2 = -1;
                break;
        }
        boolean z = false;
        int paddingLeft = i4 - getPaddingLeft() - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.g = null;
        int i5 = 0;
        int i6 = size2;
        float f = 0.0f;
        while (i5 < childCount) {
            float f2;
            int i7;
            boolean z2;
            View childAt = getChildAt(i5);
            t tVar = (t) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                tVar.c = false;
                size2 = paddingLeft;
                f2 = f;
                i7 = i6;
                z2 = z;
            } else {
                boolean z3;
                if (tVar.a > 0.0f) {
                    f += tVar.a;
                    if (tVar.width == 0) {
                        size2 = paddingLeft;
                        f2 = f;
                        i7 = i6;
                        z2 = z;
                    }
                }
                mode = tVar.leftMargin + tVar.rightMargin;
                if (tVar.width == -2) {
                    mode = MeasureSpec.makeMeasureSpec(i4 - mode, Integer.MIN_VALUE);
                } else if (tVar.width == -1) {
                    mode = MeasureSpec.makeMeasureSpec(i4 - mode, 1073741824);
                } else {
                    mode = MeasureSpec.makeMeasureSpec(tVar.width, 1073741824);
                }
                if (tVar.height == -2) {
                    i7 = MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE);
                } else if (tVar.height == -1) {
                    i7 = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                } else {
                    i7 = MeasureSpec.makeMeasureSpec(tVar.height, 1073741824);
                }
                childAt.measure(mode, i7);
                mode = childAt.getMeasuredWidth();
                i7 = childAt.getMeasuredHeight();
                if (i3 == Integer.MIN_VALUE && i7 > i6) {
                    i6 = Math.min(i7, mode2);
                }
                i7 = paddingLeft - mode;
                z3 = i7 < 0;
                tVar.b = z3;
                mode = z3 | z;
                if (tVar.b) {
                    this.g = childAt;
                }
                size2 = i7;
                i7 = i6;
                float f3 = f;
                size = mode;
                f2 = f3;
            }
            i5++;
            z = z2;
            i6 = i7;
            f = f2;
            paddingLeft = size2;
        }
        if (z || f > 0.0f) {
            int i8 = i4 - this.e;
            i3 = 0;
            while (i3 < childCount) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getVisibility() != 8) {
                    tVar = (t) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != 8) {
                        i5 = (tVar.width != 0 || tVar.a <= 0.0f) ? 0 : 1;
                        i7 = i5 != 0 ? 0 : childAt2.getMeasuredWidth();
                        if (!z || childAt2 == this.g) {
                            if (tVar.a > 0.0f) {
                                if (tVar.width != 0) {
                                    mode = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                } else if (tVar.height == -2) {
                                    mode = MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE);
                                } else if (tVar.height == -1) {
                                    mode = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                                } else {
                                    mode = MeasureSpec.makeMeasureSpec(tVar.height, 1073741824);
                                }
                                if (z) {
                                    size2 = i4 - tVar.rightMargin + tVar.leftMargin;
                                    i5 = MeasureSpec.makeMeasureSpec(size2, 1073741824);
                                    if (i7 != size2) {
                                        childAt2.measure(i5, mode);
                                    }
                                } else {
                                    childAt2.measure(MeasureSpec.makeMeasureSpec(((int) ((tVar.a * ((float) Math.max(0, paddingLeft))) / f)) + i7, 1073741824), mode);
                                }
                            }
                        } else if (tVar.width < 0) {
                            if (i7 > i8 || tVar.a > 0.0f) {
                                if (i5 == 0) {
                                    size2 = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                } else if (tVar.height == -2) {
                                    size2 = MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE);
                                } else if (tVar.height == -1) {
                                    size2 = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                                } else {
                                    size2 = MeasureSpec.makeMeasureSpec(tVar.height, 1073741824);
                                }
                                childAt2.measure(MeasureSpec.makeMeasureSpec(i8, 1073741824), size2);
                            }
                        }
                    }
                }
                i3++;
            }
        }
        setMeasuredDimension(i4, i6);
        this.f = z;
        if (this.p.a() != 0 && !z) {
            this.p.f();
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.a) {
            b();
        } else {
            c();
        }
        this.q = savedState.a;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = e() ? d() : this.q;
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.r = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f) {
            return super.onTouchEvent(motionEvent);
        }
        this.p.b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case v.DropShadowView_cornerRadius:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.m = x;
                this.n = y;
                return true;
            case o.MyAlertDialog_myAlertDialogAccentColor:
                if (!b(this.g)) {
                    return true;
                }
                x = motionEvent.getX();
                y = motionEvent.getY();
                float f = x - this.m;
                float f2 = y - this.n;
                int d = this.p.d();
                if (f * f + f2 * f2 >= ((float) (d * d)) || !this.p.b(this.g, (int) x, (int) y)) {
                    return true;
                }
                a(this.g, 0);
                return true;
            default:
                return true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.f) {
            this.q = view == this.g;
        }
    }

    public void setCoveredFadeColor(int i) {
        this.c = i;
    }

    public void setPanelSlideListener(u uVar) {
        this.o = uVar;
    }

    public void setParallaxDistance(int i) {
        this.l = i;
        requestLayout();
    }

    public void setShadowDrawable(Drawable drawable) {
        this.d = drawable;
    }

    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setSliderFadeColor(int i) {
        this.b = i;
    }
}