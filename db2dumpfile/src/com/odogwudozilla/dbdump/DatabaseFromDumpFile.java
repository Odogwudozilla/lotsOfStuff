package com.odogwudozilla.dbdump;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class DatabaseFromDumpFile {

	private final static Logger log = Logger.getLogger(DatabaseFromDumpFile.class.getName());

	// define the variable for getting user input
	static Scanner userInput = new Scanner(System.in);
	public final String SCHEMA_NAME = "QIS";
	public final String ATTACH_TO_DB2 = "attach to db2 user db2inst1 using db2inst1;";
	public final String CONNECT_TO_DATABASE_NAME = " user db2inst1 using db2inst1";
	public final String DB2MOVE_DATABASE_NAME_LOAD = " -u db2inst1 -p db2inst1";
	public final String DEFAULT_FILE_NAME = "db2dump.dmp";
	public final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
	// The list of the contents of the source file
	private static List<String> fileContent = new ArrayList<>();

	/**
	 * The directory containing the dump file to be processed.
	 */
	private File inputDirectory;

	private String databaseLocation;
	private String databaseName;
	private String tableSpacePath;
	private String defaultTableSpacePath;
	private String absoluteDumpFilePath;

	//Main Method
	public static void main(String[] args) throws IOException, ParseException {
		DatabaseFromDumpFile databaseFromDumpFile = new DatabaseFromDumpFile();
		log.setLevel(Level.INFO);

		// Process all commandline data set input data
		if (!databaseFromDumpFile.handleCommandLineArguments(args)) {
			// The error has been logged.
			pressQToExit();
			return;
		}

		// Check the file for a previous processing.
		Optional<String> fileAlreadyUpdated = databaseFromDumpFile.getFileContent().stream().filter(x -> x.contains("attach to db2")).findFirst();
		// Do not perform the update if the file already has been previously updated.
		if (fileAlreadyUpdated.isPresent()) {
			log.warning("The dmp file has already been updated. Exiting...");
			pressQToExit();
		}

		try {
			// Process the dump file.
			processDumpFile(databaseFromDumpFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Write the file contents.
		Files.write(databaseFromDumpFile.getAbsoluteDumpFilePath(), databaseFromDumpFile.getFileContent(), StandardCharsets.UTF_8);
		log.info("Database Dump file successfully processed");
		shutDownInSeconds(10);
	}

	/**
	 * sleeps the program for the stated number of seconds
	 *
	 * @param inSeconds number of seconds the program sleeps for
	 */
	public static void iSleep(int inSeconds) {
		try {
			TimeUnit.SECONDS.sleep(inSeconds);
		} catch (InterruptedException ex) {
			log.severe("The sleep was interrupted");
			Thread.currentThread().interrupt();
		}
	}

	private static void pressQToExit() {
		int counter = 3;
		while (counter > 0) {
			log.info("Press 'q' to exit");
			String lastWord = new Scanner(System.in).nextLine();
			if ("q".equalsIgnoreCase(lastWord)) {
				System.exit(1);
			}
			counter--;
		}
	}

	private static void shutDownInSeconds(int seconds) {
		System.out.print("Shutting down...");

		while (seconds > 0) {
			System.out.print(seconds + "...");
			iSleep(1);
			seconds--;
		}
	}

	private static void processDumpFile(DatabaseFromDumpFile databaseFromDumpFile) throws IOException {
		databaseFromDumpFile.replaceTargetDirectory();
		iSleep(2);
		databaseFromDumpFile.replaceDbNameAndSchema();
		iSleep(2);
		databaseFromDumpFile.replaceTableSpacePath();
		iSleep(2);
		databaseFromDumpFile.appendAttachToDb2();
		iSleep(2);
		databaseFromDumpFile.appendToConnectToDbName();
		iSleep(2);
		databaseFromDumpFile.appendToDb2Move();
		iSleep(2);
	}


	/**
	 * Replaces the target directory in the file with the chosen one
	 */
	public void replaceTargetDirectory() {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : getFileContent()) {
			if (line.contains("ON <directory-waar-db-komt-te-staan>")) {
				//first check if the default location is chosen
				if (("C:\\DB2\\NODE0000\\").equals(getDatabaseLocation())) {
					//delete the string
					eachFileLine.add(line.replace("ON <directory-waar-db-komt-te-staan>", ""));
					log.info("\"ON <directory-waar-db-komt-te-staan>\" replaced with empty string");
				} else {
					//replace with the custom target directory
					eachFileLine.add(line.replace("<directory-waar-db-komt-te-staan>", getDatabaseLocation()));
					log.info("\"<directory-waar-db-komt-te-staan>\" replaced with " + getDatabaseLocation());
				}
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
	}

	/**
	 * Replace the DB name and Schema as chosen by the user
	 */
	public void replaceDbNameAndSchema() {
		List<String> eachFileLine = new ArrayList<>();
		int dbCount = 0;
		int schemaCount = 0;
		for (String line : getFileContent()) {
			if (line.contains("__DATABASE__")) {
				//replace the databaseName
				eachFileLine.add(line.replace("__DATABASE__", getDatabaseName()));
				log.info("__DATABASE__ replaced with '" + getDatabaseName() + "'");
				dbCount++;
			} else if (line.contains("__SCHEMA__")) {
				//replace the schema
				eachFileLine.add(line.replace("__SCHEMA__", SCHEMA_NAME));
				log.info("__SCHEMA__ replaced with '" + SCHEMA_NAME + "'");
				schemaCount++;
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
		log.info("A total of " + dbCount + " DB Names and " + schemaCount + " Schema names were replaced");
	}

	/**
	 * Replaces the tableSpace path
	 */
	public void replaceTableSpacePath() {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : getFileContent()) {
			if (line.contains("/data/db2inst1/NODE0000/" + getDatabaseName() + "/")) {
				//replace the tableSpacePath in the file
				eachFileLine.add(line.replace("/data/db2inst1/NODE0000/" + getDatabaseName() + "/", getTableSpacePath()));
				log.info("'" + getDefaultTableSpacePath() + "' replaced with '" + getTableSpacePath() + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
	}

	/**
	 * Append additional text to the 'create database'
	 */
	public void appendAttachToDb2() {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : getFileContent()) {
			if (line.contains("create database")) {
				//append by replacing all string
				eachFileLine.add(line.replace("create database", ATTACH_TO_DB2 + "\ncreate database"));
				log.info("'" + ATTACH_TO_DB2 + "' appended");
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
	}

	/**
	 * Append additional text to the 'connect to'
	 */
	public void appendToConnectToDbName() {
		String connectToDbName = "connect to " + getDatabaseName();
		List<String> eachFileLine = new ArrayList<>();
		for (String line : getFileContent()) {
			if (line.contains(connectToDbName)) {
				//append by replacing all string
				eachFileLine.add(line.replace(connectToDbName, connectToDbName + CONNECT_TO_DATABASE_NAME));
				log.info("'" + CONNECT_TO_DATABASE_NAME + "' appended to '" + connectToDbName + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
	}

	/**
	 * Append additional text to the 'db2move...'
	 */
	public void appendToDb2Move() {
		String db2Move = "db2move " + getDatabaseName() + " load";
		List<String> eachFileLine = new ArrayList<>();
		for (String line : getFileContent()) {
			if (line.contains(db2Move)) {
				//append by replacing all string
				eachFileLine.add(line.replace(db2Move, db2Move + DB2MOVE_DATABASE_NAME_LOAD));
				log.info("'" + DB2MOVE_DATABASE_NAME_LOAD + "' appended to '" + db2Move + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		// Replace the content with the new changes
		fileContent = eachFileLine;
	}


	/**
	 * @param args arguments from the command line.
	 * @return {@code true} if arguments successfully processed {@code false} otherwise.
	 */
	private boolean handleCommandLineArguments(String[] args) throws ParseException, IOException {
		// Definition of the options.
		Options commandLineOptions = new Options();
		commandLineOptions.addOption("h", "help", false, "help info");
		commandLineOptions.addOption("i", "input-directory", true, "input directory of the dump file. Please enter the full absolute path to the directory");
		commandLineOptions.addOption("f", "input-file-name", true, "input file name e.g. \"db2dump.dmp\". This is only necessary if the dmp file is named differently than the default");
		commandLineOptions.addOption("d", "use-default-database-location", false, "include this param to use the default database location");
		commandLineOptions.addOption("n", "use-default-database-name", false, "include this param to use the default database name");

		commandLineOptions.getOption("i").setType(File.class);
		commandLineOptions.getOption("f").setType(String.class);
		commandLineOptions.getOption("d").setType(String.class);
		commandLineOptions.getOption("n").setType(String.class);

		// Parsing of the arguments using the options.
		CommandLineParser commandLineParser = new DefaultParser();
		CommandLine commandLineArgs;
		try {
			commandLineArgs = commandLineParser.parse(commandLineOptions, args);
		} catch (ParseException e) {
			new HelpFormatter().printHelp(getClass().getSimpleName(), commandLineOptions, true);
			log.severe("Exception while parsing command line.");
			return false;
		}

		Option helpOption = commandLineOptions.getOption("h");
		// When the user includes the 'help' option
		if (commandLineArgs.hasOption(helpOption.getOpt())) {
			// Display all options and return false (so we exit).
			new HelpFormatter().printHelp(getClass().getSimpleName(), commandLineOptions, true);
			return false;
		}

		if (!commandLineArgs.hasOption("i")){
			inputDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toFile();
			if (!inputDirectory.isDirectory()) {
				log.info("Input file directory '" + inputDirectory + "' is invalid");
				new HelpFormatter().printHelp(getClass().getSimpleName(), commandLineOptions, true);
				return false;
			}
		}

		if (commandLineArgs.hasOption("i")) {
			inputDirectory = (File) commandLineArgs.getParsedOptionValue("i");
			// Perform validation on the input directory
			if (!inputDirectory.exists()) {
				log.severe("Input file directory not found: " + inputDirectory.getAbsolutePath());
				return false;
			}

			if (!inputDirectory.isDirectory()) {
				log.severe("Input path should be a directory, e.g. \"C:\\Blablabla\"");
				return false;
			}
		}
		log.info("Input file directory is '" + inputDirectory);

		// Name of an individual dump file to be processed.
		String inputFileName;
		if (commandLineArgs.hasOption("f")) {
			inputFileName = (String) commandLineArgs.getParsedOptionValue("f");
		} else {
			inputFileName = DEFAULT_FILE_NAME;
		}

		String dumpfileFullPath = inputDirectory.toString() + FILE_SEPARATOR + inputFileName;
		if (!determineAbsoluteDumpFilePath(dumpfileFullPath)) {
			// The absolute file path cannot be determined.
			log.severe("The path to the dmp file cannot be determined");
			return false;
		}
		if (!prepareAndBackupDmpFile()) {
			// Error handled in method
			return false;
		}

		if (commandLineArgs.hasOption("d")) {
			setDatabaseLocation("C:\\DB2\\NODE0000\\");
		} else {
			log.info("Please enter the location you want to use for the database (e.g. 'C:\\DB2\\NODE0000\\'): ");
			String answer = userInput.next();
			if (!answer.endsWith(FILE_SEPARATOR)) {
				answer += FILE_SEPARATOR;
			}
			if (!isValidPath(answer)){
				log.severe("Invalid database location. Rerun the program with arg '-d' to use the default DB location");
				return false;
			}
			setDatabaseLocation(answer);
		}
		log.info("Your chosen DB location is: " + getDatabaseLocation());

		if (commandLineArgs.hasOption("n")) {
			setDatabaseName("QISST");
		} else {
			log.info("Please enter the DB name you want to use: ");
			setDatabaseName(userInput.next().toUpperCase());
		}
		log.info("Your chosen DB name is: " + getDatabaseName());
		//set the tableSpacePath
		setTableSpacePath(getDatabaseLocation() + getDatabaseName() + FILE_SEPARATOR);
		setDefaultTableSpacePath("\\data\\db2inst1\\NODE0000\\" + getDatabaseName() + FILE_SEPARATOR);
		log.info("Your tablespace path is: " + getTableSpacePath());
		log.info("The default tablespace path is: " + getDefaultTableSpacePath());


		return true;
	}

	private boolean determineAbsoluteDumpFilePath(String dumpFileArg) {

		if (dumpFileArg.isEmpty()) return false;
		//verify that the right file extension is in the supplied path
		boolean isDmp = dumpFileArg.endsWith(".dmp");
		// we have 'write' permissions
		boolean pathExistsAndWritable = Files.isWritable(Path.of(dumpFileArg));

		if (!(isDmp && pathExistsAndWritable)) {
			log.severe("Wrong file or insufficient permissions. Please check the file.");
			return false;
		}

		setAbsoluteDumpFilePath(dumpFileArg);
		log.info("The absolute path is " + getAbsoluteDumpFilePath());
		return true;

	}

	private boolean prepareAndBackupDmpFile() throws IOException {

		Path source = getAbsoluteDumpFilePath();
		// Set the name of the backup of the file
		String targetName = source.toString().replaceFirst("[.][^.]+$", "") + "_backup.dmp";
		Path targetFile = Path.of(targetName);
		//make a backup of the file if it does not already exist.
		if (Files.notExists(targetFile)) {
			log.info("Copying " + source + " to " + targetName);
			Files.copy(source, targetFile, StandardCopyOption.REPLACE_EXISTING);
		}

		// Read the file
		fileContent = Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8);
		if(fileContent.isEmpty()) {
			log.severe("The file content should not be empty at this point");
			return false;
		}

		return true;
	}


	public static boolean isValidPath(String path) {
		try {
			return Paths.get(path).isAbsolute();
		} catch (InvalidPathException | NullPointerException ex) {
			return false;
		}
	}


	public String getDatabaseLocation() {
		return databaseLocation;
	}

	public void setDatabaseLocation(String databaseLocation) {
		this.databaseLocation = databaseLocation;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableSpacePath() {
		return tableSpacePath;
	}

	public void setTableSpacePath(String tableSpacePath) {
		this.tableSpacePath = tableSpacePath;
	}

	public String getDefaultTableSpacePath() {
		return defaultTableSpacePath;
	}

	public void setDefaultTableSpacePath(String defaultTableSpacePath) {
		this.defaultTableSpacePath = defaultTableSpacePath;
	}

	public Path getAbsoluteDumpFilePath() {
		return Path.of(absoluteDumpFilePath);
	}

	public void setAbsoluteDumpFilePath(String absoluteDumpFilePath) {
		this.absoluteDumpFilePath = absoluteDumpFilePath;
	}

	public List<String> getFileContent() {
		return fileContent;
	}

}