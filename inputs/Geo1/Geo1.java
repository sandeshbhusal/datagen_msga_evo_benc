// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/geo1-ll2_unwindbound1.c
// /* 
// Geometric Series
// computes x=(z-1)* sum(z^k)[k=0..k-1] , y = z^k
// returns 1+x-y == 0
// */

// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "geo1-ll.c", 9, "reach_error"); }
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
//     int z, k;
//     long long x, y, c;
//     z = __VERIFIER_nondet_int();
//     k = __VERIFIER_nondet_int();
//     assume_abort_if_not(z >= 1);
//     assume_abort_if_not(k >= 1);

//     x = 1;
//     y = z;
//     c = 1;

//     while (counter++<1) {
//         __VERIFIER_assert(x*z - x - y + 1 == 0);

//         if (!(c < k)) 
//             break;

//         c = c + 1;
//         x = x * z + 1;
//         y = y * z;

//     }  //geo1

//     x = x * (z - 1);

//     __VERIFIER_assert(1 + x - y == 0);
//     return 0;
// }

public class Geo1 {
    public void test(int z, int k) {
        // Check conditions for z, k.
        if (!(z >= 1)) {
            return;
        }
        if (!(k >= 1)) {
            return;
        }

        long x = 1;
        long y = z;
        long c = 1;

        int counter = 0;

        ; // datagen_guard_start func_start z k
        while (counter++ < 1) {
            c = c + 1;
            x = x * z + 1;
            y = y * z;
        }

        x = x * (z - 1);

        ; // datagen_instrument geo1_inv0 x y 
        ; // datagen_guard_end func_start
    }    
}
