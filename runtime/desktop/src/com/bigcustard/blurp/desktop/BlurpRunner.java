package com.bigcustard.blurp.desktop;

import com.badlogic.gdx.backends.lwjgl.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.bootstrap.*;
import org.kohsuke.args4j.*;

public class BlurpRunner {

    public static void main (String[] args) {
        // TODO - PHIL, see https://github.com/jruby/jruby/wiki/Embedding-with-JSR-223 as to why this is here
        // To discuss.
        System.setProperty("org.jruby.embed.localvariable.behavior", "transient");

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

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = options.width;
        config.height = options.height;
        config.samples= 1;

        BlurpApp blurpApp = new BlurpApp(options.language, options.scriptName, new FitViewport(options.width, options.height), new LwjglMouseWindowChecker());
        new LwjglApplication(blurpApp, config);
	}

    private static class CommandLineOptions {

        @Option(name="-language", aliases="-l", metaVar="language", usage="Language may be either \"Java\" or the name of a JVM scripting language. If omitted, the default is \"Java\"")
        private String language = "Java";

        @Option(name="-width", aliases="-w", metaVar="width", usage="The width in pixels of the window that Blurp will run your script in", depends="-height")
        private int width = 800;

        @Option(name="-height", aliases="-h", metaVar="height", usage="The height in pixels of the window that Blurp will run your script in", depends="-width")
        private int height = 600;

        @Argument(index=0, required=true, metaVar="script-name", usage="The filename of the script to run, or in the case of Java, the fully qualified name of the class to run, which must implement BlurpRunnable")
        private String scriptName;
    }

}
