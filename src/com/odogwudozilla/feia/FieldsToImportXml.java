package com.odogwudozilla.feia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FieldsToImportXml {

	private static final String filepath = "C:\\Users\\cnnachor\\IdeaProjects\\src\\com\\odogwudozilla\\feia\\fields.csv";
	private static final String border = "=========================================================";
	private static final Integer dbName = 0;
	private static final Integer prefix = 1;
	private static final Integer xmlName = 2;
	private static final Integer dataType = 3;
	private static final Integer path = 4;
	private static final Integer path2 = 5;
	private static final Integer path3 = 6;
	private static final Integer path4 = 7;
	private static ArrayList<ArrayList<String>> fileContent;

	public static void main(String[] args) {
		fileContent = csvToArrayList();

		//testOutput();

		printUpperCaseDM2FieldNames();
		//printCamelCaseFieldNames();
		//printClassVariables();

		//generateNachrichtToEntityCode();
		//constructInsertSql();
		//constructSelectClause();
		//constructUpdateSql();
		//moveRowToInstanceVars();
		//moveInstanceVarsToParameters();
		//copyFrom();

		//createDbChangelogFile();

		//createValueObject();
		//createValueObjectMapper();
		//createViewModelSection();
	}

	private static ArrayList<ArrayList<String>> csvToArrayList() {
		fileContent = new ArrayList<>();
		try {
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				String[] split = line.split("\t");
				ArrayList<String> lineMap = new ArrayList<>(Arrays.asList(split));
				fileContent.add(lineMap);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return fileContent;
	}

	private static void testOutput() {
		System.out.println(border);
		for (ArrayList<String> line : fileContent) {
			for (String column : line) {
				System.out.print(column);
				System.out.print(",");
			}
			System.out.print("\n");
		}
		System.out.println(border);
	}

	private static void printClassVariables() {
		System.out.println(border);
		for (ArrayList<String> line : fileContent) {
			System.out.println("private " + line.get(dataType) + " " + line.get(prefix) + line.get(dbName) + ";");
		}
		System.out.println(border);
	}

	private static void printCamelCaseFieldNames() {
		System.out.println(border);
		for (ArrayList<String> line : fileContent) {
			System.out.println(line.get(dbName).substring(0, 1).toUpperCase() + line.get(0).substring(1));
		}
		System.out.println(border);
	}

	private static void printUpperCaseDM2FieldNames() {
		System.out.println(border);
		for (ArrayList<String> line : fileContent) {
			System.out.println("addAxonColumn(\"" + line.get(prefix) + line.get(dbName).toUpperCase() + "\", true, false);");
		}
		System.out.println(border);
	}

	private static void generateNachrichtToEntityCode() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {

			String code;

			code = line.get(prefix) + line.get(dbName);
			code += " = nachricht";

			if (line.size() >= path2 + 1) {
				code += ".get" + line.get(path2) + "()";
			}
			if (line.size() >= path3 + 1) {
				code += ".get" + line.get(path3) + "()";
			}
			if (line.size() >= path4 + 1) {
				code += ".get" + line.get(path4) + "()";
			}

			if (line.get(dataType).equals("String[]")) {
				code += ".toArray(new String[0]);";
			} else {
				code += ".get" + line.get(xmlName) + "();";
			}

			System.out.println(code);
		}
		System.out.println(border);

	}

	private static void constructInsertSql() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			System.out.println("+ \"" + line.get(prefix) + line.get(dbName) + ", \"");

		}
		System.out.println("\") values ( \"");
		for (ArrayList<String> line : fileContent) {
			System.out.println("+ \":" + line.get(prefix) + line.get(dbName) + ", \"");

		}

		System.out.println(border);
	}

	private static void constructSelectClause() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			System.out.println("+ \"LlmvMessageEvbMessage." + line.get(prefix) + line.get(dbName) + ", \"");
		}

		System.out.println(border);
	}

	private static void constructUpdateSql() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			System.out.println("+ \"" + line.get(prefix) + line.get(dbName) + " = :" + line.get(prefix) + line.get(dbName) + ", \"");
		}

		System.out.println(border);
	}

	private static void moveRowToInstanceVars() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			if (line.get(dataType).equals("String")) {
				String code = "";

				code += line.get(prefix) + line.get(dbName);
				code += " = resultSet.getString(\"";
				code += line.get(prefix) + line.get(dbName);
				code += "\");";

				System.out.println(code);
			} else {
				String code = "";

				code += line.get(prefix) + line.get(dbName);
				code += " = dbStringToList(resultSet.getString(\"";
				code += line.get(prefix) + line.get(dbName);
				code += "\"));";

				System.out.println(code);
			}
		}
		System.out.println(border);
	}

	private static void moveInstanceVarsToParameters() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			if (line.get(dataType).equals("String")) {
				String code = "";

				code += "helper.setString(statement, \"";
				code += line.get(prefix) + line.get(dbName);
				code += "\", ";
				code += line.get(prefix) + line.get(dbName);
				code += ");";

				System.out.println(code);
			} else {
				String code = "";

				code += "helper.setString(statement, \"";
				code += line.get(prefix) + line.get(dbName);
				code += "\", listToDbString(";
				code += line.get(prefix) + line.get(dbName);
				code += "));";

				System.out.println(code);
			}
		}
		System.out.println(border);
	}

	private static void copyFrom() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			System.out.println(
					line.get(prefix) + line.get(dbName)
							+ " = llmvMessageEvbMessage."
							+ line.get(prefix) + line.get(dbName)
							+ ";");
		}
		System.out.println(border);
	}

	private static void createDbChangelogFile() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {
			if (line.get(dataType).equals("String")) {
				System.out.println("<column name=\"" + line.get(prefix) + line.get(dbName) + "\" type=\"qis.vargraphic(80)\"/>");
			} else {
				System.out.println("<column name=\"" + line.get(prefix) + line.get(dbName) + "\" type=\"qis.dbclob(1048576)\"/>");
			}
		}
		System.out.println(border);
	}

	private static void createValueObject() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {

			System.out.println("@FunctionalTypeText");
			System.out.println("public String " + line.get(prefix) + line.get(dbName) + ";");
			System.out.println();

		}
		System.out.println(border);
	}

	private static void createValueObjectMapper() {
		System.out.println(border);

		for (ArrayList<String> line : fileContent) {

			String str = line.get(prefix) + line.get(dbName);
			String cap = str.substring(0, 1).toUpperCase() + str.substring(1);

			System.out.println("valueObject." + line.get(prefix) + line.get(dbName)
					+ " = evbMessage.get" + cap + "();");

		}
		System.out.println(border);
	}

	private static void createViewModelSection() {
		System.out.println(border);

		String lastGroup = "";
		ArrayList<String> allGroups = new ArrayList<>();

		for (ArrayList<String> line : fileContent) {

			String thisGroup = line.get(line.size() - 1);
			if (!thisGroup.equals(lastGroup) && line.get(dataType).equals("String")) {
				System.out.println();
				System.out.println("return entries;");
				System.out.println("}");
				System.out.println();
				System.out.println("private List<Entry> composeEntriesFor" + thisGroup + "(EvbDetailsValueObjectMeta eVBDetailsVOM) {");
				System.out.println("Entry entry;");
				System.out.println("List<Entry> entries = new ArrayList<>();");
				System.out.println();
				allGroups.add(thisGroup);
				lastGroup = thisGroup;
			}

			System.out.println();
			System.out.println("entry = composeEntry(\"" + line.get(xmlName) + "\", eVBDetailsVOM." + line.get(prefix) + line.get(dbName) + ");");
			System.out.println("entry.hideIfAllValuesAreEmpty();");
			System.out.println("entries.add(entry);");

		}


		System.out.println(border);
		for (String group : allGroups) {
			System.out.println(".addEntryGroup(createEntryGroup(composeEntriesFor" + group + "(eVBDetailsVOM), \"" + group + "\"))");
		}
		System.out.println(border);
		for (String group : allGroups) {
			System.out.println("MaintainEvbDetails.headers." + group + "=" + group);
		}
		System.out.println(border);
	}
}
