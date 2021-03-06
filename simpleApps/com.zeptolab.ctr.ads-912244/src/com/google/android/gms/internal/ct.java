package com.google.android.gms.internal;

public abstract class ct {
    private final Runnable kW;
    private volatile Thread pI;

    public ct() {
        this.kW = new Runnable() {
            public final void run() {
                ct.this.pI = Thread.currentThread();
                ct.this.aB();
            }
        };
    }

    public abstract void aB();

    public final void cancel() {
        onStop();
        if (this.pI != null) {
            this.pI.interrupt();
        }
    }

    public abstract void onStop();

    public final void start() {
        cu.execute(this.kW);
    }
}