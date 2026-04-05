package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsFavouritePredicate;

import java.util.logging.Logger;

/**
 * Lists all contacts in the address book in favourites to the user.
 */
public class FavouriteViewCommand extends Command {

    public static final String COMMAND_WORD = "favourite-view";

    private static final ContactIsFavouritePredicate predicate = new ContactIsFavouritePredicate();

    private static final Logger logger = LogsCenter.getLogger(FavouriteViewCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        logger.fine(String.format("Favourites: %s", model.getFilteredContactList()));
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
