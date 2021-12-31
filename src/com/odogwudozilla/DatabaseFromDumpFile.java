package com.odogwudozilla;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DatabaseFromDumpFile {

	// define the variable for getting user input
	static Scanner userInput = new Scanner(System.in);
	public final String SCHEMA_NAME = "QIS";
	public final String ATTACH_TO_DB2 = "attach to db2 user db2inst1 using db2inst1;";
	public final String CONNECT_TO_DATABASE_NAME = " user db2inst1 using db2inst1";
	public final String DB2MOVE_DATABASE_NAME_LOAD = " -u db2inst1 -p db2inst1";
	/**
	 * Determines the absolute path of the dumpfile
	 *
	 * @throws IOException
	 */
	int loopCount = 3;
	private String databaseLocation;
	private String databaseName;
	private String tableSpacePath;
	private String defaultTableSpacePath;
	private String absoluteDumpFilePath;

	//Main Method
	public static void main(String[] args) {
		DatabaseFromDumpFile df = new DatabaseFromDumpFile();

		try {
			df.determineAbsoluteDumpFilePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		df.determineDatabaseLocation();
		df.determineDatabaseName();
		try {
			df.replaceTargetDirectory();
			iSleep(2);
			df.replaceDbNameAndSchema();
			iSleep(2);
			df.replaceTableSpacePath();
			iSleep(2);
			df.appendAttachToDb2();
			iSleep(2);
			df.appendToConnectToDbName();
			iSleep(2);
			df.appendToDb2Move();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			System.out.println("The sleep was interrupted");
			Thread.currentThread().interrupt();
		}
	}

	public void determineAbsoluteDumpFilePath() throws IOException {
		while (loopCount > 0) {
			System.out.println("Please input the absolute path of the dump file (eg: C:\\blablabla\\db2dump.dmp)");
			String userAnswer = userInput.nextLine();
			//verify that the right file extension is in the supplied path
			boolean isDmp = userAnswer.endsWith(".dmp");
			boolean pathExistsAndWritable = Files.isWritable(Path.of(userAnswer));

			if (isDmp && pathExistsAndWritable) {
				setAbsoluteDumpFilePath(userAnswer);
				Path source = getAbsoluteDumpFilePath();
				// Set the name of the backup of the file
				String targetName = source.toString().replaceFirst("[.][^.]+$", "") + "_backup.dmp";
				Path targetFile = Path.of(targetName);
				System.out.println("Copying " + source + " to " + targetName);
				//make a backup of the file
				Files.copy(source, targetFile, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("The absolute path is " + getAbsoluteDumpFilePath());
				// Reset the count
				loopCount = 0;
				break;
			}
			loopCount--;

			if (loopCount == 0) {
				System.out.println("The supplied path is incorrect or the file does not exist on the location. Try again");

				System.out.println("We can do this forever, but we won't. Quitting....");
				iSleep(3);

				System.exit(1);

			}
		}


	}


	/**
	 * Determines the location for the new database
	 */
	public void determineDatabaseLocation() {
		System.out.println("Do you want to to use the default location \"C:\\DB2\\NODE0000\\\"? (0 = NO , any other key = YES)");
		String userAnswer = userInput.next();
		if (userAnswer.equals("0")) {
			System.out.println("Please enter the location you want to use: ");
			setDatabaseLocation(userInput.next());
		} else {
			setDatabaseLocation("C:\\DB2\\NODE0000\\");
		}
		System.out.println("Your chosen DB location is: " + getDatabaseLocation());
	}

	/**
	 * Sets the preferred database name
	 */
	public void determineDatabaseName() {
		System.out.println("Do you want to use the default DB name  \"QISST\"? (0 = NO , any other key = YES)");
		String userAnswer = userInput.next();
		if (userAnswer.equals("0")) {
			System.out.println("Please enter the DB name you want to use: ");
			setDatabaseName(userInput.next());
		} else {
			setDatabaseName("QISST");
		}
		System.out.println("Your chosen DB name is: " + getDatabaseName());
		//set the tableSpacePath
		setTableSpacePath(getDatabaseLocation() + getDatabaseName() + "\\");
		setDefaultTableSpacePath("\\data\\db2inst1\\NODE0000\\" + getDatabaseName() + "\\");
		System.out.println("Your tablespace path is: " + getTableSpacePath());
		System.out.println("The default tablespace path is: " + getDefaultTableSpacePath());

	}

	/**
	 * Replaces the target directory in the file with the chosen one
	 *
	 * @throws IOException
	 */
	public void replaceTargetDirectory() throws IOException {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains("ON <directory-waar-db-komt-te-staan>")) {
				//first check if the default location is chosen
				if (("C:\\DB2\\NODE0000\\").equals(getDatabaseLocation())) {
					//delete the string
					eachFileLine.add(line.replace("ON <directory-waar-db-komt-te-staan>", ""));
					System.out.println("\"ON <directory-waar-db-komt-te-staan>\" replaced with empty string");
				} else {
					//replace with the custom target directory
					eachFileLine.add(line.replace("<directory-waar-db-komt-te-staan>", getDatabaseLocation()));
					System.out.println("\"<directory-waar-db-komt-te-staan>\" replaced with " + getDatabaseLocation());
				}
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
	}

	/**
	 * Replace the DB name and Schema as chosen by the user
	 *
	 * @throws IOException
	 */
	public void replaceDbNameAndSchema() throws IOException {
		List<String> eachFileLine = new ArrayList<>();
		int dbCount = 0;
		int schemaCount = 0;
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains("__DATABASE__")) {
				//replace the databaseName
				eachFileLine.add(line.replace("__DATABASE__", getDatabaseName()));
				System.out.println("__DATABASE__ replaced with '" + getDatabaseName() + "'");
				dbCount++;
			} else if (line.contains("__SCHEMA__")) {
				//replace the schema
				eachFileLine.add(line.replace("__SCHEMA__", SCHEMA_NAME));
				System.out.println("__SCHEMA__ replaced with '" + SCHEMA_NAME + "'");
				schemaCount++;
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
		System.out.println("A total of " + dbCount + " DB Names and " + schemaCount + " Schema names were replaced");
	}

	/**
	 * Replaces the tableSpace path
	 *
	 * @throws IOException
	 */
	public void replaceTableSpacePath() throws IOException {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains("/data/db2inst1/NODE0000/" + getDatabaseName() + "/")) {
				//replace the tableSpacePath in the file
				eachFileLine.add(line.replace("/data/db2inst1/NODE0000/" + getDatabaseName() + "/", getTableSpacePath()));
				System.out.println("'" + getDefaultTableSpacePath() + "' replaced with '" + getTableSpacePath() + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
	}

	/**
	 * Append additional text to the 'create database'
	 *
	 * @throws IOException
	 */
	public void appendAttachToDb2() throws IOException {
		List<String> eachFileLine = new ArrayList<>();
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains("create database")) {
				//append by replacing all string
				eachFileLine.add(line.replace("create database", ATTACH_TO_DB2 + "\ncreate database"));
				System.out.println("'" + ATTACH_TO_DB2 + "' appended");
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
	}

	/**
	 * Append additional text to the 'connect to'
	 *
	 * @throws IOException
	 */
	public void appendToConnectToDbName() throws IOException {
		String connectToDbName = "connect to " + getDatabaseName();
		List<String> eachFileLine = new ArrayList<>();
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains(connectToDbName)) {
				//append by replacing all string
				eachFileLine.add(line.replace(connectToDbName, connectToDbName + CONNECT_TO_DATABASE_NAME));
				System.out.println("'" + CONNECT_TO_DATABASE_NAME + "' appended to '" + connectToDbName + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
	}

	/**
	 * Append additional text to the 'db2move...'
	 *
	 * @throws IOException
	 */
	public void appendToDb2Move() throws IOException {
		String db2Move = "db2move " + getDatabaseName() + " load";
		List<String> eachFileLine = new ArrayList<>();
		for (String line : Files.readAllLines(getAbsoluteDumpFilePath(), StandardCharsets.UTF_8)) {
			if (line.contains(db2Move)) {
				//append by replacing all string
				eachFileLine.add(line.replace(db2Move, db2Move + DB2MOVE_DATABASE_NAME_LOAD));
				System.out.println("'" + DB2MOVE_DATABASE_NAME_LOAD + "' appended to '" + db2Move + "'");
			} else {
				eachFileLine.add(line);
			}
		}
		Files.write(getAbsoluteDumpFilePath(), eachFileLine, StandardCharsets.UTF_8);
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

}