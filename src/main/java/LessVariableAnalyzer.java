import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eltonmarku on 04.12.15.
 */
public class LessVariableAnalyzer {
    private static final Logger log = Logger.getLogger(LessVariableAnalyzer.class.getName());
    public static Map<String, Integer> countLessVariables(String prefix, String absoluteFolderPath) throws IOException {
        Map<String, Integer> lessVariablesAnalyzed = new HashMap<String, Integer>();
        String[] extensions = new String[]{"less"};
        Collection<File> lessFiles = FileUtils.listFiles(new File(absoluteFolderPath), extensions, true);
        File analyzeLog = new File(absoluteFolderPath + "/less-analyze-debug.log");
        List<String> lines = new ArrayList<String>();
        for (File file: lessFiles) {
            List<String> fileLines = FileUtils.readLines(file);
            for (String line: fileLines) {
                String regEx = "(@" + (prefix != null ? prefix : "") + "[a-zA-Z-]*)";
                Pattern pattern = Pattern.compile(regEx);
                Matcher m = pattern.matcher(line);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    String lessVar = line.substring(start, end);
                    int varCount = 1;
                    if (lessVariablesAnalyzed.get(lessVar) != null) {
                        varCount = lessVariablesAnalyzed.get(lessVar);
                        varCount++;
                    }
                    String lineFound = lessVar + " # " + varCount;
                    lines.add(lineFound);
                    lines.add(line);
                    lines.add(file.getCanonicalPath());
                    lines.add("---------");
                    lessVariablesAnalyzed.put(lessVar, varCount);
                }
            }
        }
        FileUtils.writeLines(analyzeLog, lines, false);
        return lessVariablesAnalyzed;
    }
}
