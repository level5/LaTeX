package com.level.jvm.lesson11;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.IMUL;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;


public class ASMP {

	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		cw.visit(V1_6, ACC_PUBLIC, "Cal", null, "java/lang/Object", null);
		
		MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mw.visitCode();  
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		
		mw = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null); 
		mw.visitCode();
		
		mw.visitIntInsn(BIPUSH, 6);
		mw.visitVarInsn(ISTORE, 1);
		mw.visitIntInsn(BIPUSH, 7);
		mw.visitVarInsn(ISTORE, 2);
		
		mw.visitIntInsn(ILOAD, 1);
		mw.visitIntInsn(ILOAD, 2);
		mw.visitInsn(IADD);
		mw.visitLdcInsn(3);
		mw.visitInsn(IMUL);
		mw.visitVarInsn(ISTORE, 3);
		
		mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");  
		mw.visitIntInsn(ILOAD, 3);
		mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",  "(I)V");
		mw.visitInsn(RETURN);  
		mw.visitMaxs(0, 0);  
		mw.visitEnd();
		
		cw.visitEnd();
		
		final byte[] bs = cw.toByteArray();  
		Class<?> clazz = new ClassLoader() {  
            protected Class<?> findClass(String name) throws ClassNotFoundException {  
                    return defineClass(name, bs, 0, bs.length);  
            }  
		}.loadClass("Cal"); 
		
		Method method = clazz.getMethod("main", new Class[] { String[].class }); 
		method.invoke(null, (Object) new String[0]); 
		
	}
}
