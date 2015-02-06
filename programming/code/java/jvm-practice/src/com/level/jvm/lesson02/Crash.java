package com.level.jvm.lesson02;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Crash {

	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
		
		List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
		
		while(true) {
			ClassLoader classLoader = new ClassLoader() {
				@Override
				 public Class<?> loadClass(String name) throws ClassNotFoundException {
					if (!"com.level.jvm.lesson02.Test".equals(name)) {
						return super.loadClass(name);
					}
					try {
			            String url = "file:E:/workspaces/android/jvm-practice/bin/com/level/jvm/lesson02/Test.class";
			            URL myUrl = new URL(url);
			            URLConnection connection = myUrl.openConnection();
			            InputStream input = connection.getInputStream();
			            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			            int data = input.read();

			            while(data != -1){
			                buffer.write(data);
			                data = input.read();
			            }
			            input.close();
			            byte[] classData = buffer.toByteArray();
			            return defineClass("com.level.jvm.lesson02.Test",
			                    classData, 0, classData.length);
			        } catch (MalformedURLException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					return null;
				 }
			};
			classLoaders.add(classLoader);
			classLoader.loadClass("com.level.jvm.lesson02.Test");
		}
	}
}
