package seedu.address.logic.commands.favourite;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TOURS;
import static seedu.address.model.contact.FavouriteStatus.VALID_FAVOURITE_STATUS_FALSE;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.FavouriteStatus;
import seedu.address.model.tour.Tour;

/**
 * Removes an existing contact in the address book from favourites.
 */
public class TourFavouriteRemoveCommand extends Command {
    public static final String COMMAND_WORD = "tour-favourite-remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes to favourites an existing tour identified by the index number used "
            + "in the displayed tour list.\n"
            + "Parameters: TOUR_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_FAVOURITE_SUCCESS = "Removed tour package from favourites: %1$s";
    public static final String MESSAGE_DUPLICATE_NON_FAVOURITE = "Tour package is already not in favourites.";


    private static final Logger logger = LogsCenter.getLogger(TourFavouriteRemoveCommand.class);

    private final Index tourIndex;

    /**
     * Creates an TourFavouriteRemoveCommand to add the tour at {@code tourIndex} to favourites.
     */
    public TourFavouriteRemoveCommand(Index tourIndex) {
        requireNonNull(tourIndex);
        this.tourIndex = tourIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tour> lastShownTourList = model.getFilteredTourList();

        if (tourIndex.getZeroBased() >= lastShownTourList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TOUR_DISPLAYED_INDEX);
        }

        Tour tourToEdit = lastShownTourList.get(tourIndex.getZeroBased());

        if (!tourToEdit.isFavourite()) {
            throw new CommandException(MESSAGE_DUPLICATE_NON_FAVOURITE);
        }

        Tour editedTour = tourToEdit.setFavouriteStatus(new FavouriteStatus(VALID_FAVOURITE_STATUS_FALSE));

        model.setTour(tourToEdit, editedTour);
        model.commitAddressBook();
        logger.fine(String.format("Removed tour from favourites: %s", editedTour));
        model.updateFilteredTourList(PREDICATE_SHOW_ALL_TOURS);
        return new CommandResult(String.format(MESSAGE_REMOVE_FAVOURITE_SUCCESS, Messages.format(editedTour)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TourFavouriteRemoveCommand)) {
            return false;
        }

        TourFavouriteRemoveCommand otherCommand = (TourFavouriteRemoveCommand) other;
        return tourIndex.equals(otherCommand.tourIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tourIndex", tourIndex)
                .toString();
    }
}
