package com.dgut.mvc.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.dgut.mvc.bean.User;
import com.dgut.mvc.service.UserService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");		//�Ѿ���¼
		
		if (user != null) {
			new UserServlet().index(request, response);
		} else {
			//session���ڻ��δ��¼
			String user_name = request.getParameter("username");
			String user_password = request.getParameter("password");

			UserService service = new UserService();
			user = service.login(user_name, user_password);
			if (user != null) {
				request.getSession().setAttribute("user", user); // ��session�����û���Ϣ
				new UserServlet().index(request, response);
			} else {
				request.setAttribute("title", "��¼����");
				request.setAttribute("state", "�û������������");
				request.setAttribute("nextWeb", "login.jsp");
				request.getRequestDispatcher("WEB-INF/views/responState.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
