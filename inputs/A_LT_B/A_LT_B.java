// Original source code, etc.
// Foo
// bar
public class A_LT_B {
    public void test(int a, int b) {
        ; // datagen_guard_start altb_guard_1 a b
        if (a < b) {
            ; // datagen_instrument a_lt_b_true a b
        } else {
            ; // datagen_instrument a_lt_b_false a b
        }
        ; // datagen_guard_end altb_guard_1
        ; // datagen_instrument a_lt_b_end a b
    }    
}
