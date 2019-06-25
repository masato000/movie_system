package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.LoginInfoBeans;
import movie.model.LoginModel;

@WebServlet("/auth")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
												throws ServletException, IOException {
		String msg ="m999";
		String studentId="999";
		String password="999";

		studentId=request.getParameter("studentId");
		if("".equals(studentId)) {
		msg="m0";
		}
		password=request.getParameter("password");
		if("".equals(password)) {
			if("m999".equals(msg)) {
				msg="m1";
			}else {
				msg="m2";
			}
		}

		LoginModel loginModel=new LoginModel();
		LoginInfoBeans loginInfo=new LoginInfoBeans();
		loginInfo = loginModel.login(studentId,password);
		if(loginInfo==null) {
			if("m999".equals(msg)) {
			msg="m3";
			}
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
		}
		if("m999".equals(msg)) {
			response.sendRedirect("top");

		}else {
			/*request.setAttribute("msg", msg);*/
			String url="login"+"?"+"msg="+msg;
			response.sendRedirect(url);
		}
	}

}
