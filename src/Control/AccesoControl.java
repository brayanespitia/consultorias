package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.PersonaDao;
import Dto.Persona;

/**
 * Servlet implementation class AccesoControl
 */
@WebServlet("/AccesoControl")
public class AccesoControl extends HttpServlet {
	
	PersonaDao pD = new PersonaDao();
	Persona p = new Persona();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String accion = request.getParameter("accion");
		
		switch (accion) {
		case "ingreso":
			String cedula = request.getParameter("cedula");
			String pass = request.getParameter("pass");
			p = pD.validar(cedula, pass);
			if(p!=null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("persona", p);
		
				response.sendRedirect("WebApp/inicio.jsp");
			}
			
			
			break;
			
		case "cerrar":
			session.invalidate();
			String site = new String("http://localhost:8080/ConsultoriaZ/index.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
			break;

		default:
			break;
		}

		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
