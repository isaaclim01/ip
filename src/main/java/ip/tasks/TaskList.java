package ip.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    //Adds task to the task list
    public void addTask(Task task) {
        list.add(task);
    }

    //Returns size of the task list
    public int size() {
        return list.size();
    }

    //Removes task from task list
    public void remove(int index) {
        list.remove(index);
    }

    //Gets task from task list
    public Task get(int index) {
        return list.get(index);
    }

    //Checks if list is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //Checks if list contains object
    public boolean contains(Task t) {
        return list.contains(t);
    }

    //Performs given action for each element of task list
    @Override
    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

    @Override
    public Spliterator<Task> spliterator() {
        return list.spliterator();
    }
}
