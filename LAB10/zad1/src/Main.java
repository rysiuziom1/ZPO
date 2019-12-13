import javassist.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("out/production/zad1");
        CtClass ctClass = pool.get("Point");
        CtMethod ctMethod = ctClass.getDeclaredMethod("move");
        ctMethod.insertBefore("{ System.out.println($1); System.out.println($2); }");
        ctClass.writeFile();
    }
}
