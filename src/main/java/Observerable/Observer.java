package Observerable;

public interface Observer<T> {
    public void onChanged(Observerable observerable, T e);
}
