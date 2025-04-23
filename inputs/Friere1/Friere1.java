// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/freire1_valuebound1.c
// /* Algorithm for finding the closest integer to square root,
//  * more details, see : http://www.pedrofreire.com/sqrt/sqrt1.en.html 

// Note: for some reason using cpa was able to disprove these
// cpa.sh -kInduction -setprop solver.solver=z3 freire1.c
// */

// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "freire1.c", 10, "reach_error"); }
// extern double __VERIFIER_nondet_double(void);
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

// int main() {
//     int r;
//     double a, x;
//     a = __VERIFIER_nondet_double();
//     assume_abort_if_not(a>=0 && a<=1);
//     x = a / 2.0;
//     r = 0;

//     while (1) {
//         __VERIFIER_assert((int)(r*r - a - r + 2*x) == 0);

//         if (!(x > r))
//             break;
//         x = x - r;
//         r = r + 1;
//     }

//     __VERIFIER_assert((int)(r*r - a - r + 2*x) == 0);
//     return 0;
// }

public class Friere1 {
    public void test(int a) {
        int r = 0;
        if (!(a >= 0 && a <= 1)) {
            return;
        }

        double x = a / 2.0;
        ; // datagen_guard_start func_start a
        while (true) {
            // Check the loop condition.
            if (!(x > r)) {
                break;
            }

            x = x - r;
            r = r + 1;
        }

        ; // datagen_instrument inv0 a r x
        ; // datagen_guard_end func_end
    } 
}
