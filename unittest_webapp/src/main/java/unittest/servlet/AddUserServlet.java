package unittest.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import unittest.jpa.EntityManagerFactory;
import unittest.jpa.User;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/addUser.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String username = request.getParameter("username");
		String dispname = request.getParameter("dispname");
		String password = request.getParameter("password");
		User u = new User();
		u.setUsername(username);
		u.setDisplayName(dispname);
		u.setPassword(new BCryptPasswordEncoder().encode(password));
		EntityManager em = EntityManagerFactory.create();
		em.getTransaction().begin();
		try {
			em.persist(u);
		} finally {
			em.getTransaction().commit();
		}
		request.getRequestDispatcher("/WEB-INF/added.jsp").forward(request, response);
	}
}
