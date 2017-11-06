package ly.count.android.sdk.internal;

import java.util.HashMap;
import java.util.Map;

import ly.count.android.sdk.Session;
import ly.count.android.sdk.View;

/**
 * Views support
 */

public class ModuleViews extends ModuleBase {
    private Map<Integer, View> views = null;

    @Override
    public void init(InternalConfig config) {
        super.init(config);
        views = new HashMap<>();
    }

    /**
     * When new {@link android.app.Activity} started, starts new {@link View} with name
     * set as {@link android.app.Activity} class name.
     */
    @Override
    public void onActivityStarted(Context ctx) {
        Session session = Core.instance.getSession();
        if (session != null) {
            views.put(ctx.getActivity().hashCode(), session.view(ctx.getActivity().getClass().getName()));
        }
    }

    /**
     * When {@link android.app.Activity} stopped, stops previously started {@link View}.
     */
    @Override
    public void onActivityStopped(Context ctx) {
        int cls = ctx.getActivity().hashCode();
        if (views.containsKey(cls)) {
            views.remove(cls).end(false);
        }
    }
}
