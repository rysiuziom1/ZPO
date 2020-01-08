import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src");
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
        ctClass.writeFile("out/production/zad2");

        Loader loader = new Loader(pool);
        Class c = loader.loadClass("Factorial");
        c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[0]);
    }
}
