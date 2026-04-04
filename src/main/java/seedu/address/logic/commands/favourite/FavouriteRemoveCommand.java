package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.FavouriteStatus;

/**
 * Removes an existing contact in the address book from favourites.
 */
public class FavouriteRemoveCommand extends Command {

    public static final String COMMAND_WORD = "favourite-remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes from favourites an existing contact identified by the index number used "
            + "in the displayed contact list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_FAVOURITE_SUCCESS = "Removed contact from favourites: %1$s";
    public static final String MESSAGE_DUPLICATE_NON_FAVOURITE = "Contact is already not in favourites.";

    private static final FavouriteStatus FAVOURITE_FALSE = new FavouriteStatus("false");

    private final Index contactIndex;

    /**
     * Creates an FavouriteRemoveCommand to remove the contact at {@code contactIndex} from favourites.
     */
    public FavouriteRemoveCommand(Index contactIndex) {
        requireNonNull(contactIndex);
        this.contactIndex = contactIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Contact contactToEdit = getContactToEdit(model);
        Contact editedContact = getEditedContact(contactToEdit);
        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_REMOVE_FAVOURITE_SUCCESS, Messages.format(editedContact)));
    }

    /**
     * Returns the contact to remove from favourites.
     */
    private Contact getContactToEdit(Model model) throws CommandException {
        List<Contact> lastShownContactList = model.getFilteredContactList();

        if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownContactList.get(contactIndex.getZeroBased());
        assert contactToEdit != null : "Contact from contact list must not be null.";

        if (!contactToEdit.isFavourite()) {
            throw new CommandException(MESSAGE_DUPLICATE_NON_FAVOURITE);
        }

        return contactToEdit;
    }

    /**
     * Returns the contact with updated favourite status.
     */
    private Contact getEditedContact(Contact contactToEdit) {
        requireNonNull(contactToEdit);
        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setFavourite(FAVOURITE_FALSE);

        Contact editedContact = contactToEdit.edit(descriptor);

        assert editedContact != null : "Edited contact must not be null.";
        assert !editedContact.isFavourite() : "Contact must have been removed from favourites.";

        return editedContact;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavouriteRemoveCommand)) {
            return false;
        }

        FavouriteRemoveCommand otherCommand = (FavouriteRemoveCommand) other;
        return contactIndex.equals(otherCommand.contactIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactIndex", contactIndex)
                .toString();
    }
}
