package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.brightcove.player.model.Video.Fields;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import java.util.ArrayList;

public final class b extends com.google.android.gms.common.data.b implements Leaderboard {
    private final Game IM;
    private final int IN;

    b(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.IN = i2;
        this.IM = new com.google.android.gms.games.b(dataHolder, i);
    }

    public boolean equals(Object obj) {
        return a.a(this, obj);
    }

    public Leaderboard fW() {
        return new a(this);
    }

    public /* synthetic */ Object freeze() {
        return fW();
    }

    public String getDisplayName() {
        return getString(Fields.NAME);
    }

    public void getDisplayName(CharArrayBuffer charArrayBuffer) {
        a(Fields.NAME, charArrayBuffer);
    }

    public Game getGame() {
        return this.IM;
    }

    public Uri getIconImageUri() {
        return aa("board_icon_image_uri");
    }

    public String getIconImageUrl() {
        return getString("board_icon_image_url");
    }

    public String getLeaderboardId() {
        return getString("external_leaderboard_id");
    }

    public int getScoreOrder() {
        return getInteger("score_order");
    }

    public ArrayList getVariants() {
        ArrayList arrayList = new ArrayList(this.IN);
        int i = 0;
        while (i < this.IN) {
            arrayList.add(new g(this.zU, this.zW + i));
            i++;
        }
        return arrayList;
    }

    public int hashCode() {
        return a.a(this);
    }

    public String toString() {
        return a.b(this);
    }
}