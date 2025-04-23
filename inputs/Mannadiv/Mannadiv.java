// Original source code taken from:
// https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks/-/blob/testcomp25/c/nla-digbench-scaling/mannadiv_unwindbound1.c
// int main() {
//     int x1, x2;
//     int y1, y2, y3;
//     x1 = __VERIFIER_nondet_int();
//     x2 = __VERIFIER_nondet_int();

//     assume_abort_if_not(x1 >= 0);
//     assume_abort_if_not(x2 != 0);

//     y1 = 0;
//     y2 = 0;
//     y3 = x1;

//     while (counter++<1) {
//         __VERIFIER_assert(y1*x2 + y2 + y3 == x1);

//         if (!(y3 != 0)) break;

//         if (y2 + 1 == x2) {
//             y1 = y1 + 1;
//             y2 = 0;
//             y3 = y3 - 1;
//         } else {
//             y2 = y2 + 1;
//             y3 = y3 - 1;
//         }
//     }
//     __VERIFIER_assert(y1*x2 + y2 == x1);
//     return 0;
// }


public class Mannadiv {
    public void test(int x1, int x2) {
        ; // datagen_instrument input_vars x1 x2
        ; // datagen_guard_start func_start x1 x2
        if (x1 < 0 || x2 == 0) {
            return;
        }

        int y1 = 0;
        int y2 = 0;
        int y3 = x1;
        int counter = 0;
        while (counter++ < 1) {
            if (!(y3 != 0)) break;
            if (y2 + 1 == x2) {
                y1 = y1 + 1;
                y2 = 0;
                y3 = y3 - 1;
            } else {
                y2 = y2 + 1;
                y3 = y3 - 1;
            }
        }
        ; // datagen_instrument mannadiv_inv1 x1 y1 x2 y2

        ; // datagen_guard_end func_start
    }    
}
