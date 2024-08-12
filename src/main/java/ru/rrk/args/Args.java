package ru.rrk.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rrk.args.converters.FileConverter;
import ru.rrk.args.converters.PathConverter;
import ru.rrk.args.validators.InputFileValidator;
import ru.rrk.args.validators.OutputPathValidator;
import ru.rrk.args.validators.PrefixValidator;
import ru.rrk.args.validators.TwoFormOfStatValidator;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс, хранящий в себе аргументы, полученные при запуске программы.
 */
@Getter
@Setter
@ToString
@Parameters(parametersValidators = TwoFormOfStatValidator.class)
public class Args {
    @Parameter(
            names = {"-h", "--help"},
            description = "Calls for help",
            help = true
    )
    private boolean helpCalled;

    @Parameter(
            names = {"-o", "--output"},
            description = "Sets the path for saving the results of program execution",
            converter = PathConverter.class,
            validateWith = OutputPathValidator.class
    )
    private Path output;

    @Parameter(
            names = {"-p", "--prefix"},
            description = "Sets a prefix for program execution result files.",
            validateWith = PrefixValidator.class
    )
    private String prefix;

    @Parameter(
            names = {"-a", "--appendable"},
            description = "Sets the append mode to existing files"
    )
    private boolean appendable;

    @Parameter(
            names = "-s",
            description = "Sets the short form of statistics"
    )
    private boolean shortStatisticsForm;

    @Parameter(
            names = "-f",
            description = "Sets the full form of statistics"
    )
    private boolean fullStatisticsForm;

    @Parameter(
            converter = FileConverter.class,
            validateWith = InputFileValidator.class
    )
    private List<File> files;
}
