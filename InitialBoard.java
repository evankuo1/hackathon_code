package gameAI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class InitialBoard {
	
	int width;
	int height;
	List<ArrayList> objList;
	
	
	public static Object readFromFile() {
		 StringBuilder sb = new StringBuilder(64);

	        File helloWorldJava = new File("testcompile/HelloWorld.java");
	        if (helloWorldJava.getParentFile().exists() || helloWorldJava.getParentFile().mkdirs()) {

	            try {
	                Writer writer = null;
	                try {
	                    writer = new FileWriter(helloWorldJava);
	                    writer.write(sb.toString());
	                    writer.flush();
	                } finally {
	                    try {
	                        writer.close();
	                    } catch (Exception e) {
	                    }
	                }

	            /** Compilation Requirements *********************************************************************************************/
	                DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
	                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	                StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

	                // This sets up the class path that the compiler will use.
	                // I've added the .jar file that contains the DoStuff interface within in it...
	                List<String> optionList = new ArrayList<String>();
	                optionList.add("-classpath");
	                optionList.add(System.getProperty("java.class.path") + File.pathSeparator + "dist/InlineCompiler.jar");

	                Iterable<? extends JavaFileObject> compilationUnit
	                        = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(helloWorldJava));
	                JavaCompiler.CompilationTask task = compiler.getTask(
	                    null, 
	                    fileManager, 
	                    diagnostics, 
	                    optionList, 
	                    null, 
	                    compilationUnit);
	                /********************************************************************************************* Compilation Requirements **/
	                if (task.call()) {
	                    /** Load and execute *************************************************************************************************/
	                    // Create a new custom class loader, pointing to the directory that contains the compiled
	                    // classes, this should point to the top of the package structure!
	                    URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});
	                    // Load the class from the classloader by name....
	                    Class<?> loadedClass = classLoader.loadClass("WriteCode.Player");
	                    // Create a new instance...
	                    Object obj = loadedClass.newInstance();
	                    return obj;

	                    /************************************************************************************************* Load and execute **/
	                } else {
	                    for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
	                        System.out.format("Error on line %d in %s%n",
	                                diagnostic.getLineNumber(),
	                                diagnostic.getSource().toUri());
	                    }
	                }
	                fileManager.close();
	            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException exp) {
	                exp.printStackTrace();
	            }
	        }
            return null;
	}
	
	public InitialBoard() {
		
		width = 3;
		height = 5;
		objList = new ArrayList<ArrayList>();
		
		Player player = (Player) readFromFile();
		ArrayList playerList = new ArrayList();
		playerList.add(0);
		playerList.add(0);
		playerList.add(player);
		objList.add(playerList);
		
		Food food = new Food();
		ArrayList foodList = new ArrayList();
		foodList.add(2);
		foodList.add(2);
		foodList.add(food);
		objList.add(foodList);
		
	}
	
	int getWidth() {
		return width;
	}
	
	int getHeight() {
		return height;
	}
	
	List<ArrayList> getObjList() {
		return objList;
	}
}
