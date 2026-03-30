package seedu.address.testutil;

import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.FavoriteStatus;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.HalalStatus;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;

import java.util.HashSet;

import static seedu.address.logic.commands.CommandTestUtil.VALID_HALAL_STATUS_FALSE_FNB;

/**
 * A utility class to help with building Fnb objects.
 */
public class FnbBuilder extends ContactBuilder {
    protected HalalStatus halalStatus;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public FnbBuilder() {
        super();
        this.halalStatus = new HalalStatus(VALID_HALAL_STATUS_FALSE_FNB);
    }

    /**
     * Sets the {@code HalalStatus} of the {@code Fnb} that we are building.
     */
    public FnbBuilder withHalalStatus(String halalStatus) {
        this.halalStatus = new HalalStatus(halalStatus);
        return this;
    }

    @Override
    public Contact build() {
        return new Fnb(name, phone, email, address, tags, halalStatus, tours, favoriteStatus);
    }
}
