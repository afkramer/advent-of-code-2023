package adventofcode2023.day19;

public class Part {
    private int x;
    private int m;
    private int a;
    private int s;
    private boolean accepted;

    public Part(int x, int m, int a, int s) {
        this.x = x;
        this.m = m;
        this.a = a;
        this.s = s;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Part [x=" + x + ", m=" + m + ", a=" + a + ", s=" + s + "]";
    }

}
