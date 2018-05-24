package systemMonitor;

import java.io.*;

public class CPUMonitor {

    public static void main(String args[]) throws Exception {

        File file = new File("temp.bat");
        PrintWriter writer = new PrintWriter(file);
        writer.println("wmic cpu get loadpercentage>result.txt");
        writer.close();
        Process process = Runtime.getRuntime().exec("cmd /c start " + file.getPath());
        process.exitValue();
        System.out.println(getPercentage());
    }

    public static int getPercentage() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("result.txt"));
            String line;
            int value = 0;
            while ((line = br.readLine()) != null) {
                line = removeSpace(line);
                value = getInt(line);
                if (value != -1) {
                    return value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    public static String removeSpace(String str) {
        String result = str;
        int i = 0;
        while (i < result.length()) {
            if (result.substring(i, i + 1).equals(" ")) {
                result = result.substring(0, i) + result.substring(i + 1);
                i--;
            }
            i++;
        }
        return result;
    }

    public static int getInt(String str) {
        int result = 0;
        boolean hasInt = false;
        for (int i = 0; i < str.length(); i++) {
            if ((int) (str.charAt(i)) >= 48 && (int) (str.charAt(i)) <= 57) {
                result = result * 10 + Integer.valueOf(str.substring(i, i + 1));
                hasInt = true;
            }
        }
        if (hasInt) {
            return result;
        } else {
            return -1;
        }
    }
}
