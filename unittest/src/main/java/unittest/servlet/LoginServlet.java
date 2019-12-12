package unittest.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import unittest.jpa.EntityManagerFactory;
import unittest.jpa.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		EntityManager em = EntityManagerFactory.create();
		try {
			User u = em.createQuery(
					"select u from User u where u.username=:username", User.class)
				.setParameter("username", username)
				.getSingleResult();
			if(new BCryptPasswordEncoder().matches(password, u.getPassword())) {
				request.setAttribute("dispname", u.getDisplayName());
				request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
				return;
			}
		} catch(NoResultException e) {
		}
		response.sendError(403);
	}

}
