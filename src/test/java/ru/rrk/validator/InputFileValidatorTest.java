package ru.rrk.validator;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rrk.args.validators.InputFileValidator;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class InputFileValidatorTest {
    InputFileValidator validator = new InputFileValidator();
    String inputFileFlagName = null;
    @Mock
    File file = new File("/path");

    @Test
    public void provideOKFile_returnsOK() {
        doReturn(true)
                .when(file).exists();
        doReturn(true)
                .when(file).isFile();
        doReturn(true)
                .when(file).canRead();

        assertDoesNotThrow(() -> validator.validate(inputFileFlagName, file));
    }

    @Test
    public void provideFileThatIsNotExists_throwsParameterException() {
        doReturn(false)
                .when(file).exists();

        assertThrows(
                ParameterException.class,
                () -> validator.validate(inputFileFlagName, file),
                "File with path %s does noe exists".formatted(file.getPath())
        );
    }

    @Test
    public void provideFileThatIsNotFile_throwsParameterException() {
        doReturn(true)
                .when(file).exists();
        doReturn(false)
                .when(file).isFile();

        assertThrows(
                ParameterException.class,
                () -> validator.validate(inputFileFlagName, file),
                "Path %s passed is not a file".formatted(file.getPath())
        );
    }

    @Test
    public void provideUnReadableFile_throwsParameterException() {
        doReturn(true)
                .when(file).exists();
        doReturn(true)
                .when(file).isFile();
        doReturn(false)
                .when(file).canRead();

        assertThrows(
                ParameterException.class,
                () -> validator.validate(inputFileFlagName, file),
                "File %s is unreadable".formatted(file.getPath())
        );
    }
}
