import org.apache.commons.cli.*;

public class CommandLineArgumentParser {
    private String[] plugins;
    private String[] recipes;
    private boolean isDryRun;

    public CommandLineArgumentParser(String[] args) {
        parseCommandLine(args);
    }

    private void parseCommandLine(String[] args) {
        Options options = new Options();
        
        Option pluginsOption = Option.builder()
                .longOpt("plugins")
                .desc("Specify plugins")
                .hasArg()
                .argName("plugins")
                .required()
                .build();

        Option dryRun = Option.builder()
                .longOpt("dry-run")
                .desc("Dry run mode")
                .build();

        Option recipe = Option.builder()
                .longOpt("recipes")
                .desc("Specify recipes")
                .hasArgs()
                .argName("recipes")
                .build();

        options.addOption(pluginsOption);
        options.addOption(dryRun);
        options.addOption(recipe);

        CommandLineParser parser = new DefaultParser();

        try {
            
            CommandLine cmd = parser.parse(options, args);

            
            String pluginArg = cmd.getOptionValue("plugins");
            plugins = pluginArg.split(",");

            recipes = cmd.getOptionValues("recipes");
            isDryRun = cmd.hasOption("dry-run");

        } catch (ParseException e) {
            
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar plugin-modernizer.jar", options);
        }
    }

    public String[] getPlugins() {
        return plugins;
    }

    public String[] getRecipes() {
        return recipes;
    }

    public boolean isDryRun() {
        return isDryRun;
    }

    public static void main(String[] args) {
        CommandLineArgumentParser argumentParser = new CommandLineArgumentParser(args);

        String[] plugins = argumentParser.getPlugins();
        String[] recipes = argumentParser.getRecipes();
        boolean isDryRun = argumentParser.isDryRun();
        String iat = System.getenv("IAT");
        String GH_username = System.getenv("GITHUB_USERNAME");

        System.out.println("Plugins: " + String.join(", ", plugins));
        System.out.println("Dry run mode: " + isDryRun);
        if (recipes != null) {
            System.out.println("Recipes: " + String.join(", ", recipes));
        }
        System.out.println(iat);
        System.out.println(GH_username);
    }
}
