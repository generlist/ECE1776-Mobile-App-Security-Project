package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.er;

public abstract class b {
    protected final DataHolder zU;
    protected final int zW;
    private final int zX;

    public b(DataHolder dataHolder, int i) {
        boolean z;
        this.zU = (DataHolder) er.f(dataHolder);
        z = i >= 0 && i < dataHolder.getCount();
        er.v(z);
        this.zW = i;
        this.zX = dataHolder.I(this.zW);
    }

    protected void a(String str, CharArrayBuffer charArrayBuffer) {
        this.zU.copyToBuffer(str, this.zW, this.zX, charArrayBuffer);
    }

    protected Uri aa(String str) {
        return this.zU.parseUri(str, this.zW, this.zX);
    }

    protected boolean ab(String str) {
        return this.zU.hasNull(str, this.zW, this.zX);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return ep.equal(Integer.valueOf(bVar.zW), Integer.valueOf(this.zW)) && ep.equal(Integer.valueOf(bVar.zX), Integer.valueOf(this.zX)) && bVar.zU == this.zU;
    }

    protected boolean getBoolean(String str) {
        return this.zU.getBoolean(str, this.zW, this.zX);
    }

    protected byte[] getByteArray(String str) {
        return this.zU.getByteArray(str, this.zW, this.zX);
    }

    protected int getInteger(String str) {
        return this.zU.getInteger(str, this.zW, this.zX);
    }

    protected long getLong(String str) {
        return this.zU.getLong(str, this.zW, this.zX);
    }

    protected String getString(String str) {
        return this.zU.getString(str, this.zW, this.zX);
    }

    public boolean hasColumn(String str) {
        return this.zU.hasColumn(str);
    }

    public int hashCode() {
        return ep.hashCode(new Object[]{Integer.valueOf(this.zW), Integer.valueOf(this.zX), this.zU});
    }

    public boolean isDataValid() {
        return !this.zU.isClosed();
    }
}