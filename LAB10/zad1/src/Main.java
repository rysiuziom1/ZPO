import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src");
        CtClass ctClass = pool.get("Point");
        CtMethod ctMethod = ctClass.getDeclaredMethod("move");
        ctMethod.insertBefore("{ System.out.println(\"dx = \" + $1 + \" dy = \" + $2); int tmp = $1; $1 = $2; $2 = tmp; }");
        ctClass.writeFile("out/production/zad1");
        Loader loader = new Loader(pool);
        Class c = loader.loadClass("Point");
        c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[1]);

    }
}
