package planning;

public class NewtonSolver {
    public interface Function {
        double f(double x);
        double df(double x);
    }

    public static double solve(Function fn, double x0, int iters, double eps) {
        double x = x0;
        for (int i = 0; i < iters; i++) {
            double y = fn.f(x);
            if (Math.abs(y) < eps) return x;
            double dy = fn.df(x);
            if (Math.abs(dy) < 1e-12) break;
            x = x - y / dy;
        }
        return x;
    }
}
