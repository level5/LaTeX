package com.level.jvm.lesson06;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class Main {

	private static MyClassLoader cl = new MyClassLoader();

	public static void main(String[] args) throws Exception {

		while (true) {
			WorkerIF worker = getWorker();
			worker.doIt();
			Thread.sleep(1000);
		}
	}
	
	public static WorkerIF getWorker() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (cl.isModify()) {
			cl = new MyClassLoader();
		}
		return (WorkerIF) cl.loadClass("com.level.jvm.lesson06.Worker").newInstance();
	}
}

class MyClassLoader extends ClassLoader {

	private long timestamp;

	public MyClassLoader() {
		timestamp = getTimestamp("com.level.jvm.lesson06.Worker");
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (!"com.level.jvm.lesson06.Worker".equals(name)) {
			return super.loadClass(name);
		}
		if (this.findLoadedClass(name) != null) {
			return this.findLoadedClass(name);
		}
		byte[] clazz = loadClassBytes("com.level.jvm.lesson06.Worker");
		return this.defineClass(null, clazz, 0, clazz.length);
	}

	private long getTimestamp(String className) {
		File f = new File(getClassFile(className));
		return f.lastModified();
	}

	public boolean isModify() {
		long now = getTimestamp("com.level.jvm.lesson06.Worker");
		return now != timestamp;
	}

	private byte[] loadClassBytes(String className) {
		ByteArrayOutputStream baos = null;
		InputStream fis = null;
		byte[] result = null;
		try {
			baos = new ByteArrayOutputStream();
			fis = this.getResourceAsStream("com/level/jvm/lesson06/Worker.class");
			int c = 0;
			byte[] b = new byte[1024];
			while ((c = fis.read(b)) != -1) {
				baos.write(b, 0, c);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				fis.close();
			} catch (Exception ignore) {
			}
		}
		return result;
	}

	private String getClassFile(String className) {
		return System.getProperty("java.class.path") + "/" + className.replace(".", "/") + ".class";
	}

}