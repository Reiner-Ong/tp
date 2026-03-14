package seedu.address.model.contact;

import java.time.LocalTime;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.tag.Tag;


/**
 * Represents an Attraction Contact in the address book.
 * Guarantees: default operating hours (8am to 10pm) if unspecified.
 */
public class Attraction extends Contact {
    private final LocalTime openingHours;
    private final LocalTime closingHours;

    /**
     * Constructs an {@code Attraction} contact with default operating hours (8am to 10pm).
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.openingHours = LocalTime.of(8, 0);
        this.closingHours = LocalTime.of(22, 0);
    }

    /**
     * Constructs an {@code Attraction} contact with specified operating hours.
     * @param openingHours The opening hours of the attraction.
     * @param closingHours The closing hours of the attraction.
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                         LocalTime openingHours, LocalTime closingHours) {
        super(name, phone, email, address, tags);
        this.openingHours = openingHours;
        this.closingHours = closingHours;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public LocalTime getClosingHours() {
        return closingHours;
    }

    /**
     * Returns the operating (opening to closing) hours of the attraction.
     */
    public LocalTime[] getOperatingHours() {
        return new LocalTime[]{ this.openingHours, this.closingHours };
    }

    @Override
    public String getType() {
        return "Attraction";
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editAttractionDescriptor) {
        Name updatedName = editAttractionDescriptor.getName().orElse(getName());
        Phone updatedPhone = editAttractionDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editAttractionDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editAttractionDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editAttractionDescriptor.getTags().orElse(getTags());
        LocalTime updatedOpeningHours = editAttractionDescriptor.getOpeningHours().orElse(getOpeningHours());
        LocalTime updatedClosingHours = editAttractionDescriptor.getClosingHours().orElse(getClosingHours());

        return new Attraction(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedOpeningHours, updatedClosingHours);
    }
}
