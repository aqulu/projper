package lu.aqu.projper.ui.component;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class SpacerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private final int space;
    private final int orientation;

    public SpacerItemDecoration(Context context, int orientation, @DimenRes int dimensionRes) {
        this.orientation = orientation;
        this.space = context.getResources().getDimensionPixelSize(dimensionRes);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == HORIZONTAL) {
            outRect.right = space;
        } else if (orientation == VERTICAL) {
            outRect.bottom = space;
        }
    }
}
