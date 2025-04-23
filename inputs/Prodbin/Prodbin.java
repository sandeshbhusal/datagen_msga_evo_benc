// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/prodbin-ll_valuebound1.c
// /* shift_add algorithm for computing the 
//    product of two natural numbers
// */
// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "prodbin-ll.c", 6, "reach_error"); }
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

// int main() {
//     int a, b;
//     long long x, y, z;

//     a = __VERIFIER_nondet_int();
//     assume_abort_if_not(a>=0 && a<=1);
//     b = __VERIFIER_nondet_int();
//     assume_abort_if_not(b>=0 && b<=1);
//     assume_abort_if_not(b >= 1);

//     x = a;
//     y = b;
//     z = 0;

//     while (1) {
//         __VERIFIER_assert(z + x * y == (long long) a * b);
//         if (!(y != 0))
//             break;

//         if (y % 2 == 1) {
//             z = z + x;
//             y = y - 1;
//         }
//         x = 2 * x;
//         y = y / 2;
//     }
//     __VERIFIER_assert(z == (long long) a * b);
    
//     return 0;
// }

public class Prodbin {
    public void test(int a, int b) {
        // Check conditions for a, b.
        if (!(a >= 0 && a <= 1)) {
            return;
        }
        if (!(b >= 0 && b <= 1) || !(b >= 1)) {
            return;
        }

        ; // datagen_guard_start func_start a b
        int x = a;
        int y = b;
        int z = 0;

        while (true) {
            if (!(y != 0)) {
                break;
            }

            if (y % 2 == 1) {
                z = z + x;
                y = y - 1;
            }

            x = 2 * x;
            y = y / 2;
        }

        ; // datagen_instrument prodbin_inv1 a b x y z
        ; // datagen_guard_end func_start
    } 
}
