package com.actionbarsherlock.view;

import android.view.View;

// compiled from: ProGuard
public abstract class ActionMode {
    private Object mTag;

    // compiled from: ProGuard
    public interface Callback {
        boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem);

        boolean onCreateActionMode(ActionMode actionMode, Menu menu);

        void onDestroyActionMode(ActionMode actionMode);

        boolean onPrepareActionMode(ActionMode actionMode, Menu menu);
    }

    public abstract void finish();

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag() {
        return this.mTag;
    }

    public abstract CharSequence getTitle();

    public abstract void invalidate();

    public boolean isUiFocusable() {
        return true;
    }

    public abstract void setCustomView(View view);

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charSequence);

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charSequence);
}