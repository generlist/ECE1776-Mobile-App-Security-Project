package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.zeptolab.ctr.scorer.GoogleScorer;

public class t implements Creator {
    static void a(GetMetadataRequest getMetadataRequest, Parcel parcel, int i) {
        int p = b.p(parcel);
        b.c(parcel, 1, getMetadataRequest.wj);
        b.a(parcel, (int)GoogleScorer.CLIENT_PLUS, getMetadataRequest.Do, i, false);
        b.D(parcel, p);
    }

    public GetMetadataRequest L(Parcel parcel) {
        int o = a.o(parcel);
        int i = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < o) {
            int n = a.n(parcel);
            switch (a.S(n)) {
                case GoogleScorer.CLIENT_GAMES:
                    i = a.g(parcel, n);
                    break;
                case GoogleScorer.CLIENT_PLUS:
                    driveId = (DriveId) a.a(parcel, n, DriveId.CREATOR);
                    break;
                default:
                    a.b(parcel, n);
                    break;
            }
        }
        if (parcel.dataPosition() == o) {
            return new GetMetadataRequest(i, driveId);
        }
        throw new a.a("Overread allowed size end=" + o, parcel);
    }

    public GetMetadataRequest[] aq(int i) {
        return new GetMetadataRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return L(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return aq(i);
    }
}