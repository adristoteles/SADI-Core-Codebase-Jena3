package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=iso-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

  String hostName = request.getServerName();

      out.write("\n");
      out.write("<?xml version='1.0' encoding='UTF-8'?>\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>SADI services at ");
      out.print(hostName);
      out.write("</title>\n");
      out.write("    <link rel=\"icon\" href=\"http://sadiframework.org/favicon.ico\" type=\"image/x-icon\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://sadiframework.org/style/new.css\">\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div id='outer-frame'>\n");
      out.write("      <div id='inner-frame'>\n");
      out.write("        <div id='header'>\n");
      out.write("          <h1><a href=\"http://sadiframework.org/\">SADI</a> services at ");
      out.print(hostName);
      out.write("</h1>\n");
      out.write("        </div>\n");
      out.write("        <div id='nav'>\n");
      out.write("          <ul>\n");
      out.write("            <li class=\"page_item current_page_item\">Services</li>\n");
      out.write("          </ul>\n");
      out.write("        </div>\n");
      out.write("        <div id='content'>\n");
      out.write("          <h2>SADI Services</h2>\n");
      out.write("\t      <ul>\n");
      out.write("            <li><a href=\"./hello\">hello</a></li>\n");
      out.write("\t      </ul>\n");
      out.write("        </div> <!-- content -->\n");
      out.write("        <div id='footer'>\n");
      out.write("        </div> <!-- footer -->\n");
      out.write("      </div> <!-- inner-frame -->\n");
      out.write("    </div> <!-- outer-frame -->\n");
      out.write("  </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
