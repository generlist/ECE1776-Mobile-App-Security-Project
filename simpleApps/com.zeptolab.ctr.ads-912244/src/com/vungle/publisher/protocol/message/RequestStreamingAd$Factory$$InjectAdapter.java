package com.vungle.publisher.protocol.message;

import a.a.b;
import a.a.l;
import com.vungle.publisher.protocol.message.RequestStreamingAd.Factory;
import java.util.Set;
import javax.inject.Provider;

public final class RequestStreamingAd$Factory$$InjectAdapter extends b implements a.b, Provider {
    private b a;
    private b b;

    public RequestStreamingAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestStreamingAd$Factory", "members/com.vungle.publisher.protocol.message.RequestStreamingAd$Factory", true, Factory.class);
    }

    public final void attach(l lVar) {
        this.a = lVar.a("com.vungle.publisher.protocol.message.ExtraInfo$Factory", (Object)Factory.class, getClass().getClassLoader());
        this.b = lVar.a("members/com.vungle.publisher.protocol.message.RequestAd$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void getDependencies(Set set, Set set2) {
        set2.add(this.a);
        set2.add(this.b);
    }

    public final void injectMembers(Factory factory) {
        factory.e = (Factory) this.a.get();
        this.b.injectMembers(factory);
    }
}