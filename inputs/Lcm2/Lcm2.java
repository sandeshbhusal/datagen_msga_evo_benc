// Original source code taken from: https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/lcm2_unwindbound1.c
// /* Algorithm for computing simultaneously the GCD and the LCM, by Dijkstra */

// extern void abort(void);
// extern void __assert_fail(const char *, const char *, unsigned int, const char *) __attribute__ ((__nothrow__ , __leaf__)) __attribute__ ((__noreturn__));
// void reach_error() { __assert_fail("0", "lcm2.c", 5, "reach_error"); }
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

// int counter = 0;
// int main() {
//     unsigned a, b;
//     unsigned x, y, u, v;
//     a = __VERIFIER_nondet_uint();
//     b = __VERIFIER_nondet_uint();
//     assume_abort_if_not(a >= 1); //inf loop if remove
//     assume_abort_if_not(b >= 1);

//     assume_abort_if_not(a <= 65535);
//     assume_abort_if_not(b <= 65535);

//     x = a;
//     y = b;
//     u = b;
//     v = a;

//     while (counter++<1) {
//         __VERIFIER_assert(x*u + y*v == 2*a*b);

//         if (!(x != y))
//             break;

//         if (x > y) {
//             x = x - y;
//             v = v + u;
//         } else {
//             y = y - x;
//             u = u + v;
//         }
//     }

//     __VERIFIER_assert(x*u + y*v == 2*a*b);
//     // x == gcd(a,b)
//     //(u + v)/2==lcm(a,b)

//     return 0;
// }

public class Lcm2 {
    public void test(int a, int b) {
        // Check conditions for a, b.
        if (!(a >= 1)) {
            return;
        }
        if (!(b >= 1)) {
            return;
        }
        if (!(a <= 65535)) {
            return;
        }
        if (!(b <= 65535)) {
            return;
        }

        int x = a;
        int y = b;
        int u = b;
        int v = a;

        ; // datagen_guard_start func_start a b
        while (true) {
            if (!(x != y)) break;

            if (x > y) {
                x = x - y;
                v = v + u;
            } else {
                y = y - x;
                u = u + v;
            }
        }
        ; // datagen_instrument lcm2_inv0 x u y v a b
        ; // datagen_guard_end func_start
    }    
}
