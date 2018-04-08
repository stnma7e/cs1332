public class Notes {
    public static void main(String[] args) {
        int[] arg = {1,2,3};
        System.out.println(rec_array(arg, 0));
    }

    public static int rec_array(int[] arg, int start) {
        if (start < arg.length) {
            return arg[start] + rec_array(arg, start + 1);
        }
        else {
            return 0;
        }
    }
}
