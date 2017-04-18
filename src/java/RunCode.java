/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 *
 * @author Wolverine
 */
@WebServlet(urlPatterns = {"/run"})
public class RunCode extends HttpServlet {
    private int capacity;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RunCode</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>404 Error !</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static String classname,program;
        private static String classOutputFolder ;
static PrintWriter out;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html"); 
   out = response.getWriter(); 
program = request.getParameter("program"); 
 classname=request.getParameter("classname");
 
/* out.println(program + " ");*/
getPath(request,response);
        JavaFileObject file = getJavaFileObject(program);
        Iterable<? extends JavaFileObject> files = Arrays.asList(file);
 
        //2.Compile your files by JavaCompiler
        compile(files,response);
 
        //3.Load your class by URLClassLoader, then instantiate the instance, and call method by reflection
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    

static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private void getPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
      System.setOut(new PrintStream(outContent));
    out = response.getWriter();
      File jsp = new File(request.getSession().getServletContext().getRealPath(request.getServletPath()));
File dir = jsp.getParentFile();
classOutputFolder=dir.getPath();
outContent.reset();

    }
 
    public static class MyDiagnosticListener implements DiagnosticListener<JavaFileObject>
    {
        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic)
        {
            out.println("<span style='color:#F00'><b>"
                    +diagnostic.getKind()+" : </b><br>"
                    +"Line Number-> " + diagnostic.getLineNumber()+"<br>"
                    +"code-> " + diagnostic.getCode()+"<br>"
                    +"Message-> " + diagnostic.getMessage(Locale.ENGLISH)+"<br>"+"Source-> " + diagnostic.getSource().getName()+"<br>"
             +"<br>"+"<br></span>");
        }
    }
 
    /** java File Object represents an in-memory java source file <br>
     * so there is no need to put the source file on hard disk  **/
    public static class InMemoryJavaFileObject extends SimpleJavaFileObject
    {
        private String contents = null;
 
        public InMemoryJavaFileObject(String className, String contents) throws Exception
        {
            super(URI.create("string:///" + className.replace('.', '/')
                             + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.contents = contents;
        }
 
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException
        {
            return contents;
        }
    }
 
    /** Get a simple Java File Object ,<br>
     * It is just for demo, content of the source code is dynamic in real use case */
    private static JavaFileObject getJavaFileObject(String prog)
    {
        StringBuilder contents = new StringBuilder(
                                                   ""+prog);
        JavaFileObject so = null;
        try
        {
            so = new InMemoryJavaFileObject(classname, contents.toString());
        }
        catch (Exception exception)
        {
        }
        return so;
    }
 
    /** compile your files by JavaCompiler */
    public static void compile(Iterable<? extends JavaFileObject> files,HttpServletResponse response)
    {
        //get system compiler:
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        // for compilation diagnostic message processing on compilation WARNING/ERROR
        MyDiagnosticListener c = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(c,
                                                                              Locale.ENGLISH,
                                                                              null);
        //specify classes output folder
        Iterable options = Arrays.asList("-d", classOutputFolder);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                                                             c, options, null,
                                                             files);
        Boolean result = task.call();
        if (result == true)
        {
         /*   out.println("Succeeded");*/
            runIt();
        }
        else{
     
        }
    }
 
    /** run class from the compiled byte code file by URLClassloader */
    public static void runIt()
    {
        // Create a File object on the root of the directory
        // containing the class file
        File file = new File(classOutputFolder);
 
        try
        {
            // Convert File to a URL
            URL url = file.toURL(); // file:/classes/demo
            URL[] urls = new URL[] { url };
 
            // Create a new class loader with the directory
            ClassLoader loader = new URLClassLoader(urls);
 
            // Load in the class; Class.childclass should be located in
            // the directory file:/class/demo/
            Class thisClass = loader.loadClass(classname);
 
            Class params[] = {};
            Object paramsObj[] = {};
            Object instance = thisClass.newInstance();
            Constructor conz=thisClass.getConstructor(params);
            
           Method[] methods = thisClass.getDeclaredMethods();
         Method meth = thisClass.getMethod("main", String[].class);
                String[] paramz = null; // init params accordingly
    meth.invoke(null, (Object) paramz);
    out.println(outContent);   
    out.flush();
    outContent=new ByteArrayOutputStream();
           
        }
        catch (MalformedURLException e)
        {
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (Exception ex)
        {
        }
    }
 


}
