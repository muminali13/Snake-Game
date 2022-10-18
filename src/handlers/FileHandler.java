package handlers;

import main.GamePanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    // Create objects
    private static Scanner infile = null;
    private static FileWriter file = null;
    private static BufferedWriter bw = null;

    private static String path = "files/";

    public static void setPath(String path) {
        FileHandler.path = path;
    }

    public static String[][] readInFileTo2DArray(String szFilename, int iColumnNumber) {

        Scanner infile = null;

        String[][] a2DArray = null;
        String[] contents;
        String fileContents = "";

        try {
            infile = new Scanner(new File(szFilename));
            infile.nextLine();

            while (infile.hasNextLine()) {
                fileContents += infile.nextLine() + ", ";
            }

            contents = fileContents.split(", ");
            a2DArray = new String[contents.length / iColumnNumber][iColumnNumber];

            for (int i = 0; i < contents.length - 1; i += iColumnNumber) {
                for (int j = 0; j < iColumnNumber; j++) {
                    a2DArray[i / iColumnNumber][j] = contents[i + j];
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            GamePanel.stop();
            System.exit(-1);
        } finally {
            if (infile != null) {
                infile.close();
            }
        }

        return a2DArray;

    }

    public static void addNewUserToFile(String szUserName, String szPassword, String szName, String isAdmim) {
        FileWriter file = null;
        BufferedWriter bw = null;

        try {
            file = new FileWriter("files/userData.csv", true);
            bw = new BufferedWriter(file);

            bw.write(szUserName + ", " + szPassword + ", " + szName + ", " + isAdmim + "\n");

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            GamePanel.stop();
            System.exit(-1);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                GamePanel.stop();
                System.exit(-1);
            }
            try {
                if (file != null) {
                    file.close();
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                GamePanel.stop();
                System.exit(-1);
            }

        }
    }
}
