package com.bigcustard.blurp.desktop;

import com.badlogic.gdx.backends.lwjgl.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import org.kohsuke.args4j.*;

public class BlurpRunner {

    private static final int VIEWPORT_WIDTH = 800;
    private static final int VIEWPORT_HEIGHT = 600;

    public static void main (String[] args) {
        // TODO - PHIL, see https://github.com/jruby/jruby/wiki/Embedding-with-JSR-223 as to why this is here
        // To discuss.
        System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
        System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");

        CommandLineOptions options = new CommandLineOptions();
        CmdLineParser parser = new CmdLineParser(options, ParserProperties.defaults().withUsageWidth(120).withOptionSorter(null));

        try {
            parser.parseArgument(args);
        } catch(CmdLineException e) {
            System.out.println(e.getMessage());
            System.out.println("\nUsage - BlurpRunner [Options...] script-name\n");
            parser.printUsage(System.out);
            System.exit(1);
        }

        handleMissingScriptFile(options);
        SupportedLanguage language = SupportedLanguages.forFile(options.scriptName);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = options.width;
        config.height = options.height;
        config.samples= 1;

        BlurpApp blurpApp = new BlurpApp(language, options.scriptName, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new LwjglMouseWindowChecker());
        new LwjglApplication(blurpApp, config);
	}

    private static void handleMissingScriptFile(CommandLineOptions options) {

        if(options.scriptName == null) {
            options.scriptName = FileSelector.selectFile();
            if(options.scriptName == null) {
                exit("Blurp cancelled by user");
            }
        }
    }

    private static void exit(String message) {

        System.out.println(message);
        System.exit(1);
    }

    private static class CommandLineOptions {

        @Option(name="-width", aliases="-w", metaVar="width", usage="The width in pixels of the window that Blurp will run your script in", depends="-height")
        private int width = VIEWPORT_WIDTH;

        @Option(name="-height", aliases="-h", metaVar="height", usage="The height in pixels of the window that Blurp will run your script in", depends="-width")
        private int height = VIEWPORT_HEIGHT;

        @Argument(index=0, metaVar="script-name", usage="The filename of either a script, a Java source file, a Java class file, or the fully qualified name of a Java class that is on the classpath. Java code must extend BlurpJavaProgram, and shouldn't have a package unless running from the classpath.")
        private String scriptName = null;
    }

}
