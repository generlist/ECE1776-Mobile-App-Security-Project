package android.support.v4.view;

import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.widget.IcsLinearLayout;
import com.actionbarsherlock.view.Menu;

// compiled from: ProGuard
class g implements j {
    g() {
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        boolean z = 1;
        int i6 = (i2 & i3) != 0;
        int i7 = i4 | i5;
        if ((i2 & i7) == 0) {
            z = false;
        }
        if (i6 != 0) {
            if (i == 0) {
                return i & (i7 ^ -1);
            }
            throw new IllegalArgumentException("bad arguments");
        } else if (i != 0) {
            return i & (i3 ^ -1);
        } else {
            return i;
        }
    }

    public int a(int i) {
        int i2;
        i2 = (i & 192) != 0 ? i | 1 : i;
        if ((i2 & 48) != 0) {
            i2 |= 2;
        }
        return i2 & 247;
    }

    public void a(KeyEvent keyEvent) {
    }

    public boolean a(int i, int i2) {
        return a(a(a(i) & 247, i2, 1, R.styleable.SherlockTheme_listPopupWindowStyle, NotificationCompat.FLAG_HIGH_PRIORITY), i2, IcsLinearLayout.SHOW_DIVIDER_MIDDLE, Menu.CATEGORY_SHIFT, R.styleable.SherlockTheme_searchDropdownBackground) == i2;
    }

    public boolean b(int i) {
        return (a(i) & 247) == 0;
    }
}