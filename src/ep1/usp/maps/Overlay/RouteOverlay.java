package ep1.usp.maps.Overlay;

import java.util.ArrayList;

import org.ci.geo.route.Road;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.Overlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class RouteOverlay extends Overlay
{

        Road mRoad;
        ArrayList<GeoPoint> mPoints;
        int color;

        public RouteOverlay(Road road, MapView mv, int color) {
                mRoad = road;
                this.color = color;
                if (road.mRoute.length > 0) {
                        mPoints = new ArrayList<GeoPoint>();
                        for (int i = 0; i < road.mRoute.length; i++) {
                                mPoints.add(new GeoPoint((int) (road.mRoute[i][1] * 1000000),
                                                (int) (road.mRoute[i][0] * 1000000)));
                        }
                }
        }

        @Override
        public boolean draw(Canvas canvas, MapView mv, boolean shadow, long when) {
                super.draw(canvas, mv, shadow);
                drawPath(mv, canvas);
                return true;
        }

        public void drawPath(MapView mv, Canvas canvas) {
                int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
                Paint paint = new Paint();
                paint.setColor(color);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3);
                for (int i = 0; i < mPoints.size(); i++) {
                        Point point = new Point();
                        mv.getProjection().toPixels(mPoints.get(i), point);
                        x2 = point.x;
                        y2 = point.y;
                        if (i > 0) {
                                canvas.drawLine(x1, y1, x2, y2, paint);
                        }
                        x1 = x2;
                        y1 = y2;
                }
        }
}
