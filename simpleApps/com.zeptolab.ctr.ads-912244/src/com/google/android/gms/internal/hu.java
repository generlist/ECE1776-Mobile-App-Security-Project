package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.LocationStatusCodes;
import com.zeptolab.ctr.scorer.GoogleScorer;

public class hu implements Creator {
    static void a(ht htVar, Parcel parcel, int i) {
        int p = b.p(parcel);
        b.a(parcel, 1, htVar.Oc, false);
        b.c(parcel, LocationStatusCodes.GEOFENCE_NOT_AVAILABLE, htVar.wj);
        b.D(parcel, p);
    }

    public ht az(Parcel parcel) {
        int o = a.o(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < o) {
            int n = a.n(parcel);
            switch (a.S(n)) {
                case GoogleScorer.CLIENT_GAMES:
                    str = a.m(parcel, n);
                    break;
                case LocationStatusCodes.GEOFENCE_NOT_AVAILABLE:
                    i = a.g(parcel, n);
                    break;
                default:
                    a.b(parcel, n);
                    break;
            }
        }
        if (parcel.dataPosition() == o) {
            return new ht(i, str);
        }
        throw new a.a("Overread allowed size end=" + o, parcel);
    }

    public ht[] bt(int i) {
        return new ht[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return az(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return bt(i);
    }
}