package seedu.address.model.day;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Day's academic in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Academic {

    private final String time;
    private final String limit;
    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Time should only contain numbers & '.', 2 digits'.'2digits, ie: 13.00";
    public static final String MESSAGE_LIMIT_CONSTRAINTS =
            "Limit should only contain numbers & '.', 2 digits'.'2digits, ie: 13.00";
    public static final String VALIDATION_REGEX = "\\d{2}" + "." + "\\d{2}";

    /**
     * Constructs a {@code Academic}.
     *
     * @param time A valid time.
     * @param limit A valid limit.
     */
    public Academic(String time, String limit) {
        requireNonNull(time);
        requireNonNull(limit);
        checkArgument(isValidTime(time), MESSAGE_TIME_CONSTRAINTS);
        checkArgument(isValidLimit(limit), MESSAGE_LIMIT_CONSTRAINTS);
        this.time = time;
        this.limit = limit;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidLimit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTime() {
        return time;
    }

    public String getLimit() {
        return limit;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Academic // instanceof handles nulls
                && time.equals(((Academic) other).time) // state check
                && limit.equals(((Academic) other).limit));
    }

    @Override
    public int hashCode() {
        return (time + limit).hashCode();
    }

}
