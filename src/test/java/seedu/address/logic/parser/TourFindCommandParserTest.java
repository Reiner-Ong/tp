package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tour.TourFindCommand;
import seedu.address.model.tour.TourNameContainsKeywordsPredicate;

public class TourFindCommandParserTest {

    private TourFindCommandParser parser = new TourFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // equivalence partition: empty arg
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_blankArg_throwsParseException() {
        // equivalence partition: blank arg
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validKeyword_returnsTourFindCommand() {
        // no leading and trailing whitespaces
        TourFindCommand expectedTourFindCommand =
                new TourFindCommand(new TourNameContainsKeywordsPredicate(Arrays.asList("James")));
        assertParseSuccess(parser, "James", expectedTourFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t  \n \t  James \n", expectedTourFindCommand);
    }

    @Test
    public void parse_singleKeyword_returnsTourFindCommand() {
        // single keyword
        TourFindCommand expectedTourFindCommand =
                new TourFindCommand(new TourNameContainsKeywordsPredicate(Arrays.asList("Walking")));
        assertParseSuccess(parser, "Walking", expectedTourFindCommand);

        // single keyword with multiple leading and trailing whitespaces
        assertParseSuccess(parser, " \n \t  Walking  \t\n", expectedTourFindCommand);
    }

    @Test
    public void parse_multipleKeywords_returnsTourFindCommand() {
        // multiple keywords
        TourFindCommand expectedTourFindCommand =
                new TourFindCommand(new TourNameContainsKeywordsPredicate(Arrays.asList("Le", "Tour", "James")));
        assertParseSuccess(parser, "Le Tour James", expectedTourFindCommand);

        // multiple keywords with various whitespaces
        assertParseSuccess(parser, "\n    \t   \t\n Le \t\t \n   \t \n  Tour \t \n   \t James \t  \n\t",
                expectedTourFindCommand);
    }

}
