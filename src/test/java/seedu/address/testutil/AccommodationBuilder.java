package seedu.address.testutil;

import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.OpeningHour;

import static seedu.address.logic.commands.CommandTestUtil.VALID_STARS_DEFAULT_ACCOMMODATION;

/**
 * A utility class to help with building Accommodation objects.
 */
public class AccommodationBuilder extends ContactBuilder {
    protected AccommodationStars stars;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public AccommodationBuilder() {
        super();
        this.stars = new AccommodationStars(VALID_STARS_DEFAULT_ACCOMMODATION);
    }

    /**
     * Sets the {@code AccommodationStars} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withStars(String stars) {
        this.stars = new AccommodationStars(stars);
        return this;
    }

    @Override
    public Contact build() {
        return new Accommodation(name, phone, email, address, tags, stars, tours, favoriteStatus);
    }
}
