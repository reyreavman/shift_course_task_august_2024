package ru.rrk.validator;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.rrk.args.validators.OutputPathValidator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.powermock.api.mockito.PowerMockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Files.class)
public class OutputPathValidatorTest {
    OutputPathValidator validator = new OutputPathValidator();
    String outputPathFlagName = "-o";

    @Test
    public void providePathThatIsNotADirectory_throwsParameterException() {
        String input = "/dasdasdasd";

        assertThrows(
                ParameterException.class,
                () -> validator.validate(outputPathFlagName, input),
                "Parameter %s contains a string that is not a directory.".formatted(outputPathFlagName)
        );
    }

    @Test
    public void providePathThatIsNotWritable_throwsParameterException() {
        PowerMockito.mockStatic(Files.class);
        String input = "/path";
        Path path = Paths.get(input);

        doReturn(true)
                .when(Files.isDirectory(path));
        doReturn(false)
                .when(Files.isWritable(path));

        assertThrows(
                ParameterException.class,
                () -> validator.validate(outputPathFlagName, input),
                "Parameter %s contains a directory that is not writable."
        );
    }

    @Test
    public void providePath_returnsOK() {
        PowerMockito.mockStatic(Files.class);
        String input = "/path";
        Path path = Paths.get(input);

        Mockito.when(Files.isDirectory(path)).thenReturn(true);
        Mockito.when(Files.isWritable(path)).thenReturn(true);

        assertDoesNotThrow(() -> validator.validate(outputPathFlagName, input));
    }
}
