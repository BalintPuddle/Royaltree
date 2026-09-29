package tree.card;

public class Duration {
    public int from;
    public int to;


    public Duration(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public String get() {
        return (from + "-" + to + " " + TreeHandler.getYearFormat());
    }
}
