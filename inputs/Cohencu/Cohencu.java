// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/cohencu-ll_unwindbound1.c
// /*
// Printing consecutive cubes, by Cohen
// http://www.cs.upc.edu/~erodri/webpage/polynomial_invariants/cohencu.htm
// */

// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "cohencu-ll.c", 8, "reach_error"); }
// extern unsigned short __VERIFIER_nondet_ushort(void);
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

// int counter = 0;
// int main() {
//     short a;
//     long long n, x, y, z;
//     a = __VERIFIER_nondet_ushort();

//     n = 0;
//     x = 0;
//     y = 1;
//     z = 6;

//     while (counter++<1) {
//         __VERIFIER_assert(z == 6 * n + 6);
//         __VERIFIER_assert(y == 3 * n * n + 3 * n + 1);
//         __VERIFIER_assert(x == n * n * n);
// 	__VERIFIER_assert(y*z - 18*x - 12*y + 2*z - 6 == 0);
// 	__VERIFIER_assert((z*z) - 12*y - 6*z + 12 == 0);
//         if (!(n <= a))
//             break;

//         n = n + 1;
//         x = x + y;
//         y = y + z;
//         z = z + 6;
//     }

//     __VERIFIER_assert(z == 6*n + 6);
//     __VERIFIER_assert(6*a*x - x*z + 12*x == 0);
//     __VERIFIER_assert(a*z - 6*a - 2*y + 2*z - 10 == 0);
//     __VERIFIER_assert(2*y*y - 3*x*z - 18*x - 10*y + 3*z - 10 == 0);
//     __VERIFIER_assert(z*z - 12*y - 6*z + 12 == 0);
//     __VERIFIER_assert(y*z - 18*x - 12*y + 2*z - 6 == 0);
    
//     return 0;
// }

public class Cohencu {
    public void test(short a) {
        // Check conditions for a.
        if (!(a >= 0)) {
            return;
        }

        long n = 0;
        long x = 0;
        long y = 1;
        long z = 6;
        int counter = 0;

        ; // datagen_guard_start func_start a
        while (counter++ < 1) {
            if (!(n <= a))
                break;

            n = n + 1;
            x = x + y;
            y = y + z;
            z = z + 6;
        }

        ; // datagen_instrument cohencu_inv0 z n
        ; // datagen_instrument cohencu_inv1 a x z
        ; // datagen_instrument cohencu_inv2 a y z
        ; // datagen_instrument cohencu_inv3 x y z
        ; // datagen_instrument cohencu_inv4 y z

        ; // datagen_guard_end func_start
    } 
}
