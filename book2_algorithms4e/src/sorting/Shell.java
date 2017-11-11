package sorting;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Shell extends Sort {
    public static void sort(double[] a) {
        // int N = a.length;
        // int h = 1;
        // while (h < N / 3)
        // h = 3 * h + 1;
        // // 1, 4, 13, 40, 121, 364, 1093, ...
        // while (h >= 1) {
        // int i;
        // for (i = h; i < N; i++) {
        // int j;
        // for (j = i; j >= h && less(a[j], a[j - h]); j -= h) {
        // // j>=10的意思基本上就是说 还有没有向左挪的空间
        // Visualizer.refresh(a, j, j - h, 0.8);
        // Visualizer.doubleExchange(a, j, j - h);
        // Visualizer.refresh(a, j - h, j, 0.8);
        // }
        // System.out.println(j);
        // }
        // System.out.println(i);
        // h = h / 3;
        // }
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    Visualizer.refresh(a, j, j - h, 0.8);
                    Visualizer.doubleExchange(a, j, j - h);
                    Visualizer.refresh(a, j - h, j, 0.8);
                }
            }
            h = h / 3;
        }// 默写了一遍
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        System.out.println(N);
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale((0) - 0.5, (N - 1) + 0.5);
        StdDraw.setYscale(0, 1);
        Visualizer.showOriginalSequence(a, 0.8);
        sort(a);
    }
}
