import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Main {
    public static void main(String[] args) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("Point.move");

    }
}
