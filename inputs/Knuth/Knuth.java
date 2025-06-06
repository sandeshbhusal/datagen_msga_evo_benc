// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/knuth_unwindbound1.c
// /* algorithm searching for a divisor for factorization, by Knuth */

// #include <limits.h>

// extern void abort(void);
// #include <assert.h>
// void reach_error() { assert(0); }
// extern unsigned __VERIFIER_nondet_uint(void);
// extern void abort(void);
// void assume_abort_if_not(int cond) {
//   if(!cond) {abort();}
// }
// void __VERIFIER_assert(int cond) {
//     if (!(cond)) {
//     ERROR:
//         {reach_error();}
//     }
//     return;
// }

// extern double sqrt(double);

// int counter = 0;
// int main() {
//     unsigned n, a;
//     unsigned r, k, q, d, s, t;
//     n = __VERIFIER_nondet_uint();
//     a = __VERIFIER_nondet_uint();
//     assume_abort_if_not(n < UINT_MAX/8);
//     assume_abort_if_not(a > 2);

//     d = a;
//     r = n % d;
//     t = 0;
//     k = n % (d - 2);
//     q = 4 * (n / (d - 2) - n / d);
//     s = sqrt(n);

//     while (counter++<1) {
//         __VERIFIER_assert(d * d * q - 2 * q * d - 4 * r * d + 4 * k * d + 8 * r == 8 * n);
//         __VERIFIER_assert(k * t == t * t);
//         __VERIFIER_assert(d * d * q - 2 * d * q - 4 * d * r + 4 * d * t + 4 * a * k - 4 * a * t - 8 * n + 8 * r == 0);
//         __VERIFIER_assert(d * k - d * t - a * k + a * t == 0);

//         if (!((s >= d) && (r != 0))) break;

//         if (2 * r  + q < k) {
//             t = r;
//             r = 2 * r - k + q + d + 2;
//             k = t;
//             q = q + 4;
//             d = d + 2;
//         } else if ((2 * r  + q >= k) && (2 * r + q < d + k + 2)) {
//             t = r;
//             r = 2 * r - k + q;
//             k = t;
//             d = d + 2;
//         } else if ((2 * r  + q >= k) && (2 * r + q >= d + k + 2) && (2 * r + q < 2 * d + k + 4)) {
//             t = r;
//             r = 2 * r - k + q - d - 2;
//             k = t;
//             q = q - 4;
//             d = d + 2;
//         } else { /* ((2*r-k+q>=0)&&(2*r-k+q>=2*d+4)) */
//             t = r;
//             r = 2 * r - k + q - 2 * d - 4;
//             k = t;
//             q = q - 8;
//             d = d + 2;
//         }
//     }

//     //postconds ? cannot be obtained with DIG (Syminfer)
//     return 0;
// }

public class Knuth {
    public void test(int n, int a) {
        // Check conditions for n, a.
        if (!(n < Integer.MAX_VALUE / 8)) {
            return;
        }
        if (!(a > 2)) {
            return;
        }

        int d = a;
        int r = n % d;
        int t = 0;
        int k = n % (d - 2);
        int q = 4 * (n / (d - 2) - n / d);
        double s = Math.sqrt(n);
        int counter = 0;

        ; // datagen_guard_start func_start n a
        while (counter++ < 1) {
            if (!(s >= d && r != 0)) break;

            if (2 * r + q < k) {
                t = r;
                r = 2 * r - k + q + d + 2;
                k = t;
                q = q + 4;
                d = d + 2;
            } else if ((2 * r + q >= k) && (2 * r + q < d + k + 2)) {
                t = r;
                r = 2 * r - k + q;
                k = t;
                d = d + 2;
            } else if ((2 * r + q >= k) && (2 * r + q >= d + k + 2) && (2 * r + q < 2 * d + k + 4)) {
                t = r;
                r = 2 * r - k + q - d - 2;
                k = t;
                q = q - 4;
                d = d + 2;
            } else { /* ((2*r-k+q>=0)&&(2*r-k+q>=2*d+4)) */
                t = r;
                r = 2 * r - k + q - 2 * d - 4;
                k = t;
                q = q - 8;
                d = d + 2;
            }
        }

        // We do not care here. DIG is unable to infer invariants. Let's try
        // to infer them with datagen/evosuite/multisetga?

        ; // datagen_instrument knuth_postcond1 d q r k n s t a
        ; // datagen_guard_end func_start
    }    
}
