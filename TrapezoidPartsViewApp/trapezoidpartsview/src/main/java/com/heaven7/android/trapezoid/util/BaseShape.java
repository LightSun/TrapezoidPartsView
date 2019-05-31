package com.heaven7.android.trapezoid.util;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by heaven7 on 2019/5/30.
 */
public abstract class BaseShape<T> {

    public abstract boolean isPointIn(T range, int x, int y);

    public static class RectangleShape extends BaseShape<Rect> {
        @Override
        public boolean isPointIn(Rect range, int x, int y) {
            return range.contains(x, y);
        }
    }
    public static class TriangleRange implements Parcelable {
        Point p1;
        Point p2;
        Point p3;

        public Point getP1() {
            return p1;
        }
        public void setP1(Point p1) {
            this.p1 = p1;
        }

        public Point getP2() {
            return p2;
        }
        public void setP2(Point p2) {
            this.p2 = p2;
        }

        public Point getP3() {
            return p3;
        }
        public void setP3(Point p3) {
            this.p3 = p3;
        }

        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(p1, flags);
            dest.writeParcelable(p2, flags);
            dest.writeParcelable(p3, flags);
        }

        private void readFromParcel(Parcel source, ClassLoader loader) {
            p1 = source.readParcelable(loader);
            p2 = source.readParcelable(loader);
            p3 = source.readParcelable(loader);
        }
        public static final Creator<TriangleRange> CREATOR = new ClassLoaderCreator<TriangleRange>() {
            @Override
            public TriangleRange createFromParcel(Parcel source, ClassLoader loader) {
                TriangleRange tr = new TriangleRange();
                tr.readFromParcel(source, loader);
                return tr ;
            }
            @Override
            public TriangleRange createFromParcel(Parcel source) {
                return createFromParcel(source, null);
            }
            @Override
            public TriangleRange[] newArray(int size) {
                return new TriangleRange[size];
            }
        };
    }

    public static class TriangleShape extends BaseShape<TriangleRange> {
        @Override
        public boolean isPointIn(TriangleRange range, int x, int y) {
            if(range == null){
                return false;
            }
            Point P = new Point(x, y);
            Point A = range.getP1();
            Point B = range.getP2();
            Point C = range.getP3();

            /*利用叉乘法进行判断,假设P点就是M点*/
            int a = 0, b = 0, c = 0;
            Point MA = new Point(P.x - A.x, P.y - A.y);
            Point MB = new Point(P.x - B.x, P.y - B.y);
            Point MC = new Point(P.x - C.x, P.y - C.y);

            /*向量叉乘*/
            a = MA.x * MB.y - MA.y * MB.x;
            b = MB.x * MC.y - MB.y * MC.x;
            c = MC.x * MA.y - MC.y * MA.x;

            if((a <= 0 && b <= 0 && c <= 0)|| (a > 0 && b > 0 && c > 0)){
                return true;
            }
            return false;
        }
    }

}
