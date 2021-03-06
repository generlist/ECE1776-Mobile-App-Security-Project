package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.brightcove.player.util.ErrorUtil;
import com.inmobi.commons.analytics.iat.impl.AdTrackerConstants;
import com.zeptolab.ctr.billing.google.utils.IabHelper;
import com.zeptolab.ctr.scorer.GoogleScorer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleAnalytics extends TrackerHandler {
    private static boolean tB;
    private static GoogleAnalytics tI;
    private Context mContext;
    private String qR;
    private String qS;
    private f rk;
    private boolean tC;
    private af tD;
    private volatile Boolean tE;
    private Logger tF;
    private Set tG;
    private boolean tH;

    static interface a {
        void f(Activity activity);

        void g(Activity activity);
    }

    class b implements ActivityLifecycleCallbacks {
        b() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.d(activity);
        }

        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.e(activity);
        }
    }

    protected GoogleAnalytics(Context context) {
        this(context, t.q(context), r.bB());
    }

    private GoogleAnalytics(Context context, f fVar, af afVar) {
        this.tE = Boolean.valueOf(false);
        this.tH = false;
        if (context == null) {
            throw new IllegalArgumentException(AdTrackerConstants.MSG_APP_CONTEXT_NULL);
        }
        this.mContext = context.getApplicationContext();
        this.rk = fVar;
        this.tD = afVar;
        g.n(this.mContext);
        ae.n(this.mContext);
        h.n(this.mContext);
        this.tF = new l();
        this.tG = new HashSet();
        cg();
    }

    private int D(String str) {
        String toLowerCase = str.toLowerCase();
        if ("verbose".equals(toLowerCase)) {
            return 0;
        }
        if ("info".equals(toLowerCase)) {
            return 1;
        }
        if ("warning".equals(toLowerCase)) {
            return GoogleScorer.CLIENT_PLUS;
        }
        return ErrorUtil.ERROR.equals(toLowerCase) ? IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE : -1;
    }

    private Tracker a(Tracker tracker) {
        if (this.qR != null) {
            tracker.set("&an", this.qR);
        }
        if (this.qS != null) {
            tracker.set("&av", this.qS);
        }
        return tracker;
    }

    static GoogleAnalytics cf() {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            googleAnalytics = tI;
        }
        return googleAnalytics;
    }

    private void cg() {
        if (!tB) {
            ApplicationInfo applicationInfo;
            ApplicationInfo applicationInfo2 = null;
            try {
                applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
            } catch (NameNotFoundException e) {
                aa.v("PackageManager doesn't know about package: " + e);
                applicationInfo = applicationInfo2;
            }
            if (applicationInfo == null) {
                aa.w("Couldn't get ApplicationInfo to load gloabl config.");
            } else {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    int i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource");
                    if (i > 0) {
                        w wVar = (w) new v(this.mContext).p(i);
                        if (wVar != null) {
                            a(wVar);
                        }
                    }
                }
            }
        }
    }

    private void d(Activity activity) {
        Iterator it = this.tG.iterator();
        while (it.hasNext()) {
            ((a) it.next()).f(activity);
        }
    }

    private void e(Activity activity) {
        Iterator it = this.tG.iterator();
        while (it.hasNext()) {
            ((a) it.next()).g(activity);
        }
    }

    public static GoogleAnalytics getInstance(Context context) {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (tI == null) {
                tI = new GoogleAnalytics(context);
            }
            googleAnalytics = tI;
        }
        return googleAnalytics;
    }

    void a(a aVar) {
        this.tG.add(aVar);
    }

    void a(w wVar) {
        aa.v("Loading global config values.");
        if (wVar.bV()) {
            this.qR = wVar.bW();
            aa.v("app name loaded: " + this.qR);
        }
        if (wVar.bX()) {
            this.qS = wVar.bY();
            aa.v("app version loaded: " + this.qS);
        }
        if (wVar.bZ()) {
            int D = D(wVar.ca());
            if (D >= 0) {
                aa.v("log level loaded: " + D);
                getLogger().setLogLevel(D);
            }
        }
        if (wVar.cb()) {
            this.tD.setLocalDispatchPeriod(wVar.cc());
        }
        if (wVar.cd()) {
            setDryRun(wVar.ce());
        }
    }

    void b(a aVar) {
        this.tG.remove(aVar);
    }

    @Deprecated
    public void dispatchLocalHits() {
        this.tD.dispatchLocalHits();
    }

    public void enableAutoActivityReports(Application application) {
        if (VERSION.SDK_INT >= 14 && !this.tH) {
            application.registerActivityLifecycleCallbacks(new b());
            this.tH = true;
        }
    }

    public boolean getAppOptOut() {
        u.bR().a(com.google.android.gms.analytics.u.a.tc);
        return this.tE.booleanValue();
    }

    public Logger getLogger() {
        return this.tF;
    }

    public boolean isDryRunEnabled() {
        u.bR().a(com.google.android.gms.analytics.u.a.to);
        return this.tC;
    }

    void n(Map map) {
        synchronized (this) {
            if (map == null) {
                throw new IllegalArgumentException("hit cannot be null");
            }
            ak.a(map, "&ul", ak.a(Locale.getDefault()));
            ak.a(map, "&sr", ae.cs().getValue("&sr"));
            map.put("&_u", u.bR().bT());
            u.bR().bS();
            this.rk.n(map);
        }
    }

    public Tracker newTracker(int i) {
        Tracker a;
        synchronized (this) {
            u.bR().a(com.google.android.gms.analytics.u.a.sY);
            Tracker tracker = new Tracker(null, this);
            if (i > 0) {
                aj ajVar = (aj) new ai(this.mContext).p(i);
                if (ajVar != null) {
                    tracker.a(this.mContext, ajVar);
                }
            }
            a = a(tracker);
        }
        return a;
    }

    public Tracker newTracker(String str) {
        Tracker a;
        synchronized (this) {
            u.bR().a(com.google.android.gms.analytics.u.a.sY);
            a = a(new Tracker(str, this));
        }
        return a;
    }

    public void reportActivityStart(Activity activity) {
        if (!this.tH) {
            d(activity);
        }
    }

    public void reportActivityStop(Activity activity) {
        if (!this.tH) {
            e(activity);
        }
    }

    public void setAppOptOut(boolean z) {
        u.bR().a(com.google.android.gms.analytics.u.a.tb);
        this.tE = Boolean.valueOf(z);
        if (this.tE.booleanValue()) {
            this.rk.bk();
        }
    }

    public void setDryRun(boolean z) {
        u.bR().a(com.google.android.gms.analytics.u.a.tn);
        this.tC = z;
    }

    @Deprecated
    public void setLocalDispatchPeriod(int i) {
        this.tD.setLocalDispatchPeriod(i);
    }

    public void setLogger(Logger logger) {
        u.bR().a(com.google.android.gms.analytics.u.a.tp);
        this.tF = logger;
    }
}