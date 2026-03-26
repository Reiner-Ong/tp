package seedu.address.model;

import seedu.address.commons.exceptions.NotImplementedException;

/**
 * An AddressBook which tracks a history of states, allowing the undoing and redoing of states.
 */
public class VersionedAddressBook extends AddressBook {
    // TODO: implement AddressBookHistory class

    public VersionedAddressBook(ReadOnlyAddressBook addressBook) {
        super(addressBook);
    }

    /**
     * Saves the current address book state to history.
     */
    public void commit() {
        throw new NotImplementedException("commit() is not implemented yet");
    }

    /**
     * Restores the previous address book state from history.
     */
    public void undo() {
        throw new NotImplementedException("undo() is not implemented yet");
    }

    /**
     * Restores a previously undone address book state from history.
     */
    public void redo() {
        throw new NotImplementedException("redo() is not implemented yet");
    }
}
