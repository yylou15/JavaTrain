package Utils;

public class RandomNum {

    public static int getRandom(int min, int max) {
        return (int)(min + Math.random() * max);
    }
}
