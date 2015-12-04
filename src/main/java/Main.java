import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by eltonmarku on 04.12.15.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException {
        String prefix = args[0] != null ? args[0] : "";
        String path = args[1] != null ? args[1] : "/";
        Map<String, Integer> variables = LessVariableAnalyzer.countLessVariables(prefix, path);
        Set<String> vars = variables.keySet();
        for (String var: vars) {
            log.info(var + ": " + variables.get(var));
        }
    }
}
