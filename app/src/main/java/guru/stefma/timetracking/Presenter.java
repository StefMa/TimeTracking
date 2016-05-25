package guru.stefma.timetracking;

public class Presenter<V> {

    private V mView;

    public Presenter(V view) {
        mView = view;
    }

    public V getView() {
        return mView;
    }
}
