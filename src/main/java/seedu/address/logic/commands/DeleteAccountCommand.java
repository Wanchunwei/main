package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Account;


/**
 * Deletes an account identified using it's displayed index from the account list.
 * Raises an exception if last account in the list.
 */
public class DeleteAccountCommand extends Command {
    public static final String COMMAND_WORD = "deleteaccount";

    public static final String COMMAND_ALIAS = "dAc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the account identified by the index number used in the displayed account database.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOUNT_SUCCESS = "Deleted Account: %1$s";

    private static final String MESSAGE_LOGIN = "Please login first";

    private final Path filePath = Paths.get("data/AccountList.json");

    private final Index targetIndex;

    public DeleteAccountCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history)
            throws CommandException, IOException, IllegalValueException {
        requireNonNull(model);

        if (!model.getLoginStatus()) {
            throw new CommandException(MESSAGE_LOGIN);
        }

        List<Account> lastShownList = model.getFilteredAccountList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOUNT_DISPLAYED_INDEX);
        }

        Account itemToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (itemToDelete.getUsername().toString().equals(model.getLoggedInUser())) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOUNT_DELETION);
        }

        model.deleteAccount(itemToDelete);
        model.exportFilteredAccountList(filePath);
        return new CommandResult(String.format(MESSAGE_DELETE_ACCOUNT_SUCCESS, itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAccountCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAccountCommand) other).targetIndex)); // state check
    }

}
