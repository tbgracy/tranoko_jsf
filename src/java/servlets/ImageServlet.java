/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controllers.HouseController;
import controllers.UserController;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gracy
 */
@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {

	private final HouseController hc = new HouseController();
	private final UserController uc = new UserController();

	@Override
	public void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		InputStream sImage;
		try {
			String id = request.getParameter("proprioID");
			String table = request.getParameter("table");
			System.out.println("inside servlet->" + id);
			if (table.equals("house")){
				hc.photo(id);
				sImage = hc.getHouseImage();
			}
			else{
				uc.photo(id);
				sImage = uc.getUserImage();
			}
			byte[] bytearray = new byte[1048576];
			int size;

			response.reset();

			response.setContentType("image/jpeg");
			while ((size = sImage.read(bytearray)) != -1){
				response.getOutputStream().
					write(bytearray, 0, size);
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and
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
		try ( PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ImageServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ImageServlet at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
