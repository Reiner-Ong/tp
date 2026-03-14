package seedu.address.model.contact;

import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.tag.Tag;


/**
 * Represents an Accommodation Contact in the address book.
 * Guarantees: default number of stars (THREE_STAR) if unspecified.
 */
public class Accommodation extends Contact {
    /**
     * Represents number of stars of the accommodation.
     */
    public enum AccommodationStar {
        ONE_STAR,
        TWO_STAR,
        THREE_STAR,
        FOUR_STAR,
        FIVE_STAR
    }
    private final AccommodationStar stars;

    /**
     * Constructs an {@code Accommodation} contact with default number of stars (THREE_STAR).
     */
    public Accommodation(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.stars = AccommodationStar.THREE_STAR;
    }

    /**
     * Constructs an {@code Accommodation} contact with specified Halal status.
     * @param stars The number of stars of the accommodation.
     */
    public Accommodation(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                         AccommodationStar stars) {
        super(name, phone, email, address, tags);
        this.stars = stars;
    }

    public AccommodationStar getStars() {
        return stars;
    }

    @Override
    public String getType() {
        return "Accommodation";
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editAccommodationDescriptor) {
        Name updatedName = editAccommodationDescriptor.getName().orElse(getName());
        Phone updatedPhone = editAccommodationDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editAccommodationDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editAccommodationDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editAccommodationDescriptor.getTags().orElse(getTags());
        AccommodationStar updatedStars = editAccommodationDescriptor.getStars().orElse(getStars());

        return new Accommodation(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedStars);
    }
}
