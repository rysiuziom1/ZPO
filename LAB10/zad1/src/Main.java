import javassist.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
//        pool.insertClassPath("out/production/zad1");
        CtClass ctClass = pool.get("Point");
        CtMethod ctMethod = ctClass.getDeclaredMethod("move");
        ctMethod.insertBefore("{ System.out.println(\"dx = \" + $1 + \" dy = \" + $2); int tmp = $1; $1 = $2; $2 = tmp; }");
        ctClass.writeFile("out/production/zad1");
    }
}
