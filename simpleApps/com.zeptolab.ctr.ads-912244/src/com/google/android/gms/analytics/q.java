package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.inmobi.commons.analytics.iat.impl.AdTrackerConstants;

class q extends BroadcastReceiver {
    static final String rg;
    private final af rh;

    static {
        rg = q.class.getName();
    }

    q(af afVar) {
        this.rh = afVar;
    }

    public static void p(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(rg, true);
        context.sendBroadcast(intent);
    }

    public void o(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AdTrackerConstants.CONNECTIVITY_INTENT_ACTION);
        context.registerReceiver(this, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter);
    }

    public void onReceive(Context context, Intent intent) {
        boolean z = false;
        String action = intent.getAction();
        if (AdTrackerConstants.CONNECTIVITY_INTENT_ACTION.equals(action)) {
            boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
            af afVar = this.rh;
            if (!booleanExtra) {
                z = true;
            }
            afVar.q(z);
        } else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(rg)) {
            this.rh.bF();
        }
    }
}