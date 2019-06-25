package movie.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.beans.LoginInfoBeans;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

	//チェック除外画面
		private String excludeDispList[] =
			{
				"/login","/auth","/logout","/index","/insertuser","/inputuser","/completeuser"
			};
		private String excludeExtList[] =
			{
				"js","css","png","gif","jpg","ico","PNG"
			};


	@Override
	public void destroy() {
		//無処理
		;
	}

	private String getExt(String fileName) {

		if (fileName == null)
			return null;
		int point = fileName.lastIndexOf(".");
		if(point == -1) {
			return"";
		}
		return fileName.substring(point+1);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {



		///////////////////////////////////////
		//リクエストのサーブレットパスを取得
		String servletPath = ((HttpServletRequest)request).getServletPath();

		System.out.println("servletPath:" + servletPath);

		//除外画面に含まれている場合はチェックしない
				if( Arrays.asList(excludeDispList).contains(servletPath)){
					chain.doFilter(request, response);
					return;
				}

		//////////////////
		//拡張子による除外
		if(Arrays.asList(excludeExtList).contains(getExt(servletPath))) {
			chain.doFilter(request, response);
			return;
		}

		//////////////////////////////////////
		//loginページ以外の場合は、ログインチェックを行う
		if("/login".equals(servletPath) != true && "/auth".equals(servletPath) != true){
			///////////////////////////////
			//ログインチェックを行う（セッションからログイン情報を取得してnullでなければOK)

			HttpSession session = ((HttpServletRequest)request).getSession(true);

			//ログイン情報をセッションから取得
			LoginInfoBeans loginInfo = (LoginInfoBeans)session.getAttribute("loginInfo");

			//ログイン情報がnullなら未ログイン
			if( loginInfo == null ){
				System.out.println("ログインしていないのでログイン画面へ戻します");
				((HttpServletResponse)response).sendRedirect("login");
				return;
			}

		}

		//処理を続行する
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//無処理
		;
	}

}
