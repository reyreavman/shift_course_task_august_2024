package ru.rrk.args;

import com.beust.jcommander.JCommander;

/**
 * Класс ловит случай, когда передан флаг для справки, вызывает справку и завершает программу
 */
public class ArgsHandler {
    public static void parse(Args args, JCommander commander, String[] appArgs) {
        commander.parse(appArgs);
        if (args.isHelpCalled()) {
            System.out.println("""
                                     \s
                     Help parameter specified.
                     Other parameters are no longer checked for their presence.
                    \s""");
            commander.usage();
            System.exit(0);
        }
    }
}
