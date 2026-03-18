package seedu.address.testutil;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Person;

import java.util.Collections;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder extends ContactBuilder {
    @Override
    public Contact build() {
        return new Person(name, phone, email, address, tags, Collections.emptySet());
    }
}
