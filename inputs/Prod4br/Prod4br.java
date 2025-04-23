// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/prod4br-ll_unwindbound1.c
// /* algorithm for computing the product of two natural numbers */

// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "prod4br-ll.c", 5, "reach_error"); }
// extern int __VERIFIER_nondet_int(void);
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
//     int x, y;
//     long long a, b, p, q;

//     x = __VERIFIER_nondet_int();
//     y = __VERIFIER_nondet_int();
//     assume_abort_if_not(y >= 1);

//     a = x;
//     b = y;
//     p = 1;
//     q = 0;

//     while (counter++<1) {
//         __VERIFIER_assert(q + a * b * p == (long long) x * y);

//         if (!(a != 0 && b != 0))
//             break;

//         if (a % 2 == 0 && b % 2 == 0) {
//             a = a / 2;
//             b = b / 2;
//             p = 4 * p;
//         } else if (a % 2 == 1 && b % 2 == 0) {
//             a = a - 1;
//             q = q + b * p;
//         } else if (a % 2 == 0 && b % 2 == 1) {
//             b = b - 1;
//             q = q + a * p;
//         } else {
//             a = a - 1;
//             b = b - 1;
//             q = q + (a + b + 1) * p; /*fix a bug here---  was (a+b-1)*/
//         }
//     }

//     __VERIFIER_assert(q == (long long) x * y);
//     __VERIFIER_assert(a * b == 0);
//     return 0;
// }

public class Prod4br {
    public void test(int x, int y) {
        int a, b, p, q;

        // Check conditions for x, y.
        if (!(y >= 1)) {
            return;
        }

        a = x;
        b = y;
        p = 1;
        q = 0;

        int counter = 0;

        ; // datagen_guard_start func_start x y
        while(counter++ < 1) {
            ; // datagen_instrument prod4br_loop0 x y a b p q
            if (!(a != 0 && b != 0)) {
                break;
            }

            if (a % 2 == 0 && b % 2 == 0) {
                a = a / 2;
                b = b / 2;
                p = 4 * p;
            } else if (a % 2 == 1 && b % 2 == 0) {
                a = a - 1;
                q = q + b * p;
            } else if (a % 2 == 0 && b % 2 == 1) {
                b = b - 1;
                q = q + a * p;
            } else {
                a = a - 1;
                b = b - 1;
                q = q + (a + b + 1) * p; /*fix a bug here--- was (a+b-1)*/
            }
        }
        ; // datagen_instrument prod4br_inv1 q x y
        ; // datagen_instrument prod4br_inv2 a b
        ; // datagen_guard_end func_start
    }    
}
