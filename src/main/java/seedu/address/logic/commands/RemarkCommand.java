package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;


/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

  public static final String COMMAND_WORD = "remark";

  public static final String MESSAGE_USAGE = COMMAND_WORD
      + ": Edits the remark of the person identified "
      + "by the index number used in the last person listing. "
      + "Existing remark will be overwritten by the input.\n"
      + "Parameters: INDEX (must be a positive integer) "
      + "r/ [REMARK]\n"
      + "Example: " + COMMAND_WORD + " 1 "
      + "r/ Likes to swim.";

  public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";

  public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

  public static final String MESSAGE_REMARK_PERSON_SUCCESS = "Remark Person: %1$s";

  private final Index index;
  private final String remark;

  /**
   * @param index  of the person in the filtered person list to edit the remark
   * @param remark of the person to be updated to
   */
  public RemarkCommand(Index index, String remark) {
    requireAllNonNull(index, remark);

    this.index = index;
    this.remark = remark;
  }

  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);
    List<Person> lastShownList = model.getFilteredPersonList();

    if (index.getZeroBased() >= lastShownList.size()) {
      throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    Person personToRemark = lastShownList.get(index.getZeroBased());
    Person remarkedPerson = createRemarkedPerson(personToRemark, new Remark(remark));

    model.setPerson(personToRemark, remarkedPerson);
    model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    return new CommandResult(String.format(MESSAGE_REMARK_PERSON_SUCCESS, Messages.format(remarkedPerson)));
  }

  /**
   * Creates and returns a {@code Person} with the details of {@code personToRemark}
   * with the remark updated to {@code newRemark}.
   */
  private static Person createRemarkedPerson(Person personToRemark, Remark newRemark) {
    assert personToRemark != null;

    return new Person(
        personToRemark.getName(),
        personToRemark.getPhone(),
        personToRemark.getEmail(),
        personToRemark.getAddress(),
        personToRemark.getTags(),
        newRemark
    );
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof RemarkCommand)) {
      return false;
    }

    RemarkCommand e = (RemarkCommand) other;
    return index.equals(e.index)
        && remark.equals(e.remark);
  }
}
