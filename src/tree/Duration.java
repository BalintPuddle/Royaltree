package tree;

public class Duration {
    public int from;
    public int to;
    private String yearFormat;


    public Duration(int from, int to) {
        this.from = from;
        this.to = to;
        this.yearFormat = " AD";
    }

    public String get() {
        return (from + "-" + to + yearFormat);
    }
}
