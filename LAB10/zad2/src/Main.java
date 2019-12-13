import javassist.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("Factorial");
        List<CtMethod> m = Arrays.stream(ctClass.getDeclaredMethods())
                .filter(e -> e.getName().contains("fact"))
                .collect(Collectors.toList());
        m.forEach(e -> {
            try {
                e.useCflow("fact");
                e.insertBefore("if ($cflow(fact) == 1) System.out.println(\"method " + e.getName() + " is recursive\");");
            } catch (CannotCompileException ex) {
                ex.printStackTrace();
            }
        });
        ctClass.writeFile();
    }
}
