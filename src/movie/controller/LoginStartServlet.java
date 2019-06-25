
package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginStartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
												throws ServletException, IOException {

		String msg=request.getParameter("msg");
		String [] msgArray= {"メールアドレスが未入力です。<br>","パスワードが未入力です。","正しいメールアドレス及びパスワードを入力してください。"};
		if("m0".equals(msg)) {
			request.setAttribute("msg", msgArray[0]);
		}else if("m1".equals(msg)) {
			request.setAttribute("msg", msgArray[1]);
		}else if("m2".equals(msg)) {
			request.setAttribute("msg", (msgArray[0]+msgArray[1]));
		}else if("m3".equals(msg)) {
			request.setAttribute("msg", msgArray[2]);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
}
