package seedu.address.model.day;

import java.util.Objects;

/**
 * Represents a Day's academic in the task book.
 */
public class Academic extends Category {

    private double time;

    /**
     * Constructs a {@code Academic}.
     *
     * @param time A valid time.
     */
    public Academic(double time) {
        this.time = time;
    }

    public void addTime(double result) {
        time += result;
    }

    public void removeTime(double result) {
        time -= result;
    }

    public double getTime() {
        return time;
    }

    public String getTimeString() {
        return String.valueOf(time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Academic // instanceof handles nulls
                && time == ((Academic) other).time); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

}
