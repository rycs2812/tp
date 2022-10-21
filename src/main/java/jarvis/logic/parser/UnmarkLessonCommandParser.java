package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;

import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.UnmarkLessonCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkLessonCommand object
 */
public class UnmarkLessonCommandParser implements Parser<UnmarkLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkLessonCommand
     * and returns a UnmarkLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkLessonCommand.MESSAGE_USAGE));
        }

        Index lessonIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LESSON_INDEX).get());

        return new UnmarkLessonCommand(lessonIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

