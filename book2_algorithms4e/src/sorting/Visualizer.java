package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class Visualizer {
    public static void main(String[] args) {
        showAnimation();
    }

    public static void showAnimation() {

        boolean selectionToggled = false;
        double[] a = In.readDoubles();
        int N = a.length;
        int canvasWidth = 1000;
        int canvasHeight = 500;
        double maxBarWidth = 1; // 指坐标系里的1（因数组索引都是自然数，故1适用），而非canvas的像素宽度

        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale((0) - maxBarWidth / 2, (N - 1) + maxBarWidth / 2);
        StdDraw.setYscale(0, 1);

        showOriginalSequence(a, maxBarWidth * 0.8);// 排序之前

        if (selectionToggled) {
            // selection
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i + 1; j < N; j++) {
                    if (a[j] < a[min])
                        min = j;
                }
                refresh(a, i, min, maxBarWidth * 0.8);// 给将要交换的两个柱图上颜色
                doubleExchange(a, i, min);
                refresh(a, min, i, maxBarWidth * 0.8);// 换完的结果
            }
        } else {
            // Insertion
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                    refresh(a, j-1, j, maxBarWidth * 0.8);
                    doubleExchange(a, j - 1, j);
                    refresh(a, j, j-1, maxBarWidth * 0.8);
                }
            }
        }
    }

    public static void doubleExchange(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void showOriginalSequence(double[] a, double barWidth) {
        int N = a.length;
        for (int idx = 0; idx < N; idx++) {
            // StdDraw.line(idx, 0, idx, a[idx]);
            // if(idx%2==0){
            // StdDraw.setPenColor(StdDraw.BOOK_RED);
            // }else{
            // StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            // }
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.filledRectangle(idx, a[idx] / 2, barWidth / 2, a[idx] / 2);
        }
    }

    public static void refresh(double[] a, int p, int q, double barWidth) {
        StdDraw.clear();
        int N = a.length;
        for (int idx = 0; idx < N; idx++) {
            if (idx == p) {
                StdDraw.setPenColor(StdDraw.BOOK_RED);
            }
            if (idx == q) {
                StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            }
            if (idx != p && idx != q) {
                StdDraw.setPenColor();
            }
            // StdDraw.line(idx, 0, idx, a[idx]);
            StdDraw.filledRectangle(idx, a[idx] / 2, barWidth / 2, a[idx] / 2);
        }
        StdDraw.show(300);
    }

}
